package com.fourninetyfour.makeplans;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.*;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Plan> allPlans;
    private List<Plan> plans;
    private Button searchButton;
    private EditText searchTerms;
    SearchAdapter recyclerAdapter;
    CollectionReference plansRef;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.searchRecyclerView);
        recyclerAdapter = new SearchAdapter(allPlans, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);

        searchButton = (Button) view.findViewById(R.id.searchButton);
        searchTerms = (EditText) view.findViewById(R.id.searchEditText);
        setHasOptionsMenu(true);


        FirebaseFirestore database = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        plansRef = database.collection("plans");

        plansRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {
                    // handle error
                }
                if (!documentSnapshots.isEmpty()) {
                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED || doc.getType() == DocumentChange.Type.REMOVED) {
                            allPlans.add(doc.getDocument().toObject(Plan.class));
                            recyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if(!(searchTerms.getText().toString().equals(""))){
                    plans = new ArrayList<>();
                    recyclerAdapter = new SearchAdapter(plans, getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(recyclerAdapter);
                    for (Plan plan: allPlans){
                        if(plan.getTitle().toLowerCase().contains(searchTerms.getText().toString().toLowerCase())){
                            plans.add(plan);
                            recyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
                else {
                    recyclerAdapter = new SearchAdapter(allPlans, getContext());
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

        allPlans = new ArrayList<>();



    }

    public void onStart() {
        super.onStart();
    }
}