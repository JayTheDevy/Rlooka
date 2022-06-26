package com.example.ridalooka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button lets_goo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lets_goo= findViewById(R.id.lets_goo);
        lets_goo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    openLoginpage();
            }
        });

    }

    private void openLoginpage() {
        Intent intent = new Intent(this, login_page.class);
        startActivity(intent);

    }
}