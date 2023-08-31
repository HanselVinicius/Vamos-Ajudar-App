package br.got.vamosajudar.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import br.got.vamosajudar.databinding.ActivityOngBinding;
import br.got.vamosajudar.model.ong.Ong;
import br.got.vamosajudar.model.ong.OngResponse;
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

    private OngResponse<Ong> ongResponse;

    private int currentPage;



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
        this.viewModel.getAllOngs(currentPage);
        MutableLiveData<OngResponse<Ong>> listOfOngs = viewModel.getListOfOngs();
        listOfOngs.observe(this,ongs -> {
            this.ongResponse = ongs;
            if (ongList == null) {
                this.ongList = ongs.getContent();
            }else {
                this.ongList.addAll(ongs.getContent());
            }
            this.recyclerView.setAdapter(new OngAdapter(this.ongList));
        });

    }



    private void initializeScreen(){
        recyclerView = binding.recyclerView;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        this.recyclerView.setAdapter(new OngAdapter(this.ongList));
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!ongResponse.isLast() && !ongResponse.isEmpty() ){
                    currentPage++;
                    observeOngs();
                }
//
            }
        });


        // swipe refresh
        this.swipeRefreshLayout = binding.swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(()->{
            swipeAction();
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void swipeAction(){
        this.currentPage = 0;
        observeOngs();
    }


}