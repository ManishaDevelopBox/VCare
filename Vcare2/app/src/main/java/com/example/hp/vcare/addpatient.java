package com.example.hp.vcare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addpatient extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseReference databasepatient;
Button add,b2;
    EditText id,name,blood,phone,disease,age;
    String[] g={"None","Male","Female"};
    Spinner s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpatient);
        databasepatient = FirebaseDatabase.getInstance().getReference("patient");
        b2=(Button) findViewById(R.id.addb2);
        add=(Button) findViewById(R.id.addb1);
        id=(EditText) findViewById(R.id.adde);
        name=(EditText) findViewById(R.id.adde1);
        phone=(EditText) findViewById(R.id.adde2);
        blood=(EditText) findViewById(R.id.adde3);
        disease=(EditText) findViewById(R.id.adde4);
        age=(EditText) findViewById(R.id.adde5);
       s=(Spinner) findViewById(R.id.adds4);
        s.setOnItemSelectedListener(this);

        ArrayAdapter a1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,g);
        a1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(a1);
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                id.setText("");
                name.setText("");
                phone.setText("");
                blood.setText("");
                disease.setText("");
                age.setText("");
                s.clearFocus();
            }
        });


        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                patientadd();
            }
        });

    }
private void patientadd()
{
    final String ids=id.getText().toString();
    final String names=name.getText().toString();
    final String phones=phone.getText().toString();
    final String bg=blood.getText().toString();
    final String dis=disease.getText().toString();
    final String ages=age.getText().toString();
    final String gender=s.getSelectedItem().toString();
 databasepatient.keepSynced(true);
        if(!TextUtils.isEmpty(ids) && !TextUtils.isEmpty(names)&& !TextUtils.isEmpty(phones)&& !TextUtils.isEmpty(bg)&& !TextUtils.isEmpty(dis)&& !TextUtils.isEmpty(ages)){
            databasepatient.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.hasChild(ids))
                    {
                        Toast.makeText(getApplicationContext(),"Patient already exist",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Patient patient=new Patient(ids,names,phones,bg,dis,ages,gender);
                        databasepatient.child(ids).setValue(patient);
                        Toast.makeText(getApplicationContext(),"Patient Added...",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });


        }
        else
        {
            Toast.makeText(getApplicationContext(),"Enter all fields...",Toast.LENGTH_LONG).show();
        }

}
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
