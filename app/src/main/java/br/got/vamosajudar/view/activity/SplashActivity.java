package br.got.vamosajudar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.got.vamosajudar.R;

public class SplashActivity  extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_DURATION = 2000; // 2 segundos


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(SplashActivity.this, OngActivity.class);
            //posteriormente quero fazer algo pra pegar o token (sharedPreferences) que ja possui implementado
            // e mandar para atividade principal (OngActivity)
            startActivity(mainIntent);

            finish();
        }, SPLASH_DISPLAY_DURATION);
    }
}
