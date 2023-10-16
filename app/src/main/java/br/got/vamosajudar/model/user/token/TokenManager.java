package br.got.vamosajudar.model.user.token;

import android.content.SharedPreferences;

public class TokenManager {
    private static SharedPreferences sharedPreferences;
    public static final String TOKEN_KEY = "token";


    public static void initialize(SharedPreferences preferences){
        sharedPreferences = preferences;
    }


    public static void saveToken(String token){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY,token);
        editor.apply();
    }

    public static String getToken(){
        return sharedPreferences.getString(TOKEN_KEY, null);
    }


    public static void invalidateToken(){sharedPreferences.edit().putString(TOKEN_KEY,null).apply();}
}
