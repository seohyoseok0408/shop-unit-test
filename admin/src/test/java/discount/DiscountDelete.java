package discount;

import edu.sm.service.DiscountService;

public class DiscountDelete {
    public static void main(String[] args) {
        DiscountService discountService = new DiscountService();
        int discountId = 1; // 삭제할 할인 ID

        try {
            boolean result = discountService.remove(discountId);
            if (result) {
                System.out.println("=====================================");
                System.out.println("         [ 할인 삭제 성공 ]           ");
                System.out.println("-------------------------------------");
                System.out.println("  삭제된 할인 ID : " + discountId);
                System.out.println("-------------------------------------");
                System.out.println("할인이 성공적으로 삭제되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("         [ 할인 삭제 실패 ]           ");
                System.out.println("-------------------------------------");
                System.out.println("해당 ID의 할인을 찾을 수 없습니다: " + discountId);
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("      [ 할인 삭제 중 오류 발생 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
