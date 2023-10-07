package br.got.vamosajudar.view.components;

import static br.got.vamosajudar.view.activity.LoginActivity.USER;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.result.ActivityResultLauncher;

import com.google.android.material.navigation.NavigationView;

import br.got.vamosajudar.R;
import br.got.vamosajudar.model.user.dto.LoginResponseDTO;
import br.got.vamosajudar.view.activity.OngRegisterActivity;

public class NavHeaderMenuCreator {

    private static final String TAG = "NAVHEADERMENU";
    private NavigationView navigationView;
    private final Menu menu;
    private final Context context;
    private ActivityResultLauncher<Intent> launcher;

    public NavHeaderMenuCreator(NavigationView navigationView, Context context, ActivityResultLauncher<Intent> launcher) {
        this.navigationView = navigationView;
        this.menu = navigationView.getMenu();
        this.context = context;
        this.launcher = launcher;
    }


    public void initializeItems(LoginResponseDTO user){
        MenuItem createOngItem = menu.findItem(R.id.createOng);
        createOngItem.setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(this.context, OngRegisterActivity.class);
            intent.putExtra(USER,user);
            launcher.launch(intent);
            return true;
        });


        MenuItem logOutItem = menu.findItem(R.id.logOut);
        logOutItem.setOnMenuItemClickListener(item -> {
            return true;
        });
    }
}
