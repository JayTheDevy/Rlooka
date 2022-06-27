package com.example.ridalooka.fragments;

import android.app.ProgressDialog;
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

import com.example.ridalooka.R;
import com.example.ridalooka.models.data.Car;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;


public class AddCarFragment extends Fragment {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage storage = storage = FirebaseStorage.getInstance();
    ;


    private EditText txtName, textDes;
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

        btnImgAdd = (Button) view.findViewById(R.id.btnImgAdd);
        btnAddCar = (Button) view.findViewById(R.id.btnAddCar);

        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Capture();
            }
        });
        
    }

    private void Capture(){


        Car car = new Car();
        car.setCategory(category);
        car.setDescription(textDes.getText().toString());
        car.setName(txtName.getText().toString());
        
        Collect(car);
    }

    private void Collect(Car car)
    {
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Adding Car....");
        progressDialog.setTitle("Saving");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        db.collection("Users").document(user.getEmail()).collection(category).document().collection("cars").add(car)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        progressDialog.dismiss();
                    }
                });
    }

    public String saveImage(Uri uri){

        String[] splitArr = user.getEmail().split("@");
        String userFolderName = splitArr[0]+splitArr[1];

        String fileName = System.currentTimeMillis()+".jpg";

        try{
            //Invoking a method that will throw an exception if the object is empty
            uri.toString();

            storage.getReference().child(userFolderName).child(fileName).putFile(uri);

        }catch (Exception e){

            return null;
        }

        return userFolderName+"/"+fileName;
    }
}