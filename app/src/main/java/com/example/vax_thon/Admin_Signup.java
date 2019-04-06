package com.example.vax_thon;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_Signup extends AppCompatActivity {

    private EditText emailvalue,passwordvalue,namevalue,gendervalue,agevalue,bloodvalue,areavalue,phonevalue;
    private Button signUp,gotoLogin;
    private ProgressDialog progressDialog;

    private FirebaseAuth patientAuth;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);

        /*
        emailvalue = (EditText) findViewById(R.id.adminEditEmailId);
        passwordvalue = (EditText) findViewById(R.id.adminEditPasswordId);
        namevalue = (EditText) findViewById(R.id.adminEditNameId);
        gendervalue =(EditText) findViewById(R.id.adminEditGenderId);
        agevalue= (EditText) findViewById(R.id.adminEditAgeId);
        bloodvalue = (EditText) findViewById(R.id.adminEditBloodId);
        areavalue = (EditText) findViewById(R.id.admineditAreaId);
        phonevalue = (EditText) findViewById(R.id.adminEditphoneId);

        signUp = (Button) findViewById(R.id.adminSignUpBtnId);
        gotoLogin = (Button) findViewById(R.id.gotoLoginBtnId);
        progressDialog = new ProgressDialog(this);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPatient();
            }
        });
        gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(),Admin_Login.class));
            }
        });
        /// Firebase


    }
    void registerPatient(){
        String email = emailvalue.getText().toString().trim();
        String pass = passwordvalue.getText().toString().trim();
        FirebaseApp.initializeApp(getApplicationContext());
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"___--",Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getApplicationContext(),"BAL",Toast.LENGTH_SHORT).show();
                    }
                });
    }
    void addPatientInfo(){
        String name = namevalue.getText().toString().trim();
        String gender = gendervalue.getText().toString().trim();
        String age = agevalue.getText().toString().trim();
        String blood = bloodvalue.getText().toString().trim();
        String area = areavalue.getText().toString().trim();
        String phone = phonevalue.getText().toString().trim();
        String email = emailvalue.getText().toString().trim();



        Member_Info patientInfo = new Member_Info(name,gender,age,blood,area,phone,email);


               // .child(patient.getUid()).setValue(patientInfo);
        Toast.makeText(getApplicationContext(),"Info added",Toast.LENGTH_SHORT).show();

    }
    */

        emailvalue = (EditText) findViewById(R.id.adminEditEmailId);
        passwordvalue = (EditText) findViewById(R.id.adminEditPasswordId);
        namevalue = (EditText) findViewById(R.id.adminEditNameId);
        gendervalue =(EditText) findViewById(R.id.adminEditGenderId);
        agevalue= (EditText) findViewById(R.id.adminEditAgeId);
        bloodvalue = (EditText) findViewById(R.id.adminEditBloodId);
        areavalue = (EditText) findViewById(R.id.admineditAreaId);
        phonevalue = (EditText) findViewById(R.id.adminEditphoneId);

    signUp = (Button) findViewById(R.id.adminSignUpBtnId);
    gotoLogin = (Button) findViewById(R.id.gotoLoginBtnId);
    progressDialog = new ProgressDialog(this);

        signUp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            registerPatient();
        }
    });
        gotoLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity( new Intent(getApplicationContext(),Admin_Login.class));
        }
    });

    /// Firebase
    patientAuth = FirebaseAuth.getInstance();
    databaseReference = FirebaseDatabase.getInstance().getReference().child("Admin");


}

    void registerPatient(){
        String email = emailvalue.getText().toString().trim();
        String password=passwordvalue.getText().toString().trim();

        if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
            Toast.makeText(this,"Incomplete Input",Toast.LENGTH_SHORT).show();
        }
        else{
            progressDialog.setMessage("Registering...");
            progressDialog.show();

            patientAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        Toast.makeText(getApplicationContext(),"Successfully Registered",Toast.LENGTH_SHORT).show();
                        addPatientInfo();
                        progressDialog.dismiss();
                        finish();
                        startActivity(new Intent(getApplicationContext(),Admin_Login.class));
                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Email in Use",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    void addPatientInfo(){
        String name = namevalue.getText().toString().trim();
        String gender = gendervalue.getText().toString().trim();
        String age = agevalue.getText().toString().trim();
        String blood = bloodvalue.getText().toString().trim();
        String area = areavalue.getText().toString().trim();
        String phone = phonevalue.getText().toString().trim();
        String email = emailvalue.getText().toString().trim();




        FirebaseUser patient = patientAuth.getCurrentUser();
        Member_Info patientInfo = new Member_Info(name,gender,age,blood,area,phone,email,patient.getUid().toString());
        databaseReference.child(patient.getUid()).setValue(patientInfo)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"YEEE",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(),task.getException().getMessage().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        Toast.makeText(getApplicationContext(),"Info added",Toast.LENGTH_SHORT).show();
    }
}

