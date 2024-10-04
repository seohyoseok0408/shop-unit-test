package accesslog;

import edu.sm.service.AccessLogService;

import java.util.List;

public class AccessLogStats {
    public static void main(String[] args) {
        AccessLogService accessLogService = new AccessLogService();

        try {
            System.out.println("=====================================");
            System.out.println("       [ 접근 로그 통계 조회 ]         ");
            System.out.println("=====================================");

            // 시간별 접속 통계
            List<String> hourlyStats = accessLogService.getHourlyStats();
            System.out.println("\n 시간별 접속 통계 ");
            System.out.println("-------------------------------------");
            if (hourlyStats.isEmpty()) {
                System.out.println("시간별 접속 기록이 없습니다.");
            } else {
                for (String stat : hourlyStats) {
                    String[] parts = stat.split(":");
                    String hour = parts[0].trim() + "시";
                    String count = parts[1].trim() + "";
                    System.out.println(hour + " : " + count);
                }
            }
            System.out.println("-------------------------------------");

            // 일별 접속 통계
            List<String> dailyStats = accessLogService.getDailyStats();
            System.out.println("\n 일별 접속 통계 ");
            System.out.println("-------------------------------------");
            if (dailyStats.isEmpty()) {
                System.out.println("일별 접속 기록이 없습니다.");
            } else {
                for (String stat : dailyStats) {
                    System.out.println(stat);
                }
            }
            System.out.println("-------------------------------------");

            // 요일별 접속 통계
            List<String> weeklyStats = accessLogService.getWeeklyStats();
            System.out.println("\n 요일별 접속 통계 ");
            System.out.println("-------------------------------------");
            if (weeklyStats.isEmpty()) {
                System.out.println("요일별 접속 기록이 없습니다.");
            } else {
                for (String stat : weeklyStats) {
                    System.out.println(stat);
                }
            }
            System.out.println("-------------------------------------");

            // 월별 접속 통계
            List<String> monthlyStats = accessLogService.getMonthlyStats();
            System.out.println("\n 월별 접속 통계 ");
            System.out.println("-------------------------------------");
            if (monthlyStats.isEmpty()) {
                System.out.println("월별 접속 기록이 없습니다.");
            } else {
                for (String stat : monthlyStats) {
                    // 기존의 "1 : 20" 형식의 문자열을 "1월 : 20명"으로 변환
                    String[] parts = stat.split(":");
                    String month = parts[0].trim() + "월";
                    String count = parts[1].trim() + "";
                    System.out.println(month + " : " + count);
                }
            }
            System.out.println("-------------------------------------");

            // 연도별 접속 통계
            List<String> yearlyStats = accessLogService.getYearlyStats();
            System.out.println("\n 연도별 접속 통계 ");
            System.out.println("-------------------------------------");
            if (yearlyStats.isEmpty()) {
                System.out.println("연도별 접속 기록이 없습니다.");
            } else {
                for (String stat : yearlyStats) {
                    // 기존의 "2024 : 25" 형식의 문자열을 "2024년 : 25명"으로 변환
                    String[] parts = stat.split(":");
                    String year = parts[0].trim() + "년";
                    String count = parts[1].trim() + "";
                    System.out.println(year + " : " + count);
                }
            }
            System.out.println("-------------------------------------");

            System.out.println("=====================================");
            System.out.println("   [ 모든 접근 로그 통계 조회 완료 ]   ");
            System.out.println("=====================================");
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 접근 로그 통계 조회 실패 ]    ");
            System.out.println("-------------------------------------");
            System.out.println("에러가 발생했습니다: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
