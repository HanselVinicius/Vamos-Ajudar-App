package br.got.vamosajudar.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import javax.inject.Inject;

import br.got.vamosajudar.R;
import br.got.vamosajudar.view_model.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    public MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        this.viewModel.getAllOngs();
    }
}