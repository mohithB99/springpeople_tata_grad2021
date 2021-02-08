package com.tata.retailapp;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);

        HashMap<Integer, Product> productInfoMap = new HashMap<>();

        productInfoMap.put(1, new Product(1,(float)22.50));
        productInfoMap.put(2, new Product(2,(float)44.50));
        productInfoMap.put(3, new Product(3,(float)9.98));

        HashMap<Integer, Integer> salesMap = new HashMap<>();

        System.out.println("Enter sales in pairs of Product Number and Quantity Sold:");

        for (int i = 0; i < 3; i++) {
            Integer a = scanner.nextInt();
            Integer b = scanner.nextInt();
            salesMap.put(a, b);
        }
        float totalRetail = 0;

        for (Map.Entry<Integer, Integer> entry : salesMap.entrySet()) {
            switch (entry.getKey()){
                case 1:
                    productInfoMap.get(1).setQuantity(entry.getValue());
                    System.out.println(productInfoMap.get(1));
                    totalRetail+=productInfoMap.get(1).getRetailPrice();
                    break;
                case 2:
                    productInfoMap.get(2).setQuantity(entry.getValue());
                    System.out.println(productInfoMap.get(2));
                    totalRetail+=productInfoMap.get(2).getRetailPrice();
                    break;
                case 3:
                    productInfoMap.get(3).setQuantity(entry.getValue());
                    System.out.println(productInfoMap.get(3));
                    totalRetail+=productInfoMap.get(3).getRetailPrice();
                    break;
            }
        }
        System.out.println("Total Retail: " +  totalRetail);



    }
}
