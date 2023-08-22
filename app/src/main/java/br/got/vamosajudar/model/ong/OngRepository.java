package br.got.vamosajudar.model.ong;

import android.util.Log;

import javax.inject.Inject;

import br.got.vamosajudar.infra.Api;
import br.got.vamosajudar.infra.Repository;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class OngRepository implements Repository {


    private static final String TAG = "OngRepository";
    private final Api api;


    @Inject
    public OngRepository(Api api) {
        this.api = api;
    }

    public void performGetAllOngs(){
       Call<ResponseBody> call = this.api.getOngs();
       call.enqueue(new Callback<>() {
           @Override
           public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    ResponseBody responseBody = response.body();
                    Log.e(TAG, "onResponse: response body  " +responseBody.string());
                }catch (Exception ex){
                    Log.e(TAG, "onResponse: erro no getAll de todas as ongs: ",ex );
                }
           }

           @Override
           public void onFailure(Call<ResponseBody> call, Throwable t) {

           }
       });
    }



}
