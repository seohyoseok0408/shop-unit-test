package orderdetail;

import edu.sm.dto.OrderDetail;
import edu.sm.service.OrderDetailService;

import java.util.List;

public class OrderDetailSelectAll {
    public static void main(String[] args) {
        OrderDetailService orderDetailService = new OrderDetailService();

        try {
            List<OrderDetail> orderDetails = orderDetailService.get();

            if (orderDetails.isEmpty()) {
                System.out.println("=====================================");
                System.out.println("     [ 조회된 주문 상세 정보가 없습니다 ] ");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("       [ 전체 주문 상세 정보 조회 ]      ");
                System.out.println("=====================================");

                for (OrderDetail detail : orderDetails) {
                    System.out.println("주문 ID      : " + detail.getOid());
                    System.out.println("상품 ID      : " + detail.getPid());
                    System.out.println("상품 수량     : " + detail.getItemCnt());
                    System.out.println("금액       : " + detail.getOdPrice());
                    System.out.println("-------------------------------------");
                }

                System.out.println("총 " + orderDetails.size() + "개의 주문 상세 정보가 조회되었습니다.");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("  [ 주문 상세 정보 조회 중 오류 발생 ]  ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
