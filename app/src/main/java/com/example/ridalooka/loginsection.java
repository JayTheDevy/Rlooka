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
        setContentView(R.layout.activity_login_page);
    }
}