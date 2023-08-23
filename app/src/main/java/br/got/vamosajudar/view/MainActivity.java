package br.got.vamosajudar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Button;

import javax.inject.Inject;

import br.got.vamosajudar.R;
import br.got.vamosajudar.databinding.ActivityMainBinding;
import br.got.vamosajudar.view_model.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    public MainActivityViewModel viewModel;
    private Button btn_login;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
    }
}