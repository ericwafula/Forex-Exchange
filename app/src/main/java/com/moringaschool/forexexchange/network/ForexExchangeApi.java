package com.moringaschool.forexexchange.network;

import com.moringaschool.forexexchange.models.USDRateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ForexExchangeApi {
    @GET("{ApiKey}/latest/USD")
    Call<USDRateResponse> getCurrencies(
            @Path("ApiKey") String apiKey
    );
}
