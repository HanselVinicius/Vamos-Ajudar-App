package br.got.vamosajudar.model.ong;

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

    private static final MutableLiveData<List<Ong>>  ongList = new MutableLiveData<>();


    @Inject
    public OngRepository(Api api) {
        this.api = api;
    }

    public void performGetAllOngs(){
        Call<List<Ong>> call = this.api.getOngs();
        call.enqueue(
                new Callback<>() {
                    @Override
                    public void onResponse(Call<List<Ong>> call, Response<List<Ong>> response) {
                        if (response.isSuccessful()){
                            ongList.postValue(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Ong>> call, Throwable t) {

                    }
                }
        );
    }


    public MutableLiveData<List<Ong>> getListOfOngs() {
        return ongList;
    }

}
