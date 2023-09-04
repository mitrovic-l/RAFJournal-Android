package com.raf.dnevnjak.view.fragments;

import static com.raf.dnevnjak.view.activities.MainActivity.EMAIL_KEY;
import static com.raf.dnevnjak.view.activities.MainActivity.USERNAME_KEY;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.raf.dnevnjak.R;
import com.raf.dnevnjak.view.activities.LoginActivity;

public class ProfileFragment extends Fragment {

    private String username;
    private String email;

    private TextView usernameEditText;
    private TextView emailEditText;
    private Button logoutBtn;
    private Button changePasswordButton;

    public ProfileFragment(){
        super(R.layout.fragment_profile);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }
    private void init(View view){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
        username = sharedPreferences.getString(USERNAME_KEY, "");
        email = sharedPreferences.getString(EMAIL_KEY, "");
        initView(view);
        initListeners();
    }

    private void initView(View view) {
        usernameEditText = view.findViewById(R.id.usernameTextViewProfilePage);
        emailEditText = view.findViewById(R.id.emailTextViewProfilePage);
        logoutBtn = view.findViewById(R.id.logOutButton);
        changePasswordButton = view.findViewById(R.id.changePasswordButton);
        usernameEditText.setText(username);
        emailEditText.setText(email);
    }

    private void initListeners() {
        logoutBtn.setOnClickListener( v-> {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(getActivity().getPackageName(), Context.MODE_PRIVATE);
            sharedPreferences.edit().clear().apply();
            Intent intent = new Intent(getActivity(), LoginActivity.class);
            startActivity(intent);
            getActivity().finish();
        });
        changePasswordButton.setOnClickListener( v-> {
            FragmentManager fragmentManager = getParentFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.setReorderingAllowed(true);

            fragmentTransaction.add(R.id.mainFragmentContainer, ChangePasswordFragment.class, null).addToBackStack(null);
            fragmentTransaction.commit();
        });
    }
}
