package edu.sm.dao;

import edu.sm.dto.Order;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDao implements Dao<Integer, Order> {

    @Override
    public Order insert(Order order, Connection conn) throws Exception {
       return null;
    }

    @Override
    public Order update(Order order, Connection conn) throws Exception {
        throw new UnsupportedOperationException("주문 삭제 후 다시 주문 부탁드립니다.");
    }

    @Override
    public boolean delete(Integer oid, Connection conn) throws Exception {
        throw new UnsupportedOperationException("주문은 삭제할 수 없습니다.");
    }

    @Override
    public Order select(Integer oid, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ORDER_BY_ID);
            ps.setInt(1, oid);
            rs = ps.executeQuery();
            if (rs.next()) {
                order = new Order(
                        rs.getInt("oid"),
                        rs.getInt("cid"),
                        rs.getString("ostatus"),
                        rs.getString("oname"),
                        rs.getString("address"),
                        rs.getString("address_detail"),
                        rs.getString("zip_code"),
                        rs.getString("phone"),
                        rs.getString("msg"),
                        rs.getTimestamp("odate"),
                        rs.getInt("price")  // price 추가
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return order;
    }

    @Override
    public List<Order> select(Connection conn) throws Exception {
        List<Order> orders = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ALL_ORDERS);
            rs = ps.executeQuery();
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("oid"),
                        rs.getInt("cid"),
                        rs.getString("ostatus"),
                        rs.getString("oname"),
                        rs.getString("address"),
                        rs.getString("address_detail"),
                        rs.getString("zip_code"),
                        rs.getString("phone"),
                        rs.getString("msg"),
                        rs.getTimestamp("odate"),
                        rs.getInt("price")  // price 추가
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return orders;
    }

    // 주문 상태별 조회 메서드
    public List<Order> selectOrdersByStatus(String status, Connection conn) throws Exception {
        List<Order> orders = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ORDERS_BY_STATUS); // SQL 쿼리 실행
            ps.setString(1, status); // 상태를 파라미터로 설정
            rs = ps.executeQuery();
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("oid"),
                        rs.getInt("cid"),
                        rs.getString("ostatus"),
                        rs.getString("oname"),
                        rs.getString("address"),
                        rs.getString("address_detail"),
                        rs.getString("zip_code"),
                        rs.getString("phone"),
                        rs.getString("msg"),
                        rs.getTimestamp("odate"),
                        rs.getInt("price")  // price 추가
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return orders;
    }

    // 주문 상태 업데이트 메서드
    public void updateOrderStatus(int oid, String status, Connection conn) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sql.UPDATE_ORDERS_OSTATUS); // SQL 쿼리 실행
            ps.setString(1, status); // 새로운 상태 설정
            ps.setInt(2, oid);       // 주문 번호 설정
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
    }

    // 특정 회원의 주문 목록을 조회하는 메서드
    public List<Order> selectByCid(int cid, Connection conn) throws Exception {
        List<Order> orders = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ORDERS_BY_CID);
            ps.setInt(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                orders.add(new Order(
                        rs.getInt("oid"),
                        rs.getInt("cid"),
                        rs.getString("ostatus"),
                        rs.getString("oname"),
                        rs.getString("address"),
                        rs.getString("address_detail"),
                        rs.getString("zip_code"),
                        rs.getString("phone"),
                        rs.getString("msg"),
                        rs.getTimestamp("odate"),
                        rs.getInt("price")  // price 추가
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return orders;
    }

    // 일별 주문 통계 조회
    public List<Map<String, Object>> selectDailyOrderStats(Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> stats = new ArrayList<>();

        try {
            ps = conn.prepareStatement(Sql.SELECT_DAILY_ORDER_STATS);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> stat = new HashMap<>();
                stat.put("order_date", rs.getDate("order_date"));
                stat.put("total_orders", rs.getInt("total_orders"));
                stat.put("total_sales", rs.getInt("total_sales"));
                stats.add(stat);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return stats;
    }

    // 월별 주문 통계 조회
    public List<Map<String, Object>> selectMonthlyOrderStats(Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Map<String, Object>> stats = new ArrayList<>();

        try {
            ps = conn.prepareStatement(Sql.SELECT_MONTHLY_ORDER_STATS);
            rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> stat = new HashMap<>();
                stat.put("order_year", rs.getInt("order_year"));
                stat.put("order_month", rs.getInt("order_month"));
                stat.put("total_orders", rs.getInt("total_orders"));
                stat.put("total_sales", rs.getInt("total_sales"));
                stats.add(stat);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return stats;
    }
}
