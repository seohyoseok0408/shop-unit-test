package pay;

import edu.sm.dto.Pay;
import edu.sm.service.PayService;

public class PaySelect {
    public static void main(String[] args) {
        PayService payService = new PayService();
        int payId = 2; // 조회할 결제 ID

        try {
            Pay pay = payService.get(payId);
            if (pay != null) {
                System.out.println("=====================================");
                System.out.println("          [ 결제 정보 조회 ]          ");
                System.out.println("-------------------------------------");
                System.out.println("  결제 ID       : " + pay.getPayId());
                System.out.println("  주문 ID       : " + pay.getOid());
                System.out.println("  결제 금액     : " + pay.getPayPrice() + "원");
                System.out.println("  결제 방법     : " + pay.getPayMethod());
                System.out.println("  카드 번호     : **** **** **** " + (pay.getCard() % 10000)); // 카드 번호 마지막 4자리만 표시
                System.out.println("-------------------------------------");
                System.out.println("결제 정보가 성공적으로 조회되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("       [ 해당 결제 정보가 없습니다 ]    ");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 결제 정보 조회 중 오류 발생 ] ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
