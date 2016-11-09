package com.example.siddhantverma.assignment41;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.example.siddhantverma.assignment41.model.Listitem;

import java.util.UUID;

/**
 * Created by Siddhant Verma on 09-Nov-16.
 */
public class taskfragment extends Fragment {

    private static final String ARG_CRIME_ID = "crime_id";

    private Listitem mtask;
    private TextView Title;
    private TextView details;

    public static taskfragment newInstance(UUID Id) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, Id);

        taskfragment fragment = new taskfragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID taskid = (UUID) getArguments().getSerializable(ARG_CRIME_ID);
       // mtask = CrimeLab.get(getActivity()).getCrime(crimeId);
    for(int x=0;x<popup.newlist.size();x++)
    {
        if(popup.newlist.get(x).getTaskid().equals(taskid))
        {
            mtask=popup.newlist.get(x);
        }
    }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_detail, container, false);

       Title =(TextView)v.findViewById(R.id.temp_text);
        Title.setText(mtask.getTitle());
        Title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mtask.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        details = (TextView) v.findViewById(R.id.tempattribution);
        details.setText(mtask.getSecondary_text().toString());
        details.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mtask.setSecondary_text(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return v;
    }
}
