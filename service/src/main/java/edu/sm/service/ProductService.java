package edu.sm.service;

import edu.sm.dao.ProductDao;
import edu.sm.dto.Product;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ProductService implements MService<Integer, Product> {
    ProductDao dao;
    ConnectionPool cp;

    public ProductService() {
        dao = new ProductDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product add(Product product) throws Exception {
        Connection conn = cp.getConnection();
        try {
            conn.setAutoCommit(false);
            dao.insert(product, conn);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return product;
    }

    @Override
    public Product modify(Product product) throws Exception {
        Connection conn = cp.getConnection();
        try {
            dao.update(product, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return product;
    }

    @Override
    public Boolean remove(Integer pid) throws Exception {
        throw new UnsupportedOperationException("상품 삭제 불가능");
    }

    @Override
    public Product get(Integer pid) throws Exception {
        Connection conn = cp.getConnection();
        Product product = null;
        try {
            product = dao.select(pid, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return product;
    }

    @Override
    public List<Product> get() throws Exception {
        Connection conn = cp.getConnection();
        List<Product> products = null;
        try {
            products = dao.select(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return products;
    }

    public List<Product> getPublicProducts() throws Exception {
        Connection conn = cp.getConnection();
        List<Product> products = null;
        try {
            products = dao.selectPublicProducts(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return products;
    }

    // 카테고리별로 상품을 조회하는 메서드
    public List<Product> getProductsByCategory(int categoryId) throws Exception {
        Connection conn = cp.getConnection();
        List<Product> products;
        try {
            products = dao.selectByCategory(categoryId, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return products;
    }

    // 상품명을 이용해 조회하는 메서드
    public List<Product> getByName(String pname) throws Exception {
        Connection conn = cp.getConnection();
        List<Product> products = null;
        try {
            products = dao.selectByName(pname, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return products;
    }

    // 공개된 상품을 카테고리별로 조회
    public List<Product> getPublicProductsByCategory(int categoryId) throws Exception {
        Connection conn = cp.getConnection();
        List<Product> products;
        try {
            products = dao.selectPublicByCategory(categoryId, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return products;
    }

    // 공개된 상품을 상품명으로 조회
    public List<Product> getPublicByName(String pname) throws Exception {
        Connection conn = cp.getConnection();
        List<Product> products = null;
        try {
            products = dao.selectPublicByName(pname, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return products;
    }
}
