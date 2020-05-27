package com.example.kilo_count;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Data";
    private static final String TABLE_NAME = "UserDataStorage";
    private static final String KEY_ID = "ID";
    //private static final String SECTION = "Section";
    //private static final String FOOD_EXERCISE = "Category";
    private static final String THE_DATE = "Date";
    private static final String BREAKFAST = "Breakfast";
    private static final String LUNCH = "Lunch";
    private static final String DINNER = "Dinner";
    private static final String GYM = "Gym";
    private static final String JOGGING = "Jogging";
    private static final String FOOD_TOTAL = "Food_Total";
    private static final String EXERCISE_TOTAL = "Exercise_Total";
    private static final String TOTAL = "Total";
    //private static final String QUANTITY = "KiloJoules";

    public MyDatabase(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
//        String CREATE_CAT_TABLE = "CREATE TABLE "+ TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
//                +SECTION+" TEXT,"+ FOOD_EXERCISE + " TEXT," + QUANTITY + " DOUBLE)";

        //made this change
        String CREATE_CAT_TABLE = "CREATE TABLE "+TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                +THE_DATE+" TEXT,"+ BREAKFAST +" DOUBLE,"+LUNCH+" DOUBLE,"+DINNER+" DOUBLE,"+GYM+" DOUBLE,"+JOGGING+" DOUBLE,"
                +FOOD_TOTAL+" DOUBLE,"+EXERCISE_TOTAL+" DOUBLE,"+TOTAL+" DOUBLE)";

        db.execSQL(CREATE_CAT_TABLE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }

    //made this change
    public boolean addRow(String item1, double item2, double item3, double item4, double item5,
                          double item6, double item7, double item8, double item9){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(SECTION,item);
        //values.put(FOOD_EXERCISE,item2);
        //values.put(QUANTITY,item3);

        //and this change
        values.put(THE_DATE, item1);
        values.put(BREAKFAST, item2);
        values.put(LUNCH, item3);
        values.put(DINNER, item4);
        values.put(GYM, item5);
        values.put(JOGGING, item6);
        values.put(FOOD_TOTAL, item7);
        values.put(EXERCISE_TOTAL, item8);
        values.put(TOTAL, item9);

        long x = db.insert(TABLE_NAME, null, values);

        if (x==-1){return false;}
        else{return true;}
    }

    public Cursor getTable(){
        SQLiteDatabase sq=this.getWritableDatabase();
        String command="SELECT * FROM "+ TABLE_NAME;
        return sq.rawQuery(command,null);
    }

    public Cursor getRow(String id){
        SQLiteDatabase sq=this.getWritableDatabase();
        String command="SELECT * FROM "+TABLE_NAME+" WHERE "+THE_DATE+"=\""+id+"\"";
        return sq.rawQuery(command,null);
    }


}
