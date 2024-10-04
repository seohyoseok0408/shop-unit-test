package accesslog;

import edu.sm.dto.AccessLog;
import edu.sm.service.AccessLogService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccessLogSelect {
    public static void main(String[] args) {
        AccessLogService accessLogService = new AccessLogService();

        int logId = 7;  // 조회할 로그 ID 설정
        try {
            AccessLog accessLog = accessLogService.get(logId);
            if (accessLog != null) {

                String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                System.out.println("=====================================");
                System.out.println("         [ 접근 로그 조회 결과 ]       ");
                System.out.println("-------------------------------------");
                System.out.println("  로그 ID        : " + accessLog.getLogId());
                System.out.println("  사용자 ID (CID): " + accessLog.getCid());
                System.out.println("  접속 시간      : " + accessLog.getAccessTime());
                System.out.println("-------------------------------------");
                System.out.println("로그 정보가 성공적으로 조회되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("         [ 접근 로그 조회 실패 ]       ");
                System.out.println("-------------------------------------");
                System.out.println("  해당 로그가 없습니다.");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("         [ 접근 로그 조회 실패 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("에러가 발생했습니다: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
