package discount;

import edu.sm.dto.Discount;
import edu.sm.service.DiscountService;

import java.sql.Date;

public class DiscountUpdate {
    public static void main(String[] args) {
        DiscountService discountService = new DiscountService();

        // 기존 할인 정보를 수정
        Discount discount = Discount.builder()
                .disId(2)  // 수정할 할인 ID (기존 ID로 수정)
                .disName("새해 특가 수정")
                .disRate(15.0f)
                .disStart(Date.valueOf("2024-01-01"))
                .disEnd(Date.valueOf("2024-02-01"))
                .build();

        try {
            Discount isUpdated = discountService.modify(discount);  // 할인 수정

            if (isUpdated != null) {
                System.out.println("=====================================");
                System.out.println("         [ 할인 정보 수정 성공 ]       ");
                System.out.println("-------------------------------------");
                System.out.println("  할인 ID     : " + discount.getDisId());
                System.out.println("  할인명      : " + discount.getDisName());
                System.out.println("  할인율      : " + discount.getDisRate() + "%");
                System.out.println("  시작일      : " + discount.getDisStart());
                System.out.println("  종료일      : " + discount.getDisEnd());
                System.out.println("-------------------------------------");
                System.out.println("할인 정보가 성공적으로 수정되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("         [ 할인 정보 수정 실패 ]       ");
                System.out.println("-------------------------------------");
                System.out.println("해당 ID의 할인을 찾을 수 없습니다: " + discount.getDisId());
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 할인 정보 수정 중 오류 발생 ] ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
