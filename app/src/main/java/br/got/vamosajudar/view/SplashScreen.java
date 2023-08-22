package br.got.vamosajudar.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import br.got.vamosajudar.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(
                () -> {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
                }
                ,5000 );
    }


}
