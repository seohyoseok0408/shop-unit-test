package order;

import edu.sm.dto.Order;
import edu.sm.service.OrderService;

public class OrderSelect {
    public static void main(String[] args) {
        OrderService orderService = new OrderService();
        int id = 1; // 조회할 주문 ID

        try {
            Order order = orderService.get(id);

            if (order != null) {
                System.out.println("=====================================");
                System.out.println("          [ 주문 정보 조회 ]          ");
                System.out.println("-------------------------------------");
                System.out.println("  주문 ID      : " + order.getOid());
                System.out.println("  고객 ID      : " + order.getCid());
                System.out.println("  주문자 이름  : " + order.getOname());
                System.out.println("  주소         : " + order.getAddress());
                System.out.println("  상세 주소    : " + order.getAddressDetail());
                System.out.println("  우편번호     : " + order.getZipCode());
                System.out.println("  전화번호     : " + order.getPhone());
                System.out.println("  요청사항     : " + order.getMsg());
                System.out.println("  총 주문금액  : " + order.getPrice() + "원");
                System.out.println("-------------------------------------");
                System.out.println("주문 정보가 성공적으로 조회되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("         [ 주문 정보 조회 실패 ]      ");
                System.out.println("-------------------------------------");
                System.out.println("해당 ID의 주문을 찾을 수 없습니다: " + id);
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("      [ 주문 조회 중 오류 발생 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
