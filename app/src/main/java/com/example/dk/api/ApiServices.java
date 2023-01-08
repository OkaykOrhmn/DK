package com.example.dk.api;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiServices {

    //https://seller.digikala.com/api/v1/variants/
    @GET("/api/v1/variants/")
    Call<ResponseProducts> getProducts(
            @Header("authorization") String token);
}
