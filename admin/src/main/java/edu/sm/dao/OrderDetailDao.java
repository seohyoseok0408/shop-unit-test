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
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 상품 가격 조회 SQL
            String selectPriceSql = Sql.SELECT_PRODUCT_PRICE;
            ps = conn.prepareStatement(selectPriceSql);
            ps.setInt(1, orderDetail.getPid());
            rs = ps.executeQuery();
            int productPrice = 0;
            if (rs.next()) {
                productPrice = rs.getInt("price");
            }
            int odPrice = productPrice * orderDetail.getItemCnt();

            // order_detail insert SQL
            ps = conn.prepareStatement(Sql.INSERT_ORDER_DETAIL);
            ps.setInt(1, orderDetail.getPid());  // 상품 ID
            ps.setInt(2, orderDetail.getOid());  // 주문 ID
            ps.setInt(3, orderDetail.getItemCnt());  // 상품 개수
            ps.setInt(4, odPrice);  // 상품 가격
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
        return orderDetail;
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
        return null;
    }

    @Override
    public List<OrderDetail> select(Connection conn) throws Exception {
        List<OrderDetail> orderDetails = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ALL_ORDER_DETAILS);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        rs.getInt("order_detail_id"),
                        rs.getInt("pid"),
                        rs.getInt("oid"),
                        rs.getInt("item_cnt"),
                        rs.getInt("od_price")
                );
                orderDetails.add(orderDetail);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return orderDetails;
    }

    // 특정 주문에 대한 상세 내역 조회
    public List<OrderDetail> selectByOid(int oid, Connection conn) throws Exception {
        return null;
    }
}
