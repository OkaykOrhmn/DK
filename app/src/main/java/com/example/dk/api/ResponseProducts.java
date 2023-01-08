package com.example.dk.api;

import com.example.dk.fragments.home.model.Products;
import com.google.gson.annotations.SerializedName;

public class ResponseProducts {

    @SerializedName("status")
    public String status = "";

    @SerializedName("data")
    public Products products = new Products();





}
