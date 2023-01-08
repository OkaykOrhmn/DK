package com.example.dk.fragments.home.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dk.databinding.CartLayoutBinding;
import com.example.dk.fragments.home.model.Items;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private ArrayList<Items> responseProducts;
    private Context context;
    private Activity activity;

    public ProductsAdapter(ArrayList<Items> responseProducts) {
        this.responseProducts = responseProducts;
        this.activity = activity;

    }

    @NonNull
    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        CartLayoutBinding v = CartLayoutBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ProductsAdapter.ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, int position) {

        Items item = responseProducts.get(position);

        holder.binding.name.setText(item.title);
        holder.binding.description.setText(item.itemProducts.title);
        holder.binding.categorize.setText(item.itemProducts.category);
        holder.binding.price.setText(item.price.selling + " تومان ");
        Picasso.get().load(item.itemProducts.image).into(holder.binding.mainImage);


    }

    @Override
    public int getItemCount() {
        return responseProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CartLayoutBinding binding;

        public ViewHolder(@NonNull CartLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}