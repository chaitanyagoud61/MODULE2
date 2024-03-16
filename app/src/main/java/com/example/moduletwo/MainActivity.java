package com.example.moduletwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.moduletwo.databinding.ActivityMainBinding;
import com.example.moduletwo.modelclass.Moduletwo_viewmodel;
import com.example.moduletwo.networkinit.Networkcheck;
import com.example.moduletwo.respostory.PrefController;

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
        activityMainBinding.setLifecycleOwner(this);
        activityMainBinding.text.setOnClickListener(new View.OnClickListener() {
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




    }

    @Override
    public void check_network(Boolean isconnected) {

        if(isconnected){

        }else {

        }
    }
}