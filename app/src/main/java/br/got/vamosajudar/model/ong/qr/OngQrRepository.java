package br.got.vamosajudar.model.ong.qr;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import br.got.vamosajudar.infra.Api;
import br.got.vamosajudar.infra.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OngQrRepository implements Repository {

    private final Api api;

    @Inject
    public OngQrRepository(Api api) {
        this.api = api;
    }


    public void getQr(String id, MutableLiveData<QRDto> qrDtoMutableLiveData){
        Call<QRDto> callQr = this.api.getQr(id);



        callQr.enqueue(new Callback<QRDto>() {
            @Override
            public void onResponse(Call<QRDto> call, Response<QRDto> response) {
                qrDtoMutableLiveData.postValue(response.body());
            }

            @Override
            public void onFailure(Call<QRDto> call, Throwable t) {

            }
        });

    }


}
