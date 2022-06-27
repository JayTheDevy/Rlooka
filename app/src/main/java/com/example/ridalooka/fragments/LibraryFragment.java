package com.example.ridalooka.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.ridalooka.R;
import com.example.ridalooka.categories;
import com.example.ridalooka.models.data.Category;
import com.example.ridalooka.models.fragment.Library;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class LibraryFragment extends Fragment {

    private FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private RecyclerView rec;
    private FloatingActionButton addCategory;

    private ProgressDialog progressDialog;


    public LibraryFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((TextView) (getActivity().findViewById(R.id.txtHeader))).setText("Library");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_library, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rec = (RecyclerView) view.findViewById(R.id.libRecycle);
        addCategory = (FloatingActionButton) view.findViewById(R.id.floatingActionButtonAddCategory);

        PopulateData();
    }

    private void PopulateData(){
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setMessage("Loading....");
        progressDialog.setTitle("Loading");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        db.collection("Users").document(user.getEmail()).collection("Category")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            ArrayList<Library> list = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Category category = document.toObject(Category.class);

                                Library library = new Library(category);

                                list.add(library);
                            }
                            rec.setAdapter(new LibraryAdapter(list));
                            rec.setLayoutManager(new LinearLayoutManager(getContext()));
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }

                        progressDialog.dismiss();
                    }
                });
    }

    public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder>{

        List<Library> libItems;

        public LibraryAdapter(ArrayList<Library> libItems) {
            this.libItems = libItems;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View libView = layoutInflater.inflate(R.layout.lib_item,parent,false);

            return new ViewHolder(libView);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            Library libItem = libItems.get(position);

            TextView txtCat,txtNumGoals;

            txtCat = holder.txtCat;
            txtNumGoals = holder.txtNumGoal;

            txtCat.setText(libItem.getCategory());
            txtNumGoals.setText(libItem.getCurrentNumberOfGoal()+"/"+ libItem.getOverallNumberOfGoal());

            txtCat.setClickable(true);
            txtCat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    getActivity().getSupportFragmentManager()
                            .beginTransaction().replace(R.id.categorie_fragment_container,
                                    new AddCarFragment(txtCat.getText().toString())).commit();
                }
            });
        }

        @Override
        public int getItemCount() {
            return libItems.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            public TextView txtCat,txtNumGoal;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                txtCat = init(R.id.txtCat,itemView);
                txtNumGoal = init(R.id.txtNumGoal,itemView);
            }

            private TextView init(int id,View view){
                return (TextView) view.findViewById(id);
            }
        }
    }
}