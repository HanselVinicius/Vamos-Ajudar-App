package br.got.vamosajudar.model.user;

import android.util.Log;

import javax.inject.Inject;

import br.got.vamosajudar.infra.Api;
import br.got.vamosajudar.infra.Repository;
import br.got.vamosajudar.infra.exceptions.LoginException;
import br.got.vamosajudar.model.user.dto.LoginDTO;
import br.got.vamosajudar.model.user.dto.LoginResponseDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository implements Repository {

    private static final String TAG = "UserRepository";
    private final Api api;

    @Inject
    public UserRepository(Api api) {
        this.api = api;
    }

    public void login(LoginDTO dto) throws LoginException{
        Call<LoginResponseDTO> call = api.login(dto);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
                if (response.isSuccessful()) {
                    LoginResponseDTO res = response.body();
                    String token = res.getToken();
                    TokenManager.saveToken(token);
                }
            }

            @Override
            public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
                throw new LoginException("ERRO NA REQUISICAO DE LOGIN");
            }
        });
    }

    //todo register
    public void register(){

    }

}
