package com.example.kilo_count;

import android.content.Intent;
import android.database.Cursor;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@SuppressWarnings("All")
public class MainActivity extends AppCompatActivity {

    Button calcButton;
    Intent categories;

    Button seeDates;

    TextView showCategoryFood1, showCategoryFood2, showCategoryFood3, showCategoryExe1, showCategoryExe2;
    TextView firstFoodNum, secFoodNum, thirdFoodNum, firstExeNum, secExeNum;
    ConstraintLayout main;

    double breakfast, lunch, dinner, gym, jogging;
    MyDatabase db;
    String today;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        calcButton = (Button) findViewById(R.id.toTheCalculator);
        calcButton.setText("Calculator");
        seeDates = (Button)findViewById(R.id.someDates);
        seeDates.setText("Details");

        DateFormat dateFormat=new SimpleDateFormat("dd MMMM yyyy");
        Date date=new Date();
        today=dateFormat.format(date);

        System.out.println(today);

//        showContent = (Button)findViewById(R.id.showSavedInfo);
//        showContent.setText("Refresh");

        showCategoryFood1 = (TextView) findViewById(R.id.showNameOfCategory1);
        showCategoryFood1.setText("Breakfast:");
        showCategoryFood2 = (TextView)findViewById(R.id.showNameOfCategory2);
        showCategoryFood2.setText("Lunch:");
        showCategoryFood3 = (TextView) findViewById(R.id.showNameOfCategory3);
        showCategoryFood3.setText("Dinner:");
        showCategoryExe1 = (TextView) findViewById(R.id.showOfNameCategory4);
        showCategoryExe1.setText("Gym:");
        showCategoryExe2 = (TextView) findViewById(R.id.showOfNameCategory5);
        showCategoryExe2.setText("Jogging:");

        firstFoodNum = (TextView)findViewById(R.id.food1Num);
        //firstFoodNum.setText(""+breakfast);
        secFoodNum = (TextView)findViewById(R.id.food2Num);
        //secFoodNum.setText(""+lunch);
        thirdFoodNum = (TextView)findViewById(R.id.food3Num);
        //thirdFoodNum.setText(""+dinner);
        firstExeNum = (TextView)findViewById(R.id.exe1Num);
        //firstExeNum.setText(""+gym);
        secExeNum = (TextView)findViewById(R.id.exe2Num);
        //secExeNum.setText(""+jogging);
        main=findViewById(R.id.mainLayout);
        main.setVerticalScrollBarEnabled(true);

        calcButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                categories = new Intent(getApplicationContext(), Categories.class);
                startActivity(categories);
                finish();

            }
        });

        seeDates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toDates = new Intent(getApplicationContext(), Dates.class);
                startActivity(toDates);
            }
        });



//        showContent.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                SharedPreferences sharedPref = getSharedPreferences("userKiloJoules", Context.MODE_PRIVATE);
////                SharedPreferences somePref = getSharedPreferences("someKils", Context.MODE_PRIVATE);
////
////                Float userKJ = sharedPref.getFloat("estimatedKiloJoules", 0);
////
////                String nameOfCat = somePref.getString("clickedCategory", "");
////
////                System.out.println("The saved content is: "+nameOfCat+" = "+ userKJ + " kJ");
////                showCategory.setText(nameOfCat+": "+userKJ+" kJ");
//
////                getData();
//                //update();
//            }
//        });

        getData();

//        update();
//        getData();


    }

    public void update(){
//        SharedPreferences sharedPref = getSharedPreferences("userKiloJoules", Context.MODE_PRIVATE);
//        SharedPreferences somePref = getSharedPreferences("someKJs", Context.MODE_PRIVATE);
//
//        Float userKJ = sharedPref.getFloat("estimatedKiloJoules", 0);
//
//        String nameOfCat = somePref.getString("clickedCategory", "");
//
//        System.out.println("The saved content is: "+nameOfCat+" = "+ userKJ + " kJ");
//
//        showCategoryFood1.setText(nameOfCat+": "+userKJ+" kJ");

        //System.out.println(getIntent().getStringExtra("savedEstimateNum"));

        if(getIntent().getStringExtra("clickedCategory").equals("Breakfast")){
            firstFoodNum.setText(""+breakfast);
        }
        else if(getIntent().getStringExtra("clickedCategory").equals("Lunch")){
            secFoodNum.setText(""+lunch);
        }
        else if(getIntent().getStringExtra("clickedCategory").equals("Dinner")){
            thirdFoodNum.setText(""+dinner);
        }
        else if(getIntent().getStringExtra("clickedCategory").equals("Gym")){
            firstExeNum.setText(""+gym);
        }
        else if(getIntent().getStringExtra("clickedCategory").equals("Jogging")){
            secExeNum.setText(""+jogging);
        }

    }

    public void getData(){
        db = new MyDatabase(this);
        Cursor data = db.getRow(today);

        if (data!=null){
            data.moveToFirst();
        }
        if (data.isFirst()){

            breakfast = 0;
            lunch = 0;
            dinner = 0;
            gym = 0;
            jogging = 0;

            ArrayList<String> list = new ArrayList<>();
            list.add(data.getString(1)+data.getString(2));
            System.out.println(Arrays.toString(list.toArray()));
            System.out.println(data.getString(2));

            breakfast+=data.getDouble(2);
            lunch += data.getDouble(3);
            dinner += data.getDouble(4);
            gym += data.getDouble(5);
            jogging += data.getDouble(6);

            while(data.moveToNext()){
                list.add(data.getString(1)+data.getString(2));
                breakfast+=data.getDouble(2);
                lunch += data.getDouble(3);
                dinner += data.getDouble(4);
                gym += data.getDouble(5);
                jogging += data.getDouble(6);
            }

            firstFoodNum.setText(""+breakfast);
            secFoodNum.setText(""+lunch);
            thirdFoodNum.setText(""+dinner);
            firstExeNum.setText(""+gym);
            secExeNum.setText(""+jogging);

//            System.out.println(Arrays.toString(list.toArray()));
//            System.out.println(list.get(2));
        }
    }
}
