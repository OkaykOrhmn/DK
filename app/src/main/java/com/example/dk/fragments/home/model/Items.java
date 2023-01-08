package com.example.dk.fragments.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Items {

    @SerializedName("title")
    public String title = "";

    @SerializedName("product")
    public ItemProduct itemProducts = new ItemProduct();

    @SerializedName("price")
    public Price price = new Price();


}
