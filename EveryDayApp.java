package com.everyday.utility;

import com.everyday.dao.RetailDao;
import com.everyday.dao.RetailImpl;
import com.everyday.models.FoodItems;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EveryDayApp {

    public static  void main(String... args){
        RetailDao retailDao=new RetailImpl();
        FoodItems foodItems=new FoodItems();
        foodItems.setItemCode(102);
        foodItems.setItemName("Laddu");
        foodItems.setQuantity(40);
        foodItems.setUnitPrice(128);
        foodItems.setVegetarian(true);
        foodItems.setDom(LocalDate.now());
        foodItems.setDoe(LocalDate.now().plus(2, ChronoUnit.DAYS));
        try {
            retailDao.addCategory(foodItems);
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }
}
