package com.example.kilo_count;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

@SuppressWarnings("All")
public class Categories extends AppCompatActivity {

    ListView catList;

    String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categories);

        catList = (ListView) findViewById(R.id.catListView);

        final ArrayList<String> foodCats = new ArrayList<>();

        foodCats.add("Breakfast");
        foodCats.add("Lunch");
        foodCats.add("Dinner");
        foodCats.add("Gym");
        foodCats.add("Jogging");


        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,foodCats);
        catList.setAdapter(arrayAdapter);


        catList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


//                SharedPreferences somePref = getSharedPreferences("someKJs", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = somePref.edit();
//
//                editor.putString("clickedCategory", foodCats.get(i));
//                editor.apply();

                Intent kiloCalc = new Intent(getApplicationContext(), Calculator.class);
                kiloCalc.putExtra("clickedCategory", foodCats.get(i));
                startActivity(kiloCalc);
                finish();

            }
        });

    }

    @Override
    public void onBackPressed(){

        Intent backToMain = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(backToMain);
    }

}
