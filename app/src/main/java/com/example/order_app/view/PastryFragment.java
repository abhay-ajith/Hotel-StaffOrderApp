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
import android.widget.Toast;

import com.example.order_app.Adapters.PastryAdapter;
import com.example.order_app.R;
import com.example.order_app.viewmodel.PastryData;


public class PastryFragment extends Fragment {

    Button addtocart;


    public PastryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pastry, container, false);

        addtocart=view.findViewById(R.id.addtocart_pas);

        RecyclerView recyclerView = view.findViewById(R.id.pas_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        PastryData[] pastryData = new PastryData[]{
                new PastryData("Cake",R.drawable.pastery_icon)
        };

        PastryAdapter pastryAdapter = new PastryAdapter(pastryData,this);
        recyclerView.setAdapter(pastryAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Added to orders",Toast.LENGTH_SHORT).show();
            }
        });
    }
}