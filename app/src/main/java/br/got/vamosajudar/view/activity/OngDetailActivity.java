package br.got.vamosajudar.view.activity;

import static br.got.vamosajudar.view.components.OngAdapter.ONG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;


import com.squareup.picasso.Picasso;

import br.got.vamosajudar.databinding.ActivityOngDetailBinding;
import br.got.vamosajudar.model.ong.Ong;
import br.got.vamosajudar.model.ong.qr.QRDto;
import br.got.vamosajudar.view_model.DetailActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OngDetailActivity extends AppCompatActivity {


    private DetailActivityViewModel detailActivityViewModel;

    private ActivityOngDetailBinding binding;

    private final static String TAG = "ONGDETAILACT";

    private Ong ong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOngDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.detailActivityViewModel = new ViewModelProvider(this).get(DetailActivityViewModel.class);
        var it = getIntent();
        ong = (Ong) it.getSerializableExtra(ONG);
        initializeFields();
    }


    private void initializeFields(){
        Picasso.get()
                .load(ong.getImage())
                .into(binding.imageView);
        binding.nameId.setText( ong.getName());
        binding.descricaoId.setText(ong.getDescription());
        binding.objetivoId.setText(ong.getGoal());
        binding.ruaId.setText(ong.getAddress().getStreet());
        binding.cidadeId.setText(ong.getAddress().getCity());
        binding.paisId.setText(ong.getAddress().getCountry());
        binding.emailId.setText(ong.getContact().getEmail());
        binding.telefoneId.setText(ong.getContact().getPhone());
        binding.siteId.setText(ong.getContact().getWebsite());

    }





    private void getQrAndBrCode(){
//        LiveData<QRDto> qr = this.detailActivityViewModel.getQr(ong.getId());
//        qr.observe(this, data -> qr );
    }

}