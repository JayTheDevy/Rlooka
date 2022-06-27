package com.example.ridalooka.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.ridalooka.R;
import com.example.ridalooka.models.data.Car;
import com.example.ridalooka.models.data.Category;
import com.example.ridalooka.models.fragment.CategoryView;
import com.example.ridalooka.models.fragment.Library;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewFragment extends Fragment {

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private RecyclerView rec;

    private ProgressDialog progressDialog;
    private String category;

    public CategoryViewFragment() {
        // Required empty public constructor
    }
    public CategoryViewFragment(String category) {
        // Required empty public constructor
        this.category = category;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category_view, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        rec = (RecyclerView) view.findViewById(R.id.catViewRecycle);

        ArrayList<CategoryView> categoryViewArrayList = new ArrayList<>();
        categoryViewArrayList.add(new CategoryView(R.drawable.luncher));
        categoryViewArrayList.add(new CategoryView(R.drawable.luncher));
        categoryViewArrayList.add(new CategoryView(R.drawable.luncher));
        categoryViewArrayList.add(new CategoryView(R.drawable.luncher));
        categoryViewArrayList.add(new CategoryView(R.drawable.luncher));
        categoryViewArrayList.add(new CategoryView(R.drawable.luncher));
        categoryViewArrayList.add(new CategoryView(R.drawable.luncher));
        categoryViewArrayList.add(new CategoryView(R.drawable.luncher));
        categoryViewArrayList.add(new CategoryView(R.drawable.luncher));
        categoryViewArrayList.add(new CategoryView(R.drawable.luncher));
        categoryViewArrayList.add(new CategoryView(R.drawable.luncher));

        rec.setAdapter(new CategoryViewAdapter(categoryViewArrayList));
        rec.setLayoutManager(new GridLayoutManager(getContext(),3, LinearLayoutManager.VERTICAL,false));
    }

    private void populateData(){
        db.collection("Users").document(user.getEmail()).collection("Category")
                .document(category).collection("cars").get().
                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<CategoryView> list = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Car car = document.toObject(Car.class);

                                CategoryView library = new CategoryView(car.getImgUrl());

                                list.add(library);
                            }
                            rec.setAdapter(new CategoryViewAdapter(list));
                            rec.setLayoutManager(new LinearLayoutManager(getContext()));
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }

                        progressDialog.dismiss();
                    }
                });
    }

    public class CategoryViewAdapter extends RecyclerView.Adapter<CategoryViewFragment.CategoryViewAdapter.ViewHolder>{

        List<CategoryView> categoryViewList;

        public CategoryViewAdapter(ArrayList<CategoryView> categoryViewList) {
            this.categoryViewList = categoryViewList;
        }

        @NonNull
        @Override
        public CategoryViewFragment.CategoryViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View libView = layoutInflater.inflate(R.layout.selected_category_item,parent,false);

            return new CategoryViewFragment.CategoryViewAdapter.ViewHolder(libView);
        }

        @Override
        public void onBindViewHolder(@NonNull CategoryViewFragment.CategoryViewAdapter.ViewHolder holder, int position) {
            CategoryView cvItem = categoryViewList.get(position);

            ImageButton imgBtn = holder.imgBtn;

            // TODO: Handle all u need to handle  TO MAKE THE IMAGE WORK

            if(cvItem.getImageLink() != null)
                imgBtn.setImageBitmap(null);
            else // todo: Delete the resource part because I am using it to test without the firebase
                imgBtn.setImageResource(cvItem.getResLink());
        }
        @Override
        public int getItemCount() {
            return categoryViewList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public ImageButton imgBtn;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                imgBtn = init(R.id.imageButton,itemView);
            }

            private ImageButton init(int id,View view){
                return (ImageButton) view.findViewById(id);
            }
        }
    }

}