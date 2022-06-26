package com.example.ridalooka;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class createacc extends AppCompatActivity {
     EditText mEmail, mPassword,mConPassword;
     Button mCreatebtn;
     ProgressDialog progressDialog;
     //Firebase
     //Get current user()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createacc);


        mEmail = findViewById(R.id.mEmail);
        mPassword= findViewById(R.id.mPassword);
        mConPassword= findViewById(R.id.mConPassword);
        mCreatebtn= findViewById(R.id.mCreatebtn);
        progressDialog=new ProgressDialog(this);
        mCreatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
        String email= mEmail.getText().toString();
         String password = mPassword.getText().toString();
         String ConPassword = mConPassword.getText().toString();

         if (email.matches(email))
         {
             mEmail.setError("Enter email tuu");

         } else if(password.isEmpty()|| password.length()<8)
         {
             mPassword.setError("Enter password");
         } else if(ConPassword.isEmpty()|| ConPassword.length()<8)
         {
             mConPassword.setError("Password does not match");
         } else
         {
             progressDialog.setMessage("Registering....");
             progressDialog.setTitle("Registration");
             progressDialog.setCanceledOnTouchOutside(false);
             progressDialog.show();

            //FireBase verifying the inputs then create a method onComplete that will send user to next activity after verifying




         }
    }
}