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
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sql.INSERT_CATEGORY);
            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getCategoryDetail());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
        return category;
    }

    @Override
    public Category update(Category category, Connection conn) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sql.UPDATE_CATEGORY);
            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getCategoryDetail());
            ps.setInt(3, category.getCategoryId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
        return category;
    }

    @Override
    public boolean delete(Integer categoryId, Connection conn) throws Exception {
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            ps = conn.prepareStatement(Sql.DELETE_CATEGORY);
            ps.setInt(1, categoryId);
            int result = ps.executeUpdate();
            if (result == 1) {
                flag = true;
            }
        } catch (SQLException e) {
            // 참조 무결성 위반 에러 처리
            if (e.getSQLState().equals("23000")) { // 23000은 MySQL에서 참조 무결성 위반 시 발생하는 에러 코드
                throw new Exception("해당 카테고리는 사용 중인 상품이 있어서 삭제할 수 없습니다.");
            } else {
                throw e; // 다른 SQL 에러일 경우 다시 던짐
            }
        } finally {
            if (ps != null) ps.close();
        }
        return flag;
    }


    @Override
    public Category select(Integer categoryId, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Category category = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_CATEGORY_BY_ID);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            if (rs.next()) {
                category = new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getString("category_detail")
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return category;
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
