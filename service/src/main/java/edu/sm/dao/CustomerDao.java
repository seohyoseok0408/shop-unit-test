package edu.sm.dao;

import edu.sm.dto.Customer;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao implements Dao<Integer, Customer> {

    @Override
    public Customer insert(Customer customer, Connection conn) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sql.INSERT_CUSTOMER);
            ps.setString(1, customer.getPwd()); // 비밀번호 설정
            ps.setString(2, customer.getCname());   // 이름 설정
            ps.setString(3, customer.getEmail());   // 이메일 설정
            ps.setString(4, customer.getPhone());   // 전화번호 설정
            ps.setDate(5, customer.getBirth_date()); // 생년월일 설정
            ps.setString(6, customer.getNick_name()); // 닉네임 설정
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return customer;
    }

    @Override
    public Customer update(Customer customer, Connection conn) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Integer i, Connection conn) throws Exception {
        boolean flag = false;
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sql.DELETE_CUSTOMER);
            ps.setInt(1, i);
            int result = ps.executeUpdate();
            if (result == 1) {
                flag = true;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
        }
        return flag;
    }

    @Override
    public Customer select(Integer i, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_CUSTOMER_BY_ID); // SQL 쿼리 사용
            ps.setInt(1, i);
            rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCid(rs.getInt("cid"));
                customer.setEmail(rs.getString("email"));
                customer.setCname(rs.getString("cname"));
                customer.setPhone(rs.getString("phone"));
                customer.setBirth_date(rs.getDate("birth_date")); // SQL Date 가져오기
                customer.setNick_name(rs.getString("nick_name"));
                customer.setGrade(rs.getInt("grade"));
                customer.setJoin_date(rs.getTimestamp("join_date"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return customer;
    }

    @Override
    public List<Customer> select(Connection con) throws Exception {
        return null;
    }


    public List<Customer> selectByName(String name, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> customers = new ArrayList<>();
        try {
            ps = conn.prepareStatement(Sql.SELECT_CUSTOMER_BY_NAME); // SQL 쿼리에서 이름으로 찾기
            ps.setString(1, "%" + name + "%"); // 이름에 대해 부분 매칭
            rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setCid(rs.getInt("cid"));
                customer.setEmail(rs.getString("email"));
                customer.setCname(rs.getString("cname"));
                customer.setPhone(rs.getString("phone"));
                customer.setBirth_date(rs.getDate("birth_date"));
                customer.setNick_name(rs.getString("nick_name"));
                customer.setGrade(rs.getInt("grade"));
                customer.setJoin_date(rs.getTimestamp("join_date"));
                customers.add(customer);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return customers;
    }

    public Customer selectByEmail(String email, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_CUSTOMER_EMAIL);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCid(rs.getInt("cid"));
                customer.setPwd(rs.getString("pwd"));
                customer.setCname(rs.getString("cname"));
                customer.setEmail(rs.getString("email"));
                customer.setPhone(rs.getString("phone"));
                customer.setBirth_date(rs.getDate("birth_date"));
                customer.setNick_name(rs.getString("nick_name"));
                customer.setGrade(rs.getInt("grade"));
                customer.setJoin_date(rs.getTimestamp("join_date"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return customer;
    }

}
