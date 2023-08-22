package br.got.vamosajudar.infra.appmodule;

import javax.inject.Singleton;

import br.got.vamosajudar.infra.Api;
import br.got.vamosajudar.infra.Repository;
import br.got.vamosajudar.model.ong.OngRepository;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class ApiModule {


    @Provides
    @Singleton
    public static Api provideApi(){
        return new Retrofit.Builder()
                .baseUrl("https://vamos-ajudar-api.henriquebarucco.com.br")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Api.class);
    }


    /*por algum motivo estava dando conflito com o provideApi causando o erro de não retornar um Retrofit
     com url valido*/

//    @Provides
//    @Singleton
//    public static Repository provideOngRepository(Api api){
//        return new OngRepository(api);
//    }

}
