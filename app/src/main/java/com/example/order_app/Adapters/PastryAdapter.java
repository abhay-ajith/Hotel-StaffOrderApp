package com.example.order_app.Adapters;

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
import com.example.order_app.viewmodel.PastryData;

public class PastryAdapter extends RecyclerView.Adapter<PastryAdapter.ViewHolder> {

    PastryData[] pastryData;

    Fragment fragment;

    int quantity;

    public PastryAdapter(PastryData[] pastryData, Fragment fragment) {
        this.pastryData = pastryData;
        this.fragment = fragment;
    }


    @NonNull
    @Override
    public PastryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.pastryitemlayout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PastryAdapter.ViewHolder holder, int position) {
        final PastryData pastryDataList = pastryData[position];

        holder.textView.setText(pastryDataList.getItem());
        holder.imageView.setImageResource(pastryDataList.getItemImage());
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
        return pastryData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        TextView textView,quantityTxt;

        ImageButton imageButtonPlus,imageButtonMinus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_pas_item);
            textView=itemView.findViewById(R.id.txt_pas_item);
            imageButtonPlus=itemView.findViewById(R.id.addquantity_pas);
            imageButtonMinus=itemView.findViewById(R.id.subquantity_pas);
            quantityTxt =itemView.findViewById(R.id.quantity_pas);
        }
    }
}
