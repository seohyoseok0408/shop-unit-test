package discount;

import edu.sm.dto.Discount;
import edu.sm.service.DiscountService;

import java.util.List;

public class DiscountSelectAll {
    public static void main(String[] args) {
        DiscountService discountService = new DiscountService();

        try {
            List<Discount> discounts = discountService.get();  // 모든 할인 조회

            if (discounts.isEmpty()) {
                System.out.println("=====================================");
                System.out.println("     [ 조회된 할인 정보가 없습니다 ]   ");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("       [ 전체 할인 목록 조회 ]        ");
                System.out.println("=====================================");

                for (Discount discount : discounts) {
                    System.out.println("할인 ID     : " + discount.getDisId());
                    System.out.println("할인명      : " + discount.getDisName());
                    System.out.println("할인율      : " + discount.getDisRate() + "%");
                    System.out.println("시작일      : " + discount.getDisStart());
                    System.out.println("종료일      : " + discount.getDisEnd());
                    System.out.println("-------------------------------------");
                }

                System.out.println("총 " + discounts.size() + "개의 할인이 조회되었습니다.");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 할인 목록 조회 중 오류 발생 ] ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
