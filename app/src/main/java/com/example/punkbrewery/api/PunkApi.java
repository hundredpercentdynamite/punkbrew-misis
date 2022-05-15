package com.example.punkbrewery.api;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class PunkApi {
    final static private String base = "https://api.punkapi.com/v2/";
    final static private PunkService punkService = new Retrofit.Builder()
            .baseUrl(base)
            .addConverterFactory(JacksonConverterFactory.create())
            .build().create(PunkService.class);

    static public PunkService getPunkService() {
        return punkService;
    }

    static public String getApiBase() {
        return base;
    }
}
