package br.got.vamosajudar.view_model;


import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import br.got.vamosajudar.Application;
import br.got.vamosajudar.infra.exceptions.DadosInvalidosException;
import br.got.vamosajudar.infra.exceptions.LoginException;
import br.got.vamosajudar.infra.observer.Publisher;
import br.got.vamosajudar.infra.observer.Subscriber;
import br.got.vamosajudar.model.user.UserRepository;
import br.got.vamosajudar.model.user.dto.LoginDTO;
import br.got.vamosajudar.model.user.dto.ProfileDTO;
import br.got.vamosajudar.model.user.dto.UserRegisterDTO;
import br.got.vamosajudar.model.user.token.UserCallback;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginActivityViewModel extends ViewModel implements UserCallback {

    public static final String TAG = "LoginActivityViewModel";

    private final Publisher userPublisher = Application.getUserPublisher();
    private final UserRepository repository;


    @Inject
    public LoginActivityViewModel(UserRepository repository){
        this.repository = repository;
    }


    public void executeLogin(String username, String password, Subscriber sub){
        try {
            if (username.isBlank() || password.isBlank()) {
                throw new DadosInvalidosException("DADOS INVALIDOS OU NÃO INSERIDOS");
            }
            userPublisher.subscribe(sub);
            this.repository.login(new LoginDTO(username, password),this);

        }catch (LoginException  ex){
            this.onTokenError(ex);
            Log.e(TAG, "executeLogin: ERRO NO LOGIN" ,ex );
        }
    }

    public void executeGetProfile(String token,MutableLiveData<String> profileDTOMutableLiveData){
        try {
            if (token.isBlank()) {
                throw new DadosInvalidosException("DADOS INVALIDOS OU NÃO INSERIDOS");
            }
            this.repository.getProfile(this,token,profileDTOMutableLiveData);

        }catch (Exception ex){
            Log.e(TAG, "executeGetProfile: ERRO NO GET DO PROFILE",ex );
            this.onTokenError(ex);
        }
    }


    public void executeRegister(String login,String email,String password,String name,Subscriber sub){
            if (login.isBlank() || email.isBlank() || password.isBlank() || name.isBlank()){
                throw new DadosInvalidosException("DADOS INVALIDOS OU NÃO INSERIDOS");
            }
            userPublisher.subscribe(sub);
            this.repository.register(new UserRegisterDTO(login,email,password,name),this);
    }



    @Override
    public void onTokenSaved() {
        userPublisher.notifySubscribers();
    }

    @Override
    public void onTokenError(Exception ex) {
        Log.e(TAG, "onTokenError: ERRO DO TOKEN",ex );
        userPublisher.notifySubscribersOnError(ex);
    }

    @Override
    public void onRegisterSuccess() {
        userPublisher.notifySubscribers();
    }


}
