package com.fourninetyfour.makeplans;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class AddPlanFragment extends Fragment {

    private Calendar start;
    private Calendar end;
    private TextView startTime, endTime;
    private View v;
    private Spinner eventType;
    private Button photoBtn, cancel, save;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_add_plan, null);
        startTime = v.findViewById(R.id.start_date);
        endTime = v.findViewById(R.id.end_date);
        eventType = v.findViewById(R.id.event_type_spinner);
        photoBtn = v.findViewById(R.id.select_photo);
        cancel = v.findViewById(R.id.Cancel);
        save = v.findViewById(R.id.Save);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.event_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventType.setAdapter(adapter);
        return v;
    }


    private void initializeCalendars() {
        start = Calendar.getInstance();
        start.set(Calendar.HOUR_OF_DAY, 8);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);
        end = Calendar.getInstance();
        end.set(Calendar.HOUR_OF_DAY, 8);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

    }
}
