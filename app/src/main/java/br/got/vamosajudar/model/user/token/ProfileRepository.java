package br.got.vamosajudar.model.user.token;


import javax.inject.Inject;

import br.got.vamosajudar.infra.Api;
import br.got.vamosajudar.model.user.dto.LoginResponseDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {

    private final Api api;

    @Inject
    public ProfileRepository(Api api) {
        this.api = api;
    }

    public void performGetProfile(String token, GetCallback<LoginResponseDTO> getCallback) {
        Call<LoginResponseDTO> call = this.api.getProfile("Bearer "+token);
        call.enqueue(
                new Callback<>() {
                    @Override
                    public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
                        if (!response.isSuccessful()){
                            getCallback.getFail(new Exception(response.raw().toString()));
                            return;
                        }
                        getCallback.getSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
                        getCallback.getFail(t);
                    }
                }
        );
    }
}
