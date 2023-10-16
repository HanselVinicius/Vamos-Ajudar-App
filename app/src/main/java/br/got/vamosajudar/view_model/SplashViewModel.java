package br.got.vamosajudar.view_model;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import br.got.vamosajudar.model.user.dto.LoginResponseDTO;
import br.got.vamosajudar.model.user.token.GetCallback;
import br.got.vamosajudar.model.user.token.ProfileRepository;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel

public class SplashViewModel extends ViewModel  {


    private final ProfileRepository profileRepository;


    @Inject
    public SplashViewModel(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public void getProfile(String token, GetCallback<LoginResponseDTO> getCallback){
        profileRepository.performGetProfile(token,getCallback);
    }


}
