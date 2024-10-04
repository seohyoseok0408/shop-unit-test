package edu.sm.dao;

import edu.sm.dto.Review;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao implements Dao<Integer, Review> {

    @Override
    public Review insert(Review review, Connection conn) throws Exception {
        return null;
    }

    @Override
    public Review update(Review review, Connection conn) throws Exception {
        return null;
    }

    @Override
    public boolean delete(Integer rid, Connection conn) throws Exception {
        PreparedStatement ps = null;
        boolean flag = false;
        try {
            ps = conn.prepareStatement(Sql.DELETE_REVIEW);
            ps.setInt(1, rid);
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
    public Review select(Integer rid, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Review review = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ONE_REVIEW);
            ps.setInt(1, rid);
            rs = ps.executeQuery();
            if (rs.next()) {
                review = new Review(
                        rs.getInt("rid"),
                        rs.getInt("pid"),
                        rs.getInt("cid"),
                        rs.getInt("rate"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("img"),
                        rs.getTimestamp("rdate")
                );
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return review;
    }

    @Override
    public List<Review> select(Connection conn) throws Exception {
        List<Review> reviews = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ALL_REVIEWS);
            rs = ps.executeQuery();
            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("rid"),
                        rs.getInt("pid"),
                        rs.getInt("cid"),
                        rs.getInt("rate"),
                        rs.getString("title"),
                        rs.getString("content"),
                        rs.getString("img"),
                        rs.getTimestamp("rdate")
                ));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return reviews;
    }

    // 상품 ID로 모든 리뷰 조회
    public List<Review> selectByProductId(int pid, Connection conn) throws Exception {
        List<Review> reviews = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(Sql.SELECT_REVIEWS_BY_PRODUCT_ID);
            ps.setInt(1, pid);
            rs = ps.executeQuery();

            while (rs.next()) {
                Review review = new Review();
                review.setRid(rs.getInt("rid"));
                review.setPid(rs.getInt("pid"));
                review.setCid(rs.getInt("cid"));
                review.setRate(rs.getInt("rate"));
                review.setTitle(rs.getString("title"));
                review.setContent(rs.getString("content"));
                review.setImg(rs.getString("img"));
                review.setRdate(rs.getTimestamp("rdate"));

                reviews.add(review);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }

        return reviews;
    }
}
