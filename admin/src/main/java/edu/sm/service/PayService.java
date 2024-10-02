package edu.sm.service;

import edu.sm.dao.PayDao;
import edu.sm.dto.Pay;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class PayService implements MService<Integer, Pay> {
    PayDao dao;
    ConnectionPool cp;

    public PayService() {
        dao = new PayDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Pay add(Pay pay) throws Exception {
        Connection conn = cp.getConnection();
        try {
            conn.setAutoCommit(false);
            dao.insert(pay, conn);
            conn.commit();
            System.out.println("Pay record inserted successfully.");
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return pay;
    }

    @Override
    public Pay modify(Pay pay) throws Exception {
        throw new UnsupportedOperationException("Pay records cannot be updated.");
    }

    @Override
    public Boolean remove(Integer id) throws Exception {
        throw new UnsupportedOperationException("Pay records cannot be deleted.");
    }

    @Override
    public Pay get(Integer payId) throws Exception {
        Connection conn = cp.getConnection();
        Pay pay = null;
        try {
            pay = dao.select(payId, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return pay;
    }
    // 특정 주문 ID로 결제 정보 조회
    public Pay getByOrderId(int oid) throws Exception {
        Connection conn = cp.getConnection();
        Pay pay = null;
        try {
            pay = dao.selectByOrderId(oid, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return pay;
    }

    @Override
    public List<Pay> get() throws Exception {
        Connection conn = cp.getConnection();
        List<Pay> pays = null;
        try {
            pays = dao.select(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return pays;
    }
}
