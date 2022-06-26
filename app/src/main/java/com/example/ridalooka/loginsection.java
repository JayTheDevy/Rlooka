package com.example.ridalooka;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.view.View;
import android.widget.ProgressBar;
import  android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;

public class loginsection extends AppCompatActivity {

    //Variables
    EditText mEmail, mPassword;
    Button LoginBtn, BackBtn;
    ProgressDialog progressDialog;
    //Firebase fBase;  //this would be the variable for firebase.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsection);


        mEmail = findViewById(R.id.mEmail);
        mPassword = findViewById(R.id.mPassword);
        LoginBtn = findViewById(R.id.LoginBtn);

        LoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Performlogin();
            }
        });
    }

    private void Performlogin() {
        String email= mEmail.getText().toString();
        String password = mPassword.getText().toString();
        if (email.matches(email)) {
            mEmail.setError("Enter email tuu");

        } else if (password.isEmpty() || password.length() < 8) {
            mPassword.setError("Enter password");
        } else {
            progressDialog.setMessage(" Logging in....");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            //FireBase verifying the inputs then create a method onComplete that will send user to next activity after verifying
        }


    }
}