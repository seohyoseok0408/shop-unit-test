package order;

import edu.sm.service.OrderService;

public class OrderUpdateStatus {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        int orderId = 1; // 업데이트할 주문 번호
        String newStatus = "배송 중"; // 변경할 상태

        try {
            // 메서드 호출
            orderService.changeOrderStatus(orderId, newStatus);

            System.out.println("=====================================");
            System.out.println("       [ 주문 상태 변경 성공 ]        ");
            System.out.println("-------------------------------------");
            System.out.println("  주문번호     : " + orderId);
            System.out.println("  새로운 상태  : " + newStatus);
            System.out.println("-------------------------------------");
            System.out.println("주문 상태가 '" + newStatus + "'로 성공적으로 변경되었습니다.");
            System.out.println("=====================================");

        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("    [ 주문 상태 변경 중 오류 발생 ]    ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
