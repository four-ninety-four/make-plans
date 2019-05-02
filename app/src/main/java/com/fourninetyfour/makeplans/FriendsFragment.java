package com.fourninetyfour.makeplans;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.List;


public class FriendsFragment extends Fragment {
   View v;
   private TextView profileName, profileTopLocation, profileLocation, profilePhone, profileEmail;
   private ImageView profilePhoto;
   private Button viewFriends, addFriends;

   private List<User> userList = new ArrayList<>();
   private User user;

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
                DocumentSnapshot doc = task.getResult();
                user = doc.toObject(User.class);

                profileName.setText(user.getFirst() + " " + user.getLast());
                profileTopLocation.setText(user.getCity() + ", " + user.getState());
                profileLocation.setText(user.getCity() + ", " + user.getState());
                profilePhone.setText(user.getPhone());
                profileEmail.setText(user.getEmail());
            }
        });

        addFriends.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                Fragment fragment = new AddFriendFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }

        });

        viewFriends.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v) {
                Fragment fragment = new ViewFriendFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return v;
    }
}