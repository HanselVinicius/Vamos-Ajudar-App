package br.got.vamosajudar.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import br.got.vamosajudar.R;
import br.got.vamosajudar.model.user.dto.LoginResponseDTO;
import br.got.vamosajudar.model.user.token.GetCallback;
import br.got.vamosajudar.model.user.token.TokenManager;
import br.got.vamosajudar.view_model.SplashViewModel;
import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class SplashActivity  extends AppCompatActivity implements GetCallback<LoginResponseDTO> {

    private static final int SPLASH_DISPLAY_DURATION = 2000; // 2 segundos

    private SplashViewModel splashViewModel;

    private static final String TAG = "splashScreen";

    public static final String PROFILE = "PROFILE";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.splashViewModel = new ViewModelProvider(this).get(SplashViewModel.class);
        this.splashViewModel.getProfile(TokenManager.getToken(),this);
    }


    @Override
    public void getSuccess(LoginResponseDTO returned) {
        Intent mainIntent = new Intent(SplashActivity.this, OngActivity.class);
        mainIntent.putExtra(PROFILE,returned);
        startActivity(mainIntent);
    }

    @Override
    public void getFail(Throwable ex) {
        Log.e(TAG, "getFail: error on profile get ",ex);
        Intent mainIntent = new Intent(SplashActivity.this, OngActivity.class);
        startActivity(mainIntent);
    }
}
