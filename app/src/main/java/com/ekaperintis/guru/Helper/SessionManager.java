package com.ekaperintis.guru.Helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SessionManager {
    private static String TAG = SessionManager.class.getSimpleName();

    // Shared Preferences
    SharedPreferences pref;

    SharedPreferences.Editor editor;
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "edutrackid";
    private static final String KEY_IS_STATUS = "isStatus";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_IS_SCANNING = "isScanning";
    private static final String KEY_VERSI ="versi";

    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public void setScanning(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_SCANNING, isLoggedIn);
        editor.commit();

        Log.d(TAG, "User login session modified!");
    }

    public void setStatus(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_STATUS, isLoggedIn);
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public void setUserId(String uid) {
        editor.putString("uid", uid);
        editor.commit();
        Log.d(TAG, "User id session modified!");
    }


    public String getUserId(){
        return pref.getString("uid",null);
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public boolean isScanning(){
        return pref.getBoolean(KEY_IS_SCANNING, false);
    }

    public boolean isStatus(){
        return pref.getBoolean(KEY_IS_STATUS, false);
    }


    public void setNama(String nama){
        editor.putString("nama", nama);
        editor.commit();
    }
    public void setEmail(String email){
        editor.putString("email", email);
        editor.commit();
    }

    public void setPhone(String phone){
        editor.putString("phone", phone);
        editor.commit();
    }

    public void setAvatar(String avatar){
        editor.putString("avatar", avatar);
        editor.commit();
    }

    public void setToken(String token){
        editor.putString("token", token);
        editor.commit();
    }

    public void setNolp(String token){
        editor.putString("nolp", token);
        editor.commit();
    }

    public void setKeyVersi(String versi){
        editor.putString(KEY_VERSI, versi);
        editor.commit();
    }

    public String getKeyVersi() {
        return pref.getString(KEY_VERSI, null);
    }

    public String getNama(){
        return pref.getString("nama",null);
    }
    public String getEmail(){
        return pref.getString("email",null);
    }

    public String getPhone(){
        return pref.getString("phone",null);
    }

    public String getAvatar(){
        return pref.getString("avatar",null);
    }

    public String getToken() {
        return pref.getString("token",null);
    }

}
