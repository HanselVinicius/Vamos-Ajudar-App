package br.got.vamosajudar.model.user;

import android.content.SharedPreferences;

public class TokenManager {
    private static SharedPreferences sharedPreferences;
    private static final String TOKEN_KEY = "token";


    public static void initialize(SharedPreferences preferences){
        sharedPreferences = preferences;
    }


    public static void saveToken(String token){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY,token);
        //todo algo para esperar o salvamento do shared, no momento temos que clicar duas vezes no login para avançar
        editor.apply();
    }

    public static String getToken(){
        return sharedPreferences.getString(TOKEN_KEY, null);
    }

}
