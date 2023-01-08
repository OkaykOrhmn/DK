package com.example.dk.api;

public class Tools {

    private static ApiServices apiServices = null ;

    public static ApiServices getApiServicesInstance (){

        if (apiServices == null ){
            //client
            apiServices = ApiClient.getClient().create(ApiServices.class);
        }

        return apiServices ;
    }
}
