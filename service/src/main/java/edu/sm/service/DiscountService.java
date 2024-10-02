package edu.sm.service;

import edu.sm.dao.DiscountDao;
import edu.sm.dto.Discount;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DiscountService implements MService<Integer, Discount> {
    DiscountDao dao;
    ConnectionPool cp;

    public DiscountService() {
        dao = new DiscountDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Discount add(Discount discount) throws Exception {
        Connection conn = cp.getConnection();
        try {
            conn.setAutoCommit(false);
            dao.insert(discount, conn);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return discount;
    }

    @Override
    public Discount modify(Discount discount) throws Exception {
        Connection conn = cp.getConnection();
        try {
            dao.update(discount, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return discount;
    }

    @Override
    public Boolean remove(Integer disId) throws Exception {
        Connection conn = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(disId, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return result;
    }

    @Override
    public Discount get(Integer disId) throws Exception {
        Connection conn = cp.getConnection();
        Discount discount = null;
        try {
            discount = dao.select(disId, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return discount;
    }

    @Override
    public List<Discount> get() throws Exception {
        Connection conn = cp.getConnection();
        List<Discount> discounts = null;
        try {
            discounts = dao.select(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return discounts;
    }
}
