package com.example.moduletwo.modelclass;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.moduletwo.respostory.Moduletwo_repo;

import java.util.ArrayList;
import java.util.List;

public class Moduletwo_viewmodel extends AndroidViewModel {

    public Moduletwo_repo moduletwoRepo;
    public MutableLiveData<ArrayList<String>> name = new MutableLiveData();
    public MutableLiveData<String> phn_num  = new MutableLiveData<>();
    public Moduletwo_viewmodel(@NonNull Application application) {
        super(application);

        moduletwoRepo = new Moduletwo_repo(application);
    }

    public void getData(){
        ArrayList<String> add_name = new ArrayList<>();
        add_name.add("doma");
        add_name.add("domac");

        name.setValue(add_name);

    }
}
