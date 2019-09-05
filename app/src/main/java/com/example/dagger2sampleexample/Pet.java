package com.example.dagger2sampleexample;

import android.util.Log;

public class Pet {

    Dog dog;
    Name name;

    String kind;
    String nick;

    public Pet(Dog dog, Name name) {

        this.dog = dog;
        this.name = name;

        this.kind = dog.getKind();
        this.nick = name.getName();

    }

    void print() {
        Log.e("Pet.instance said like ", "result : Dog = "+kind+ ", Name = "+nick);
    }

}
