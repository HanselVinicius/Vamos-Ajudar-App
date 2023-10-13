package br.got.vamosajudar.model.ong;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import br.got.vamosajudar.infra.Api;
import br.got.vamosajudar.infra.Repository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OngRepository implements Repository {


    private static final String TAG = "OngRepository";
    private final Api api;


    private static final MutableLiveData<OngResponseList<Ong>>  ongList = new MutableLiveData<>();


    @Inject
    public OngRepository(Api api) {
        this.api = api;
    }

    public void performGetAllOngs(int page){
        Call<OngResponseList<Ong>> call = this.api.getOngs(page);
        call.enqueue(
                new Callback<>() {
                    @Override
                    public void onResponse(Call<OngResponseList<Ong>> call, Response<OngResponseList<Ong>> response) {

                        if (response.isSuccessful()){
                            ongList.postValue(response.body());
                        }else {
                            Log.e(TAG, "onFailure: ERRO NA REQUISICAO DE ONG: " +response.raw() );
                        }
                    }

                    @Override
                    public void onFailure(Call<OngResponseList<Ong>> call, Throwable t) {
                        Log.e(TAG, "onFailure: ERRO NA REQUISICAO DE ONG: ",t );
                    }
                }
        );
    }


    public void performRegisterOngs(String token,OngRegisterDTO ongRegisterDTO){

        Call<Ong> call = this.api.registerOngs("Bearer "+token,ongRegisterDTO);
        call.enqueue(
                //todo melhorar os tratamentos de erros e etc
                new Callback<>() {
                    @Override
                    public void onResponse(Call<Ong> call, Response<Ong> response) {
                        if (response.isSuccessful()) {
                            Log.d(TAG, "onResponse: ONG REGISTRADA COM SUCESSO");
                        } else {
                            Log.e(TAG, "onFailure: ERRO NA REQUISICAO DE ONG: " + response.raw());
                        }
                    }

                    @Override
                    public void onFailure(Call<Ong> call, Throwable t) {
                        //todo tratar erro
                    }
                }
        );
    }


    public MutableLiveData<OngResponseList<Ong>> getListOfOngs() {
        return ongList;
    }

    public void performDeleteOng(String token) {
        //todo melhorar os tratamentos de erros e etc

        Call<Void> call = this.api.deleteOng("Bearer "+token);
        call.enqueue(
                new Callback<>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Log.d(TAG, "onResponse: ONG DELETADA COM SUCESSO");
                        }else {
                            Log.e(TAG, "onFailure: ERRO NA REQUISICAO DE ONG: " +response.raw() );
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        //todo tratar erro
                    }
                }
        );
    }
}
