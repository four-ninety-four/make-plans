package com.fourninetyfour.makeplans;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class FriendsFragment extends Fragment {

    View v;
    Button add_friend;
    Button view_friend;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_friends, null);
        setHasOptionsMenu(true);

        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        add_friend = v.findViewById(R.id.profile_add_friends);
        view_friend = v.findViewById(R.id.profile_view_friends);


        add_friend.setOnClickListener(new View.OnClickListener() {
            public void onClick (View v){
                Fragment fragment = new AddFriendFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.rellay1, fragment);
                ft.addToBackStack(null);
                ft.commit();
        }

        });

        view_friend.setOnClickListener((v) -> {
           Fragment fragment = new ViewFriendFragment();
        });

        return inflater.inflate(R.layout.fragment_friends, null);
    }
}