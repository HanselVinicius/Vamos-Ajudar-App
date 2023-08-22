package br.got.vamosajudar.infra;

import br.got.vamosajudar.model.ong.Ong;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {


    @GET("/v1/ong")
    Call<ResponseBody> getOngs();

    @POST
    Call<ResponseBody> registerOngs(@Body Ong ong);




}
