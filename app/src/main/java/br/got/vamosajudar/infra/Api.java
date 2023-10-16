package br.got.vamosajudar.infra;

import br.got.vamosajudar.model.ong.OngRegisterDTO;
import br.got.vamosajudar.model.ong.OngResponseList;
import br.got.vamosajudar.model.ong.Ong;
import br.got.vamosajudar.model.ong.qr.QRDto;
import br.got.vamosajudar.model.user.dto.LoginDTO;
import br.got.vamosajudar.model.user.dto.LoginResponseDTO;
import br.got.vamosajudar.model.user.dto.UserRegisterDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {


    @GET("/v1/ong")
    Call<OngResponseList<Ong>> getOngs(@Query("page") int page);

    @POST("/v1/ong")
    Call<Ong> registerOngs(@Header("Authorization") String Bearer, @Body OngRegisterDTO ong);


    @POST("v1/auth/login")
    Call<LoginResponseDTO> login(@Body LoginDTO loginDTO);


    @POST("v1/auth/register")
    Call<UserRegisterDTO> registerUser(@Body UserRegisterDTO userRegisterDTO);

    @GET("v1/ong/donate/{id}")
    Call<QRDto> getQr(@Path("id") String id);


    @GET("v1/auth/perfil")
    Call<LoginResponseDTO> getProfile(@Header("Authorization") String token);


    @DELETE("/v1/ong")
    Call<Void> deleteOng(@Header("Authorization") String bearer);
}
