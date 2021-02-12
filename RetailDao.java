package com.everyday.dao;

import com.everyday.models.Category;
import com.everyday.models.FoodItems;

import java.sql.SQLException;
import java.util.List;

public interface RetailDao {

    void addCategory(Category category) throws SQLException;
    List<Category> getAllCategories();
    Category getCategoryById(long categoryId);
    boolean deleteCategory(long categoryId);
    Category updateCategory(Category category);

}
