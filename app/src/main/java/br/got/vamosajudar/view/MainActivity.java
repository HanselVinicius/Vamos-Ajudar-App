package br.got.vamosajudar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import javax.inject.Inject;

import br.got.vamosajudar.Application;
import br.got.vamosajudar.R;
import br.got.vamosajudar.databinding.ActivityMainBinding;
import br.got.vamosajudar.infra.exceptions.DadosInvalidosException;
import br.got.vamosajudar.infra.exceptions.LoginException;
import br.got.vamosajudar.infra.observer.Subscriber;
import br.got.vamosajudar.model.user.TokenManager;
import br.got.vamosajudar.view_model.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity implements Subscriber {

    public MainActivityViewModel viewModel;
    private Button btn_login;
    private Button btn_register;
    private Button btn_without_login;
    private EditText edt_username;
    private EditText edt_password;
    private ActivityMainBinding binding;
    private static final String TOKEN = "TOKEN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        initializeFields();
        onClicks();
        setContentView(binding.getRoot());
        this.viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }




    private void login(){
        try {
            this.viewModel.executeLogin(this.edt_username.getText().toString(), this.edt_password.getText().toString(), this);
        }catch (DadosInvalidosException ex){
            Snackbar.make(binding.loginScreen,"POR FAVOR PREENCHA OS DADOS",Snackbar.LENGTH_LONG).show();
        }
    }


    private void onClicks(){
        btn_login.setOnClickListener(l-> login());
    }


    private void initializeFields(){
        this.btn_login = binding.buttonLogin;
        this.btn_register = binding.buttonRegister;
        this.btn_without_login = binding.buttonSemLogin;
        this.edt_username = binding.editTextUsername;
        this.edt_password = binding.editTextPassword;
    }

    @Override
    public void update(){
        String token = TokenManager.getToken();
        if ( token != null){
            var it = new Intent(MainActivity.this,OngActivity.class);
            it.putExtra(TOKEN,TokenManager.getToken());
            startActivity(it);
            finish();
        }
    }
}