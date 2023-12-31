package br.got.vamosajudar.view.activity;

import static br.got.vamosajudar.view.activity.OngActivity.REQUEST_CODE;
import static br.got.vamosajudar.view.activity.OngRegisterActivity.PICK_IMAGE_REQUEST;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import br.got.vamosajudar.R;
import br.got.vamosajudar.databinding.ActivityLoginBinding;
import br.got.vamosajudar.infra.exceptions.DadosInvalidosException;
import br.got.vamosajudar.infra.observer.Subscriber;
import br.got.vamosajudar.model.user.dto.LoginResponseDTO;
import br.got.vamosajudar.utils.Utils;
import br.got.vamosajudar.view.components.ImagePicker;
import br.got.vamosajudar.view_model.LoginActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginActivity extends AppCompatActivity implements Subscriber {

    public static final String PROFILE = "PROFILE";
    public static final int REQUST_CODE_LOGIN_VALUE = 10;
    public LoginActivityViewModel viewModel;
    private Button btn_login;
    private Button btn_register;
    private EditText edt_username;
    private EditText edt_password;
    private ActivityLoginBinding binding;
    public static final String USER = "USER";

    private Dialog dialog;

    private MutableLiveData<LoginResponseDTO> loginResponseLiveData;

    private Uri imageBase64 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        initializeFields();
        onClicks();
        setContentView(binding.getRoot());
        this.viewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);
        this.loginResponseLiveData = new MutableLiveData<>();
    }


    private void login(){
        try {
            if(Utils.isNetworkConnected(this)) {
             this.viewModel.executeLogin(this.edt_username.getText().toString(), this.edt_password.getText().toString(), this,loginResponseLiveData);
            }else {
                Snackbar.make(binding.loginScreen,"POR FAVOR CONECTE-SE A INTERNET",Snackbar.LENGTH_LONG).show();
            }
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
            ImageView imagePicker = dialog.findViewById(R.id.pick_image_register_user);
            imagePicker.setOnClickListener(l-> new ImagePicker(this).openGallery());

            btnCancel.setOnClickListener(l -> dialog.cancel());

            btnRegister.setOnClickListener(l -> {
                if(Utils.isNetworkConnected(this)) {
                    this.viewModel.executeRegister(
                            txtUsername.getText().toString(),
                            txtEmail.getText().toString(),
                            txtPassword.getText().toString(),
                            txtName.getText().toString(),
                            Utils.compressImageToBase64(this, this.imageBase64),
                            this);
                }else {
                    Snackbar.make(binding.loginScreen,"POR FAVOR CONECTE-SE A INTERNET",Snackbar.LENGTH_LONG).show();
                }
            });

            dialog.show();
        }catch (DadosInvalidosException ex){
            Snackbar.make(binding.loginScreen,"POR FAVOR PREENCHA OS DADOS",Snackbar.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null ){
            this.imageBase64 = data.getData();
        }
    }

    private void initializeFields(){
        this.btn_login = binding.buttonLogin;
        this.btn_register = binding.buttonRegister;
        this.edt_username = binding.editTextUsername;
        this.edt_password = binding.editTextPassword;
    }

    /** de fato o uso de callback e um observable aqui é muito overkill mas como se trata de um projeto de estudo para a faculdade, porque não?
     mas tendo sempre em vista que fazer uma infraestrutura toda dessa para o contexto atual é complexidade desnecessaria
     "an idiot admires complexity a genius admires simplicity"
     */
    @Override
    public void update(){
        this.loginResponseLiveData.observe(this,
                loginResponseDTO -> {
                if (loginResponseDTO != null){
                    var it = new Intent(LoginActivity.this,OngActivity.class);
                    it.putExtra(USER,loginResponseDTO);
                    it.putExtra(REQUEST_CODE,REQUST_CODE_LOGIN_VALUE);
                    setResult(RESULT_OK,it);
                    finish();
                }
        });
        if (dialog != null){
            dialog.cancel();
            Snackbar.make(binding.loginScreen,"REGISTRO EFETUADO COM SUCESSO",Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void updateOnError(Exception ex) {
        //uma bazuca pra matar uma barata de fato...
        Snackbar.make(binding.loginScreen,"USUÁRIO NÃO ENCONTRADO",Snackbar.LENGTH_LONG).show();

    }
}