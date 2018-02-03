package com.example.hp.vcare;

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

public class discharge extends AppCompatActivity {

    Button dis;
    EditText id,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discharge);
        dis=(Button) findViewById(R.id.disb1);
        id=(EditText) findViewById(R.id.dise2);
        name=(EditText) findViewById(R.id.dise1);
        dis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(id.getText()) ) {
                    String pid=id.getText().toString().trim();
                    del(pid);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Enter id of Patient...",Toast.LENGTH_LONG).show();
                }


            }
        });

    }
    public void del(final String pid)
    {
        /*DatabaseReference dz=FirebaseDatabase.getInstance().getReference("patient");
        DatabaseReference dr= dz.child(pid);
        dz.keepSynced(true);
        dr.removeValue();
        Toast.makeText(getApplicationContext(),"Patient discharge...",Toast.LENGTH_LONG).show();*/
        final DatabaseReference dR = FirebaseDatabase.getInstance().getReference("patient");
        final DatabaseReference z=dR.child(pid);
        dR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(pid)) {
                    z.removeValue();
                    Toast.makeText(getApplicationContext(), "Patient Discharged", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Patient Doesn't Exist With this ID", Toast.LENGTH_SHORT).show();
                }
                dR.keepSynced(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
