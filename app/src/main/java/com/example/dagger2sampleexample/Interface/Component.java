package com.example.dagger2sampleexample.Interface;

import com.example.dagger2sampleexample.MainActivity;
import com.example.dagger2sampleexample.Module.Module;

@dagger.Component(modules = Module.class)
public interface Component {

    void inject(MainActivity mainActivity);
}
