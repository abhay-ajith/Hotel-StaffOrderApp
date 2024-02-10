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
import android.widget.Toast;

import com.example.order_app.R;

public class NewLoginFragment extends Fragment {

    Button newloginbtn;
    Button signupbtn;

    public NewLoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_new_login, container, false);

       newloginbtn=view.findViewById(R.id.newLoginBtn);
       signupbtn=view.findViewById(R.id.signupBtn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        newloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMain();
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert_to_db();
            }
        });

    }

    private void insert_to_db() {

        Toast.makeText(getContext(),"Data Successfully Added",Toast.LENGTH_SHORT).show();
    }

    private void goToMain() {
        NavDirections action = NewLoginFragmentDirections.toMain();
        Navigation.findNavController(newloginbtn).navigate(action);
    }
}
