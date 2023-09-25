package br.got.vamosajudar.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.got.vamosajudar.R;
import br.got.vamosajudar.databinding.ActivityOngRegisterBinding;

public class OngRegisterActivity extends AppCompatActivity {

    private ActivityOngRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOngRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

    }
}