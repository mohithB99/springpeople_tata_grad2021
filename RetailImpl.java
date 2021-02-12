package com.everyday.dao;

import com.everyday.helpers.PostgresConnHelper;
import com.everyday.models.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class RetailImpl implements RetailDao {
    private Connection conn;
    private PreparedStatement pre,epre,fpre,apre;
    private ResourceBundle resourceBundle;
    private FoodItems foodItem;
    private Apparel apparel;
    private Electronics electronics;


    public RetailImpl(){
        conn= PostgresConnHelper.getConnection();
        if(conn!=null)
         System.out.println("Connection ready...");
        else
         System.out.println("Connection has issue...");
        resourceBundle=ResourceBundle.getBundle("db");
    }

    @Override
    public void addCategory(Category category) throws SQLException {
        String addCategory=resourceBundle.getString("addcategory");
        String addFoodItem=resourceBundle.getString("addfooditems");
        String addApparel=resourceBundle.getString("addapparel");
        String addElectronics=resourceBundle.getString("addelectronics");
        try {
            pre=conn.prepareStatement(addCategory);
            epre=conn.prepareStatement(addElectronics);
            fpre=conn.prepareStatement(addFoodItem);
            apre=conn.prepareStatement(addApparel);
            pre.setLong(1,category.getItemCode());
            pre.setString(2,category.getItemName());
            pre.setLong(3,category.getUnitPrice());
            pre.setInt(4,category.getQuantity());
            pre.executeUpdate();
           if(category instanceof FoodItems){
               fpre.setDate(1, Date.valueOf(((FoodItems) category).getDoe()));
               fpre.setDate(2,Date.valueOf(((FoodItems) category).getDom()));
               fpre.setBoolean(3,((FoodItems) category).isVegetarian());
               fpre.setLong(4,category.getItemCode());
               fpre.executeUpdate();
           }
            if(category instanceof Apparel){
               apparel= (Apparel) category;
                apre.setInt(1, apparel.getSize());
                if(apparel.getMaterial().equals(Material.Cotton))
                   apre.setInt(2,1);
                else
                    apre.setInt(2,2);
                apre.setLong(3,category.getItemCode());
                apre.executeUpdate();
            }
            if(category instanceof Electronics){
                electronics= (Electronics) category;
                epre.setInt(1, electronics.getWarranty());
                epre.setLong(2,category.getItemCode());
                epre.executeUpdate();
            }
           conn.commit();


        } catch (SQLException exception) {
           System.out.println(exception.getMessage());

                conn.rollback();

        }
        finally {
            conn.close();
        }


    }

    @Override
    public List<Category> getAllCategories() {
        return null;
    }

    @Override
    public Category getCategoryById(long categoryId) {
        return null;
    }

    @Override
    public boolean deleteCategory(long categoryId) {
        return false;
    }

    @Override
    public Category updateCategory(Category category) {
        return null;
    }
}
