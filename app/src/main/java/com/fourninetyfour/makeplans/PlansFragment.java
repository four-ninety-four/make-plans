package com.fourninetyfour.makeplans;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_plans, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.plansRecyclerView);
        PlanAdapter recyclerAdapter = new PlanAdapter(plans, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        plans = new ArrayList<>();
        plans.add(new Plan(0, 0, "Barbecue", "Billy's Big Barbecue", 0));
        plans.add(new Plan(1, 1, "Birthday", "Barry's Big Birthday", 1));
    }
}