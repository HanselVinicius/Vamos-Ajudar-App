package br.got.vamosajudar.view.activity;

import static br.got.vamosajudar.view.components.OngAdapter.ONG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.Base64;

import br.got.vamosajudar.R;
import br.got.vamosajudar.databinding.ActivityOngDetailBinding;
import br.got.vamosajudar.infra.exceptions.QrCodeException;
import br.got.vamosajudar.model.ong.Ong;
import br.got.vamosajudar.model.ong.qr.QRDto;
import br.got.vamosajudar.utils.Utils;
import br.got.vamosajudar.view_model.DetailActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OngDetailActivity extends AppCompatActivity {


    private DetailActivityViewModel detailActivityViewModel;

    private ActivityOngDetailBinding binding;

    private String qrCode;

    private String brCode;

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
        if (Utils.isNetworkConnected(this)) {
            getQrAndBrCode();
        }else {
            Snackbar.make(binding.detailScreen, "POR FAVOR CONECTE-SE A INTERNET",Snackbar.LENGTH_SHORT).show();
        }
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


        binding.donateButtonId.setOnClickListener(v-> dialogInitialize());

    }

    private void dialogInitialize(){
        try {
            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_donate);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            TextView txtBrCode = dialog.findViewById(R.id.txt_br_code);
            ImageView imageQrCode = dialog.findViewById(R.id.qr_code_img);
            txtBrCode.setText(this.brCode);
            txtBrCode.setOnClickListener(v -> {
                var clipboard = (ClipboardManager) getApplicationContext().getSystemService(Context.CLIPBOARD_SERVICE);
                var clip = ClipData.newPlainText("Texto Copiado", txtBrCode.getText());
                clipboard.setPrimaryClip(clip);
                Snackbar.make(binding.detailScreen, "Texto Copiado", Snackbar.LENGTH_SHORT).show();
            });
            byte[] decodedImage = Base64.getDecoder().decode(this.qrCode);
            Bitmap decodedBitmap = BitmapFactory.decodeByteArray(decodedImage, 0, decodedImage.length);
            imageQrCode.setImageBitmap(decodedBitmap);
            dialog.show();
        }catch (NullPointerException ex){
            Log.e(TAG, "dialogInitialize: ERRO AO MOSTRAR A DIALOG ", ex );
            Snackbar.make(binding.detailScreen, "POR FAVOR CONECTE-SE A INTERNET",Snackbar.LENGTH_SHORT ).show();
        }
    }




    private void getQrAndBrCode() {
        try {
            LiveData<QRDto> qr = this.detailActivityViewModel.getQr(ong.getId());
            qr.observe(this, data -> {
                Log.e(TAG, "getQrAndBrCode: dto " + data);
                if (data != null) {
                    this.qrCode = data.getQrcode64();
                    this.brCode = data.getBrcode().toString();
                } else {
                    binding.donateButtonId.setOnClickListener(v -> {
                        Snackbar.make(binding.detailScreen, "ONG NÃO POSSUI METODO DE PAGAMENTO CADASTRADO",
                                Snackbar.LENGTH_LONG).show();
                    });
                }
            });
        }catch (QrCodeException | NullPointerException ex){
            Snackbar.make(binding.detailScreen,ex.getMessage(),Snackbar.LENGTH_SHORT).show();
        }
    }

}