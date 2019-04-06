package com.example.vax_thon;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminProfile extends AppCompatActivity {

    private Button addfriendbtn,dashboardbtn,familybtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        addfriendbtn = findViewById(R.id.addmemberbtn);
        dashboardbtn =findViewById(R.id.daboardbtn);
        familybtn = findViewById(R.id.familyId);
        familybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Family.class));
            }
        });

        addfriendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddFriend.class));
            }
        });
        dashboardbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AdminDashBoard.class));
            }
        });
    }
}
