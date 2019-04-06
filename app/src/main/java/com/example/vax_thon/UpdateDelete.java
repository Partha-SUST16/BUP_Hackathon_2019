package com.example.vax_thon;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateDelete extends AppCompatActivity {

    private EditText name,date;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        date = findViewById(R.id.vaccinedateid);
        name = findViewById(R.id.vaccinenameid);
        btn = findViewById(R.id.submitbtn);
        final String key = getIntent().getExtras().get("uid").toString();
        String key2 = getIntent().getExtras().get("ref").toString();
        Toast.makeText(getApplicationContext(),key,Toast.LENGTH_SHORT).show();
        FirebaseDatabase.getInstance().getReference().child("Member")
                .child(key).child("vaccine").child(key2).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String vname = "",vdate="";
                        vdate = dataSnapshot.child("date").getValue(String.class);
                        vname= dataSnapshot.child("name").getValue(String.class);
                        date.setText(vdate);
                        name.setText(vname);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vname = name.getText().toString();
                String vdate = date.getText().toString();
                SetVaccine st = new SetVaccine();
                st.name = vname;st.date=vdate;
                FirebaseDatabase.getInstance().getReference()
                        .child("Member").child(key).child("vaccine").child(vname).setValue(st).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                            finish();
                        else
                            Toast.makeText(getApplicationContext(),task.getException().getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
