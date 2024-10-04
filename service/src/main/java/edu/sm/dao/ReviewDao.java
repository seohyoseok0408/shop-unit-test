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
    // 리뷰 추가 메서드
    public Review insert(Review review, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;

        // 동일한 상품에 대해 이미 리뷰를 작성했는지 확인
        String checkQuery = Sql.CHECK_DUPLICATE_REVIEW;
        ps = conn.prepareStatement(checkQuery);
        ps.setInt(1, review.getCid());
        ps.setInt(2, review.getPid());
        rs = ps.executeQuery();

        if (rs.next() && rs.getInt(1) > 0) {
            throw new Exception("이미 해당 상품에 대한 리뷰를 작성하셨습니다.");
        }

        // 리뷰가 없을 경우에만 추가
        try {
            ps = conn.prepareStatement(Sql.INSERT_REVIEW);
            ps.setInt(1, review.getPid());
            ps.setInt(2, review.getCid());
            ps.setInt(3, review.getRate());
            ps.setString(4, review.getTitle());
            ps.setString(5, review.getContent());
            ps.setString(6, review.getImg());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }

        return review;
    }

    @Override
    public Review update(Review review, Connection conn) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sql.UPDATE_REVIEW);
            ps.setInt(1, review.getPid());
            ps.setInt(2, review.getCid());
            ps.setInt(3, review.getRate());
            ps.setString(4, review.getTitle());
            ps.setString(5, review.getContent());
            ps.setString(6, review.getImg());
            ps.setInt(7, review.getRid());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
        return review;
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
        return null;
    }

    @Override
    public List<Review> select(Connection conn) throws Exception {
       return null;
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
