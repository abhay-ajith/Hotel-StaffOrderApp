package com.example.order_app.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.order_app.view.MenuFragmentDirections;
import com.example.order_app.viewmodel.MenucatData;
import com.example.order_app.R;

public class MenucatAdapter extends RecyclerView.Adapter<MenucatAdapter.ViewHolder> {

    MenucatData[] menucatData;
    Context context;

    public MenucatAdapter(MenucatData[] menucatData, Fragment fragment){
        this.menucatData=menucatData;
        this.context=fragment.requireContext();
    }


    @NonNull
    @Override
    public MenucatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.menucatlayout,parent,false);
        ViewHolder viewHolder =new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenucatAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final MenucatData menucatDataList = menucatData[position];

        holder.textViewCat.setText(menucatDataList.getMenucat());
        holder.imageView.setImageResource(menucatDataList.getMenucat_image());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMenucat(v,menucatDataList, position);
            }
        });

    }

    private void goToMenucat(View view ,MenucatData menucatDataList, int postion) {
        if(postion==0) {
            NavDirections action = MenuFragmentDirections.toBeverage();
            Navigation.findNavController(view).navigate(action);
        }
        else if(postion ==1){
            NavDirections action = MenuFragmentDirections.toPastry();
            Navigation.findNavController(view).navigate(action);
        }
        else if(postion ==2){
            NavDirections action = MenuFragmentDirections.toSnack();
            Navigation.findNavController(view).navigate(action);
        }
    }


    @Override
    public int getItemCount() {
        return menucatData.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewCat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.image_cat);
            textViewCat =itemView.findViewById(R.id.txt_cat);
        }
    }
}
