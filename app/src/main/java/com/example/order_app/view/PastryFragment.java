package com.example.order_app.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.order_app.R;
import com.example.order_app.viewmodel.PastryData;


public class PastryFragment extends Fragment {



    public PastryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pastry, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.pas_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        PastryData[] pastryData = new PastryData[]{
                new PastryData("Cake",R.drawable.pastery_icon)
        };

        PastryAdapter pastryAdapter = new PastryAdapter(pastryData,this);
        recyclerView.setAdapter(pastryAdapter);
        return view;
    }
}