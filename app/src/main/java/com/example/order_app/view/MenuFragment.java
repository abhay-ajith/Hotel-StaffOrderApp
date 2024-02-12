package com.example.order_app.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.order_app.Adapters.MenucatAdapter;
import com.example.order_app.viewmodel.MenucatData;
import com.example.order_app.R;

public class MenuFragment extends Fragment {


    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.itemcategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        MenucatData[] menucatData = new MenucatData[]{
                new MenucatData("Beverage", R.drawable.beverage_icon),
                new MenucatData("Pastry", R.drawable.pastery_icon),
                new MenucatData("Snack",R.drawable.snack_icon)
        };

        MenucatAdapter menucatAdapter = new MenucatAdapter(menucatData, this);
        recyclerView.setAdapter(menucatAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}