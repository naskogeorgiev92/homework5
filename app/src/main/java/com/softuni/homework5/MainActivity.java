package com.softuni.homework5;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context ctx = this;
    Button mBtnInsert, mBtnDelete, mBtnDisplay;
    ArrayList<Product> mData;
    MyAdapter mAdapter;
    RecyclerView mRecyclerView;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(ctx);
        mData = new ArrayList<>();
        initializeViews();
    }

    private void initializeViews() {
        mBtnInsert = (Button) findViewById(R.id.btn_insert_data);
        mBtnDelete = (Button) findViewById(R.id.btn_delete_data);
        mBtnDisplay = (Button) findViewById(R.id.btn_display_data);
        mBtnInsert.setOnClickListener(this);
        mBtnDelete.setOnClickListener(this);
        mBtnDisplay.setOnClickListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ctx));

        mAdapter = new MyAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void insertData() {
        dbHelper.createProduct(new Product("Apple", "Fruit", 0.67));
        dbHelper.createProduct(new Product("Banana", "Fruit", 1.10));
        dbHelper.createProduct(new Product("Grape", "Fruit", 1.43));
        dbHelper.createProduct(new Product("Melons", "Fruit", 2.27));
        dbHelper.createProduct(new Product("Oranges", "Fruit", 1.02));
        dbHelper.createProduct(new Product("Broccoli", "Vegetable", 0.97));
        dbHelper.createProduct(new Product("Celery", "Vegetable", 1.31));
        dbHelper.createProduct(new Product("Garlic", "Vegetable", 0.35));
        dbHelper.createProduct(new Product("Potato", "Vegetable", 2.20));
        dbHelper.createProduct(new Product("Zucchini", "Vegetable", 2.60));
        dbHelper.createProduct(new Product("Steak", "Meat", 3.10));
        dbHelper.createProduct(new Product("Wings", "Meat", 3.90));
        dbHelper.createProduct(new Product("Ham", "Meat", 4.30));
        dbHelper.createProduct(new Product("Bacon", "Meat", 2.85));
        dbHelper.createProduct(new Product("Fish", "Meat", 5.50));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_insert_data:
                insertData();
                break;
            case R.id.btn_delete_data:
                dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 1);
                break;
            case R.id.btn_display_data:
                mData.clear();
                mData.addAll(dbHelper.getProducts());
                mAdapter.notifyDataSetChanged();
                break;
        }
    }

}
