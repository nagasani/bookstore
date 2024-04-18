package com.example.bookstore.order;
public class NoDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double bookPrice) {
        return bookPrice;
    }
}

