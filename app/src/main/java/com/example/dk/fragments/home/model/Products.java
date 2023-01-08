package com.example.dk.fragments.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Products {

    @SerializedName("items")
    public ArrayList<Items> items = new ArrayList<>();
}
