package orderdetail;

import edu.sm.dto.OrderDetail;
import edu.sm.service.OrderDetailService;

import java.util.List;

public class OrderDetailSelectByOid {
    public static void main(String[] args) {
        OrderDetailService orderDetailService = new OrderDetailService();
        int oid = 11;

        try {
            List<OrderDetail> orderDetails = orderDetailService.getByOid(oid);

            if (orderDetails.isEmpty()) {
                System.out.println("=====================================");
                System.out.println("   [ 주문 ID " + oid + "에 대한 주문 상세 정보가 없습니다 ] ");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("   [ 주문 ID " + oid + "에 대한 주문 상세 정보 ]   ");
                System.out.println("=====================================");

                for (OrderDetail detail : orderDetails) {
                    System.out.println("주문 상세 번호 : " + detail.getOrderDetailId());
                    System.out.println("상품 번호     : " + detail.getPid());
                    System.out.println("개수         : " + detail.getItemCnt());
                    System.out.println("가격         : " + detail.getOdPrice() + "원");
                    System.out.println("-------------------------------------");
                }

                System.out.println("총 " + orderDetails.size() + "개의 주문 상세 정보가 조회되었습니다.");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("   [ 주문 상세 정보 조회 중 오류 발생 ]   ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
