package order;

import edu.sm.dto.Order;
import edu.sm.service.OrderService;

import java.util.List;

public class OrderSelectAll {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();

        try {
            List<Order> orders = orderService.get();

            if (orders.isEmpty()) {
                System.out.println("=====================================");
                System.out.println("      [ 조회된 주문 정보가 없습니다 ]   ");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("         [ 전체 주문 목록 조회 ]       ");
                System.out.println("=====================================");

                for (Order order : orders) {
                    System.out.println("주문 ID      : " + order.getOid());
                    System.out.println("고객 ID      : " + order.getCid());
                    System.out.println("주문 상태    : " + order.getOstatus());
                    System.out.println("수령인 이름  : " + order.getOname());
                    System.out.println("주소         : " + order.getAddress());
                    System.out.println("상세 주소    : " + order.getAddressDetail());
                    System.out.println("우편번호     : " + order.getZipCode());
                    System.out.println("전화번호     : " + order.getPhone());
                    System.out.println("메시지       : " + order.getMsg());
                    System.out.println("주문 날짜    : " + order.getOdate());
                    System.out.println("금액         : " + order.getPrice() + "원");
                    System.out.println("-------------------------------------");
                }

                System.out.println("총 " + orders.size() + "개의 주문이 조회되었습니다.");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("      [ 주문 목록 조회 중 오류 발생 ]   ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
