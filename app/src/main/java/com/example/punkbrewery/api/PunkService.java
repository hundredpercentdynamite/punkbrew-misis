package com.example.punkbrewery.api;

import com.example.punkbrewery.entities.Beer;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface PunkService {
    @GET("beers")
    Call<List<Beer>> getBeerList(@QueryMap Map<String, String> options);

    @GET("beers/{id}")
    Call<List<Beer>> getBeerById(@Path("id") String beerId);

    @GET("beers/random")
    Call<List<Beer>> getRandomBeer();
}
