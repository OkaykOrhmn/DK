package com.example.dk.fragments.home;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dk.api.Connectivity;
import com.example.dk.api.ProductsRepository;
import com.example.dk.api.ResponseProducts;
import com.example.dk.fragments.home.model.Products;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel{

    private static final String TAG = "Kia---HomeViewModel----> ";
    @SuppressLint("StaticFieldLeak")
    private final Context context;

    private final ProductsRepository productsRepository = new ProductsRepository();
    public MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<Boolean> connect = new MutableLiveData<>();
    public MutableLiveData<Boolean> success = new MutableLiveData<>();
    public MutableLiveData<Products> responceProductsArray = new MutableLiveData<>();



    public HomeViewModel(@NonNull Application application) {
        super(application);
        this.context = application;

    }

    @SuppressLint("LongLogTag")
    public void getAllProducts(){
        if (Connectivity.isConnected(context)) {
            loading.setValue(true);
            connect.setValue(true);
            Call<ResponseProducts> call = productsRepository.callAllProducts();

            call.enqueue(new Callback<ResponseProducts>() {
                @Override
                public void onResponse(Call<ResponseProducts> call, Response<ResponseProducts> response) {

                    responceProductsArray.setValue(response.body().products);
                    Log.d(TAG, "onResponse: -> "+ response.body().products.items.size());

                    success.setValue(true);
                    loading.setValue(false);

                }

                @Override
                public void onFailure(Call<ResponseProducts> call, Throwable t) {

                    Log.d(TAG, "onfail: -> "+ t.getMessage());

                    success.setValue(false);
                    loading.setValue(false);


                }



//
            });
        }else{
            connect.setValue(false);
            success.setValue(false);
            loading.setValue(false);

        }
    }

}
