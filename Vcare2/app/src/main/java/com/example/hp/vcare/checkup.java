package com.example.hp.vcare;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class checkup extends Fragment  {
    DatabaseReference dr;
Button fetch,submit,clear;
    EditText id,name,phone,medicine,time,advice;
    Spinner doctor;

    public checkup() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
      View v=   inflater.inflate(R.layout.fragment_checkup, container, false);

        id=(EditText) v.findViewById(R.id.checke);
        name=(EditText) v.findViewById(R.id.checke1);
        phone=(EditText) v.findViewById(R.id.checke2);
        medicine=(EditText) v.findViewById(R.id.checke3);
        time=(EditText) v.findViewById(R.id.checke4);
        advice=(EditText) v.findViewById(R.id.checke5);
        doctor=(Spinner) v.findViewById(R.id.checks4);
        fetch=(Button) v.findViewById(R.id.checkb3);
        clear=(Button) v.findViewById(R.id.checkb2);
        submit=(Button) v.findViewById(R.id.checkb1);
        fetch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(id.getText())  ) {
                    String pid=id.getText().toString().trim();
                    getdetails(pid);
                }
                else
                {
                    Toast.makeText(getActivity(),"Enter id of Patient...",Toast.LENGTH_LONG).show();
                }

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id.setText("");
                name.setText("");
                phone.setText("");
                medicine.setText("");
                time.setText("");
                advice.setText("");
            }
        });
        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String pid=id.getText().toString();
                String doctors=doctor.getSelectedItem().toString();
                String medicines=medicine.getText().toString();
                String times=time.getText().toString();
                String adv=advice.getText().toString();
                add(pid,doctors,medicines,times,adv);
            }
        });
return v;
    }
    public void add(final  String id,final  String doctor,final String medicine,final String time,final String adv)
    {
       dr=FirebaseDatabase.getInstance().getReference("patient").child(id);
        dr.keepSynced(true);
        dr.child("doctors").setValue(doctor);
        dr.child("medicines").setValue(medicine);
        dr.child("times").setValue(time);
        dr.child("adv").setValue(adv);
        Toast.makeText(getActivity(),"Patient details are added...",Toast.LENGTH_LONG).show();

    }
    public void getdetails(final String pid)
    {
         dr=FirebaseDatabase.getInstance().getReference("patient");
        dr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(pid)) {
                    String names=dataSnapshot.child(pid).child("name").getValue().toString();
                    String phones=dataSnapshot.child(pid).child("phone").getValue().toString();
                    name.setText(names);
                    phone.setText(phones);
                } else {
                    Toast.makeText(getActivity(), "Patient Doesn't Exist With this ID", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
