package com.example.kilo_count;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
@SuppressWarnings("All")

public class Details extends AppCompatActivity {

    TextView dayDate, totalNKI, num1, num2, num3, num4, num5, numNKI;
    String today;

    MyDatabase db;
    double breakfast, lunch, dinner, gym, jogging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent=getIntent();
        today=intent.getStringExtra("DATE");


        dayDate = (TextView)findViewById(R.id.theDate);
        dayDate.setText(today);
        totalNKI = (TextView)findViewById(R.id.totalForTheDay);
        totalNKI.setText("Total:");

        num1 = (TextView)findViewById(R.id.num1);
        num2 = (TextView)findViewById(R.id.num2);
        num3 = (TextView)findViewById(R.id.num3);
        num4 = (TextView)findViewById(R.id.num4);
        num5 = (TextView)findViewById(R.id.num5);
        numNKI = (TextView)findViewById(R.id.NKI);

        getData();

    }
    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void getData() {
        db = new MyDatabase(this);
        Cursor data = db.getRow(today);

        if (data != null) {
            data.moveToFirst();
        }
        if (data.isFirst()) {

            breakfast = 0;
            lunch = 0;
            dinner = 0;
            gym = 0;
            jogging = 0;

            ArrayList<String> list = new ArrayList<>();
            list.add(data.getString(1) + data.getString(2));
            System.out.println(Arrays.toString(list.toArray()));
            System.out.println(data.getString(2));

            breakfast += data.getDouble(2);
            lunch += data.getDouble(3);
            dinner += data.getDouble(4);
            gym += data.getDouble(5);
            jogging += data.getDouble(6);

            while (data.moveToNext()) {
                list.add(data.getString(1) + data.getString(2));
                breakfast += data.getDouble(2);
                lunch += data.getDouble(3);
                dinner += data.getDouble(4);
                gym += data.getDouble(5);
                jogging += data.getDouble(6);
            }

            num1.setText("" + round(breakfast,2));
            num2.setText("" + round(lunch,2));
            num3.setText("" + round(dinner,2));
            num4.setText("" + round(gym,2));
            num5.setText("" + round(jogging,2));
            numNKI.setText(""+round(((breakfast+lunch+dinner)-(gym+jogging)),2)+" kJ");

        }
    }
}
