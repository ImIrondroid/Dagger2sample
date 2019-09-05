package com.example.dagger2sampleexample.Module;

import com.example.dagger2sampleexample.Dog;
import com.example.dagger2sampleexample.Name;
import com.example.dagger2sampleexample.Pet;

import dagger.Provides;

@dagger.Module
public class Module {

    @Provides
    Pet providePet(Dog dog, Name name) {
        return new Pet(dog, name);
    }

    @Provides
    Dog provideDog() {
        return new Dog("Moltiz");
    }

    @Provides
    Name provideName() {
        return new Name("mozzi");
    }

}
