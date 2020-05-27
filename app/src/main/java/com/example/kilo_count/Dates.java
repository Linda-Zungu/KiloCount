package com.example.kilo_count;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Dates extends AppCompatActivity {

    static ListView listWithDates;
    MyDatabase db;
    String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dates);

        db=new MyDatabase(this);
//
//        DateFormat dateFormat=new SimpleDateFormat("dd MMMM yyyy");
//        Date date=new Date();
//        String today=dateFormat.format(date);
//
//        System.out.println(today);
//
        listWithDates = (ListView)findViewById(R.id.dateList);
//
//        final ArrayList<String> list = new ArrayList<>();
//        list.add(today);
//
//        final ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
//        listWithDates.setAdapter(arrayAdapter);


        listWithDates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDate=(String) listWithDates.getItemAtPosition(i);
                openDetails();
            }
        });

        addDates();
    }

    public void addDates(){
        Cursor data=db.getTable();

        if (data!=null){
            data.moveToFirst();

        }
        if (data.isFirst()){
            ArrayList<String> list=new ArrayList<>();
            list.add(data.getString(1));
            //System.out.println("The date:"+data.getString(1));
            while(data.moveToNext()){
                String x=data.getString(1);
                if (!list.contains(x)) {
                    list.add(x);
                }

            }

            ListAdapter adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);
            listWithDates.setAdapter(adapter);
        }
    }

    public void openDetails(){
        Intent toDetails = new Intent(this, Details.class);
        toDetails.putExtra("DATE",selectedDate);
        startActivity(toDetails);
    }
}
