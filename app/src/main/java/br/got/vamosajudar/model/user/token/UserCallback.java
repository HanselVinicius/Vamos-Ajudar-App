package br.got.vamosajudar.model.user.token;

public interface UserCallback {

    void onTokenSaved();

    void onTokenError(Exception ex);

    void onRegisterSuccess();
}
