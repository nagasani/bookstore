package com.example.bookstore.order;
public class SeasonalDiscountStrategy implements DiscountStrategy {
    @Override
    public double applyDiscount(double bookPrice) {
        return bookPrice * 0.90; // 10% discount
    }
}

