package com.example.order_app.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.order_app.R;

public class MainFragment extends Fragment {

    Button oldlogin;
    Button newlogin;

    public MainFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        oldlogin = view.findViewById(R.id.oldStaffLogin);
        newlogin = view.findViewById(R.id.newStaffLogin);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        oldlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMenu(v);
            }
        });

        newlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewLogin(v);
            }
        });
    }

    private void goToMenu(View view) {
        NavDirections action = MainFragmentDirections.toMenu();
        Navigation.findNavController(view).navigate(action);

    }

    private void goToNewLogin(View view) {
        NavDirections action = MainFragmentDirections.toNewLogin();
        Navigation.findNavController(view).navigate(action);
    }
}