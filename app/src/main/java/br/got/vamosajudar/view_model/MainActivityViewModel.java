package br.got.vamosajudar.view_model;


import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import br.got.vamosajudar.model.user.UserRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {

    public static final String TAG = "MainActivityViewModel";

    private final UserRepository repository;

    @Inject
    public MainActivityViewModel (UserRepository repository){
        this.repository = repository;
    }


    public void executeLogin(){
        this.repository.login();
    }



}
