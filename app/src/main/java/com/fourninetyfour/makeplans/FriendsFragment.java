package com.fourninetyfour.makeplans;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class FriendsFragment extends Fragment {
   View v;
   private TextView profileName, profileTopLocation, profileLocation, profilePhone, profileEmail;
   private ImageView profilePhoto;
   private Button viewFriends, addFriends;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_friends, null);
        profileName = v.findViewById(R.id.profile_fullname);
        profileTopLocation = v.findViewById(R.id.profile_location_top);
        profileLocation = v.findViewById(R.id.profile_location);
        profilePhone = v.findViewById(R.id.profile_phone);
        profileEmail = v.findViewById(R.id.profile_email);
        profilePhoto = v.findViewById(R.id.profile_image);
        viewFriends = v.findViewById(R.id.profile_view_friends);
        addFriends = v.findViewById(R.id.profile_add_friends);

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference userRef = database.collection("users").document(uid);
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    profileName.setText(document.get("first").toString() + " " + document.get("last").toString());
                    profileTopLocation.setText(document.get("state").toString() + ", USA");
                    profileLocation.setText(document.get("city").toString() + ", " + document.get("state").toString());
                    profileEmail.setText(document.get("email").toString());
                    profilePhone.setText(document.get("phone").toString());
                    //profilePhoto.setImageDrawable((Drawable) document.get("image"));
                }
            }
        });

        return v;
    }
}