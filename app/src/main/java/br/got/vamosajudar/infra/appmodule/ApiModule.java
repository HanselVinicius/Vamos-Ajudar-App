package br.got.vamosajudar.infra.appmodule;

import javax.inject.Singleton;

import br.got.vamosajudar.infra.Api;
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
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(Api.class);
    }

}
