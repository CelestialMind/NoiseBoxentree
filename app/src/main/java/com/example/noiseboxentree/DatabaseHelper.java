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
    private static final String CUSTOM_NOISE_BOX_TABLE = "CUSTOM_NOISE_BOX_TABLE";
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

    private static final String CATALOG_NOISE_BOX_TABLE = "CATALOG_NOISE_BOX_TABLE";


    // constructor
    public DatabaseHelper(@Nullable Context context) {
        super(context, "noise_boxes.db", null, 1);
    }

    // necessary methods
    // Creation
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // this String is the SQL code that creates the table
        String createCustomTableStatement ="CREATE TABLE " + CUSTOM_NOISE_BOX_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY  KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_INNER_LENGTH + " INT," +
                COLUMN_INNER_WIDTH + " INT," +
                COLUMN_INNER_HEIGHT + " INT," +
                COLUMN_HAVING_SP_ADVANCED + " BOOL," +
                COLUMN_HAVING_FAN + " BOOL)";

        String createCatalogTableStatement ="CREATE TABLE " + CUSTOM_NOISE_BOX_TABLE +
                "(" + COLUMN_ID + " INTEGER PRIMARY  KEY AUTOINCREMENT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_INNER_LENGTH + " INT," +
                COLUMN_INNER_WIDTH + " INT," +
                COLUMN_INNER_HEIGHT + " INT," +
                COLUMN_HAVING_SP_ADVANCED + " BOOL," +
                COLUMN_HAVING_FAN + " BOOL)";


//                COLUMN_LENGTH + " INT," +
//                COLUMN_WIDTH + " INT," +
//                COLUMN_HEIGHT + " INT," +
        // executes the code prepared above
        sqLiteDatabase.execSQL(createCustomTableStatement);
        sqLiteDatabase.execSQL(createCatalogTableStatement);
    }

    // Upgrade
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // methods
    // Add a row
    public boolean addOne(NoiseBox noiseBox, String table){
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

        switch (table){
            case CUSTOM_NOISE_BOX_TABLE:
                // returns a boolean indicating the success of the adding
                return db.insert(CUSTOM_NOISE_BOX_TABLE, null, cv) != -1;
            case CATALOG_NOISE_BOX_TABLE:
                // returns a boolean indicating the success of the adding
                return db.insert(CATALOG_NOISE_BOX_TABLE, null, cv) != -1;
            default:
                return false;
        }
    }

    // Delete a row
    public boolean deleteOne(NoiseBox noiseBox, String table){
        // creates an instance of SQLite Database and assigns a writable database to it
        SQLiteDatabase db = getWritableDatabase();

        String queryString;
        Cursor cursor;
        boolean b;
        switch (table) {
            case CUSTOM_NOISE_BOX_TABLE:
                // prepares SQLite command
                queryString ="DELETE FROM " + CUSTOM_NOISE_BOX_TABLE + " WHERE " + COLUMN_ID + " = " + noiseBox.getId();

                // execute the prepared command and assigns the result to Cursor (it handles the retrieved rows)
                cursor = db.rawQuery(queryString, null);
                b = cursor.moveToFirst(); // if true then all is ok
                cursor.close();
                db.close();
                return b; // it will let me know if everything is ok or not
            case CATALOG_NOISE_BOX_TABLE:
                // prepares SQLite command
                queryString ="DELETE FROM " + CATALOG_NOISE_BOX_TABLE + " WHERE " + COLUMN_ID + " = " + noiseBox.getId();

                // execute the prepared command and assigns the result to Cursor (it handles the retrieved rows)
                cursor = db.rawQuery(queryString, null);
                b = cursor.moveToFirst(); // if true then all is ok
                cursor.close();
                db.close();
                return b; // it will let me know if everything is ok or not
            default:
                return false;
        }
    }

    // Retrieve a table
    public ArrayList<NoiseBox> getEverything(String table){

        ArrayList<NoiseBox> result = new ArrayList<>();

        String queryString;
        Cursor cursor;
        SQLiteDatabase db;

        switch (table) {
            case CUSTOM_NOISE_BOX_TABLE:
                queryString = "SELECT * FROM " + CUSTOM_NOISE_BOX_TABLE;

                db = this.getReadableDatabase();

                cursor = db.rawQuery(queryString, null);

                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(0);
                        String name = cursor.getString(1);
                        int iL = cursor.getInt(2);
                        int iW = cursor.getInt(3);
                        int iH = cursor.getInt(4);
                        boolean isAdv = cursor.getInt(5) == 1;
                        boolean isFan = cursor.getInt(6) == 1;

                        NoiseBox instance = new NoiseBox(id, name, iL, iW, iH, isAdv, isFan);

                        result.add(instance);
                    } while (cursor.moveToNext());
                }

                cursor.close();
                db.close();

                return result;
            case CATALOG_NOISE_BOX_TABLE:
                queryString = "SELECT * FROM " + CATALOG_NOISE_BOX_TABLE;

                db = this.getReadableDatabase();

                cursor = db.rawQuery(queryString, null);

                if (cursor.moveToFirst()) {
                    do {
                        int id = cursor.getInt(0);
                        String name = cursor.getString(1);
                        int iL = cursor.getInt(2);
                        int iW = cursor.getInt(3);
                        int iH = cursor.getInt(4);
                        boolean isAdv = cursor.getInt(5) == 1;
                        boolean isFan = cursor.getInt(6) == 1;

                        NoiseBox instance = new NoiseBox(id, name, iL, iW, iH, isAdv, isFan);

                        result.add(instance);
                    } while (cursor.moveToNext());
                }

                cursor.close();
                db.close();

                return result;
            default:
                return null;
        }
    }
}
