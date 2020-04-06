package com.example.letsgopubg.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final String DB_NAME = "fb_mei_bank";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate: started");
        String createUserTable = "CREATE TABLE users (_id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT NOT NULL, " +
                "password TEXT NOT NULL, " +
                "first_name TEXT, last_name TEXT)";


        String createItemsTable = "CREATE TABLE items (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, image_url TEXT," +
                "description TEXT)";

        sqLiteDatabase.execSQL(createUserTable);

        sqLiteDatabase.execSQL(createItemsTable);

        addInitialItems(sqLiteDatabase);
    }

    private void addInitialItems(SQLiteDatabase db) {
        Log.d(TAG, "addInitialItems: started");
        ContentValues values = new ContentValues();
        values.put("name", "Bike");
        values.put("image_url", "https://cdn.shopify.com/s/files/1/0903/4494/products/Smashing-Pumpkin-GX-Eagle-complete-front-white.jpg");
        values.put("description", "The perfect mountain bike");

        db.insert("items", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}



