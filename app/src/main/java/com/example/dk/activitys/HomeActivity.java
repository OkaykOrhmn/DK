package com.example.dk.activitys;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.dk.R;
import com.example.dk.databinding.ActivityHomeBinding;
import com.example.dk.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private NavController navController;
    private boolean isDubblePress = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Handler h = new Handler();
        Runnable r = () -> {
            binding.drawer.closeDrawer(binding.menu.getRoot());

        };


        NavigationView navView = binding.menu.navigationDrawer;
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupWithNavController(navView, navController);



        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                binding.title.setText(item.getTitle());

                switch (item.getItemId()){
                    case R.id.homeFragment:
                        navController.navigate(R.id.homeFragment);
                        h.postDelayed(r, 100);
                        break;

                    case R.id.settingFragment:
                        navController.navigate(R.id.settingFragment);
                        h.postDelayed(r, 100);

                        break;

                    case R.id.aboutFragment:
                        navController.navigate(R.id.aboutFragment);
                        h.postDelayed(r, 100);

                        break;
                }
                return false;
            }
        });


        //drawer
        binding.menuDrawer.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                binding.drawer.openDrawer(binding.menu.getRoot());
                binding.menu.getRoot().setOnClickListener(null);
            }
        });





    }


    @Override
    public void onBackPressed() {
        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);

        if (binding.drawer.isDrawerOpen(binding.menu.getRoot())) {
            binding.drawer.closeDrawer(binding.menu.getRoot());


        }else
        if (navController.getCurrentDestination().getId() == R.id.homeFragment) {

            if (isDubblePress) {

                finish();

            } else {
                this.isDubblePress = true;
                Toast.makeText(this, "برای خروج دوباره امتحان کنید", Toast.LENGTH_LONG).show();
                Handler h = new Handler();
                Runnable r = () -> {
                    isDubblePress = false;
                };
                h.postDelayed(r, 4000);
            }
        }



        else {
            binding.title.setText("لیست محصولات");
            super.onBackPressed();
        }
    }


}