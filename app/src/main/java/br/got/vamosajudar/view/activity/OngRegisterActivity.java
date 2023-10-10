package br.got.vamosajudar.view.activity;

import static br.got.vamosajudar.view.activity.OngActivity.REQUEST_CODE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import br.got.vamosajudar.R;
import br.got.vamosajudar.databinding.ActivityOngRegisterBinding;

import br.got.vamosajudar.infra.validator.Validator;
import br.got.vamosajudar.infra.validator.validators.NotEmptyValidation;
import br.got.vamosajudar.view.components.ImagePicker;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;

import java.util.HashMap;
import java.util.Map;

public class OngRegisterActivity extends AppCompatActivity {

    private ActivityOngRegisterBinding binding;
    private EditText ongNameEditText;

    private EditText ongDescriptionEditText;
    private EditText ongGoalEditText;
    private EditText ongStreetEditText;

    private EditText postalCodeEditText;

    private EditText estadoEditText;

    private EditText emailEditText;

    private EditText phoneEditText;

    private EditText websiteEditText;

    private EditText ongNumberAddressEditText;

    private Button confirmCreationButton, cancelCreationButton;

    private HashMap<EditText, Validator> validatorHashMap;

    private ImageView imagePicker;

    private Uri imageBase64;

    private EditText pixEditText;

    public static final int PICK_IMAGE_REQUEST = 69;

    public static final int REQUEST_CODE_REGISTER_ONG = 20;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOngRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeScreen();
        onClicks();
    }

    private void initValidatonMap(){
        this.validatorHashMap = new HashMap<>();
        validatorHashMap.put(ongNameEditText,new NotEmptyValidation());
        validatorHashMap.put(ongDescriptionEditText,new NotEmptyValidation());
        validatorHashMap.put(ongGoalEditText,new NotEmptyValidation());
        validatorHashMap.put(ongStreetEditText,new NotEmptyValidation());
        validatorHashMap.put(postalCodeEditText,new NotEmptyValidation());
        validatorHashMap.put(estadoEditText,new NotEmptyValidation());
        validatorHashMap.put(emailEditText,new NotEmptyValidation());
        validatorHashMap.put(phoneEditText,new NotEmptyValidation());
        validatorHashMap.put(websiteEditText,new NotEmptyValidation());
        validatorHashMap.put(ongNumberAddressEditText,new NotEmptyValidation());
        validatorHashMap.put(pixEditText,new NotEmptyValidation());
    }



    private void initializeScreen(){
        //edit texts
        ongNameEditText = binding.ongNameEditText;
        ongDescriptionEditText = binding.ongDescriptionEditText;
        ongGoalEditText = binding.ongGoalEditText;
        ongStreetEditText = binding.ongStreet;
        ongNumberAddressEditText = binding.ongNumberAddresss;
        postalCodeEditText = binding.postalCode;
        estadoEditText = binding.estado;
        emailEditText = binding.emailId;
        phoneEditText = binding.phoneId;
        websiteEditText = binding.websiteId;
        pixEditText = binding.pixId;

        //buttons
        confirmCreationButton = binding.confirmCreationBtn;
        cancelCreationButton = binding.cancelCreationBtn;
        imagePicker = binding.addPhotoRegisterOng;
    }



    private void onClicks(){
        initValidatonMap();
        confirmCreationButton.setOnClickListener(view ->{
            boolean isValid = true;
            for (Map.Entry<EditText, Validator> entry : validatorHashMap.entrySet()) {
            if (!entry.getValue().validate(entry.getKey())){
                entry.getKey().setError("DADO INVÁLIDO");
                isValid = false;
            }
        }
            if (this.imageBase64 == null){
                isValid = false;
            }


            if (isValid){
                var intent = new Intent(OngRegisterActivity.this,OngActivity.class);
                intent.putExtra(REQUEST_CODE,REQUEST_CODE_REGISTER_ONG);

                //monta dto coloca na intent e faz o finish ja fazendo a requisicao na tela de ongs
            }

        });

        cancelCreationButton.setOnClickListener(view -> {
            finish();
        });

        imagePicker.setOnClickListener(view -> {
            new ImagePicker(this).openGallery();
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null ){
            this.imageBase64 = data.getData();
        }
    }
}





