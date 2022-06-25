package com.example.ridalooka;

import androidx.appcompat.app.AppCompatActivity;
import  android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

import android.os.Bundle;

public class loginsection extends AppCompatActivity {

    //Variables
    EditText mEmail, mPassword;
    Button LoginBtn, BackBtn;
    //Firebase fBase;  //this would be the variable for firebase.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsection);
    }

    TextView buttonTextview =(TextView)findViewById(R.id.button);


}