package order;

import edu.sm.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderStats {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        try {
            // 일별 주문 통계 출력
            System.out.println("=====================================");
            System.out.println("          [ 일별 주문 통계 ]          ");
            System.out.println("=====================================");
            List<Map<String, Object>> dailyStats = orderService.getDailyOrderStats();
            if (dailyStats.isEmpty()) {
                System.out.println("일별 주문 통계 데이터가 없습니다.");
            } else {
                for (Map<String, Object> stat : dailyStats) {
                    System.out.printf("%s | 총 주문 수 : %d | 총 매출 : %,d원\n",
                            stat.get("order_date"),    // 날짜 필드명
                            stat.get("total_orders"),  // 주문 수 필드명
                            stat.get("total_sales")    // 매출 필드명
                    );
                }
            }
            System.out.println("=====================================");

            // 월별 주문 통계 출력
            System.out.println("\n=====================================");
            System.out.println("          [ 월별 주문 통계 ]          ");
            System.out.println("=====================================");
            List<Map<String, Object>> monthlyStats = orderService.getMonthlyOrderStats();
            if (monthlyStats.isEmpty()) {
                System.out.println("월별 주문 통계 데이터가 없습니다.");
            } else {
                for (Map<String, Object> stat : monthlyStats) {
                    System.out.printf("%d년 %d월 | 총 주문 수 : %d | 총 매출 : %,d원\n",
                            stat.get("order_year"),   // 연도 필드명
                            stat.get("order_month"),  // 월 필드명
                            stat.get("total_orders"), // 주문 수 필드명
                            stat.get("total_sales")   // 매출 필드명
                    );
                }
            }
            System.out.println("=====================================");
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("        [ 주문 통계 조회 중 오류 발생 ] ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
