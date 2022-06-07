package com.example.noiseboxentree;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // names for the DB
    private static final String NOISE_BOX_TABLE = "NOISE_BOX_TABLE";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_NAME = "NAME";
    private static final String COLUMN_LENGTH = "LENGTH";
    private static final String COLUMN_WIDTH = "WIDTH";
    private static final String COLUMN_HEIGHT = "HEIGHT";
    private static final String COLUMN_INNER_LENGTH = "INNER_LENGTH";
    private static final String COLUMN_INNER_WIDTH = "INNER_WIDTH";
    private static final String COLUMN_INNER_HEIGHT = "INNER_HEIGHT";
    private static final String COLUMN_HAVING_SP_ADVANCED = "HAVING_SP_ADVANCED";
    private static final String COLUMN_HAVING_FAN = "HAVING_FAN";


    // constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, "noise_boxes.db", null, 1);
    }

    // necessary methods
    // Creation
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // this String is the SQL code that creates the table
        String createTableStatement ="CREATE TABLE " + NOISE_BOX_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY  KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_LENGTH + " INT," +
                COLUMN_WIDTH + " INT," +
                COLUMN_HEIGHT + " INT," +
                COLUMN_INNER_LENGTH + " INT," +
                COLUMN_INNER_WIDTH + " INT," +
                COLUMN_INNER_HEIGHT + " INT," +
                COLUMN_HAVING_SP_ADVANCED + " BOOL," +
                COLUMN_HAVING_FAN + " BOOL)";

        // executes the code prepared above
        sqLiteDatabase.execSQL(createTableStatement);
    }

    // Upgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // methods
    // Add a row
    public boolean addOne(NoiseBox noiseBox){
        // creates an instance of SQLiteDatabase and assigns a writable database to it
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues(); // class that helps to handle data

        // puts the data from this NoiseBox instance into a map to the keys representing the columns
        cv.put(COLUMN_NAME, noiseBox.getName());
        cv.put(COLUMN_LENGTH, noiseBox.getLength());
        cv.put(COLUMN_WIDTH, noiseBox.getWidth());
        cv.put(COLUMN_HEIGHT, noiseBox.getHeight());
        cv.put(COLUMN_INNER_LENGTH, noiseBox.getInnerLength());
        cv.put(COLUMN_INNER_WIDTH, noiseBox.getInnerWidth());
        cv.put(COLUMN_INNER_HEIGHT, noiseBox.getInnerHeight());
        cv.put(COLUMN_HAVING_SP_ADVANCED, noiseBox.isHavingSpAdvanced());
        cv.put(COLUMN_HAVING_FAN, noiseBox.isHavingFan());

        // returns a boolean indicating the success of the adding
        return db.insert(NOISE_BOX_TABLE, null, cv) != -1;
    }

    // Delete a row
    public boolean deleteOne(NoiseBox noiseBox){
        // creates an instance of SQLite Database and assigns a writable database to it
        SQLiteDatabase db = getWritableDatabase();
        // prepares SQLite command
        String queryString ="DELETE FROM " + NOISE_BOX_TABLE + " WHERE " + COLUMN_ID + " = " + noiseBox.getId();

        // execute the prepared command and assigns the result to Cursor (it handles the retrieved rows)
        Cursor cursor = db.rawQuery(queryString, null);
        boolean b = cursor.moveToFirst(); // if true then all is ok
        cursor.close();
        db.close();
        return b; // it will let me know if everything is ok or not
    }

    // Retrieve a table
    public ArrayList<NoiseBox> getEverything(){

        ArrayList<NoiseBox> result = new ArrayList<>();

        String queryString = "SELECT * FROM " + NOISE_BOX_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()){
            do{
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
//                int l = cursor.getInt(2);
//                int w = cursor.getInt(3);
//                int h = cursor.getInt(4);
                int iL = cursor.getInt(5);
                int iW = cursor.getInt(6);
                int iH = cursor.getInt(7);
                boolean isAdv = cursor.getInt(8) == 1;
                boolean isFan = cursor.getInt(9) == 1;

                NoiseBox instance = new NoiseBox(id, name, iL, iW, iH, isAdv, isFan);

                result.add(instance);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return result;
    }
}
