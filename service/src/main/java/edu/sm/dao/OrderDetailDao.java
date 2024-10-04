package edu.sm.dao;

import edu.sm.dto.OrderDetail;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDao implements Dao<Integer, OrderDetail> {

    @Override
    public OrderDetail insert(OrderDetail orderDetail, Connection conn) throws Exception {
        return null;
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail, Connection conn) throws Exception {
        // 주문 상세는 수정할 수 없다는 예외 처리
        throw new UnsupportedOperationException("Order details cannot be updated.");
    }

    @Override
    public boolean delete(Integer orderDetailId, Connection conn) throws Exception {
        // 주문 상세는 삭제할 수 없다는 예외 처리
        throw new UnsupportedOperationException("Order details cannot be deleted.");
    }

    @Override
    public OrderDetail select(Integer id, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderDetail orderDetail = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ORDER_DETAIL_BY_ID);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                orderDetail = new OrderDetail(
                        rs.getInt("order_detail_id"),
                        rs.getInt("pid"),
                        rs.getInt("oid"),
                        rs.getInt("item_cnt"),
                        rs.getInt("od_price")
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return orderDetail;
    }

    @Override
    public List<OrderDetail> select(Connection conn) throws Exception {
        return null;
    }

    // 특정 주문에 대한 상세 내역 조회
    public List<OrderDetail> selectByOid(int oid, Connection conn) throws Exception {
        List<OrderDetail> orderDetails = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ORDER_DETAILS_BY_OID);
            ps.setInt(1, oid);
            rs = ps.executeQuery();
            while (rs.next()) {
                orderDetails.add(new OrderDetail(
                        rs.getInt("order_detail_id"),
                        rs.getInt("oid"),
                        rs.getInt("pid"),
                        rs.getInt("item_cnt"),
                        rs.getInt("od_price")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return orderDetails;
    }
}
