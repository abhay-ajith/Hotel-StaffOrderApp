package com.example.order_app.view;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order_app.R;
import com.example.order_app.viewmodel.BeveragecatData;

import java.util.ArrayList;
import java.util.List;

public class BeverageAdapter extends RecyclerView.Adapter<BeverageAdapter.ViewHolder> {

    BeveragecatData[] beveragecatData;

    Context context;

    int quantity;

    public BeverageAdapter(BeveragecatData[] beveragecatData, Fragment fragment) {
        this.beveragecatData = beveragecatData;
        this.context = fragment.requireContext();
    }

    @NonNull
    @Override
    public BeverageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.beverageitemlayout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BeverageAdapter.ViewHolder holder, int position) {
        final BeveragecatData beveragecatDataList = beveragecatData[position];

        holder.textView.setText(beveragecatDataList.getItem());
        holder.imageView.setImageResource(beveragecatDataList.getItemImage());
        holder.imageButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                displayQuantity(holder.quantityTxt);

            }
        });

        holder.imageButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity==0){
                    Toast.makeText(v.getContext(), "Cant Reduce the Amount",Toast.LENGTH_SHORT).show();
                }
                else {
                    quantity--;
                    displayQuantity(holder.quantityTxt);
                }
            }
        });

    }

    private void displayQuantity(TextView quantityTxt) {
        quantityTxt.setText(String.valueOf(quantity));
    }

    @Override
    public int getItemCount() {
        return beveragecatData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView,quantityTxt;

        ImageButton imageButtonPlus,imageButtonMinus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_bev_item);
            textView=itemView.findViewById(R.id.txt_bev_item);
            imageButtonPlus=itemView.findViewById(R.id.addquantity_bev);
            imageButtonMinus=itemView.findViewById(R.id.subquantity_bev);
            quantityTxt =itemView.findViewById(R.id.quantity_bev);

        }
    }
}
