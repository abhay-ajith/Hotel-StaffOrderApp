package com.example.order_app.view;

import android.content.ContentValues;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.order_app.Database.OrderProvider;
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

        EditText usernameEditText = requireView().findViewById(R.id.staffNameNew);
        EditText passwordEditText = requireView().findViewById(R.id.passwordNew);

        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Validation: Check if username and password are not empty
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(requireContext(), "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put(OrderProvider.Username, username);
        values.put(OrderProvider.Password, password);
        getActivity().getContentResolver().insert(OrderProvider.CONTENT_URI_USER, values);

        Log.d("abhay", "Added Successfully New User ");
    }

    private void goToMain() {
        NavDirections action = NewLoginFragmentDirections.toMain();
        Navigation.findNavController(newloginbtn).navigate(action);
    }
}
