package br.got.vamosajudar.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.got.vamosajudar.R;
import br.got.vamosajudar.databinding.ActivityOngRegisterBinding;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import br.got.vamosajudar.databinding.ActivityOngRegisterBinding;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOngRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initializeScreen();

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



}





