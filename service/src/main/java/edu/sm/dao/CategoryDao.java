package edu.sm.dao;

import edu.sm.dto.Category;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao implements Dao<Integer, Category> {

    @Override
    public Category insert(Category category, Connection conn) throws Exception {
        return null;
    }

    @Override
    public Category update(Category category, Connection conn) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Integer categoryId, Connection conn) throws Exception {
       return false;
    }


    @Override
    public Category select(Integer categoryId, Connection conn) throws Exception {
        return null;
    }

    @Override
    public List<Category> select(Connection conn) throws Exception {
        List<Category> categories = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ALL_CATEGORIES);
            rs = ps.executeQuery();
            while (rs.next()) {
                categories.add(new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("category_detail")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return categories;
    }
}
