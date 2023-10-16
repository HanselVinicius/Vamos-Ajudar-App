package br.got.vamosajudar.model.user.token;

public interface GetCallback<T> {

    void getSuccess(T returned);

    void getFail(Throwable ex);


}
