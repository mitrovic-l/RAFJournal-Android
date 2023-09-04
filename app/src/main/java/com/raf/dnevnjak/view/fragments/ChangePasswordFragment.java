package com.raf.dnevnjak.view.fragments;

import static com.raf.dnevnjak.view.activities.MainActivity.USERNAME_KEY;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.raf.dnevnjak.R;
import com.raf.dnevnjak.database.SQLiteDB;

public class ChangePasswordFragment extends Fragment {

    private Button changePasswordButton;
    private EditText changePasswordEditText;

    public ChangePasswordFragment(){
        super(R.layout.fragment_password);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }
    private void init(View view){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        initView(view);
        initListeners();
    }

    private void initView(View view){
        changePasswordButton = view.findViewById(R.id.changePasswordButton);
        changePasswordEditText = view.findViewById(R.id.changePasswordET);
    }
    private void initListeners(){
        changePasswordButton.setOnClickListener( v -> {
            String newPass = changePasswordEditText.getText().toString();
            System.out.println("------------------------NOVI PASS: " + newPass);
            Context context = getActivity().getApplicationContext();
            if (newPass == null || newPass == ""){
                Toast.makeText(context, "Password cannot be blank", Toast.LENGTH_LONG).show();
                return;
            }
            if (newPass.trim().length() == 0 || newPass.length()<5){
                Toast.makeText(context, "Password cannot be blank or shorter than 5 characters", Toast.LENGTH_LONG).show();
                return;
            }
            SQLiteDB SQLiteDb = new SQLiteDB(getActivity());
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
            String username = sharedPreferences.getString(USERNAME_KEY, "");
            SQLiteDb.updatePasswordForUser(username, newPass);
            Toast.makeText(getContext(), "Password changed", Toast.LENGTH_SHORT).show();
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            transaction.replace(R.id.mainFragmentContainer, ProfileFragment.class, null);
            transaction.commit();
        });
    }
}
