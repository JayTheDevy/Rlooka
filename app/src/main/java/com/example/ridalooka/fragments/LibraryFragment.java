package com.example.ridalooka.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.example.ridalooka.R;
import com.example.ridalooka.models.fragment.Library;

public class LibraryFragment extends Fragment {

    private RecyclerView rec;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public LibraryFragment() {
        // Required empty public constructor
    }

    public static LibraryFragment newInstance(String param1, String param2) {
        LibraryFragment fragment = new LibraryFragment();
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

        ArrayList<Library> list = new ArrayList<>();

        list.add(new Library("Coup",1,10));
        list.add(new Library("SUV",4,14));
        list.add(new Library("Hatch",10,11));
        list.add(new Library("Sports",2,22));

        rec.setAdapter(new LibraryAdapter(list));
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
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