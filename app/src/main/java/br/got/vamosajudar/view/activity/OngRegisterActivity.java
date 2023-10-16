package br.got.vamosajudar.view.activity;

import static br.got.vamosajudar.view.activity.OngActivity.REQUEST_CODE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import br.got.vamosajudar.databinding.ActivityOngRegisterBinding;

import br.got.vamosajudar.infra.validator.Validator;
import br.got.vamosajudar.infra.validator.validators.MaxCharsValidation;
import br.got.vamosajudar.infra.validator.validators.NotEmptyValidation;
import br.got.vamosajudar.model.ong.Address;
import br.got.vamosajudar.model.ong.Contact;
import br.got.vamosajudar.model.ong.OngRegisterDTO;
import br.got.vamosajudar.utils.Utils;
import br.got.vamosajudar.view.components.ImagePicker;

import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class OngRegisterActivity extends AppCompatActivity {

    private ActivityOngRegisterBinding binding;
    private EditText ongNameEditText;

    private EditText ongDescriptionEditText;
    private EditText ongGoalEditText;
    private EditText ongStreetEditText;

    private EditText postalCodeEditText;

    private EditText countryEditText;

    private EditText estadoEditText;

    private EditText cityEditText;

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

    public static final String CREATED_ONG = "CREATED_ONG";


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
        NotEmptyValidation notEmptyValidation = new NotEmptyValidation();
        validatorHashMap.put(ongNameEditText, notEmptyValidation);
        validatorHashMap.put(ongDescriptionEditText, notEmptyValidation);
        validatorHashMap.put(ongGoalEditText, notEmptyValidation);
        validatorHashMap.put(ongStreetEditText, notEmptyValidation);
        validatorHashMap.put(postalCodeEditText, notEmptyValidation);
        validatorHashMap.put(estadoEditText, notEmptyValidation);
        validatorHashMap.put(emailEditText, notEmptyValidation);
        validatorHashMap.put(phoneEditText, notEmptyValidation);
        validatorHashMap.put(websiteEditText, notEmptyValidation);
        validatorHashMap.put(ongNumberAddressEditText, notEmptyValidation);
        validatorHashMap.put(pixEditText, notEmptyValidation);
        validatorHashMap.put(cityEditText, notEmptyValidation);
        validatorHashMap.put(countryEditText, notEmptyValidation);
        validatorHashMap.put(estadoEditText,new MaxCharsValidation(2));
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
        cityEditText = binding.cidadeId;
        countryEditText = binding.paisId;

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
            if(!Utils.isNetworkConnected(this)){
                isValid = false;
                Snackbar.make(binding.ongRegisterActivity,"POR FAVOR CONECTE-SE A INTERNET",Snackbar.LENGTH_LONG).show();
            }

            if (this.imageBase64 == null){
                isValid = false;
                Snackbar.make(binding.ongRegisterActivity,"POR FAVOR INSIRA UMA IMAGEM",Snackbar.LENGTH_LONG).show();
            }
            if (isValid){
                var intent = new Intent(OngRegisterActivity.this,OngActivity.class);
                intent.putExtra(REQUEST_CODE,REQUEST_CODE_REGISTER_ONG);
                intent.putExtra(CREATED_ONG, getOnRegistered());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
        cancelCreationButton.setOnClickListener(view -> finish());
        imagePicker.setOnClickListener(view -> new ImagePicker(this).openGallery());
    }

    private OngRegisterDTO getOnRegistered() {
        return new OngRegisterDTO(this.ongNameEditText.getText().toString(),
                this.ongDescriptionEditText.getText().toString(),
                this.ongGoalEditText.getText().toString(),
                this.getAddress(),
                this.getContact(),
                Utils.compressImageToBase64(this,this.imageBase64),
                this.pixEditText.getText().toString());
    }

    private Address getAddress(){
        return new Address(this.ongStreetEditText.getText().toString(),
                this.ongNumberAddressEditText.getText().toString(),
                this.cityEditText.getText().toString(),
                this.estadoEditText.getText().toString(),
                this.postalCodeEditText.getText().toString(),
                this.countryEditText.getText().toString());
    }

    private Contact getContact(){
        return new Contact(this.emailEditText.getText().toString(),
                this.phoneEditText.getText().toString(),
                this.websiteEditText.getText().toString());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null ){
            this.imageBase64 = data.getData();
        }
    }
}





