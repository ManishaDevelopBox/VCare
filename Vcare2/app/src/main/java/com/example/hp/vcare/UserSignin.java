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


public class UserSignin extends Fragment {
    Button save, fetch;
    DatabaseReference dr;
    Spinner doctor;
    EditText name, pid, checkedby, premed, newmed, lastadvice, newadvice, time;
    public UserSignin() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_user_signin, container, false);
        pid = (EditText) v.findViewById(R.id.upe2);
        name = (EditText) v.findViewById(R.id.upe1);
        checkedby = (EditText) v.findViewById(R.id.upe4);
        premed = (EditText) v.findViewById(R.id.upe5);
        time = (EditText) v.findViewById(R.id.upe9);
        newmed = (EditText) v.findViewById(R.id.upe6);
        lastadvice = (EditText) v.findViewById(R.id.upe7);
        newadvice = (EditText) v.findViewById(R.id.upe8);
        doctor = (Spinner) v.findViewById(R.id.ups4);
        fetch = (Button) v.findViewById(R.id.upb2);
        save = (Button) v.findViewById(R.id.upb1);
        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(pid.getText()) && !TextUtils.isEmpty(name.getText())) {
                    String id = pid.getText().toString().trim();
                    getdetails(id);
                }
                else
                {
                    Toast.makeText(getActivity(),"Enter id and name of patient...",Toast.LENGTH_LONG).show();
                }
            }
        });
        save.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                String id=pid.getText().toString();
                String doctors=doctor.getSelectedItem().toString();
                String medicines=newmed.getText().toString();
                String times=time.getText().toString();
                String adv=newadvice.getText().toString();
                add(id,doctors,medicines,times,adv);
            }
        });
        return v;
    }
    public void add(final  String id,final  String doctor,final String medicine,final String time,final String adv)
    {

        DatabaseReference dz=FirebaseDatabase.getInstance().getReference("patient");
        dr=dz.child(id);
        dr.keepSynced(true);
        dr.child("doctors").setValue(doctor);
        dr.child("medicines").setValue(medicine);
        dr.child("times").setValue(time);
        dr.child("adv").setValue(adv);
        Toast.makeText(getActivity(),"Patient details updated..",Toast.LENGTH_LONG).show();
    }
    public void getdetails(final String pid)
    {
        dr=FirebaseDatabase.getInstance().getReference("patient");
        dr.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(pid)) {
                    if(dataSnapshot.child(pid).child("doctors").getValue()==null){
                        Toast.makeText(getActivity(), "Doctor not Visited yet", Toast.LENGTH_SHORT).show();
                    }else {
                        String checked = dataSnapshot.child(pid).child("doctors").getValue().toString();
                        String preme = dataSnapshot.child(pid).child("medicines").getValue().toString();
                        String lastad = dataSnapshot.child(pid).child("adv").getValue().toString();
                        checkedby.setText(checked);
                        premed.setText(preme);
                        lastadvice.setText(lastad);
                    }
                } else {
                    Toast.makeText(getActivity(), "Patient Doesn't Exist With this ID", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}