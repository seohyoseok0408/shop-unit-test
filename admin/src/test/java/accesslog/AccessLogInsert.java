package accesslog;

import edu.sm.dto.AccessLog;
import edu.sm.service.AccessLogService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AccessLogInsert {
    public static void main(String[] args) {
        AccessLogService accessLogService = new AccessLogService();

        AccessLog accessLog = new AccessLog();
        accessLog.setCid(20);  // 사용자의 ID를 설정
        try {
            accessLogService.add(accessLog);

            String formattedDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

            System.out.println("=====================================");
            System.out.println("         [ 접속 로그 추가 완료 ]        ");
            System.out.println("-------------------------------------");
            System.out.println("  사용자 ID (CID) : " + accessLog.getCid());
            System.out.println("  등록 시간       : " + formattedDateTime);
            System.out.println("-------------------------------------");
            System.out.println("접속 로그가 성공적으로 추가되었습니다.");
            System.out.println("=====================================");
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("         [ 접속 로그 추가 실패 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("에러가 발생했습니다: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
