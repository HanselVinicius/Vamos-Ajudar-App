package br.got.vamosajudar.infra;

import java.util.List;

import br.got.vamosajudar.model.ong.Ong;
import br.got.vamosajudar.model.user.dto.LoginDTO;
import br.got.vamosajudar.model.user.dto.LoginResponseDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {


    @GET("/v1/ong")
    Call<List<Ong>> getOngs();

    @POST
    Call<ResponseBody> registerOngs(@Body Ong ong);


    @POST("v1/auth/login")
    Call<LoginResponseDTO> login(@Body LoginDTO loginDTO);


}
