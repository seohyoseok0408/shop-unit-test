package edu.sm.service;

import edu.sm.dao.CustomerDao;
import edu.sm.dto.Customer;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CustomerService implements MService<Integer, Customer> {
    CustomerDao dao;
    ConnectionPool cp;

    public CustomerService() {
        dao = new CustomerDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Customer add(Customer customer) throws Exception {
        Connection conn = cp.getConnection();
        try {
            conn.setAutoCommit(false);
            dao.insert(customer, conn);
            conn.commit();
            System.out.println("Customer Service add 실행됨");
        } catch(Exception e) {
            conn.rollback();
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }

        return customer;
    }

    @Override
    public Customer modify(Customer customer) throws Exception {
        Connection conn = cp.getConnection();
        try {
            dao.update(customer, conn);
            System.out.println("Customer Service modify 실행됨");
        } catch(Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return customer;
    }

    @Override
    public Boolean remove(Integer i) throws Exception {
        Connection conn = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(i, conn);
            System.out.println("Customer Service remove 실행됨");
        } catch(Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return result;
    }

    @Override
    public Customer get(Integer i) throws Exception {
        Connection conn = cp.getConnection();
        Customer customer = null;
        try {
            customer = dao.select(i, conn);
            System.out.println("Customer Service get 실행됨");
        } catch(Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return customer;
    }

    @Override
    public List<Customer> get() throws Exception {
        Connection conn = cp.getConnection();
        List<Customer> customers = null;
        try {
            customers = dao.select(conn);
            System.out.println("Customer Service get() 실행됨");

        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return customers;
    }

    // 사용자 이름으로 고객 검색
    public List<Customer> getByName(String name) throws Exception {
        Connection conn = cp.getConnection();
        List<Customer> customers = null;
        try {
            customers = dao.selectByName(name, conn);
            System.out.println("Customer Service getByName() 실행됨");
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return customers;
    }
    // 로그인 함수: 이메일과 비밀번호를 체크하여 사용자를 반환
    public Customer login(String email, String pwd) throws Exception {
        Connection con = cp.getConnection();
        Customer customer = null;
        try {
            // 이메일로 사용자 조회
            customer = dao.selectByEmail(email, con);

            // 사용자가 존재하고 비밀번호가 맞으면 로그인 성공
            if (customer != null && customer.getPwd().equals(pwd)) {
                System.out.println("로그인 성공: " + customer.getCname());
                return customer; // 로그인 성공 시 사용자 반환
            } else {
                System.out.println("로그인 실패: 이메일 또는 비밀번호가 잘못되었습니다.");
                return null; // 로그인 실패
            }
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(con);
        }
    }

}













