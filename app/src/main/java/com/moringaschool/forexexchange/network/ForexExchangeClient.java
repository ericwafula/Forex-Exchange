package com.moringaschool.forexexchange.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForexExchangeClient {
    private static Retrofit retrofit = null;

    public static ForexExchangeApi getClient(){
        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://v6.exchangerate-api.com/v6/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ForexExchangeApi.class);
    }
}
