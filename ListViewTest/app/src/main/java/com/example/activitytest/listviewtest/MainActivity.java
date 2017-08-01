package com.example.activitytest.listviewtest;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private List<Fruit> fruitList = new ArrayList<Fruit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruits();
        FruitAdapter adapter = new FruitAdapter(MainActivity.this, R.layout.fruit_item, fruitList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Fruit fruit = fruitList.get(i);
                Toast.makeText(MainActivity.this, fruit.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initFruits() {
        Fruit apple = new Fruit("Apple", R.drawable.img_3292);
        fruitList.add(apple);
        Fruit banana = new Fruit("Banana", R.drawable.img_3295);
        fruitList.add(banana);
        Fruit orange = new Fruit("Orange", R.drawable.img_3296);
        fruitList.add(orange);
        Fruit watermelon = new Fruit("Watermelon", R.drawable.img_3297);
        fruitList.add(watermelon);
        Fruit pear = new Fruit("Pear", R.drawable.img_3298);
        fruitList.add(pear);
        Fruit grape = new Fruit("Grape", R.drawable.img_3299);
        fruitList.add(grape);
        Fruit pineapple = new Fruit("Pineapple", R.drawable.img_3301);
        fruitList.add(pineapple);
        Fruit strawberry = new Fruit("Strawberry", R.drawable.img_3302);
        fruitList.add(strawberry);
        Fruit cherry = new Fruit("Cherry", R.drawable.img_3303);
        fruitList.add(cherry);
        Fruit mango = new Fruit("Mango", R.drawable.img_3304);
        fruitList.add(mango);
    }
}