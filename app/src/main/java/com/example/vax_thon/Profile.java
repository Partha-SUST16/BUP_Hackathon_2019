package com.example.vax_thon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private TextView patientname,patientage,patientarea,patientblood,patientemail,patientgender,patientphone;
    private Button setV,seeVdate;
    private FirebaseAuth patientAuth;
    private FirebaseAuth.AuthStateListener patientAuthListener;
    private DatabaseReference patientReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        patientname = (TextView) findViewById(R.id.patientName);
        patientage = (TextView) findViewById(R.id.patientAge);
        patientgender = (TextView) findViewById(R.id.patientGender);
        patientarea = (TextView) findViewById(R.id.patientArea);
        patientblood = (TextView) findViewById(R.id.patientBlood);
        patientphone = (TextView) findViewById(R.id.patientPhone);
        patientemail = (TextView) findViewById(R.id.patientEmail);
        setV = findViewById(R.id.setvaccineid);
        seeVdate = findViewById(R.id.seevdateid);
        String key = "";
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            key = (String)b.get("uid");
        }
        final String finalKey = key;
        setV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(),Medicine.class);
                i.putExtra("uid", finalKey);
                startActivity(i);
            }
        });
        seeVdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SeeVaccine.class);
                i.putExtra("uid", finalKey);
                startActivity(i);
            }
        });
        FirebaseDatabase.getInstance()
                .getReference().child("Member")
                .child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String myname = dataSnapshot.child("name").getValue().toString();
                    String myage = dataSnapshot.child("age").getValue().toString();
                    String mygender = dataSnapshot.child("gender").getValue().toString();
                    String myblood = dataSnapshot.child("blood").getValue().toString();
                    String myarea = dataSnapshot.child("area").getValue().toString();
                    String myphone = dataSnapshot.child("phone").getValue().toString();
                    String myemail = dataSnapshot.child("email").getValue().toString();

                    patientname.setText("  Name:  "+myname);
                    patientage.setText("  Age:  "+myage);
                    patientblood.setText("  Blood Group:  "+myblood);
                    patientgender.setText("  Gender:  "+mygender);
                    patientarea.setText("  Area:  "+myarea);
                    patientphone.setText("  Phone No:  "+myphone);
                    patientemail.setText("  Email Address:  "+myemail);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
