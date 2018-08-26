package br.edu.iff.pooa.relp.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class CepConfig {
    private final Retrofit retrofit;

    public CepConfig(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl("https://viacep.com.br/ws/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public CepService getCepService(){
        return this.retrofit.create(CepService.class);
    }
}
