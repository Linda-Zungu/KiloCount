package com.example.kilo_count;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("All")
public class Calculator extends AppCompatActivity {

    Button saveButton, one, two, three, four,
            five, six, seven, eight, nine, zero,
            point, delete;

    TextView numbersTextView;
    MyDatabase db;

    String today;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        saveButton = (Button)findViewById(R.id.saveEstimationButton);
        saveButton.setText("Save");

        numbersTextView = (TextView)findViewById(R.id.numbersTextView);
        numbersTextView.setText("");

        db = new MyDatabase(this);


        //Connecting the buttons to their respective variables
        one = (Button)findViewById(R.id.oneButton);
        two = (Button)findViewById(R.id.twoButton);
        three = (Button)findViewById(R.id.threeButton);
        four = (Button)findViewById(R.id.fourButton);
        five = (Button)findViewById(R.id.fiveButton);
        six = (Button)findViewById(R.id.sixButton);
        seven = (Button)findViewById(R.id.sevenButton);
        eight = (Button)findViewById(R.id.eightButton);
        nine = (Button)findViewById(R.id.nineButton);
        zero = (Button)findViewById(R.id.zeroButton);
        delete = (Button)findViewById(R.id.deleteButton);
        point = (Button)findViewById(R.id.pointButton);

        //


        DateFormat dateFormat=new SimpleDateFormat("dd MMMM yyyy");
        Date date=new Date();
        today=dateFormat.format(date);

        System.out.println(today);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                float saveEstimatedNum = Float.parseFloat(""+numbersTextView.getText());
                String saveEstimatedNum = ""+numbersTextView.getText();
//
//                SharedPreferences sharedPref = getSharedPreferences("userKiloJoules", Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putFloat("estimatedKiloJoules", saveEstimatedNum);
//                editor.apply();

                Intent backToMain = new Intent(getApplicationContext(), MainActivity.class);
//                System.out.println(saveEstimatedNum);
                backToMain.putExtra("savedEstimateNum", saveEstimatedNum);
                backToMain.putExtra("clickedCategory", getIntent().getStringExtra("clickedCategory"));

                System.out.println((getIntent().getStringExtra("clickedCategory")).getClass());
                System.out.println(saveEstimatedNum);

//                boolean x=db.addRow("food", getIntent().getStringExtra("clickedCategory"), Double.parseDouble(saveEstimatedNum));
//                //boolean x = db.addRow(today, );
//                if (x){
//                    toast(x);
//                }
//                else{
//                    toast(x);
//                }
                //Made this change
                boolean x=false;
                if(getIntent().getStringExtra("clickedCategory").equals("Breakfast")){
                    x = db.addRow(today, Double.parseDouble(saveEstimatedNum),0,0,0,0,
                            Double.parseDouble(saveEstimatedNum),0,Double.parseDouble(saveEstimatedNum));
                }
                else if(getIntent().getStringExtra("clickedCategory").equals("Lunch")){
                    x = db.addRow(today, 0,Double.parseDouble(saveEstimatedNum),0,0,0,
                            Double.parseDouble(saveEstimatedNum),0,Double.parseDouble(saveEstimatedNum));
                }
                else if(getIntent().getStringExtra("clickedCategory").equals("Dinner")){
                    x = db.addRow(today, 0,0,Double.parseDouble(saveEstimatedNum),0,0,
                            Double.parseDouble(saveEstimatedNum),0,Double.parseDouble(saveEstimatedNum));
                }
                else if(getIntent().getStringExtra("clickedCategory").equals("Gym")){
                    x = db.addRow(today, 0,0,0,Double.parseDouble(saveEstimatedNum),0,
                            0,Double.parseDouble(saveEstimatedNum),0-Double.parseDouble(saveEstimatedNum));
                }
                else if(getIntent().getStringExtra("clickedCategory").equals("Jogging")){
                    x = db.addRow(today, 0,0,0,0,Double.parseDouble(saveEstimatedNum),
                            0,Double.parseDouble(saveEstimatedNum),0-Double.parseDouble(saveEstimatedNum));
                }

                if (x){
                    toast(x);
                }
                else{
                    toast(x);
                }

                startActivity(backToMain);
            }
        });




        //Buttons on Calculator
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (numbersTextView.getText().length()>0){
                    StringBuffer sb = new StringBuffer(numbersTextView.getText());
                    sb = sb.deleteCharAt(numbersTextView.getText().length()-1);
                    numbersTextView.setText(sb.toString());
                }
            }
        });

        point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbersTextView.setText(numbersTextView.getText()+".");
            }
        });

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbersTextView.setText(numbersTextView.getText()+"1");
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //numbersTextView.setText("2");
                numbersTextView.setText(numbersTextView.getText()+"2");
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbersTextView.setText(numbersTextView.getText()+"3");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbersTextView.setText(numbersTextView.getText()+"4");
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbersTextView.setText(numbersTextView.getText()+"5");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbersTextView.setText(numbersTextView.getText()+"6");
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbersTextView.setText(numbersTextView.getText()+"7");
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbersTextView.setText(numbersTextView.getText()+"8");
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbersTextView.setText(numbersTextView.getText()+"9");
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numbersTextView.setText(numbersTextView.getText()+"0");
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent backToCategories = new Intent(getApplicationContext(), Categories.class);
        startActivity(backToCategories);
    }

    public void toast(boolean k){
        if(k == true){
            Toast toast = Toast.makeText(getApplicationContext(), "Data Saved", Toast.LENGTH_SHORT);
            toast.show();
        }

        else{
            Toast toast = Toast.makeText(getApplicationContext(), "Data Not Saved", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
