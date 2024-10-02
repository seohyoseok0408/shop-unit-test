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
        PreparedStatement ps = null;
        ResultSet generatedKeys = null; // 추가
        try {
            ps = conn.prepareStatement(Sql.INSERT_PRODUCT, PreparedStatement.RETURN_GENERATED_KEYS); // RETURN_GENERATED_KEYS 옵션 사용
            ps.setInt(1, product.getCategoryId());
            ps.setInt(2, product.getDisId());
            ps.setString(3, product.getPname());
            ps.setInt(4, product.getPrice());
            ps.setInt(5, product.getCnt());
            ps.setString(6, product.getImg1());
            ps.setString(7, product.getImg2());
            ps.setString(8, product.getImg3());
            ps.setString(9, product.getImg4());
            ps.setString(10, product.getContent());
            ps.setBoolean(11, product.isPublic());

            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new Exception("상품 삽입에 실패하였습니다. (영향을 받은 행이 없습니다)");
            }

            // 자동 생성된 키 가져오기
            generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                product.setPid(generatedKeys.getInt(1)); // 삽입된 상품 ID 설정
            } else {
                throw new Exception("상품 삽입에 실패하였습니다. (생성된 ID를 가져올 수 없습니다)");
            }

        } catch (Exception e) {
            throw e;
        } finally {
            if (generatedKeys != null) generatedKeys.close(); // 추가: ResultSet 닫기
            if (ps != null) ps.close();
        }
        return product;
    }

    @Override
    public Product update(Product product, Connection conn) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sql.UPDATE_PRODUCT);
            ps.setInt(1, product.getCategoryId());
            ps.setInt(2, product.getDisId());
            ps.setString(3, product.getPname());
            ps.setInt(4, product.getPrice());
            ps.setInt(5, product.getCnt());
            ps.setString(6, product.getImg1());
            ps.setString(7, product.getImg2());
            ps.setString(8, product.getImg3());
            ps.setString(9, product.getImg4());
            ps.setString(10, product.getContent());
            ps.setBoolean(11, product.isPublic());
            ps.setInt(12, product.getPid());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
        return product;
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
        List<Product> products = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ALL_PRODUCTS);
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

    public List<Product> selectAllSortedBy(String sortBy, Integer categoryId, Connection conn) throws Exception {
        List<Product> products = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = getQueryForSorting(sortBy); // 정렬 기준에 따른 SQL 쿼리 결정
            ps = conn.prepareStatement(query);

            // 카테고리 번호가 있으면 category_id로 필터링, 없으면 전체
            if (categoryId != null) {
                ps.setInt(1, categoryId); // category_id
                ps.setInt(2, categoryId); // 두 번째 파라미터는 OR 조건 처리
            } else {
                ps.setNull(1, java.sql.Types.INTEGER);
                ps.setNull(2, java.sql.Types.INTEGER);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setPid(rs.getInt("pid"));
                product.setPname(rs.getString("pname"));
                product.setPrice(rs.getInt("price"));
                product.setCnt(rs.getInt("cnt"));
                product.setImg1(rs.getString("img1"));
                product.setContent(rs.getString("content"));
                product.setPdate(rs.getTimestamp("pdate"));
                products.add(product);
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
