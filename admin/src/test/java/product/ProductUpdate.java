package product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

public class ProductUpdate {
    public static void main(String[] args) {
        try {
            ProductService productService = new ProductService();

            // 기존 상품 조회
            int pid = 15;
            Product product = productService.get(pid);

            if (product != null) {
                // 상품 정보 수정 (다양한 속성 업데이트 가능)
                product.setPrice(60000);
                product.setCnt(20);
                product.setPublic(true); // false, true
                Product updatedProduct = productService.modify(product);

                if (updatedProduct != null) {
                    System.out.println("=====================================");
                    System.out.println("          [ 상품 수정 성공 ]          ");
                    System.out.println("-------------------------------------");
                    System.out.println("  상품 ID       : " + updatedProduct.getPid());
                    System.out.println("  카테고리 ID   : " + updatedProduct.getCategoryId());
                    System.out.println("  할인 ID       : " + updatedProduct.getDisId());
                    System.out.println("  상품명        : " + updatedProduct.getPname());
                    System.out.println("  가격          : " + updatedProduct.getPrice() + "원");
                    System.out.println("  수량          : " + updatedProduct.getCnt());
                    System.out.println("  공개 여부     : " + (updatedProduct.isPublic() ? "공개" : "비공개"));
                    System.out.println("-------------------------------------");
                    System.out.println("상품 정보가 성공적으로 수정되었습니다.");
                    System.out.println("=====================================");
                } else {
                    System.out.println("=====================================");
                    System.out.println("        [ 상품 수정 중 오류 발생 ]     ");
                    System.out.println("=====================================");
                }
            } else {
                System.out.println("=====================================");
                System.out.println("           [ 상품 조회 실패 ]          ");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 상품 수정 중 오류 발생 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
