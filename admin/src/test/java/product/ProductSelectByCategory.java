package product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.util.List;

public class ProductSelectByCategory {
    public static void main(String[] args) {
        // ProductService 인스턴스 생성
        ProductService productService = new ProductService();

        try {
            // 특정 카테고리 ID로 상품을 조회 (예: categoryId = 1)
            int categoryId = 14;
            List<Product> products = productService.getProductsByCategory(categoryId);

            // 조회 결과 출력
            if (products.isEmpty()) {
                System.out.println("=====================================");
                System.out.println("     [ 카테고리 " + categoryId + "에 속하는 상품이 없습니다 ] ");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("   [ 카테고리 " + categoryId + "에 속하는 상품 목록 ]   ");
                System.out.println("=====================================");

                for (Product product : products) {
                    System.out.println("상품 ID      : " + product.getPid());
                    System.out.println("상품명       : " + product.getPname());
                    System.out.println("가격         : " + product.getPrice() + "원");
                    System.out.println("이미지1      : " + product.getImg1());
                    System.out.println("설명         : " + product.getContent());
                    System.out.println("-------------------------------------");
                }

                System.out.println("총 " + products.size() + "개의 상품이 카테고리 " + categoryId + "에 속합니다.");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("     [ 카테고리별 상품 조회 중 오류 발생 ] ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
