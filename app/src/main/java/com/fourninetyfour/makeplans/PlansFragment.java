package com.fourninetyfour.makeplans;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.*;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;


public class PlansFragment extends Fragment {


    View v;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Plan> plans = new ArrayList<Plan>();
    private FloatingActionButton createPlan;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_plans, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.plansRecyclerView);
        final PlanAdapter recyclerAdapter = new PlanAdapter(plans, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);

        FirebaseFirestore database = FirebaseFirestore.getInstance();
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

        CollectionReference plansRef = database.collection("plans");

        plansRef.whereEqualTo("userID", uid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {
                    // handle error
                }
                if (!documentSnapshots.isEmpty()) {
                    for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                        if (doc.getType() == DocumentChange.Type.ADDED) {
                            plans.add(doc.getDocument().toObject(Plan.class));
                            recyclerAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        });

        createPlan = (FloatingActionButton) v.findViewById(R.id.planFab);
        setHasOptionsMenu(true);
        /* Fragment fragment = new AddPlanFragment();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.commit(); */
        createPlan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new AddPlanFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }
}