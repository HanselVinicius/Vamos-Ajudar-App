package br.got.vamosajudar.model.ong;

import android.util.Log;

import javax.inject.Inject;

import br.got.vamosajudar.infra.Api;
import br.got.vamosajudar.infra.Repository;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OngRepository implements Repository {


    private static final String TAG = "OngRepository";
    private final Api api;


    @Inject
    public OngRepository(Api api) {
        this.api = api;
    }

    public void performGetAllOngs(){

    }



}
