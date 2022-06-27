package com.example.ridalooka;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ridalooka.fragments.AddCarFragment;
import com.example.ridalooka.fragments.AddCategoriesFragment;
import com.example.ridalooka.fragments.CarListGraphFragment;
import com.example.ridalooka.fragments.LibraryFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.example.ridalooka.fragments.LibraryFragment;
import com.google.firebase.firestore.FirebaseFirestore;

public class categories extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        new Button().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.chart:
                        getSupportFragmentManager().beginTransaction().replace(R.id.categorie_fragment_container,new CarListGraphFragment()).commit();
                        return true;
                    case R.id.lib:
                        getSupportFragmentManager().beginTransaction().replace(R.id.categorie_fragment_container,new LibraryFragment()).commit();
                        return true;
                }

                return false;
            }
        });
    }

    public void toAddCategory(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.categorie_fragment_container,new AddCategoriesFragment()).commit();
    }

    public void toAddCar(String category){
        getSupportFragmentManager().beginTransaction().replace(R.id.categorie_fragment_container,new AddCarFragment(category)).commit();
    }
}