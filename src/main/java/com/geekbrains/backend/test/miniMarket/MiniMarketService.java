package com.geekbrains.backend.test.miniMarket;

import lombok.experimental.UtilityClass;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@UtilityClass
public class MiniMarketService {
    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl("http://localhost:8189/market/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
