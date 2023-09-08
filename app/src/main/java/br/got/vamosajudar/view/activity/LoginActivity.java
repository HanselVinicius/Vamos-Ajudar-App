package br.got.vamosajudar.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

import br.got.vamosajudar.R;
import br.got.vamosajudar.databinding.ActivityLoginBinding;
import br.got.vamosajudar.infra.exceptions.DadosInvalidosException;
import br.got.vamosajudar.infra.observer.Subscriber;
import br.got.vamosajudar.model.user.token.TokenManager;
import br.got.vamosajudar.view_model.LoginActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity implements Subscriber {

    public LoginActivityViewModel viewModel;
    private Button btn_login;
    private Button btn_register;
    private EditText edt_username;
    private EditText edt_password;
    private ActivityLoginBinding binding;
    public static final String TOKEN = "TOKEN";

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        initializeFields();
        onClicks();
        setContentView(binding.getRoot());
        this.viewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
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
        btn_register.setOnClickListener(l->register());
    }

    private void register() {
        try {
            dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_register_user);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            Button btnCancel = dialog.findViewById(R.id.btn_cancel_user);
            Button btnRegister = dialog.findViewById(R.id.btn_register_user);
            EditText txtName = dialog.findViewById(R.id.edit_text_name_user_register);
            EditText txtEmail = dialog.findViewById(R.id.edt_txt_email_user);
            EditText txtUsername = dialog.findViewById(R.id.edt_txt_username);
            EditText txtPassword = dialog.findViewById(R.id.edit_txt_password_register);

            btnCancel.setOnClickListener(l -> {
                dialog.cancel();
            });

            btnRegister.setOnClickListener(l -> {
                this.viewModel.executeRegister(
                        txtUsername.getText().toString(),
                        txtEmail.getText().toString(),
                        txtPassword.getText().toString(),
                        txtName.getText().toString(),
                        this);
            });

            dialog.show();
        }catch (DadosInvalidosException ex){
            Snackbar.make(binding.loginScreen,"POR FAVOR PREENCHA OS DADOS",Snackbar.LENGTH_LONG).show();

        }
    }



    private void initializeFields(){
        this.btn_login = binding.buttonLogin;
        this.btn_register = binding.buttonRegister;
        this.edt_username = binding.editTextUsername;
        this.edt_password = binding.editTextPassword;
    }

    @Override
    public void update(){
        String token = TokenManager.getToken();
        if ( token != null){
            var it = new Intent(LoginActivity.this,OngActivity.class);
            it.putExtra(TOKEN,TokenManager.getToken());
            setResult(RESULT_OK,it);
            finish();
        }
        if (dialog != null){
            dialog.cancel();
            Snackbar.make(binding.loginScreen,"REGISTRO EFETUADO COM SUCESSO",Snackbar.LENGTH_LONG).show();
        }
    }
}