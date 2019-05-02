package com.fourninetyfour.makeplans;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


public class PlansActivity extends Fragment{

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_plans, container, false);
        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.getMenu().getItem(1).setChecked(true);
        //loading the default fragment
        openFragment(new PlansFragment());
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        BottomNavigationView navigation = (BottomNavigationView) view.findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment fragment = null;

                        switch (item.getItemId()) {
                            case R.id.navigation_search:
                                fragment = new SearchFragment();
                                break;

                            case R.id.navigation_plans:
                                fragment = new PlansFragment();
                                break;

                            case R.id.navigation_friends:
                                fragment = new FriendsFragment();
                                break;
                        }
                        return openFragment(fragment);
                    }
                });
    }
    private boolean openFragment(final Fragment fragment)  {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
        return true;
    }
}