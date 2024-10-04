package order;

import edu.sm.dto.Order;
import edu.sm.service.OrderService;

import java.util.List;

public class OrderSelectByCid {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        int customerId = 20;  // 조회할 고객 ID

        try {
            List<Order> orders = orderService.getByCustomerId(customerId);

            if (orders != null && !orders.isEmpty()) {
                System.out.println("=====================================");
                System.out.println("     [ 고객 ID: " + customerId + "의 주문 목록 ]    ");
                System.out.println("=====================================");

                for (Order order : orders) {
                    System.out.println("주문 ID      : " + order.getOid());
                    System.out.println("주문 상태    : " + order.getOstatus());
                    System.out.println("주문자 이름  : " + order.getOname());
                    System.out.println("주소         : " + order.getAddress());
                    System.out.println("세부 주소    : " + order.getAddressDetail());
                    System.out.println("우편번호     : " + order.getZipCode());
                    System.out.println("전화번호     : " + order.getPhone());
                    System.out.println("요청 사항    : " + order.getMsg());
                    System.out.println("주문 날짜    : " + order.getOdate());
                    System.out.println("금액         : " + order.getPrice() + "원");
                    System.out.println("-------------------------------------");
                }

                System.out.println("총 " + orders.size() + "개의 주문이 조회되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("  [ 고객 ID: " + customerId + "의 주문 목록 없음 ]   ");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("     [ 고객 주문 조회 중 오류 발생 ]  ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
