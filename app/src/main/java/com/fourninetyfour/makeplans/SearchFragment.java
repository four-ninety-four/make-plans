package com.fourninetyfour.makeplans;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;


public class SearchFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Plan> plans;
    private Button searchButton;
    private EditText searchTerms;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.searchRecyclerView);
        PlanAdapter recyclerAdapter = new PlanAdapter(plans, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(recyclerAdapter);

        searchButton = (Button) view.findViewById(R.id.searchButton);
        searchTerms = (EditText) view.findViewById(R.id.searchEditText);


        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        plans = new ArrayList<>();


    }
}