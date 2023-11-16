package com.example.th6;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class productHelper extends SQLiteOpenHelper {
    // Database name and version
    private static final String DATABASE_NAME = "product.db";
    private static final int DATABASE_VERSION = 1;

    // Table name and columns
    private static final String TABLE_NAME = "product";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";


    // Constructor
    public productHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create database
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create table
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " TEXT PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_PRICE + " DOUBLE" +")";
        // Execute SQL statement
        db.execSQL(CREATE_TABLE);
    }

    // Upgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // SQL statement to drop table
        String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        // Execute SQL statement
        db.execSQL(DROP_TABLE);
        // Create table again
        onCreate(db);
    }

    // Add product
    public void addproduct(product pd) {
        // Get writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create content values to store data
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, pd.getId());
        values.put(COLUMN_NAME, pd.getName());
        values.put(COLUMN_PRICE, pd.getPrice());
        // Insert data into table
        db.insert(TABLE_NAME, null, values);
        // Close database
        db.close();
    }

    // Get all product
    @SuppressLint("Range")
    public List<product> getAllproduct() {
        // Get readable database
        SQLiteDatabase db = this.getReadableDatabase();
        // Create list to store students
        List<product> products = new ArrayList<>();
        // SQL statement to select all records
        String SELECT_ALL = "SELECT * FROM " + TABLE_NAME;
        // Execute SQL statement and get cursor
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        // Loop through cursor and create students
        if (cursor.moveToFirst()) {
            do {
                product pd = new product();
                pd.setId(cursor.getString(cursor.getColumnIndex(COLUMN_ID)));
                pd.setName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)));
                pd.setPrice(cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE)));
                // Add product to list
                products.add(pd);
            } while (cursor.moveToNext());
        }
        // Close cursor and database
        cursor.close();
        db.close();
        // Return list of product
        return products;
    }

    // Delete product
    public void deleteproduct(product pd) {
        // Get writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // SQL statement to delete record
        String DELETE = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";
        // Execute SQL statement with student id as argument
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(pd.getId())});
        // Close database
        db.close();
    }

    // Update product
    public void updateproduct(product pd) {
        // Get writable database
        SQLiteDatabase db = this.getWritableDatabase();
        // Create content values to store data
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, pd.getName());
        values.put(COLUMN_PRICE, pd.getPrice());
        // SQL statement to update record
        String UPDATE = "UPDATE " + TABLE_NAME + " SET "
                + COLUMN_NAME + " = ?,"
                + COLUMN_PRICE + " = ?,"
                + " WHERE " + COLUMN_ID + " = ?";
        // Execute SQL statement with student data and id as arguments
        db.update(TABLE_NAME, values, COLUMN_ID + " = ?", new String[]{String.valueOf(pd.getId())});
        // Close database
        db.close();
    }
}
