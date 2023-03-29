package com.example.gamereview;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.google.gson.Gson;


public class SharedPreference {
    public static String My_Prefrences = "gameReview";
    static SharedPreferences mPref;
    Editor editor;


    public SharedPreference(Context mContext) {
        mPref = mContext.getSharedPreferences(My_Prefrences, Context.MODE_PRIVATE);
        editor = mPref.edit();
    }



    public static UserModel getLoggedUserObj() {
        Gson gson = new Gson();
        String json = mPref.getString(Constants.USER_OBJ, "");
        UserModel obj = gson.fromJson(json, UserModel.class);
        return obj;
    }

    public static void setLoogedUserObj(UserModel userModel) {
        Editor editor2 = mPref.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userModel);
        editor2.putString(Constants.USER_OBJ, json);
        editor2.apply();
    }


    public static void setUserLogin() {
        Editor editor = mPref.edit();
        editor.putBoolean(Constants.USER_LOGIN, true);
        editor.apply();
    }

    public static boolean isUserLogin() {
        return mPref.getBoolean(Constants.USER_LOGIN,false);
    }

    public static void resetUserLogin() {
        Editor editor = mPref.edit();
        editor.putBoolean(Constants.USER_LOGIN, false);
        editor.apply();
    }


    public static void removeAllData(){
        Editor editor = mPref.edit();
        editor.remove(Constants.USER_OBJ);
        resetUserLogin();
        editor.apply();
        mPref.edit().clear().apply();

    }




}
