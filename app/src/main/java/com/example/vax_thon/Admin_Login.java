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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Admin_Login extends AppCompatActivity {

    private EditText emailbtn,passbtn;
    private Button login,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member__login);

        emailbtn = findViewById(R.id.adminEmailId);
        passbtn = findViewById(R.id.adminPasswordId);

        login = findViewById(R.id.loginBtnId);
        signup = findViewById(R.id.signupBtnId);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Admin_Signup.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                process();
            }
        });

    }
    void process()
    {
        String email = emailbtn.getText().toString().toLowerCase().trim();
        String pass = passbtn.getText().toString().trim();
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                            startActivity(new Intent(getApplicationContext(),AdminProfile.class));
                        else
                            Toast.makeText(getApplicationContext(),"Error!!!",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
