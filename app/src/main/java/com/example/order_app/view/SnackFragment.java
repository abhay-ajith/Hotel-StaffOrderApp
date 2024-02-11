package com.example.order_app.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.order_app.R;
import com.example.order_app.viewmodel.SnackData;

public class SnackFragment extends Fragment {

    Button addtocart;

    public SnackFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_snack, container, false);

        addtocart=view.findViewById(R.id.addtocart_sna);
        RecyclerView recyclerView = view.findViewById(R.id.sna_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        SnackData[] snackData = new SnackData[]{
                new SnackData("Lays",R.drawable.snack_icon)
        };

        SnackAdapter snackAdapter = new SnackAdapter(snackData,this);
        recyclerView.setAdapter(snackAdapter);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = SnackFragmentDirections.toOrdersSna();
                Navigation.findNavController(v).navigate(action);
            }
        });
    }
}