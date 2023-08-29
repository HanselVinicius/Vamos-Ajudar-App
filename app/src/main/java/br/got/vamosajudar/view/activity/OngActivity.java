package br.got.vamosajudar.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import br.got.vamosajudar.databinding.ActivityOngBinding;
import br.got.vamosajudar.model.ong.Ong;
import br.got.vamosajudar.view.components.OngAdapter;
import br.got.vamosajudar.view_model.OngActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OngActivity extends AppCompatActivity {


    private ActivityOngBinding binding;

    private RecyclerView recyclerView;
    private OngActivityViewModel viewModel;

    private SwipeRefreshLayout swipeRefreshLayout;

    private List<Ong> ongList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOngBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.viewModel = new ViewModelProvider(this).get(OngActivityViewModel.class);
        observeOngs();
        initializeScreen();
    }

    private void observeOngs(){
        this.viewModel.getAllOngs();
        viewModel.getListOfOngs().observe(this,ongs -> {
            this.ongList = ongs;
            this.recyclerView.setAdapter(new OngAdapter(this.ongList));
        });

    }



    private void initializeScreen(){
        recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.recyclerView.setAdapter(new OngAdapter(this.ongList));
        this.swipeRefreshLayout = binding.swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(()->{
            swipeAction();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void swipeAction(){
        observeOngs();
    }


}