package edu.sm.service;

import edu.sm.dao.CategoryDao;
import edu.sm.dto.Category;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CategoryService implements MService<Integer, Category> {
    CategoryDao dao;
    ConnectionPool cp;

    public CategoryService() {
        dao = new CategoryDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category add(Category category) throws Exception {
        Connection conn = cp.getConnection();
        try {
            conn.setAutoCommit(false);
            dao.insert(category, conn);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return category;
    }

    @Override
    public Category modify(Category category) throws Exception {
        Connection conn = cp.getConnection();
        try {
            dao.update(category, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return category;
    }

    @Override
    public Boolean remove(Integer categoryId) throws Exception {
        Connection conn = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(categoryId, conn);
            if (result) {
                System.out.println("카테고리 삭제됨");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage()); // "해당 카테고리는 사용 중인 상품이 있어서 삭제할 수 없습니다." 메시지 출력
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return result;
    }


    @Override
    public Category get(Integer categoryId) throws Exception {
        Connection conn = cp.getConnection();
        Category category = null;
        try {
            category = dao.select(categoryId, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return category;
    }

    @Override
    public List<Category> get() throws Exception {
        Connection conn = cp.getConnection();
        List<Category> categories = null;
        try {
            categories = dao.select(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return categories;
    }
}
