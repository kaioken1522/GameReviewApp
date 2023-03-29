package com.example.gamereview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;


public class login extends Fragment {

    Button mLoginBtn,mSignupBtn;
    TextInputEditText mEmailEt,mPasswordEt;
    DBHelper dbHandler;
    SharedPreference sharedPreference;
    public MainActivity mainActivity = new MainActivity();
    public DrawerLayout drawerLayout;
    public Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fragment_login, container, false);
        return  v;



    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        sharedPreference=new SharedPreference(getActivity());
        mLoginBtn=view.findViewById(R.id.mLoginBtn);
        mSignupBtn=view.findViewById(R.id.mSignupBtn);
        mEmailEt=view.findViewById(R.id.mEmailEt);
        mPasswordEt=view.findViewById(R.id.mPasswordEt);
        dbHandler=new DBHelper(getActivity());
        drawerLayout = new DrawerLayout(getContext());
        toolbar = new Toolbar(getContext());
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mEmailEt.getText().toString().trim().isEmpty()||mPasswordEt.getText().toString().trim().isEmpty()){
                    Toast.makeText(getActivity(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                }else{
                    loginUser(mEmailEt.getText().toString().trim(),mPasswordEt.getText().toString().trim());
                }
            }
        });

        mSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new signup()).commit();
            }
        });
    }

    private void loginUser(String email, String password) {
        UserModel userModel = dbHandler.validateAndLogin(email, password);
        if(userModel!=null){
            // setting logged user as true
            SharedPreference.setUserLogin();
            // Saving logged user in Shared Preference
            SharedPreference.setLoogedUserObj(userModel);
            mainActivity.userName = email;
            //drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            Toast.makeText(getActivity(), "Login Successful", Toast.LENGTH_SHORT).show();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MainPage()).commit();
        }else{
            Toast.makeText(getActivity(), "User Not Found ", Toast.LENGTH_SHORT).show();
        }

    }





}