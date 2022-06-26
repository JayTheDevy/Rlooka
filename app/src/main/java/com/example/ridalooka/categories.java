package com.example.ridalooka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ridalooka.fragments.CarListGraph;
import com.example.ridalooka.fragments.CategoryViewFragment;

public class categories extends AppCompatActivity {

    private TextView txtHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        txtHeader = (TextView) findViewById(R.id.txtHeader);

        getSupportFragmentManager().beginTransaction().replace(R.id.categorie_fragment_container, new CarListGraph()).commitNow();
    }

}