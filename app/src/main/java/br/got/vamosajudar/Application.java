package br.got.vamosajudar;

import android.content.Context;
import android.content.SharedPreferences;

import br.got.vamosajudar.infra.observer.Publisher;
import br.got.vamosajudar.model.user.TokenManager;
import br.got.vamosajudar.model.user.UserPublisher;
import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class Application extends android.app.Application {

    private static Publisher userPublisher;

    @Override
    public void onCreate() {
        super.onCreate();
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        TokenManager.initialize(sharedPreferences);
        userPublisher = new UserPublisher();
    }


    public static Publisher getUserPublisher() {
        return userPublisher;
    }
}
