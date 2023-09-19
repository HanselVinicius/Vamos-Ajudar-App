package br.got.vamosajudar.view.activity;

import static br.got.vamosajudar.view.components.OngAdapter.ONG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;

import br.got.vamosajudar.R;
import br.got.vamosajudar.model.ong.Ong;

public class OngDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ong_detail);
        var it = getIntent();
        Ong ong = (Ong) it.getSerializableExtra(ONG);

    }
}