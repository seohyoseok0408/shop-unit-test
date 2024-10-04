package edu.sm.service;

import edu.sm.dao.OrderDao;
import edu.sm.dto.Order;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class OrderService implements MService<Integer, Order> {
    OrderDao dao;
    ConnectionPool cp;

    public OrderService() {
        dao = new OrderDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order add(Order order) throws Exception {
        Connection conn = cp.getConnection();
        try {
            conn.setAutoCommit(false);
            dao.insert(order, conn);
            conn.commit();
            System.out.println("OrderService add() 실행됨");
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return order;
    }

    @Override
    public Order modify(Order order) throws Exception {
        throw new UnsupportedOperationException("주문 삭제 후 다시  주문부탁");
    }

    @Override
    public Boolean remove(Integer oid) throws Exception {
        throw new UnsupportedOperationException("주문은 삭제할 수 없습니다.");
    }

    @Override
    public Order get(Integer oid) throws Exception {
        Connection conn = cp.getConnection();
        Order order = null;
        try {
            order = dao.select(oid, conn);
            System.out.println("OrderService get(oid) 함수 실행됨");
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return order;
    }

    @Override
    public List<Order> get() throws Exception {
        Connection conn = cp.getConnection();
        List<Order> orders = null;
        try {
            orders = dao.select(conn);
            System.out.println("OrderService get() 함수 실행됨");
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return orders;
    }


    // 특정 고객의 주문 목록을 조회하는 메서드
    public List<Order> getByCustomerId(int cid) throws Exception {
        Connection conn = cp.getConnection();
        List<Order> orders = null;
        try {
            orders = dao.selectByCid(cid, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return orders;
    }
}
