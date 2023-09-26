package br.got.vamosajudar.view.activity;

import static br.got.vamosajudar.view.activity.LoginActivity.TOKEN;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.got.vamosajudar.R;
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

    //todo alterar talvez para um Set
    private List<Ong> ongList;

    private OngResponse<Ong> ongResponse;

    private int currentPage;

    private LinearLayoutManager linearLayoutManager;

    private ImageView user_icon;

    private OngAdapter ongAdapter;

    private DrawerLayout drawerLayout;

    private ActionBarDrawerToggle actionBarDrawerToggle;
    public static final String INTENT_TOKEN_ONG_REGISTER_ACTIVITY = "INTENT_TOKEN_REGISTER_ONG_ACTIVITY";

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
                ongAdapter = new OngAdapter(this.ongList, getApplicationContext());
                recyclerView.setAdapter(ongAdapter);
            }else {
                //todo aplicar melhoes praticas para atualizar a tela
                ongAdapter.notifyDataSetChanged();
            }
        });

    }


    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                int resultCode = result.getResultCode();
                Intent data = result.getData();

                if (resultCode == RESULT_OK && data != null) {
                    String token = data.getStringExtra(TOKEN);
                    updateInterfaceOnToken(token);
                }
            }
    );

    private void updateInterfaceOnToken(String token){
        this.user_icon.setOnClickListener(
                v->{
//                    drawerLayout.openDrawer(GravityCompat.START);
                    Intent intent = new Intent(OngActivity.this, OngRegisterActivity.class);
                    intent.putExtra(INTENT_TOKEN_ONG_REGISTER_ACTIVITY,token);
                    startActivity(intent);
                }
        );
    }

    private void initializeScreen(){

        //user icon
        this.user_icon.setOnClickListener(
                v -> {
                    Intent mainIntent = new Intent(OngActivity.this, LoginActivity.class);
                    launcher.launch(mainIntent);
                }
        );

        //drawer layout
        this.drawerLayout = binding.navigationDrawer;

        //recyvler view
        this.recyclerView.setAdapter(new OngAdapter(this.ongList, getApplicationContext()));
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