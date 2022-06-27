package com.example.ridalooka.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.ridalooka.R;
import com.example.ridalooka.models.data.Car;
import com.example.ridalooka.models.data.Category;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class AddCategoriesFragment extends Fragment {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private EditText txtName, textDes;
    private Button btnAddCategory;
    private SeekBar seekBarGoals;
    private TextView txtProgress;
    private ProgressDialog progressDialog;

    public AddCategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtName = (EditText) view.findViewById(R.id.txtName);
        textDes = (EditText) view.findViewById(R.id.txtDescription);

        btnAddCategory = (Button) view.findViewById(R.id.btnAddCategory);
        seekBarGoals = (SeekBar) view.findViewById(R.id.seekBarGoals);

        txtProgress = ((TextView) (view.findViewById(R.id.txtProgress)));

        btnAddCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Capture();
            }
        });

        seekBarGoals.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ((TextView) (view.findViewById(R.id.textView9))).setText("Goals: "+ i);
                txtProgress.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void Capture(){
        Category category = new Category();
        category.setGoal(Integer.parseInt(txtProgress.getText().toString()));
        category.setDescription(textDes.getText().toString());
        category.setName(txtName.getText().toString());

        Collect(category);
    }

    private void Collect(Category category) {
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Registering....");
        progressDialog.setTitle("Registration");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        db.collection("Users").document(user.getEmail()).collection("Category")
                .document(category.getName()).set(category)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();
            }
        });

    }
}