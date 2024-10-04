package edu.sm.dao;

import edu.sm.dto.Product;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Dao<Integer, Product> {

    @Override
    public Product insert(Product product, Connection conn) throws Exception {
        return null;
    }

    @Override
    public Product update(Product product, Connection conn) throws Exception {
      return null;
    }

    @Override
    public boolean delete(Integer pid, Connection conn) throws Exception {
        throw new UnsupportedOperationException("상품은 삭제 불가능");
    }

    @Override
    public Product select(Integer pid, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_PRODUCT_BY_ID);
            ps.setInt(1, pid);
            rs = ps.executeQuery();
            if (rs.next()) {
                product = new Product(
                        rs.getInt("pid"),
                        rs.getInt("category_id"),
                        rs.getInt("dis_id"),
                        rs.getString("pname"),
                        rs.getInt("price"),
                        rs.getInt("cnt"),
                        rs.getString("img1"),
                        rs.getString("img2"),
                        rs.getString("img3"),
                        rs.getString("img4"),
                        rs.getString("content"),
                        rs.getTimestamp("pdate"),
                        rs.getBoolean("is_public")
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return product;
    }

    @Override
    public List<Product> select(Connection conn) throws Exception {
       return null;
    }
    public List<Product> selectPublicProducts(Connection conn) throws Exception {
        List<Product> products = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_PUBLIC_PRODUCTS); // 공개된 상품만 조회
            rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("pid"),
                        rs.getInt("category_id"),   // 카테고리 ID 포함
                        rs.getInt("dis_id"),        // 할인 ID 포함
                        rs.getString("pname"),
                        rs.getInt("price"),
                        rs.getInt("cnt"),
                        rs.getString("img1"),
                        rs.getString("img2"),
                        rs.getString("img3"),
                        rs.getString("img4"),
                        rs.getString("content"),
                        rs.getTimestamp("pdate"),
                        rs.getBoolean("is_public")

                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return products;
    }

    public List<Product> selectByCategory(int categoryId, Connection conn) throws Exception {
        List<Product> products = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_PRODUCTS_BY_CATEGORY);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("pid"),
                        rs.getInt("category_id"),
                        rs.getInt("dis_id"),
                        rs.getString("pname"),
                        rs.getInt("price"),
                        rs.getInt("cnt"),
                        rs.getString("img1"),
                        rs.getString("img2"),
                        rs.getString("img3"),
                        rs.getString("img4"),
                        rs.getString("content"),
                        rs.getTimestamp("pdate"),
                        rs.getBoolean("is_public")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return products;
    }

    // 상품명으로 조회
    public List<Product> selectByName(String pname, Connection conn) throws Exception {
        List<Product> products = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(Sql.SELECT_PRODUCT_BY_NAME);
            ps.setString(1, "%" + pname + "%"); // 부분 일치 검색
            rs = ps.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("pid"),
                        rs.getInt("category_id"),
                        rs.getInt("dis_id"),
                        rs.getString("pname"),
                        rs.getInt("price"),
                        rs.getInt("cnt"),
                        rs.getString("img1"),
                        rs.getString("img2"),
                        rs.getString("img3"),
                        rs.getString("img4"),
                        rs.getString("content"),
                        rs.getTimestamp("pdate"),
                        rs.getBoolean("is_public")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return products;
    }

    private String getQueryForSorting(String sortBy) {
        switch (sortBy) {
            case "price_asc":
                return Sql.SELECT_PRODUCT_ORDER_BY_PRICE_ASC;
            case "price_desc":
                return Sql.SELECT_PRODUCT_ORDER_BY_PRICE_DESC;
            case "review":
                return Sql.SELECT_PRODUCT_ORDER_BY_REVIEW_COUNT;
            case "sales_count":
                return Sql.SELECT_PRODUCT_ORDER_BY_SALES_COUNT; // 판매량 순 정렬 추가
            default:
                return Sql.SELECT_PRODUCT_ORDER_BY_PRICE_ASC; // 기본 정렬은 가격 오름차순
        }
    }

    // 카테고리별 공개 상품만 조회
    public List<Product> selectPublicByCategory(int categoryId, Connection conn) throws Exception {
        List<Product> products = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_PUBLIC_PRODUCTS_BY_CATEGORY);
            ps.setInt(1, categoryId);
            rs = ps.executeQuery();
            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("pid"),
                        rs.getInt("category_id"),
                        rs.getInt("dis_id"),
                        rs.getString("pname"),
                        rs.getInt("price"),
                        rs.getInt("cnt"),
                        rs.getString("img1"),
                        rs.getString("img2"),
                        rs.getString("img3"),
                        rs.getString("img4"),
                        rs.getString("content"),
                        rs.getTimestamp("pdate"),
                        rs.getBoolean("is_public")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return products;
    }

    // 상품명으로 공개 상품만 조회
    public List<Product> selectPublicByName(String pname, Connection conn) throws Exception {
        List<Product> products = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(Sql.SELECT_PUBLIC_PRODUCT_BY_NAME);
            ps.setString(1, "%" + pname + "%"); // 부분 일치 검색
            rs = ps.executeQuery();

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("pid"),
                        rs.getInt("category_id"),
                        rs.getInt("dis_id"),
                        rs.getString("pname"),
                        rs.getInt("price"),
                        rs.getInt("cnt"),
                        rs.getString("img1"),
                        rs.getString("img2"),
                        rs.getString("img3"),
                        rs.getString("img4"),
                        rs.getString("content"),
                        rs.getTimestamp("pdate"),
                        rs.getBoolean("is_public")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return products;
    }


}
