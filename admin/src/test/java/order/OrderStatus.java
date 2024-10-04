package order;

import edu.sm.dto.Order;
import edu.sm.service.OrderService;

import java.util.List;

public class OrderStatus {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        String status = "배송 완료"; // 조회할 주문 상태

        try {
            List<Order> orders = orderService.getOrdersByStatus(status);

            if (orders.isEmpty()) {
                System.out.println("=====================================");
                System.out.println("     [ '" + status + "' 상태의 주문 없음 ]   ");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("      [ '" + status + "' 상태의 주문 목록 ] ");
                System.out.println("=====================================");

                for (Order order : orders) {
                    System.out.println("주문번호   : " + order.getOid());
                    System.out.println("주문자 이름: " + order.getOname());
                    System.out.println("주문 날짜  : " + order.getOdate());
                    System.out.println("-------------------------------------");
                }

                System.out.println("총 " + orders.size() + "개의 주문이 '" + status + "' 상태로 조회되었습니다.");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("     [ 주문 상태 조회 중 오류 발생 ]   ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
