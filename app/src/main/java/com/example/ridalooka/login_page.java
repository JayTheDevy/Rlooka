package com.example.ridalooka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login_page extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    EditText mEmail, mPassword;
    Button loginBtn;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsection);

        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null)
            toMainActivity();

        mEmail = findViewById(R.id.mEmail);
        mPassword= findViewById(R.id.mPassword);
        loginBtn= findViewById(R.id.LoginBtn);
        progressDialog=new ProgressDialog(this);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth() {
            progressDialog.setMessage("Logging in....");
            progressDialog.setTitle("Log in");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            //FireBase verifying the inputs then create a method onComplete that will send user to next activity after verifying
            mAuth.signInWithEmailAndPassword(mEmail.getText().toString(),mPassword.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                progressDialog.dismiss();
                                Toast.makeText(login_page.this, "Login Successful", Toast.LENGTH_LONG).show();
                                toMainActivity();
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(login_page.this, "Login failed.",
                                        Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
    }

    private void toMainActivity() {
        startActivity(new Intent(getApplicationContext(), categories.class));
    }
}