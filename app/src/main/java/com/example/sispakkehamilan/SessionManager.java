package com.example.sispakkehamilan;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.sispakkehamilan.modul.Dataakun;

import java.util.HashMap;

public class SessionManager {
    private Context _context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public static final String IS_LOGGED_IN = "isLoggedIn";
    public static final String USERNAME = "username";
    private static final String KEY_USER_LEVEL = "userLevel";
    public static final String NAME = "name";
    private static final String PREF_NAME = "MyAppPref";

    public static final String ALAMAT = "alamat";
    public static final String LEVELL = "level";

    public static final String NOHP = "nohp";

    public SessionManager (Context context){
        this._context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createLoginSession(Dataakun user){
        editor.putBoolean(IS_LOGGED_IN, true);
        editor.putString(USERNAME, user.getUsername());
        editor.putString(LEVELL, user.getLevel());
        editor.putString(NAME, user.getNama());
        editor.putString(ALAMAT, user.getAlamat());
        editor.putString(NOHP, user.getNohp());
        editor.commit();
    }

    public HashMap<String,String> getUserDetail(){
        HashMap<String,String> user = new HashMap<>();
        user.put(USERNAME, sharedPreferences.getString(USERNAME,null));
        user.put(LEVELL, sharedPreferences.getString(LEVELL,null));
        user.put(NAME, sharedPreferences.getString(NAME,null));
        user.put(ALAMAT, sharedPreferences.getString(ALAMAT,null));
        user.put(NOHP, sharedPreferences.getString(NOHP,null));
        return user;
    }

    public void setLogin(boolean isLoggedIn, String userlevel) {
        editor.putBoolean(IS_LOGGED_IN, isLoggedIn);
        editor.putString(KEY_USER_LEVEL, userlevel);
        editor.apply();
    }

    public void logoutSession(){
        editor.clear();
        editor.commit();
    }

    public boolean isLoggedIn(){
        return sharedPreferences.getBoolean(IS_LOGGED_IN, false);
    }

    public String getUserLevel() {
        return sharedPreferences.getString(KEY_USER_LEVEL, "");
    }
}

