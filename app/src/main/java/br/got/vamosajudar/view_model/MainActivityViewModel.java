package br.got.vamosajudar.view_model;


import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import br.got.vamosajudar.Application;
import br.got.vamosajudar.infra.exceptions.DadosInvalidosException;
import br.got.vamosajudar.infra.exceptions.LoginException;
import br.got.vamosajudar.infra.observer.Publisher;
import br.got.vamosajudar.infra.observer.Subscriber;
import br.got.vamosajudar.model.user.UserRepository;
import br.got.vamosajudar.model.user.dto.LoginDTO;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {

    public static final String TAG = "MainActivityViewModel";


    private final UserRepository repository;

    @Inject
    public MainActivityViewModel (UserRepository repository){
        this.repository = repository;
    }


    public void executeLogin(String username, String password, Subscriber sub){
        try {
            if (username.isBlank() && password.isBlank()) {
                throw new DadosInvalidosException("DADOS INVALIDOS OU NÃO INSERIDOS");
            }
            Publisher userPublisher = Application.getUserPublisher();
            userPublisher.subscribe(sub);
            this.repository.login(new LoginDTO(username, password));
            userPublisher.notifySubscribers();
        }catch (LoginException ex){
            Log.e(TAG, "executeLogin: ERRO NO LOGIN" ,ex );
        }
    }



}
