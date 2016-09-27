package com.softuni.homework5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;


class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_PRODUCTS = "products";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_PRICE = "price";

    private static final String DATABASE_NAME = "ProductsDB";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase database;
    private String[] allColumns = {COLUMN_NAME, COLUMN_CATEGORY, COLUMN_PRICE};

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String DATABASE_CREATE = "create table "
                + TABLE_PRODUCTS + "( "
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_NAME + " text not null, "
                + COLUMN_CATEGORY + " text not null, "
                + COLUMN_PRICE + " real not null);";
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }

    void createProduct(Product product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, product.getName());
        values.put(COLUMN_CATEGORY, product.getCategory());
        values.put(COLUMN_PRICE, product.getPrice());

        database = this.getWritableDatabase();
        database.insert(TABLE_PRODUCTS, null, values);
        database.close();
    }

    ArrayList<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();

        database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_PRODUCTS, allColumns, null, null, null, null, COLUMN_NAME + " ASC", null);
        while (cursor.moveToNext()) {
            products.add(new Product(cursor.getString(0), cursor.getString(1), cursor.getDouble(2)));
        }
        cursor.close();
        database.close();
        return products;
    }
}
