package br.got.vamosajudar.model.user;

import android.util.Log;

import javax.inject.Inject;

import br.got.vamosajudar.infra.Api;
import br.got.vamosajudar.infra.Repository;

public class UserRepository implements Repository {

    private static final String TAG = "UserRepository";
    private final Api api;

    @Inject
    public UserRepository(Api api) {
        this.api = api;
    }

    //todo login
    public void login(){
        Log.e(TAG, "login: ola mainAct"  );
    }

    //todo register
    public void register(){

    }

}
