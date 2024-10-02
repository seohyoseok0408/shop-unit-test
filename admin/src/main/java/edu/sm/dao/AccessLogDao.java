package edu.sm.dao;

import edu.sm.dto.AccessLog;
import edu.sm.frame.Dao;
import edu.sm.frame.Sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccessLogDao implements Dao<Integer, AccessLog> {

    @Override
    public AccessLog insert(AccessLog accessLog, Connection conn) throws Exception {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(Sql.INSERT_ACCESS_LOG);
            ps.setInt(1, accessLog.getCid()); // 사용자 ID
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) ps.close();
        }
        return accessLog;
    }

    @Override
    public AccessLog update(AccessLog accessLog, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Access logs can't be updated.");
    }

    @Override
    public boolean delete(Integer logId, Connection conn) throws Exception {
        throw new UnsupportedOperationException("Access logs can't be deleted.");
    }

    @Override
    public AccessLog select(Integer logId, Connection conn) throws Exception {
        PreparedStatement ps = null;
        ResultSet rs = null;
        AccessLog accessLog = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ACCESS_LOG_BY_ID);
            ps.setInt(1, logId);
            rs = ps.executeQuery();
            if (rs.next()) {
                accessLog = new AccessLog();
                accessLog.setLogId(rs.getInt("log_id"));
                accessLog.setCid(rs.getInt("cid"));
                accessLog.setAccessTime(rs.getTimestamp("access_time"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return accessLog;
    }

    @Override
    public List<AccessLog> select(Connection conn) throws Exception {
        List<AccessLog> accessLogs = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(Sql.SELECT_ALL_ACCESS_LOGS);
            rs = ps.executeQuery();
            while (rs.next()) {
                AccessLog accessLog = new AccessLog();
                accessLog.setLogId(rs.getInt("log_id"));
                accessLog.setCid(rs.getInt("cid"));
                accessLog.setAccessTime(rs.getTimestamp("access_time"));
                accessLogs.add(accessLog);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return accessLogs;
    }

    // 시간별 접속 통계 조회
    public List<String> selectHourlyStats(Connection conn) throws Exception {
        return executeStatsQuery(conn, Sql.SELECT_HOURLY_ACCESS_STATS);
    }

    // 일별 접속 통계 조회
    public List<String> selectDailyStats(Connection conn) throws Exception {
        return executeStatsQuery(conn, Sql.SELECT_DAILY_ACCESS_STATS);
    }

    // 요일별 접속 통계 조회
    public List<String> selectWeeklyStats(Connection conn) throws Exception {
        return executeStatsQuery(conn, Sql.SELECT_WEEKLY_ACCESS_STATS);
    }

    // 월별 접속 통계 조회
    public List<String> selectMonthlyStats(Connection conn) throws Exception {
        return executeStatsQuery(conn, Sql.SELECT_MONTHLY_ACCESS_STATS);
    }

    // 연도별 접속 통계 조회
    public List<String> selectYearlyStats(Connection conn) throws Exception {
        return executeStatsQuery(conn, Sql.SELECT_YEARLY_ACCESS_STATS);
    }

    // 공통 통계 쿼리 실행 메서드
    private List<String> executeStatsQuery(Connection conn, String sql) throws Exception {
        List<String> stats = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String result = rs.getString(1) + ": " + rs.getInt(2) + "명";
                stats.add(result);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (ps != null) ps.close();
            if (rs != null) rs.close();
        }
        return stats;
    }
}
