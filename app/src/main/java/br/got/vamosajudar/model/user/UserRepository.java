package br.got.vamosajudar.model.user;

import android.util.Log;

import javax.inject.Inject;

import br.got.vamosajudar.infra.Api;
import br.got.vamosajudar.infra.Repository;
import br.got.vamosajudar.infra.exceptions.ForbiddenException;
import br.got.vamosajudar.infra.exceptions.LoginException;
import br.got.vamosajudar.model.user.dto.LoginDTO;
import br.got.vamosajudar.model.user.dto.LoginResponseDTO;
import br.got.vamosajudar.model.user.dto.UserRegisterDTO;
import br.got.vamosajudar.model.user.token.UserCallback;
import br.got.vamosajudar.model.user.token.TokenManager;
import br.got.vamosajudar.view_model.LoginActivityViewModel;
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

    public void login(LoginDTO dto, UserCallback callback) throws LoginException{
        Call<LoginResponseDTO> call = api.login(dto);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<LoginResponseDTO> call, Response<LoginResponseDTO> response) {
                 if (response.isSuccessful() && response.code() != 403) {
                    LoginResponseDTO res = response.body();
                    String token = res.getToken();
                    TokenManager.saveToken(token);
                    callback.onTokenSaved();
                }else {
                     Log.e(TAG, "onResponse: quero saber os codigos e erros" +response.raw() );
                     callback.onTokenError(new ForbiddenException(response.message(),response.code()));
                 }
            }

            @Override
            public void onFailure(Call<LoginResponseDTO> call, Throwable t) {
                throw new LoginException("ERRO NA REQUISICAO DE LOGIN",t);
            }
        });
    }

    public void register(UserRegisterDTO userRegisterDTO,UserCallback userCallback){
        Call<UserRegisterDTO> call = api.registerUser(userRegisterDTO);
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<UserRegisterDTO> call, Response<UserRegisterDTO> response) {
                if (response.isSuccessful()){
                    userCallback.onRegisterSuccess();
                }
            }

            @Override
            public void onFailure(Call<UserRegisterDTO> call, Throwable t) {
                Log.e(TAG, "onFailure: FALHA NA REQUISICAO DE REGISTRO",t );
            }
        });
    }

}
