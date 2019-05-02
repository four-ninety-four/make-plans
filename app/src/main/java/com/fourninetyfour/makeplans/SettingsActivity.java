package com.fourninetyfour.makeplans;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ContextThemeWrapper;
import android.view.*;
import android.widget.Button;
import android.widget.Switch;
import android.support.v7.widget.Toolbar;

public class SettingsActivity extends Fragment {

    View view;
    Switch theme;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        theme = (Switch) view.findViewById(R.id.themeSwitch);
        Button dumb = view.findViewById(R.id.dumb);
        dumb.setVisibility(view.GONE);

        if (prefs.getBoolean("darkMode", false) == true){
            theme.setChecked(true);
            dumb.setVisibility(view.VISIBLE);
        }

        dumb.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                IMDUMB(view.getContext(),"oHg5SJYRHA0");
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        boolean changed = false;
        if (theme.isChecked() != prefs.getBoolean("darkMode", false))
            changed = true;

        if (theme.isChecked())
            prefs.edit().putBoolean("darkMode",true).apply();
        else
            prefs.edit().putBoolean("darkMode",false).apply();

        if(changed){
            this.getActivity().finish();
            startActivity(this.getActivity().getIntent());
        }

        super.onDestroyView();

    }

    public static void IMDUMB(Context context, String id){
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            context.startActivity(webIntent);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (item.getItemId() == R.id.action_settings) {
            Fragment fragment = new SettingsActivity();
            fragmentTransaction.add(R.id.MainFragment, fragment);
            fragmentTransaction.addToBackStack(null);
            return true;
        } else return false;
    }
}