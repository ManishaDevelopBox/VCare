package com.example.hp.vcare;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;


public class Home extends Fragment {
    Button b;
    FloatingActionButton fab;
    Button b1, b2, b3;
    TextView t1, t2, t3;
    RelativeLayout r1, r2;
    private ViewFlipper viewFlipper;
    private float lastX;


    int flag = 1;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        b = (Button) v.findViewById(R.id.hob1);
        b1 = (Button) v.findViewById(R.id.hob2);
        b2 = (Button) v.findViewById(R.id.hob3);
        viewFlipper = (ViewFlipper) v.findViewById(R.id.fffffff);

        r1 = (RelativeLayout) v.findViewById(R.id.homerelativelayout1);
        r2 = (RelativeLayout) v.findViewById(R.id.homerelativelayout2);
        fab = (FloatingActionButton) v.findViewById(R.id.floatingActionButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent i = new Intent(getActivity(), addpatient.class);
                startActivity(i);
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent i = new Intent(getActivity(), discharge.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {
                Intent i = new Intent(getActivity(), medication.class);
                startActivity(i);
            }
        });

        r1.setVisibility(View.INVISIBLE);
        r2.setVisibility(View.INVISIBLE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 0) {
                    r1.setVisibility(View.INVISIBLE);
                    r2.setVisibility(View.INVISIBLE);

                }
                if (flag == 1) {
                    r1.setVisibility(View.VISIBLE);
                    r2.setVisibility(View.VISIBLE);
                }
                if (flag == 0) {
                    flag = 1;
                } else {
                    flag = 0;
                }
            }
        });
        return v;
    }

}