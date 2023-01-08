package com.example.dk.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;

import com.example.dk.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(2000);

        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);

        binding.logo.setAnimation(animation);


        Handler h = new Handler();
        Runnable r = () -> {
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        };h.postDelayed(r, 3000);

    }

    @Override
    protected void onPause() {
        super.onPause();

//        Intent intent = new Intent(this, HomeActivity.class);
//        startActivity(intent);

    }

}