package com.example.logonrm.starwarsapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StarWarsService {

    @GET("/api/people/{number}")
    Call<StarWars> buscarPessoa(@Path(value = "number")String number);

    @GET("/api/films/{number}")
    Call<StarWars> buscarFilme(@Path(value = "number")String number);
}
