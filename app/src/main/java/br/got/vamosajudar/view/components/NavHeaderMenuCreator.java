package br.got.vamosajudar.view.components;

import static br.got.vamosajudar.view.activity.LoginActivity.USER;
import static br.got.vamosajudar.view.activity.OngDetailActivity.IS_FROM_USER;
import static br.got.vamosajudar.view.components.OngAdapter.ONG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import br.got.vamosajudar.R;
import br.got.vamosajudar.model.ong.Ong;
import br.got.vamosajudar.model.user.dto.LoginResponseDTO;
import br.got.vamosajudar.view.activity.OngDetailActivity;
import br.got.vamosajudar.view.activity.OngRegisterActivity;

public class NavHeaderMenuCreator {

    private static final String TAG = "NAVHEADERMENU";
    private NavigationView navigationView;
    private final Menu menu;
    private final Activity context;
    private ActivityResultLauncher<Intent> launcher;

    private MenuItem myOngItem;

    public NavHeaderMenuCreator(NavigationView navigationView, Activity context, ActivityResultLauncher<Intent> launcher) {
        this.navigationView = navigationView;
        this.menu = navigationView.getMenu();
        this.context = context;
        this.launcher = launcher;
    }


    public void initializeItems(LoginResponseDTO user){
        MenuItem createOngItem = menu.findItem(R.id.createOng);
        createOngItem.setOnMenuItemClickListener(item -> {
            Intent intent = new Intent(this.context.getApplicationContext(), OngRegisterActivity.class);
            intent.putExtra(USER,user);
            launcher.launch(intent);
            return true;
        });

        myOngItem = menu.findItem(R.id.ong);
        myOngItem.setTitle(user.getOng() != null ? user.getOng().getName() : "SEM ONG CADASTRADA");
        myOngItem.setOnMenuItemClickListener(item -> {
            if(user.getOng() == null){
                return false;
            }
            Intent intent = new Intent(this.context.getApplicationContext(), OngDetailActivity.class);
            intent.putExtra(IS_FROM_USER,true);
            intent.putExtra(ONG,new Ong(user.getOng()));
            launcher.launch(intent);
            return true;
        });



        MenuItem logOutItem = menu.findItem(R.id.logOut);
        logOutItem.setOnMenuItemClickListener(item -> {
            this.context.finishAffinity();
            return true;
        });
    }



}
