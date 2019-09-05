package com.example.dagger2sampleexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dagger2sampleexample.Interface.Component;
import com.example.dagger2sampleexample.Interface.DaggerComponent;
import com.example.dagger2sampleexample.Module.Module;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject
    Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Component component = DaggerComponent.builder()
                .module(new Module())
                .build();

        component.inject(this);

        pet.print();
    }




}
