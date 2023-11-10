package br.got.vamosajudar.view_model;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import br.got.vamosajudar.model.ong.OngRegisterDTO;
import br.got.vamosajudar.model.ong.OngResponseList;
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


    public void getAllOngs(int page){
        if(this.repository.getListOfOngs().getValue() != null) {
            this.repository.getListOfOngs().getValue().getContent().clear();
        }
        this.repository.performGetAllOngs( page);
    }

    public void registerOngs(String token,OngRegisterDTO ong){
        this.repository.performRegisterOngs(token,ong);
    }


    public MutableLiveData<OngResponseList<Ong>> getListOfOngs() {
        return repository.getListOfOngs();
    }


    public void deleteOng(String token) {
        this.repository.performDeleteOng(token);
    }
}
