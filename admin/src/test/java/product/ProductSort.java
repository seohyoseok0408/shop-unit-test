package product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.util.List;

public class ProductSort {
    public static void main(String[] args) {
        ProductService productService = new ProductService();

        try {
            // 전체 상품 가격 오름차순 정렬
            System.out.println("=====================================");
            System.out.println("    [ 전체 상품 가격 오름차순 정렬 ]   ");
            System.out.println("=====================================");
            List<Product> productsByPriceAsc = productService.getProductsSortedBy("price_asc", null);
            if (!productsByPriceAsc.isEmpty()) {
                for (Product product : productsByPriceAsc) {
                    printProductDetails(product);
                }
            } else {
                System.out.println("상품이 없습니다.");
            }

            // 카테고리 ID 1 상품 가격 내림차순 정렬
            System.out.println("\n=====================================");
            System.out.println(" [ 카테고리 ID 14 상품 가격 내림차순 정렬 ] ");
            System.out.println("=====================================");
            List<Product> productsByCategoryAndPriceDesc = productService.getProductsSortedBy("price_desc", 14);
            if (!productsByCategoryAndPriceDesc.isEmpty()) {
                for (Product product : productsByCategoryAndPriceDesc) {
                    printProductDetails(product);
                }
            } else {
                System.out.println("해당 카테고리에 상품이 없습니다.");
            }

            // 카테고리 ID 2 상품 리뷰 기준으로 정렬
            System.out.println("\n=====================================");
            System.out.println("  [ 카테고리 ID 13 상품 리뷰 기준 정렬 ]  ");
            System.out.println("=====================================");
            List<Product> productsByCategoryAndReview = productService.getProductsSortedBy("review", 13);
            if (!productsByCategoryAndReview.isEmpty()) {
                for (Product product : productsByCategoryAndReview) {
                    printProductDetails(product);
                }
            } else {
                System.out.println("해당 카테고리에 상품이 없습니다.");
            }

            // 상품 판매량 기준으로 정렬
            System.out.println("\n=====================================");
            System.out.println("      [ 상품 판매량 기준으로 정렬 ]     ");
            System.out.println("=====================================");
            List<Product> productsBySalesCount = productService.getProductsSortedBy("sales_count", null);
            if (!productsBySalesCount.isEmpty()) {
                for (Product product : productsBySalesCount) {
                    printProductDetails(product);
                }
            } else {
                System.out.println("해당 카테고리에 상품이 없습니다.");
            }

        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 상품 정렬 중 오류 발생 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }

    // 상품 정보를 예쁘게 출력하는 메서드
    private static void printProductDetails(Product product) {
        System.out.println("상품 ID       : " + product.getPid());
        System.out.println("상품명        : " + product.getPname());
        System.out.println("가격          : " + product.getPrice() + "원");
        System.out.println("재고          : " + product.getCnt());
        System.out.println("설명          : " + product.getContent());
        System.out.println("-------------------------------------");
    }
}
