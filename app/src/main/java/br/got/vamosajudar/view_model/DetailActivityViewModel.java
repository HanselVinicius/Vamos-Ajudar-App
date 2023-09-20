package br.got.vamosajudar.view_model;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import br.got.vamosajudar.model.ong.qr.OngQrRepository;
import br.got.vamosajudar.model.ong.qr.QRDto;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetailActivityViewModel extends ViewModel  {


    public static final String TAG = "OngQrRepository";


    private final OngQrRepository ongQrRepository;

    private MutableLiveData<QRDto> qrLiveData;


    @Inject
    public DetailActivityViewModel(OngQrRepository ongQrRepository) {
        this.ongQrRepository = ongQrRepository;
        qrLiveData = new MutableLiveData<>();
    }

    public LiveData<QRDto> getQr(String id) {
        ongQrRepository.getQr(id, this.qrLiveData);
        return qrLiveData;
    }


}
