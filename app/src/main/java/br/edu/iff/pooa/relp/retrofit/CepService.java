package br.edu.iff.pooa.relp.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CepService {
    @GET("{cep}/json")
    Call<CEP> buscarCEP(@Path("cep") String cep);
}
