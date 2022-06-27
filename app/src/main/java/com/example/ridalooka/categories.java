package com.example.ridalooka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ridalooka.fragments.AddCarFragment;
import com.example.ridalooka.fragments.AddCategoriesFragment;
import com.example.ridalooka.fragments.CarListGraphFragment;
import com.example.ridalooka.fragments.LibraryFragment;
import com.google.firebase.firestore.FirebaseFirestore;

public class categories extends AppCompatActivity {

    private TextView txtHeader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        txtHeader = (TextView) findViewById(R.id.txtHeader);

        getSupportFragmentManager().beginTransaction().replace(R.id.categorie_fragment_container, new LibraryFragment()).commitNow();
    }

}