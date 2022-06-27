package com.example.ridalooka.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ridalooka.R;
import com.example.ridalooka.models.data.Car;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.UUID;


public class AddCarFragment extends Fragment {

    private static final int RESULT_OK = -1;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private StorageReference storage = FirebaseStorage.getInstance().getReference();

    private final static int IMAGE_REQUEST = 2;

    private Uri imgUri;
    private EditText txtName, textDes, txtCategory;
    private Button btnImgAdd,btnAddCar;
    private ProgressDialog progressDialog;
    private String category;



    public AddCarFragment() {
        // Required empty public constructor
        category = "n/a";

    }

    // constructor
    public AddCarFragment(String category) {
        this.category = category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_car, container, false);
    }

    //use view to find the id needed
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtName = (EditText) view.findViewById(R.id.txtName);
        textDes = (EditText) view.findViewById(R.id.textDes);
        txtCategory = (EditText) view.findViewById(R.id.txtCategoryName);

        btnImgAdd = (Button) view.findViewById(R.id.btnImgAdd);
        btnAddCar = (Button) view.findViewById(R.id.btnAddCar);

        btnImgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Capture();
            }
        });
        
    }

    private void Capture(){

        if (!(imgUri != null))
        {
            Toast.makeText(getActivity(), "Please Upload a picture", Toast.LENGTH_SHORT).show();
        }
        Car car = new Car();
        car.setCategory(category);
        car.setDescription(textDes.getText().toString());
        car.setName(txtName.getText().toString());

        car.setImgUrl(saveImage(imgUri));

        Collect(car);
    }

    private void Collect(Car car)
    {
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Adding Car...");
        progressDialog.setTitle("Saving");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        db.collection("Users").document(user.getEmail()).collection("Category").
                document(category)
                .collection("cars").add(car)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        progressDialog.dismiss();
                    }
                });
    }


    private void selectImage(){
        Intent in = new Intent();
        in.setType("image/*");
        in.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(in,IMAGE_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_REQUEST && resultCode == RESULT_OK){
            if(data.getData() != null){
                imgUri = data.getData();
                btnImgAdd.setText("Imaged Loaded");
            }
        }
    }


    public String saveImage(Uri uri){

        String fileName = UUID.randomUUID().toString()+".jpg";

        try{
            //Invoking a method that will throw an exception if the object is empty
            uri.toString();

            storage.child(fileName).putFile(uri);

        }catch (Exception e){

            return null;
        }

        return fileName;
    }
}