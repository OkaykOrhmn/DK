package com.example.dk.api;

import retrofit2.Call;

public class ProductsRepository {

    public Call<ResponseProducts> callAllProducts(){
        //callApi
        Call<ResponseProducts> call = Tools.getApiServicesInstance().getProducts("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzM4NCJ9.eyJ0b2tlbl9pZCI6NzQ4OSwicGF5bG9hZCI6bnVsbH0.hERdWk0JHMIp3Vw2WeMRHHYh7YmA51EpbugSpjQ-7Ppr6G2eJXOGIByUATQvnOMv");

        return call;
    }
}
