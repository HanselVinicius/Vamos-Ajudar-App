package br.got.vamosajudar.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.got.vamosajudar.R;
import br.got.vamosajudar.databinding.ActivityOngRegisterBinding;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import br.got.vamosajudar.databinding.ActivityOngRegisterBinding;
import br.got.vamosajudar.infra.validator.Validator;
import br.got.vamosajudar.infra.validator.validators.NotEmptyValidation;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

    private String imageBase64;

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

        //buttons
        confirmCreationButton = binding.confirmCreationBtn;
        cancelCreationButton = binding.cancelCreationBtn;
    }



    private void onClicks(){
        initValidatonMap();
        confirmCreationButton.setOnClickListener(view ->{
            boolean isValid = true;
            for (Map.Entry<EditText, Validator> entry : validatorHashMap.entrySet()) {
            if (!entry.getValue().validate(entry.getKey())){
                entry.getKey().setText(R.string.dado_invalido);
                isValid = false;
            }
        }
            if (isValid){
                //monta dto coloca na intent e faz o finish ja fazendo a requisicao na tela de ongs
            }

        });

        cancelCreationButton.setOnClickListener(view -> {
            finish();
        });

        imagePicker.setOnClickListener(view -> {
            
        });

    }



}





