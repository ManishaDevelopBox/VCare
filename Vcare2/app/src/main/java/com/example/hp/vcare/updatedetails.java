package com.example.hp.vcare;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class updatedetails extends Fragment {
    Button dis;
    EditText id,name;
    DatabaseReference databaseReference;
    public updatedetails() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_updatedetails, container,false);
        databaseReference=FirebaseDatabase.getInstance().getReference("patient");
        databaseReference.keepSynced(true);
        dis=(Button) v.findViewById(R.id.db1);
        id=(EditText) v.findViewById(R.id.de2);
        name=(EditText) v.findViewById(R.id.de1);
        dis.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(id.getText()) && !TextUtils.isEmpty(name.getText()) ) {
                    String pid=id.getText().toString();
                    dele(pid);
                }
                else
                {
                    Toast.makeText(getActivity(),"Enter id and name of Patient...",Toast.LENGTH_LONG).show();
                }
            }
        });
        return v;
    }
    public void dele(final String pid)
    {
        final DatabaseReference dR = FirebaseDatabase.getInstance().getReference("patient");
        final DatabaseReference z=dR.child(pid);
        dR.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild(pid)) {
                    z.removeValue();
                    Toast.makeText(getActivity(), "Patient Deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Patient Doesn't Exist With this ID", Toast.LENGTH_SHORT).show();
                }
                dR.keepSynced(true);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getActivity(), "Error Occured", Toast.LENGTH_SHORT).show();
            }
        });
    }
}