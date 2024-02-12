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
import com.example.order_app.viewmodel.BeveragecatData;
import com.example.order_app.viewmodel.SnackData;

public class SnackAdapter extends RecyclerView.Adapter<SnackAdapter.ViewHolder> {

    SnackData[] snackData;

    Fragment fragment;

    int quantity;

    public SnackAdapter(SnackData[] snackData, Fragment fragment) {
        this.snackData = snackData;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public SnackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater =LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.snackitemlayout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SnackAdapter.ViewHolder holder, int position) {
        final SnackData snackDataList = snackData[position];

        holder.textView.setText(snackDataList.getItem());
        holder.imageView.setImageResource(snackDataList.getItemImage());
        holder.imageButtonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(snackDataList.getQuantity());
                quantity++;
                snackDataList.setQuantity(String.valueOf(quantity));
                holder.quantityTxt.setText(String.valueOf(quantity));
            }
        });

        holder.imageButtonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity==0){
                    Toast.makeText(v.getContext(), "Cant Reduce the Amount",Toast.LENGTH_SHORT).show();
                }
                else {
                    int quantity = Integer.parseInt(snackDataList.getQuantity());
                    quantity--;
                    snackDataList.setQuantity(String.valueOf(quantity));
                    holder.quantityTxt.setText(String.valueOf(quantity));
                }
            }
        });

    }

    private void displayQuantity(TextView quantityTxt) {
        quantityTxt.setText(String.valueOf(quantity));
    }

    public SnackData getItem(int position) {
        return snackData[position];
    }

    @Override
    public int getItemCount() {
        return snackData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        TextView textView,quantityTxt;

        ImageButton imageButtonPlus,imageButtonMinus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image_sna_item);
            textView =itemView.findViewById(R.id.txt_sna_item);
            imageButtonPlus=itemView.findViewById(R.id.addquantity_sna);
            imageButtonMinus=itemView.findViewById(R.id.subquantity_sna);
            quantityTxt =itemView.findViewById(R.id.quantity_sna);
        }
    }
}
