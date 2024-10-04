package discount;

import edu.sm.dto.Discount;
import edu.sm.service.DiscountService;

import java.sql.Date;

public class DiscountInsert {
    public static void main(String[] args) {
        DiscountService discountService = new DiscountService();

        // 새로운 할인 정보를 생성
        Discount discount = Discount.builder()
                .disName("새해 특가")
                .disRate(20.0f)
                .disStart(Date.valueOf("2024-01-01"))
                .disEnd(Date.valueOf("2024-01-31"))
                .build();

        try {
            discountService.add(discount);  // 할인 추가

            System.out.println("=====================================");
            System.out.println("         [ 할인 추가 성공 ]           ");
            System.out.println("-------------------------------------");
            System.out.println("  할인명      : " + discount.getDisName());
            System.out.println("  할인율      : " + discount.getDisRate() + "%");
            System.out.println("  시작일      : " + discount.getDisStart());
            System.out.println("  종료일      : " + discount.getDisEnd());
            System.out.println("-------------------------------------");
            System.out.println("할인 정보가 성공적으로 추가되었습니다.");
            System.out.println("=====================================");

        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("      [ 할인 추가 중 오류 발생 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
