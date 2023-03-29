package com.example.gamereview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link signup#newInstance} factory method to
 * create an instance of this fragment.
 */
public class signup extends Fragment {

    Button signupBtn;
    TextInputEditText mFirstNameEt,mLastNameEt, mContactEt, mEmailEt,mDobEt,mPasswrdEt, mUserNameEt;
    DBHelper dbHandler;

//    String email = mEmailEt.getText().toString().trim();

   // String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public signup() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment signup.
     */
    // TODO: Rename and change types and number of parameters
    public static signup newInstance(String param1, String param2) {
        signup fragment = new signup();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        dbHandler = new DBHelper(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_signup, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        signupBtn=view.findViewById(R.id.mLoginBtn);
        mFirstNameEt=view.findViewById(R.id.mFirstName);
        mLastNameEt=view.findViewById(R.id.mLastNameEt);
        mContactEt=view.findViewById(R.id.mmobileNumber_Et);
        mEmailEt=view.findViewById(R.id.mEmailEt);
        //mDobEt=view.findViewById(R.id.mDobEt);
        mPasswrdEt=view.findViewById(R.id.mPasswordEt);
        mUserNameEt = view.findViewById(R.id.mUserNameEt);

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mFirstNameEt.getText().toString().trim().isEmpty()||mLastNameEt.getText().toString().trim().isEmpty()
                || mEmailEt.getText().toString().trim().isEmpty()
              ||mPasswrdEt.getText().toString().trim().isEmpty() ||mUserNameEt.getText().toString().trim().isEmpty()){
                    Toast.makeText(getActivity(), "Please fill all the details", Toast.LENGTH_SHORT).show();
                }else{
                    saveUser(mFirstNameEt.getText().toString().trim(),mLastNameEt.getText().toString().trim(),mEmailEt.getText().toString(),
                            mPasswrdEt.getText().toString().trim(),mContactEt.getText().toString().trim());
                }
            }
        });
//
//        mDobEt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Utils.showDatePicker(getActivity(),mDobEt);
//            }
//        });


    }

    private void saveUser(String fName, String lName, String email, String password,String phone) {
        long id = dbHandler.insertNewUser(fName,lName,email,password,phone);
        if(id<=0){
            Toast.makeText(getActivity(), "Signup Unsuccessful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getActivity(), "Signup Successful Go to Login Page", Toast.LENGTH_SHORT).show();
            mFirstNameEt.setText("");
            mLastNameEt.setText("");
            mEmailEt.setText("");
            mPasswrdEt.setText("");
            mContactEt.setText("");
            mUserNameEt.setText("");
        }
    }
}