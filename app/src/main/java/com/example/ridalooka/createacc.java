package com.example.ridalooka;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class createacc extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

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

    private void saveEmail(String email){
        db.collection("Users").document(email).set(new HashMap() {
                })
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(createacc.this, "Email saved", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void PerformAuth() {
        String email= mEmail.getText().toString();
         String password = mPassword.getText().toString();
         String ConPassword = mConPassword.getText().toString();

         if (!email.contains("@") && !email.contains("."))
         {
             mEmail.setError("Enter email tuu");

         } else if(password.isEmpty()|| password.length()< 6)
         {
             mPassword.setError("Enter password equal or greater then 6 characters");
         } else if(!ConPassword.equals(password))
         {
             mConPassword.setError("Password does not match");
         } else
         {
             progressDialog.setMessage("Registering....");
             progressDialog.setTitle("Registration");
             progressDialog.setCanceledOnTouchOutside(false);
             progressDialog.show();

            //FireBase verifying the inputs then create a method onComplete that will send user to next activity after verifying
             mAuth.createUserWithEmailAndPassword(email,password)
                     .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                         @Override
                         public void onComplete(@NonNull Task<AuthResult> task) {
                             if (task.isSuccessful()) {
                                 // Sign in success, update UI with the signed-in user's information
                                 FirebaseUser user = mAuth.getCurrentUser();
                                 progressDialog.dismiss();
                                 saveEmail(user.getEmail());
                                 Toast.makeText(createacc.this, "Accounted Create", Toast.LENGTH_LONG).show();
                                 toMainActivity();
                             } else {
                                 // If sign in fails, display a message to the user.
                                 Toast.makeText(createacc.this, "Authentication failed.",
                                         Toast.LENGTH_SHORT).show();
                                 progressDialog.dismiss();
                             }
                         }
                     });
         }
    }

    private void toMainActivity(){
        startActivity(new Intent(getApplicationContext(), categories.class));
    }
}