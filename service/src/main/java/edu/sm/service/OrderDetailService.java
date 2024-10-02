package edu.sm.service;

import edu.sm.dao.OrderDetailDao;
import edu.sm.dto.OrderDetail;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailService implements MService<Integer, OrderDetail> {

    OrderDetailDao dao;
    ConnectionPool cp;

    public OrderDetailService() {
        dao = new OrderDetailDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderDetail add(OrderDetail orderDetail) throws Exception {
        Connection conn = cp.getConnection();
        try {
            conn.setAutoCommit(false);
            dao.insert(orderDetail, conn);
            conn.commit();
            System.out.println("OrderDetail 추가됨");
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return orderDetail;
    }

    @Override
    public OrderDetail modify(OrderDetail orderDetail) throws Exception {
        // OrderDetail은 수정이 불가능하다는 예외 처리
        throw new UnsupportedOperationException("Order details cannot be modified.");
    }

    @Override
    public Boolean remove(Integer orderDetailId) throws Exception {
        // OrderDetail은 삭제가 불가능하다는 예외 처리
        throw new UnsupportedOperationException("Order details cannot be deleted.");
    }

    @Override
    public OrderDetail get(Integer id) throws Exception {
        Connection conn = cp.getConnection();
        OrderDetail orderDetail = null;
        try {
            orderDetail = dao.select(id, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return orderDetail;
    }

    @Override
    public List<OrderDetail> get() throws Exception {
        Connection conn = cp.getConnection();
        List<OrderDetail> orderDetails = null;
        try {
            orderDetails = dao.select(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return orderDetails;
    }

    // 특정 주문에 대한 주문 상세 목록을 조회하는 메서드
    public List<OrderDetail> getByOid(int oid) throws Exception {
        Connection conn = cp.getConnection();
        List<OrderDetail> orderDetails = null;
        try {
            orderDetails = dao.selectByOid(oid, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return orderDetails;
    }
}
