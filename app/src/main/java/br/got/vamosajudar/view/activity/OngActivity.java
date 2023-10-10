package br.got.vamosajudar.view.activity;

import static br.got.vamosajudar.view.activity.LoginActivity.REQUST_CODE_LOGIN_VALUE;
import static br.got.vamosajudar.view.activity.LoginActivity.USER;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.got.vamosajudar.R;
import br.got.vamosajudar.databinding.ActivityOngBinding;
import br.got.vamosajudar.model.ong.Ong;
import br.got.vamosajudar.model.ong.OngResponse;
import br.got.vamosajudar.model.user.dto.LoginResponseDTO;
import br.got.vamosajudar.view.components.NavHeaderMenuCreator;
import br.got.vamosajudar.view.components.OngAdapter;
import br.got.vamosajudar.view_model.OngActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OngActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "ONGACTIVITY";
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

    private Toolbar toolbar;

    private LoginResponseDTO user;

    private  NavigationView navigationView;

    private View headerView;

    public static final String REQUEST_CODE = "REQUEST_CODE";


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
        this.toolbar = binding.myToolbar;
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


    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                int resultCode = result.getResultCode();
                Intent data = result.getData();

                if (resultCode == RESULT_OK && data != null) {
                    int requestCode = data.getIntExtra(REQUEST_CODE,-1);
                    switch (requestCode){
                        case REQUST_CODE_LOGIN_VALUE -> {
                            this.user = (LoginResponseDTO) data.getSerializableExtra(USER);
                            updateInterfaceOnToken(user);
                        }
                    }

                }
            }
    );



    private void updateInterfaceOnToken(LoginResponseDTO user){
        navigationView.setVisibility(View.VISIBLE);
        ImageView headerImage = headerView.findViewById(R.id.profile_image);
        TextView headerUsrName = headerView.findViewById(R.id.header_usr_name);
        new NavHeaderMenuCreator(navigationView,this,launcher).initializeItems(user);
        headerUsrName.setText(user.getLogin());
        this.user_icon.setOnClickListener(
                v->{}
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return super.onCreateOptionsMenu(menu);
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
        initDrawer(drawerLayout);
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

    private void initDrawer(DrawerLayout drawerLayout) {
        navigationView = binding.navView;
        headerView = navigationView.getHeaderView(0);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.nav_open,R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
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




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}