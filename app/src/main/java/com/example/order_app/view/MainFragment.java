package com.example.order_app.view;

import android.database.Cursor;
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
import android.widget.EditText;
import android.widget.Toast;

import com.example.order_app.Database.OrderProvider;
import com.example.order_app.R;
import com.example.order_app.view.MainFragmentDirections;

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
                loginExistingStaff();
            }
        });

        newlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewLogin();
            }
        });
    }

    private void loginExistingStaff() {
        EditText usernameEditText = requireView().findViewById(R.id.staffName);
        EditText passwordEditText = requireView().findViewById(R.id.password);

        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Please enter username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        boolean loginSuccessful = checkCredentials(username, password);

        if (loginSuccessful) {
            goToMenu();
            Toast.makeText(getContext(), "Login Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkCredentials(String username, String password) {
        String[] projection = {OrderProvider.Username};

        String selection = OrderProvider.Username + " = ? AND " + OrderProvider.Password + " = ?";
        String[] selectionArgs = {username, password};

        Cursor cursor = getContext().getContentResolver().query(
                OrderProvider.CONTENT_URI_USER,
                projection,
                selection,
                selectionArgs,
                null
        );

        boolean isValid = cursor != null && cursor.getCount() > 0;

        if (cursor != null) {
            cursor.close();
        }

        return isValid;
    }

    private void goToMenu() {
        NavDirections action = MainFragmentDirections.toMenu();
        Navigation.findNavController(requireView()).navigate(action);
    }

    private void goToNewLogin() {
        NavDirections action = MainFragmentDirections.toNewLogin();
        Navigation.findNavController(requireView()).navigate(action);
    }
}
