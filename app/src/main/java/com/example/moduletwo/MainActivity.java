package com.example.moduletwo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import com.example.moduletwo.databinding.ActivityMainBinding;
import com.example.moduletwo.modelclass.Moduletwo_viewmodel;
import com.example.moduletwo.networkinit.Networkcheck;
import com.example.moduletwo.respostory.PrefController;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Networkcheck {


    ActivityMainBinding activityMainBinding;
    public Moduletwo_viewmodel moduletwoViewmodel;
    public Networkcheck networkcheck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        moduletwoViewmodel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(Moduletwo_viewmodel.class);
        networkcheck =this;

        activityMainBinding.homeSearch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        activityMainBinding.bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.home_menu){

                    Toast.makeText(getApplicationContext(),"home",Toast.LENGTH_SHORT).show();
                }else if(item.getItemId() == R.id.services_menu){
                    Toast.makeText(getApplicationContext(),"services",Toast.LENGTH_SHORT).show();

                }else if(item.getItemId() == R.id.profile_menu){
                    Toast.makeText(getApplicationContext(),"profile",Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });

        activityMainBinding.haliText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PrefController.saveData("name","chaitanya_pref",getApplicationContext());
                Intent intent = new Intent(MainActivity.this, Second.class);
                intent.putExtra("name","chaitanya");
                startActivity(intent);
            }
        });

        moduletwoViewmodel.name.observe(this, new Observer<ArrayList<String>>() {
            @Override
            public void onChanged(ArrayList<String> strings) {

            }
        });



        activityMainBinding.setLifecycleOwner(this);
    }

    @Override
    public void check_network(Boolean isconnected) {

        if(isconnected){

        }else {

        }
    }
}