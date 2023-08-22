package br.got.vamosajudar.view_model;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import br.got.vamosajudar.model.ong.OngRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {

    public static final String TAG = "MainActivityViewModel";

    private OngRepository repository;

    @Inject
    public MainActivityViewModel (OngRepository repository){
        this.repository = repository;
    }


    public void getAllOngs(){
        this.repository.performGetAllOngs();
    }





}
