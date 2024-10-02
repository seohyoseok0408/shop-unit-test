package edu.sm.service;

import edu.sm.dao.AddressDao;
import edu.sm.dto.Address;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AddressService implements MService<Integer, Address> {
    AddressDao dao;
    ConnectionPool cp;

    public AddressService() {
        dao = new AddressDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Address add(Address address) throws Exception {
        Connection conn = cp.getConnection();
        try {
            conn.setAutoCommit(false);
            dao.insert(address, conn);
            conn.commit();
            System.out.println("Address 추가됨");
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return address;
    }

    @Override
    public Address modify(Address address) throws Exception {
        Connection conn = cp.getConnection();
        try {
            dao.update(address, conn);
            System.out.println("Address 수정됨");
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return address;
    }

    @Override
    public Boolean remove(Integer aid) throws Exception {
        Connection conn = cp.getConnection();
        Boolean result = false;
        try {
            result = dao.delete(aid, conn);
            System.out.println("Address 삭제됨");
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return result;
    }

    @Override
    public Address get(Integer aid) throws Exception {
        Connection conn = cp.getConnection();
        Address address = null;
        try {
            address = dao.select(aid, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return address;
    }

    @Override
    public List<Address> get() throws Exception {
        Connection conn = cp.getConnection();
        List<Address> addresses = null;
        try {
            addresses = dao.select(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return addresses;
    }

    // 특정 고객의 주소 목록을 조회하는 메서드
    public List<Address> getByCustomerId(int cid) throws Exception {
        Connection conn = cp.getConnection();
        List<Address> addresses = null;
        try {
            addresses = dao.selectByCid(cid, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return addresses;
    }
}
