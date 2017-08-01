package com.example.activitytest.listviewtest;

/**
 * Created by lingnanmiao on 2017/7/20.
 */

public class Fruit {
    private String name;
    private int imageId;

    public Fruit(String name, int ID){
        this.name = name;
        this.imageId = ID;
    }

    public String getName(){
        return name;
    }

    public int getId(){
        return imageId;
    }
}
