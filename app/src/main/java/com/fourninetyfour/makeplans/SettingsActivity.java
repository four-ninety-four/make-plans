package com.fourninetyfour.makeplans;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ContextThemeWrapper;
import android.view.*;
import android.widget.Switch;

public class SettingsActivity extends Fragment {

    View view;
    Switch theme;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SharedPreferences prefs = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE);
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        super.onCreateView(inflater, container, savedInstanceState);

        theme = (Switch) view.findViewById(R.id.themeSwitch);

        if (prefs.getBoolean("darkMode", false) == true)
            theme.setChecked(true);


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
}