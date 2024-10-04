package accesslog;

import edu.sm.dto.AccessLog;
import edu.sm.service.AccessLogService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AccessLogSelectAll {
    public static void main(String[] args) {
        AccessLogService accessLogService = new AccessLogService();

        try {
            List<AccessLog> accessLogs = accessLogService.get();
            if (accessLogs.isEmpty()) {
                System.out.println("=====================================");
                System.out.println("     [ 조회된 접근 로그가 없습니다  ]    ");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("         [ 전체 접근 로그 조회 ]        ");
                System.out.println("-------------------------------------");

                // 각 로그를 출력
                for (AccessLog log : accessLogs) {
                    // accessTime을 "yyyy-MM-dd HH:mm" 형식으로 포맷
                    String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                    System.out.println("로그 ID        : " + log.getLogId());
                    System.out.println("사용자 ID (CID): " + log.getCid());
                    System.out.println("접속 시간      : " + log.getAccessTime());
                    System.out.println("-------------------------------------");
                }

                System.out.println("총 " + accessLogs.size() + "개의 로그가 조회되었습니다.");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 접근 로그 조회 실패 ]        ");
            System.out.println("-------------------------------------");
            System.out.println("에러가 발생했습니다: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
