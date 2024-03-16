package com.example.moduletwo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.moduletwo.databinding.ActivitySecondBinding;
import com.example.moduletwo.modelclass.Moduletwo_viewmodel;

public class Second extends AppCompatActivity {

    ActivitySecondBinding activitySecondBinding;
    public Moduletwo_viewmodel moduletwoViewmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activitySecondBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(activitySecondBinding.getRoot());
        moduletwoViewmodel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(Moduletwo_viewmodel.class);

        activitySecondBinding.setLifecycleOwner(this);
        Intent intent = getIntent();
        intent.getStringExtra("name");


    }
}