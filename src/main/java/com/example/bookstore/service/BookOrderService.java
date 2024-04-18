package com.example.bookstore.service;

import org.springframework.stereotype.Service;
import com.example.bookstore.order.BookOrder;
import com.example.bookstore.order.ClearanceDiscountStrategy;
import com.example.bookstore.order.DiscountStrategy;
import com.example.bookstore.order.NoDiscountStrategy;
import com.example.bookstore.order.SeasonalDiscountStrategy;

@Service
public class BookOrderService 
{
    public double processOrder(double basePrice, String discountType) 
    {
        DiscountStrategy strategy = determineStrategy(discountType);
        BookOrder order = new BookOrder(basePrice, strategy);
        return order.calculatePriceAfterDiscount();
    }

    private DiscountStrategy determineStrategy(String discountType) 
    {
        switch (discountType) {
            case "seasonal":
                return new SeasonalDiscountStrategy();
            case "clearance":
                return new ClearanceDiscountStrategy();
            default:
                return new NoDiscountStrategy();
        }
    }
}
