package edu.sm.dao;

import edu.sm.dto.Pay;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PayDao implements Dao<Integer, Pay> {
    @Override
    public Pay insert(Pay pay, Connection conn) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sql.INSERT_PAY);
            ps.setInt(1, pay.getOid());
            ps.setInt(2, pay.getPayPrice());
            ps.setString(3, pay.getPayMethod());
            ps.setLong(4, pay.getCard());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
        return pay;
    }

    @Override
    public Pay update(Pay pay, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Pay records cannot be updated.");
    }

    @Override
    public boolean delete(Integer id, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Pay records cannot be deleted.");
    }

    // 특정 주문 ID(oid)에 대한 결제 정보 조회
    public Pay selectByOrderId(int oid, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pay pay = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_PAY_BY_ORDER_ID);
            ps.setInt(1, oid);
            rs = ps.executeQuery();
            if (rs.next()) {
                pay = new Pay(
                        rs.getInt("pay_id"),
                        rs.getInt("oid"),
                        rs.getInt("pay_price"),
                        rs.getString("pay_method"),
                        rs.getLong("card"),
                        rs.getTimestamp("pay_date")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return pay;
    }

    @Override
    public Pay select(Integer payId, Connection conn) throws Exception {
        return null;
    }

    @Override
    public List<Pay> select(Connection conn) throws Exception {
        return null;
    }

}
