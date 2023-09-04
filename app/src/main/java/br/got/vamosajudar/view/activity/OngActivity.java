package br.got.vamosajudar.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    private LinearLayoutManager linearLayoutManager;

    private ImageView user_icon;

    private OngAdapter ongAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOngBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.viewModel = new ViewModelProvider(this).get(OngActivityViewModel.class);
        this.ongList = new ArrayList<>();
        this.recyclerView = binding.recyclerView;
        this.linearLayoutManager = new LinearLayoutManager(this);
        this.user_icon = binding.userIcon;
        recyclerView.setLayoutManager(linearLayoutManager);
        observeOngs();
        initializeScreen();
    }

    private void observeOngs(){
        this.viewModel.getAllOngs(currentPage);
        MutableLiveData<OngResponse<Ong>> listOfOngs = viewModel.getListOfOngs();
        listOfOngs.observe(this,ongs -> {
            this.ongResponse = ongs;
            this.ongList.addAll(ongs.getContent().stream().distinct().collect(Collectors.toList()));
            if (ongAdapter == null) {
                ongAdapter = new OngAdapter(this.ongList);
                recyclerView.setAdapter(ongAdapter);
            }else {
                //todo aplicar melhoes praticas para atualizar a tela
                ongAdapter.notifyDataSetChanged();
            }
        });

    }



    private void initializeScreen(){

        //user icon
        this.user_icon.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mainIntent = new Intent(OngActivity.this, LoginActivity.class);
                        startActivity(mainIntent);
                    }
                }
        );


        //recyvler view
        this.recyclerView.setAdapter(new OngAdapter(this.ongList));
        this.recyclerView.addOnScrollListener(onScroll());


        // swipe refresh
        this.swipeRefreshLayout = binding.swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(()->{
            swipeAction();
            swipeRefreshLayout.setRefreshing(false);
        });
    }


    private RecyclerView.OnScrollListener onScroll(){
      return  new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int visibleItemCount = linearLayoutManager.getChildCount();
                int totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();


                int loadThreshold = 5; // Por exemplo, inicie o carregamento quando faltar 5 itens para o final

                if (dy > 0 && (visibleItemCount + firstVisibleItemPosition) >= (totalItemCount - loadThreshold)) {
                    // Estamos nos aproximando do final, então inicie o carregamento de mais itens
                    if (!ongResponse.isLast() && !ongResponse.isEmpty()) {
                        currentPage++;
                        viewModel.getAllOngs(currentPage);

                    }
                }
            }
        };
    }


    private void swipeAction(){
        this.ongList.clear();
        this.recyclerView.getAdapter().notifyDataSetChanged();
        this.currentPage = 0;
        this.viewModel.getAllOngs(0);
    }


}