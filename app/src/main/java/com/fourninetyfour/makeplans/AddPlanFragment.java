package com.fourninetyfour.makeplans;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.*;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.*;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import org.w3c.dom.Document;

import java.text.SimpleDateFormat;
import java.util.*;

import static android.app.Activity.RESULT_OK;

public class AddPlanFragment extends Fragment {
    private String userName;
    private Calendar startDate, endDate;
    private String savedLocation, savedStartDateTime, savedEndDateTime, savedDescription, savedTitle;
    private TextView startTime, endTime, title, description, location;
    private Boolean savedEventType;
    private View v;
    private Spinner eventType;
    private ImageView photoSelect;
    private Button photoBtn, cancel, save;
    private static int LOAD_IMAGE_RESULTS = 100;
    Uri imageURI;
    String savedImageURI;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    FirebaseAuth auth = FirebaseAuth.getInstance();
    FirebaseFirestore database = FirebaseFirestore.getInstance();
    FirebaseDatabase usersDB = FirebaseDatabase.getInstance();
    private final static SimpleDateFormat dateFormat
            = new SimpleDateFormat("mm-dd-yyyy, hh:mm", Locale.getDefault());


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_add_plan, null);
        startTime = v.findViewById(R.id.start_date);
        endTime = v.findViewById(R.id.end_date);
        title = v.findViewById(R.id.event_title);
        description = v.findViewById(R.id.event_description);
        location = v.findViewById(R.id.event_location);
        eventType = v.findViewById(R.id.event_type_spinner);
        photoSelect = v.findViewById(R.id.selected_picture);
        cancel = v.findViewById(R.id.Cancel);
        save = v.findViewById(R.id.Save);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getFragmentManager().popBackStack();

            }
        });
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.event_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventType.setAdapter(adapter);
        photoSelect.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openGallery();
            }
        });
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePickerStart();
            }
        });
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePickerEnd();
            }
        });

        mStorageRef = FirebaseStorage.getInstance().getReference("images");

        DocumentReference usersRef = database.collection("users").document(auth.getCurrentUser().getUid());
        usersRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    userName = document.get("first").toString() +" " + document.get("last").toString();
                }
            }
        });

        /* Upload to database and create event */

        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Map<String, String> planMap = new HashMap<>();
                planMap.put("userID", auth.getCurrentUser().getUid());
                planMap.put("userName", userName);

                savedStartDateTime = startTime.getText().toString();
                planMap.put("start", savedStartDateTime);

                savedEndDateTime = endTime.getText().toString();
                planMap.put("end", savedEndDateTime);

                savedTitle = title.getText().toString();
                planMap.put("title", savedTitle);

                savedDescription = description.getText().toString();
                planMap.put("description", savedDescription);

                savedLocation = location.getText().toString();
                planMap.put("location", savedLocation);

                if (eventType.getSelectedItem().toString().equals("Public")) {
                    savedEventType = false;
                    planMap.put("isHidden", "0");
                }
                else {
                    savedEventType = true;
                    planMap.put("isHidden", "1");
                }


                savedImageURI = imageURI.toString();
                String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime());
                planMap.put("image", timeStamp + "." + getFileExtension(imageURI));
                uploadFile(timeStamp);

                if (savedImageURI == null || savedStartDateTime.isEmpty() || savedEndDateTime.isEmpty() ||
                savedTitle.isEmpty() || savedDescription.isEmpty() || savedLocation.isEmpty()) {
                    Toast toast = Toast.makeText(getContext(), "Missing field - Event cannot be created. Please make sure all fields are filled out.", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    DocumentReference docRef = database.collection("plans").document();
                    planMap.put("documentID", docRef.getId());
                    docRef.set(planMap);
                    Toast.makeText(getActivity(), "Plan added!", Toast.LENGTH_SHORT).show();
                    Fragment fragment = new PlansFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.replace(R.id.fragment_container, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });


        return v;
    }

    public void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, LOAD_IMAGE_RESULTS);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Here we need to check if the activity that was triggers was the Image Gallery.
        // If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
        // If the resultCode is RESULT_OK and there is some data we know that an image was picked.
        if (resultCode == RESULT_OK && requestCode == LOAD_IMAGE_RESULTS) {
            // Let's read picked image data - its URI
            imageURI = data.getData();
            photoSelect.setImageURI(imageURI);
        }
    }




    public void showDateTimePickerStart() {
        final Calendar currentDate = Calendar.getInstance();
        startDate = Calendar.getInstance();
        new DatePickerDialog(this.getContext(), R.style.Theme_AppCompat_DayNight_Dialog_Alert, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                startDate.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(getContext(),  R.style.Theme_AppCompat_DayNight_Dialog_Alert, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        startDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        startDate.set(Calendar.MINUTE, minute);
                        updateTextStart();
                        //Log.v(TAG, "The choosen one " + date.getTime());
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    public void updateTextStart() {
        SimpleDateFormat dateFormat
                = new SimpleDateFormat("MM-dd-yyyy, hh:mm a", Locale.getDefault());
        startTime.setText(dateFormat.format(startDate.getTime()));

    }

    public void showDateTimePickerEnd() {
        final Calendar currentDate = Calendar.getInstance();
        endDate = Calendar.getInstance();
        new DatePickerDialog(this.getContext(), R.style.Theme_AppCompat_DayNight_Dialog_Alert, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                endDate.set(year, monthOfYear, dayOfMonth);
                new TimePickerDialog(getContext(), R.style.Theme_AppCompat_DayNight_Dialog_Alert, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        endDate.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        endDate.set(Calendar.MINUTE, minute);
                        updateTextEnd();
                        //Log.v(TAG, "The choosen one " + date.getTime());
                    }
                }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), false).show();
            }
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DATE)).show();
    }

    public void updateTextEnd() {
        SimpleDateFormat dateFormat
                = new SimpleDateFormat("MM-dd-yyyy, hh:mm a", Locale.getDefault());
        endTime.setText(dateFormat.format(endDate.getTime()));

    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

   private void uploadFile(String timeStamp) {
       if (savedImageURI != null) {
           StorageReference fileReference = mStorageRef.child(timeStamp +
                   "." + getFileExtension(imageURI));

           fileReference.putFile(imageURI);
       }
   }



}


