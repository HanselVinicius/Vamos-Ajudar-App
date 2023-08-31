package br.got.vamosajudar.infra;

import br.got.vamosajudar.model.ong.OngResponse;
import br.got.vamosajudar.model.ong.Ong;
import br.got.vamosajudar.model.user.dto.LoginDTO;
import br.got.vamosajudar.model.user.dto.LoginResponseDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {


    @GET("/v1/ong")
    Call<OngResponse<Ong>> getOngs(@Query("page") int page);

    @POST
    Call<ResponseBody> registerOngs(@Body Ong ong);


    @POST("v1/auth/login")
    Call<LoginResponseDTO> login(@Body LoginDTO loginDTO);


}
