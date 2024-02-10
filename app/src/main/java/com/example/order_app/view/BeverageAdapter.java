package com.example.order_app.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order_app.R;
import com.example.order_app.viewmodel.BeveragecatData;

public class BeverageAdapter extends RecyclerView.Adapter<BeverageAdapter.ViewHolder> {

    BeveragecatData[] beveragecatData;

    Context context;

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

    }

    @Override
    public int getItemCount() {
        return beveragecatData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_bev_item);
            textView=itemView.findViewById(R.id.txt_bev_item);
        }
    }
}
