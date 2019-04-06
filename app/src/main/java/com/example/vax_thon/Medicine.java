package com.example.vax_thon;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

public class Medicine extends AppCompatActivity {

    private Button submit;
    private EditText name,date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        name = findViewById(R.id.vaccinenameid);
        date = findViewById(R.id.vaccinedateid);
        submit = findViewById(R.id.submitbtn);
        final SetVaccine sv =new SetVaccine();

        final SetVaccine st = sv;


        final String key = getIntent().getExtras().get("uid").toString();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sv.date = date.getText().toString().trim();
                sv.name = name.getText().toString().trim();
                final String ch = sv.name;
                Toast.makeText(getApplicationContext(),ch,Toast.LENGTH_SHORT).show();
                FirebaseDatabase.getInstance()
                        .getReference().child("Member")
                        .child(key).child("vaccine").child(ch).setValue(st).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            //finish();
                            //Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT).show();
                            startActivity( new Intent(getApplicationContext(),Notification.class));
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),task.getException().getMessage().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
