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

public class Member_Login extends AppCompatActivity {

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
                startActivity(new Intent(getApplicationContext(),Member_Signup.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(emailbtn.getText().toString().toLowerCase().trim(),
                                passbtn.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                           startActivity(new Intent(getApplicationContext(),MemberDashboard.class));
                        else
                            Toast.makeText(getApplicationContext(),task.getException().getMessage().toString(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
