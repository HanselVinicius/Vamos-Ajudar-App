package br.got.vamosajudar.model.ong;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import br.got.vamosajudar.infra.Api;
import br.got.vamosajudar.infra.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OngRepository implements Repository {


    private static final String TAG = "OngRepository";
    private final Api api;


    private static final MutableLiveData<OngResponse<Ong>>  ongList = new MutableLiveData<>();


    @Inject
    public OngRepository(Api api) {
        this.api = api;
    }

    public void performGetAllOngs(int page){
        Call<OngResponse<Ong>> call = this.api.getOngs(page);
        call.enqueue(
                new Callback<>() {
                    @Override
                    public void onResponse(Call<OngResponse<Ong>> call, Response<OngResponse<Ong>> response) {

                        if (response.isSuccessful()){
                            ongList.postValue(response.body());
                        }else {
                            Log.e(TAG, "onFailure: ERRO NA REQUISICAO DE ONG: " +response.raw() );
                        }
                    }

                    @Override
                    public void onFailure(Call<OngResponse<Ong>> call, Throwable t) {
                        Log.e(TAG, "onFailure: ERRO NA REQUISICAO DE ONG: ",t );
                    }
                }
        );
    }


    public MutableLiveData<OngResponse<Ong>> getListOfOngs() {
        return ongList;
    }

}
