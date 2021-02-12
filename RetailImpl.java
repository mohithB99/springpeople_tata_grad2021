package com.everyday.dao;

import com.everyday.helpers.PostgresConnHelper;
import com.everyday.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RetailImpl implements RetailDao {
    private Connection conn;
    private PreparedStatement pre,epre,fpre,apre;
    private Statement statement;
    private ResultSet resultSet;
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
               fpre.setLong(3,category.getItemCode());
               fpre.setBoolean(4,((FoodItems) category).isVegetarian());

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
    public List<? extends Category> getAllCategories(int type) throws SQLException {
         conn=PostgresConnHelper.getConnection();
         List<Category> categoryList=null;
         List<FoodItems> foodItemsList=new ArrayList<FoodItems>();
         List<Apparel> apparelList=new ArrayList<Apparel>();
         List<Electronics> electronicsList=new ArrayList<Electronics>();
         FoodItems foodItems=null;
         Apparel apparel=null;
         Electronics electronics=null;
         String query=null;
        switch(type){
            case 1:
                categoryList=new ArrayList<Category>();
                query=resourceBundle.getString("selectfooditems");
                statement=conn.createStatement();
                resultSet=statement.executeQuery(query);
                while(resultSet.next()){
                  foodItems=new FoodItems();
                  foodItems.setItemCode(resultSet.getLong(1));
                  foodItems.setItemName(resultSet.getString(2));
                  foodItems.setQuantity(resultSet.getInt(3));
                  foodItems.setVegetarian(resultSet.getBoolean(4));
                  foodItemsList.add(foodItems);
                }
                categoryList.addAll(foodItemsList);
                break;
            case 2:
                categoryList=new ArrayList<Category>();
                query=resourceBundle.getString("selectapparels");
                statement=conn.createStatement();
                resultSet=statement.executeQuery(query);
                while(resultSet.next()){
                    apparel=new Apparel();
                    apparel.setItemCode(resultSet.getLong(1));
                    apparel.setItemName(resultSet.getString(2));
                    apparel.setQuantity(resultSet.getInt(3));
                    apparel.setSize(resultSet.getInt(4));
                   if(resultSet.getInt(5)==1)
                      apparel.setMaterial(Material.Cotton);
                   else
                       apparel.setMaterial(Material.Woolen);
                    apparelList.add(apparel);
                }
                categoryList.addAll(apparelList);
                break;
            case 3:
                categoryList=new ArrayList<Category>();
                query=resourceBundle.getString("selectelectronics");
                statement=conn.createStatement();
                resultSet=statement.executeQuery(query);
                while(resultSet.next()){
                   electronics=new Electronics();
                    electronics.setItemCode(resultSet.getLong(1));
                    electronics.setItemName(resultSet.getString(2));
                    electronics.setQuantity(resultSet.getInt(3));
                     electronics.setWarranty(resultSet.getByte(4));
                    electronicsList.add(electronics);
                }
                categoryList.addAll(electronicsList);
                break;

        }

      return categoryList;
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
