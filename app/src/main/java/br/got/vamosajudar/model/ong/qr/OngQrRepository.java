package br.got.vamosajudar.model.ong.qr;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import br.got.vamosajudar.infra.Api;
import br.got.vamosajudar.infra.exceptions.QrCodeException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OngQrRepository {

    private final Api api;

    public static final String TAG = "OngQrRepository";

    @Inject
    public OngQrRepository(Api api) {
        this.api = api;
    }


    public void getQr(String id, MutableLiveData<QRDto> qrDtoMutableLiveData){
        Call<QRDto> callQr = this.api.getQr(id);
    try {
        callQr.enqueue(new Callback<QRDto>() {
            @Override
            public void onResponse(Call<QRDto> call, Response<QRDto> response) {
                qrDtoMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<QRDto> call, Throwable t) {
                Log.e(TAG, "onFailure: ERRO NO REQUEST DE QR ", t);
                throw new QrCodeException("HOUVE UM ERRO AO BUSCAR O QR CODE", t);
            }
        });
    }catch (QrCodeException ex){
        throw new QrCodeException("HOUVE UM ERRO AO BUSCAR O QR CODE", ex);
    }

    }


}
