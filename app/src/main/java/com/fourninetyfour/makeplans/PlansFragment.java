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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


public class PlansFragment extends Fragment {


    View v;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Plan> plans;
    private FloatingActionButton createPlan;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_plans, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.plansRecyclerView);
        PlanAdapter recyclerAdapter = new PlanAdapter(plans, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);

        createPlan = (FloatingActionButton) v.findViewById(R.id.planFab);
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

        plans = new ArrayList<>();
        plans.add(new Plan(0, "John's Birthday", "John's 13th Birthday Party", "Wed, May 1 2019, 08:00 AM", "Wed, May 1 2019, 12:00 PM",
                "John's House", "birthday", false));


    }
}