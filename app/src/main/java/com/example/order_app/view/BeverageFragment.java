package com.example.order_app.view;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.order_app.Adapters.BeverageAdapter;
import com.example.order_app.R;
import com.example.order_app.Database.OrderProvider;
import com.example.order_app.viewmodel.BeveragecatData;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BeverageFragment extends Fragment {

    Button addtocart;
    RecyclerView recyclerView;

    BeverageAdapter beverageAdapter;

    public BeverageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beverage, container, false);
        addtocart = view.findViewById(R.id.addtocart_bev);
        recyclerView = view.findViewById(R.id.bev_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        BeveragecatData[] beveragecatData = new BeveragecatData[]{
                new BeveragecatData("Lime Juice",R.drawable.limejuice,"0"),
                new BeveragecatData("Beer",R.drawable.beer,"0"),
                new BeveragecatData("Pepsi",R.drawable.pepsi,"0")
        };

        beverageAdapter = new BeverageAdapter(beveragecatData, this);
        recyclerView.setAdapter(beverageAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertCartItem();
            }
        });
    }

    @SuppressLint("Range")
    private void insertCartItem() {

        for (int i = 0; i < beverageAdapter.getItemCount(); i++) {
            // Get the BeveragecatData at the current position
            BeveragecatData beverage = beverageAdapter.getItem(i);

            // Retrieve data for the current item
            String itemName = beverage.getItem();
            String quantity = beverage.getQuantity();
            if(quantity.equals("0")){
                continue;
            }

            // Insert the item into the database
            ContentValues values = new ContentValues();
            values.put(OrderProvider.itemName, itemName);
            values.put(OrderProvider.quantity, Integer.parseInt(quantity));
            getActivity().getContentResolver().insert(OrderProvider.CONTENT_URI, values);

            Log.d("abhay", "Added Successfully ");
            //Toast.makeText(getContext(), "Item inserted: " + itemName + " " + quantity, Toast.LENGTH_SHORT).show();
        }

        Cursor cursor = getActivity().getContentResolver().query(OrderProvider.CONTENT_URI, null, null, null, null);
        if(cursor != null){
            try{
                while (cursor.moveToNext()){
                    String item_Name=cursor.getString(cursor.getColumnIndex(OrderProvider.itemName));
                    int quant=Integer.parseInt(cursor.getString(cursor.getColumnIndex(OrderProvider.quantity)));
                    Log.d("Abhay",item_Name + " " + quant);
                    Toast.makeText(getContext(),item_Name + " " + quant,Toast.LENGTH_SHORT).show();
                }
            }finally {
                cursor.close();
            }
        }
    }
}
