package edu.sm.dao;

import edu.sm.dto.Discount;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DiscountDao implements Dao<Integer, Discount> {

    @Override
    public Discount insert(Discount discount, Connection conn) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sql.INSERT_DISCOUNT);
            ps.setString(1, discount.getDisName());
            ps.setFloat(2, discount.getDisRate());
            ps.setDate(3, discount.getDisStart());
            ps.setDate(4, discount.getDisEnd());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
        return discount;
    }

    @Override
    public Discount update(Discount discount, Connection conn) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sql.UPDATE_DISCOUNT);
            ps.setString(1, discount.getDisName());
            ps.setFloat(2, discount.getDisRate());
            ps.setDate(3, discount.getDisStart());
            ps.setDate(4, discount.getDisEnd());
            ps.setInt(5, discount.getDisId());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
        return discount;
    }

    @Override
    public boolean delete(Integer disId, Connection conn) throws Exception {
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            ps = conn.prepareStatement(Sql.DELETE_DISCOUNT);
            ps.setInt(1, disId);
            int result = ps.executeUpdate();
            if (result == 1) flag = true;
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
        return flag;
    }

    @Override
    public Discount select(Integer disId, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Discount discount = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_DISCOUNT_BY_ID);
            ps.setInt(1, disId);
            rs = ps.executeQuery();
            if (rs.next()) {
                discount = new Discount(
                        rs.getInt("dis_id"),
                        rs.getString("dis_name"),
                        rs.getFloat("dis_rate"),
                        rs.getDate("dis_start"),
                        rs.getDate("dis_end")
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return discount;
    }

    @Override
    public List<Discount> select(Connection conn) throws Exception {
        List<Discount> discounts = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ALL_DISCOUNTS);
            rs = ps.executeQuery();
            while (rs.next()) {
                discounts.add(new Discount(
                        rs.getInt("dis_id"),
                        rs.getString("dis_name"),
                        rs.getFloat("dis_rate"),
                        rs.getDate("dis_start"),
                        rs.getDate("dis_end")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return discounts;
    }
}
