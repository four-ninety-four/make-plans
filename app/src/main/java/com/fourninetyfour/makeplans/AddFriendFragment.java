package com.fourninetyfour.makeplans;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class AddFriendFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Friend> allFriends;
    private List<Friend> friends;
    private Button searchButton;
    private EditText searchTerms;
    AddFriendAdapter recyclerAdapter;
    CollectionReference plansRef;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_friends, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.searchFriendRecyclerView);
        recyclerAdapter = new AddFriendAdapter(allFriends, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);

        searchButton = (Button) view.findViewById(R.id.searchFriendButton);
        searchTerms = (EditText) view.findViewById(R.id.searchFriendEditText);
        setHasOptionsMenu(true);


        FirebaseFirestore database = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        plansRef = database.collection("users");
        plansRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for(QueryDocumentSnapshot document : task.getResult()){
                        allFriends.add(document.toObject(Friend.class));
                        recyclerAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(!(searchTerms.getText().toString().equals(""))){
                    friends = new ArrayList<>();
                    recyclerAdapter = new AddFriendAdapter(friends, getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(recyclerAdapter);
                    for (Friend friend: allFriends){
                        if(friend.getFirst().toLowerCase().contains(searchTerms.getText().toString().toLowerCase())){
                            friends.add(friend);
                            recyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
                else {
                    recyclerAdapter = new AddFriendAdapter(allFriends, getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(recyclerAdapter);
                }
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        allFriends = new ArrayList<>();



    }

    public void onStart() {
        super.onStart();
    }
}

