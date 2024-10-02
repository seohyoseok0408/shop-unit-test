package edu.sm.service;

import edu.sm.dao.ReviewDao;
import edu.sm.dto.Review;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ReviewService implements MService<Integer, Review> {
    ReviewDao dao;
    ConnectionPool cp;

    public ReviewService() {
        dao = new ReviewDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Review add(Review review) throws Exception {
        Connection conn = cp.getConnection();
        try {
            conn.setAutoCommit(false);
            dao.insert(review, conn);
            conn.commit();
            System.out.println("리뷰 추가됨");
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return review;
    }

    @Override
    public Review modify(Review review) throws Exception {
        Connection conn = cp.getConnection();
        try {
            conn.setAutoCommit(false);
            dao.update(review, conn);
            conn.commit();
            System.out.println("리뷰 수정됨");
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return review;
    }

    @Override
    public Boolean remove(Integer rid) throws Exception {
        Connection conn = cp.getConnection();
        boolean result = false;
        try {
            conn.setAutoCommit(false);
            result = dao.delete(rid, conn);
            conn.commit();
            System.out.println("리뷰 삭제됨");
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return result;
    }

    @Override
    public Review get(Integer rid) throws Exception {
        Connection conn = cp.getConnection();
        Review review = null;
        try {
            review = dao.select(rid, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return review;
    }

    @Override
    public List<Review> get() throws Exception {
        Connection conn = cp.getConnection();
        List<Review> reviews = null;
        try {
            reviews = dao.select(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return reviews;
    }

    // 상품 ID로 모든 리뷰 조회
    public List<Review> getByProductId(int pid) throws Exception {
        Connection conn = cp.getConnection();
        List<Review> reviews = null;

        try {
            reviews = dao.selectByProductId(pid, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }

        return reviews;
    }
}
