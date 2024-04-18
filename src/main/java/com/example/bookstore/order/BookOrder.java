package com.example.bookstore.order;

public class BookOrder 
{
    private double basePrice;
    private DiscountStrategy discountStrategy;

    public BookOrder(double basePrice, DiscountStrategy discountStrategy) {
        this.basePrice = basePrice;
        this.discountStrategy = discountStrategy;
    }

    public double calculatePriceAfterDiscount() {
        return discountStrategy.applyDiscount(basePrice);
    }

    // Set a new discount strategy
    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }
}
