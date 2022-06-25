package com.example.ridalooka;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.ridalooka.models.fragment.CategoryView;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewFragment extends Fragment {

    private RecyclerView rec;
    
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoryViewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoryViewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoryViewFragment newInstance(String param1, String param2) {
        CategoryViewFragment fragment = new CategoryViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
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