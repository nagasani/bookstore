package com.example.bookstore.order;

public class ClearanceDiscountStrategy implements DiscountStrategy 
{
    @Override
    public double applyDiscount(double bookPrice) {
        return bookPrice * 0.50; // 50% discount
    }
}
