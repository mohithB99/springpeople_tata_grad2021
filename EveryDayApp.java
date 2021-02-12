package com.everyday.utility;

import com.everyday.dao.RetailDao;
import com.everyday.dao.RetailImpl;
import com.everyday.models.Apparel;
import com.everyday.models.Electronics;
import com.everyday.models.FoodItems;
import com.everyday.models.Material;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class EveryDayApp {

    public static  void main(String... args){
        RetailDao retailDao=new RetailImpl();
        try {
            retailDao.addCategory(createElectronics());
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

    }
    public static FoodItems createFoodItem(){
        FoodItems foodItems=new FoodItems();
        foodItems.setItemCode(new Random().nextInt(1000000));
        foodItems.setItemName("Laddu");
        foodItems.setQuantity(40);
        foodItems.setUnitPrice(114);
        foodItems.setVegetarian(true);
        foodItems.setDom(LocalDate.now());
        foodItems.setDoe(LocalDate.now().plus(2, ChronoUnit.DAYS));
       return foodItems;
    }
    public static Apparel createApparel(){
        Apparel apparel=new Apparel();
        apparel.setItemCode(new Random().nextInt(1000000));
        apparel.setItemName("Ladies Top");
        apparel.setQuantity(40);
        apparel.setUnitPrice(2300);
        apparel.setMaterial(Material.Cotton);
        apparel.setSize(42);
        return apparel;
    }
    public static Electronics createElectronics(){
       Electronics electronics=new Electronics();
        electronics.setItemCode(new Random().nextInt(1000000));
        electronics.setItemName("TV");
        electronics.setQuantity(40);
        electronics.setUnitPrice(65000);
        electronics.setWarranty((byte)12);
        return electronics;
    }
}
