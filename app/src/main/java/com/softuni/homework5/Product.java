package com.softuni.homework5;

class Product {

    private String mName, mCategory;
    private double mPrice;

    Product(String mName, String mCategory, double mPrice) {
        this.mName = mName;
        this.mCategory = mCategory;
        this.mPrice = mPrice;
    }

    String getName() {
        return mName;
    }

    String getCategory() {
        return mCategory;
    }

    double getPrice() {
        return mPrice;
    }
}
