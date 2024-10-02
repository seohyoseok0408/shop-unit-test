package edu.sm.service;

import edu.sm.dao.CartDao;
import edu.sm.dto.Cart;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CartService implements MService<Integer, Cart> {
    CartDao dao;
    ConnectionPool cp;

    public CartService() {
        dao = new CartDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cart add(Cart cart) throws Exception {
        Connection conn = cp.getConnection();
        try {
            conn.setAutoCommit(false);
            dao.insert(cart, conn);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return cart;
    }

    @Override
    public Cart modify(Cart cart) throws Exception {
        Connection conn = cp.getConnection();
        try {
            dao.update(cart, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return cart;
    }

    @Override
    public Boolean remove(Integer cartId) throws Exception {
        throw new UnsupportedOperationException("removeByCidPid 를 사용하세요");
    }

    @Override
    public Cart get(Integer cid) throws Exception {
        throw new UnsupportedOperationException("지원되지 않는 기능");

    }

    @Override
    public List<Cart> get() throws Exception {
        throw new UnsupportedOperationException("지원되지 않는 기능");

    }

    public List<Cart> getCartByCid(Integer cid) throws Exception {
        Connection conn = cp.getConnection();
        List<Cart> carts = null;
        try {
            carts = dao.selectByCid(cid, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return carts;
    }

    public boolean removeByCidPid(int cid, int pid) throws Exception {
        Connection conn = cp.getConnection();
        boolean result;
        try {
            result = dao.deleteByCidPid(cid, pid, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return result;
    }

}