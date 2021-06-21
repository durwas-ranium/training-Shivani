package com.testing.mvvm_java;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    public final static String KEY_FOR_IS_LOGIN = "isLogin";
    public final static String KEY_FOR_ACCESS_TOKEN = "accessToken";
    public static final String KEY_FOR_PREFERENCES = "LOGIN_CREDENTIAL";

    public static SessionManager sessionManager;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(KEY_FOR_PREFERENCES,Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static SessionManager getInstance(Context context) {

        if (sessionManager == null) {
            sessionManager = new SessionManager(context);
        }
        return sessionManager;
    }

    public void createLoginSession(String accessToken) {

        editor.putBoolean(KEY_FOR_IS_LOGIN,true);
        editor.putString(KEY_FOR_ACCESS_TOKEN, accessToken);

        editor.commit();
    }

    public String getAccessToken() {
        return sharedPreferences.getString(KEY_FOR_ACCESS_TOKEN, null);
    }
}
