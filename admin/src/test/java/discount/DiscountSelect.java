package discount;

import edu.sm.dto.Discount;
import edu.sm.service.DiscountService;

public class DiscountSelect {
    public static void main(String[] args) {
        DiscountService discountService = new DiscountService();
        int discountId = 2;  // 조회할 할인 ID

        try {
            Discount discount = discountService.get(discountId);

            if (discount != null) {
                System.out.println("=====================================");
                System.out.println("         [ 할인 정보 조회 ]           ");
                System.out.println("-------------------------------------");
                System.out.println("  할인 ID     : " + discount.getDisId());
                System.out.println("  할인명      : " + discount.getDisName());
                System.out.println("  할인율      : " + discount.getDisRate() + "%");
                System.out.println("  시작일      : " + discount.getDisStart());
                System.out.println("  종료일      : " + discount.getDisEnd());
                System.out.println("-------------------------------------");
                System.out.println("할인 정보가 성공적으로 조회되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("         [ 할인 정보 조회 실패 ]       ");
                System.out.println("-------------------------------------");
                System.out.println("해당 ID의 할인을 찾을 수 없습니다: " + discountId);
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 할인 조회 중 오류 발생 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
