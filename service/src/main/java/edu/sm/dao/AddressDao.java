package edu.sm.dao;

import edu.sm.dto.Address;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AddressDao implements Dao<Integer, Address> {

    @Override
    public Address insert(Address address, Connection conn) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sql.INSERT_ADDRESS);
            ps.setInt(1, address.getCid());
            ps.setString(2, address.getAname());
            ps.setString(3, address.getAddress());
            ps.setString(4, address.getAddressDetail());
            ps.setString(5, address.getZipCode());
            ps.setString(6, address.getPhone());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
        return address;
    }

    @Override
    public Address update(Address address, Connection conn) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sql.UPDATE_ADDRESS);
            ps.setString(1, address.getAname());
            ps.setString(2, address.getAddress());
            ps.setString(3, address.getAddressDetail());
            ps.setString(4, address.getZipCode());
            ps.setString(5, address.getPhone());
            ps.setInt(6, address.getAid());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
        return address;
    }

    @Override
    public boolean delete(Integer aid, Connection conn) throws Exception {
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            ps = conn.prepareStatement(Sql.DELETE_ADDRESS);
            ps.setInt(1, aid);
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
    public Address select(Integer aid, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Address address = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ADDRESS_BY_ID);
            ps.setInt(1, aid);
            rs = ps.executeQuery();
            if (rs.next()) {
                address = new Address(
                        rs.getInt("aid"),
                        rs.getInt("cid"),
                        rs.getString("aname"),
                        rs.getString("address"),
                        rs.getString("address_detail"),
                        rs.getString("zip_code"),
                        rs.getString("phone")
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return address;
    }

    @Override
    public List<Address> select(Connection conn) throws Exception {
        List<Address> addresses = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ALL_ADDRESSES);
            rs = ps.executeQuery();
            while (rs.next()) {
                addresses.add(new Address(
                        rs.getInt("aid"),
                        rs.getInt("cid"),
                        rs.getString("aname"),
                        rs.getString("address"),
                        rs.getString("address_detail"),
                        rs.getString("zip_code"),
                        rs.getString("phone")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return addresses;
    }

    // 특정 고객의 주소 목록을 조회하는 메서드
    public List<Address> selectByCid(int cid, Connection conn) throws Exception {
        List<Address> addresses = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ADDRESSES_BY_CID);
            ps.setInt(1, cid);
            rs = ps.executeQuery();
            while (rs.next()) {
                addresses.add(new Address(
                        rs.getInt("aid"),
                        rs.getInt("cid"),
                        rs.getString("aname"),
                        rs.getString("address"),
                        rs.getString("address_detail"),
                        rs.getString("zip_code"),
                        rs.getString("phone")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return addresses;
    }
}
