package br.got.vamosajudar.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import br.got.vamosajudar.databinding.ActivityOngBinding;
import br.got.vamosajudar.view_model.OngActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OngActivity extends AppCompatActivity {


    private ActivityOngBinding binding;

    private RecyclerView recyclerView;
    private OngActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOngBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.viewModel = new ViewModelProvider(this).get(OngActivityViewModel.class);
        this.viewModel.getAllOngs();
        viewModel.getListOfOngs().observe(this,ongs -> {
            Log.e("TAG", "onCreate:  teste " +ongs );
        });
    }


    private void initializeScreen(){
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }




}