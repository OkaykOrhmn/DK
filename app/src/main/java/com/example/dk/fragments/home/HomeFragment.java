package com.example.dk.fragments.home;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dk.R;
import com.example.dk.api.ResponseProducts;
import com.example.dk.databinding.FragmentHomeBinding;
import com.example.dk.fragments.home.HomeViewModel;
import com.example.dk.fragments.home.adapter.ProductsAdapter;
import com.example.dk.fragments.home.model.Items;
import com.example.dk.fragments.home.model.Products;
import com.google.android.material.button.MaterialButton;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private int pastVisibleItems, visibleItemCount, totalItemCount;

    private static final String TAG = "Kia--HomeFr----> ";
    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private ProductsAdapter productsAdapter;
    private ArrayList<Items> responseProductsArrayList = new ArrayList<>();

    boolean a ;
    int allCount = 3219 * 5;
    int startList = 0;
    int plusList = 10;
    int endList = 50;
    int end;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);


        homeViewModel.getAllProducts();
        homeViewModel.loading.observe(getViewLifecycleOwner(), aBoolean -> {
            Log.d(TAG, "loading");
            if (aBoolean) {
                Log.d(TAG, "loading t");
                binding.progressCircular.setVisibility(View.VISIBLE);
                binding.recAllPr.setVisibility(View.INVISIBLE);

            } else {
                Log.d(TAG, "loading f");
                binding.progressCircular.setVisibility(View.GONE);
                binding.recAllPr.setVisibility(View.VISIBLE);


            }

        });

        homeViewModel.connect.observe(getViewLifecycleOwner(), aBoolean -> {
            Log.d(TAG, "connect");
            if (!aBoolean) {
                Dialog dialog = new Dialog(getContext(), R.style.Dialog);
                dialog.setContentView(R.layout.connectiom_dialog);
                dialog.show();

                MaterialButton tryA = dialog.findViewById(R.id.tryA);
                MaterialButton exit = dialog.findViewById(R.id.exit);
                tryA.setClickable(true);

                tryA.setOnClickListener(view1 -> {
                    tryA.setClickable(false);
                    dialog.dismiss();
                    homeViewModel.getAllProducts();

                });


                exit.setOnClickListener(view1 -> {
                    requireActivity().finish();
                });
            }

        });

        homeViewModel.success.observe(getViewLifecycleOwner(), aBoolean -> {
            if (aBoolean) {


            } else {

            }

        });

        homeViewModel.responceProductsArray.observe(getViewLifecycleOwner(), responseProducts -> {
//            responseProducts.addAll(responseProductsArrayList);
            responseProductsArrayList.clear();
            transactionsRecyclerView(responseProducts);
            productsAdapter.notifyDataSetChanged();
            Log.d(TAG, "aray is -> " + responseProductsArrayList);
        });


        return binding.getRoot();
    }

    //----------------------------Recycler View---------------------------------------------------->
    @SuppressLint("RestrictedApi")
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void transactionsRecyclerView(Products transactions) {

        for (int i = startList; i < startList+10; i++) {
            responseProductsArrayList.add(transactions.items.get(i));
        }

//        startList+=10;

        productsAdapter = new ProductsAdapter(responseProductsArrayList);
        binding.recAllPr.setAdapter(productsAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recAllPr.setLayoutManager(layoutManager);
        binding.recAllPr.setHasFixedSize(true);
        Log.d(TAG, "aray is -> " + responseProductsArrayList);
        binding.recAllPr.setHasFixedSize(true);

        binding.recAllPr.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                visibleItemCount = layoutManager.getChildCount();
                totalItemCount = layoutManager.getItemCount();
//                Log.d(TAG, "visibleItemCount: "+visibleItemCount);
//                Log.d(TAG, "totalItemCount: "+totalItemCount);


                if (dy > 0) { //check for scroll down
                    pastVisibleItems = layoutManager.findFirstVisibleItemPosition();
//                    Log.d(TAG, "pastVisibleItems: "+pastVisibleItems);

                    if ( pastVisibleItems  == totalItemCount-6 && totalItemCount==startList+10) {
                        if (totalItemCount < 50){

                            binding.progressHorizontal.setVisibility(View.VISIBLE);

                            Handler h = new Handler();
                            Runnable r = new Runnable() {
                                @Override
                                public void run() {
                                        binding.progressHorizontal.setVisibility(View.INVISIBLE);


                                    Log.d(TAG, "startlist: "+startList);
                                    Log.d(TAG, "totalItemCount: "+totalItemCount);
                                    for (int i = startList; i < startList+10; i++) {
                                        if (startList>=50){
                                            break;
                                        }else{


                                            responseProductsArrayList.add(transactions.items.get(i));
//
//
//                                            }

                                        }


                                    }
                                    productsAdapter.notifyDataSetChanged();
                                    Log.d(TAG, "startList: "+startList);
                                    Log.d(TAG, "totalItemCount: "+totalItemCount);

//                                plusList+=10;

                                }
                            };

                            h.postDelayed(r,2000);

                            startList+=10;

                        }


                    }
                }
            }
        });

//        for (int x = 0; x < 10; x++) {
//                responseProductsArrayList.add(transactions.items.get(x));
//        }
//
//
//
//        binding.scrollable.setOnScrollChangeListener((View.OnScrollChangeListener) (view, i, i1, i2, i3) -> {
//            Log.d(TAG, "scrolling i: " + i);
//            Log.d(TAG, "scrolling i1: " + i1);
//            Log.d(TAG, "scrolling i2: " + i2);
//            Log.d(TAG, "scrolling i3: " + i3);
//
//
//            if (i3 == end || i1 == 0) {
//
//                for (int x = startList; x < plusList; x++) {
//                    if (end == allCount) {
//                        break;
//                    } else {
//                        responseProductsArrayList.add(transactions.items.get(x));
//
//                    }
//
//                }
//                startList =plusList;
//                plusList += startList;
//                end += 3219;
//            }
//
//
//        });




    }
//----------------------------Recycler View---------------------------------------------------//


}