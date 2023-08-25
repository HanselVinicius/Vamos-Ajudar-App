package br.got.vamosajudar.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import br.got.vamosajudar.model.ong.Ong;
import br.got.vamosajudar.model.ong.OngRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class OngActivityViewModel extends ViewModel {

    private static final String TAG = "ONGVIEWMODEL";
    private final OngRepository repository;

    @Inject
    public OngActivityViewModel(OngRepository repository){
        this.repository = repository;
    }


    public void getAllOngs(){
         this.repository.performGetAllOngs();
    }


    public MutableLiveData<List<Ong>> getListOfOngs() {
        return repository.getListOfOngs();
    }








}
