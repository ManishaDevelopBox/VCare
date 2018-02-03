package com.example.hp.vcare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class medication extends AppCompatActivity {
    DatabaseReference dz,dr;
Button fetch,back;
    EditText pid,name,medicine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        pid=(EditText) findViewById(R.id.mede1);
        name=(EditText) findViewById(R.id.mede2);
        medicine=(EditText) findViewById(R.id.mede3);
        fetch=(Button) findViewById(R.id.medb1);
        back=(Button) findViewById(R.id.medb2);
        fetch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(pid.getText())) {
                    String id = pid.getText().toString().trim();
                    getdetails(id);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter id of Patient...",Toast.LENGTH_LONG).show();
                }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getApplicationContext(),nav.class);
                startActivity(i);
            }
        });
    }
    public void getdetails(final String pid)
    {
        dz= FirebaseDatabase.getInstance().getReference("patient");
        dr=dz.child(pid);
        dz.keepSynced(true);
        dz.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(pid)) {
                    if(dataSnapshot.child(pid).child("medicines").getValue()==null){
                        Toast.makeText(getApplicationContext(), "Doctor not Visited yet", Toast.LENGTH_SHORT).show();
                    }else {
                        String names=dataSnapshot.child(pid).child("name").getValue().toString();
                        String med=dataSnapshot.child(pid).child("medicines").getValue().toString();
                        name.setText(names);
                        medicine.setText(med);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Patient Doesn't Exist With this ID", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
