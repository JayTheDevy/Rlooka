package com.example.ridalooka;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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

        getSupportFragmentManager().beginTransaction().replace(R.id.categorie_fragment_container,new LibraryFragment()).commit();


        FirebaseFirestore db = FirebaseFirestore.getInstance();

    }


    public void toAddCategory(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.categorie_fragment_container,new AddCategoriesFragment()).commit();
    }
    public void toLibrary(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.categorie_fragment_container,new LibraryFragment()).commit();
    }
    public void toGraph(View view){
        getSupportFragmentManager().beginTransaction().replace(R.id.categorie_fragment_container,new CarListGraphFragment()).commit();
    }
    public void toAddCar(String category){
        getSupportFragmentManager().beginTransaction().replace(R.id.categorie_fragment_container,new AddCarFragment(category)).commit();
    }
}