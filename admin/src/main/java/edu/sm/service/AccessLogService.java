package edu.sm.service;

import edu.sm.dao.AccessLogDao;
import edu.sm.dto.AccessLog;
import edu.sm.frame.ConnectionPool;
import edu.sm.frame.MService;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AccessLogService implements MService<Integer, AccessLog> {
    AccessLogDao dao;
    ConnectionPool cp;

    public AccessLogService() {
        dao = new AccessLogDao();
        try {
            cp = ConnectionPool.create();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public AccessLog add(AccessLog accessLog) throws Exception {
        Connection conn = cp.getConnection();
        try {
            conn.setAutoCommit(false);
            dao.insert(accessLog, conn);
            conn.commit();
            System.out.println("AccessLog 추가됨");
        } catch (Exception e) {
            conn.rollback();
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return accessLog;
    }

    @Override
    public AccessLog modify(AccessLog accessLog) throws Exception {
        throw new UnsupportedOperationException("Access logs can't be modified.");
    }

    @Override
    public Boolean remove(Integer logId) throws Exception {
        throw new UnsupportedOperationException("Access logs can't be removed.");
    }

    @Override
    public AccessLog get(Integer logId) throws Exception {
        Connection conn = cp.getConnection();
        AccessLog accessLog = null;
        try {
            accessLog = dao.select(logId, conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return accessLog;
    }

    @Override
    public List<AccessLog> get() throws Exception {
        Connection conn = cp.getConnection();
        List<AccessLog> accessLogs = null;
        try {
            accessLogs = dao.select(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return accessLogs;
    }

    public List<String> getHourlyStats() throws Exception {
        Connection conn = cp.getConnection();
        List<String> stats = null;
        try {
            stats = dao.selectHourlyStats(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return stats;
    }

    public List<String> getDailyStats() throws Exception {
        Connection conn = cp.getConnection();
        List<String> stats = null;
        try {
            stats = dao.selectDailyStats(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return stats;
    }

    public List<String> getWeeklyStats() throws Exception {
        Connection conn = cp.getConnection();
        List<String> stats = null;
        try {
            stats = dao.selectWeeklyStats(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return stats;
    }

    public List<String> getMonthlyStats() throws Exception {
        Connection conn = cp.getConnection();
        List<String> stats = null;
        try {
            stats = dao.selectMonthlyStats(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return stats;
    }

    public List<String> getYearlyStats() throws Exception {
        Connection conn = cp.getConnection();
        List<String> stats = null;
        try {
            stats = dao.selectYearlyStats(conn);
        } catch (Exception e) {
            throw e;
        } finally {
            cp.releaseConnection(conn);
        }
        return stats;
    }
}
