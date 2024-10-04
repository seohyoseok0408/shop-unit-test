package product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

import java.util.List;

public class ProductSelectAll {
    public static void main(String[] args) {
        try {
            ProductService productService = new ProductService();

            // 전체 상품 조회
            List<Product> products = productService.get();

            if (products != null && !products.isEmpty()) {
                System.out.println("=====================================");
                System.out.println("         [ 전체 상품 목록 조회 ]       ");
                System.out.println("=====================================");

                for (Product product : products) {
                    System.out.println("상품 ID       : " + product.getPid());
                    System.out.println("카테고리 ID   : " + product.getCategoryId());
                    System.out.println("할인 ID       : " + product.getDisId());
                    System.out.println("상품명        : " + product.getPname());
                    System.out.println("가격          : " + product.getPrice() + "원");
                    System.out.println("수량          : " + product.getCnt());
                    System.out.println("이미지1       : " + product.getImg1());
                    System.out.println("이미지2       : " + product.getImg2());
                    System.out.println("이미지3       : " + product.getImg3());
                    System.out.println("이미지4       : " + product.getImg4());
                    System.out.println("상세 설명     : " + product.getContent());
                    System.out.println("공개 여부     : " + (product.isPublic() ? "공개" : "비공개"));
                    System.out.println("-------------------------------------");
                }

                System.out.println("총 " + products.size() + "개의 상품이 조회되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("           [ 조회된 상품 없음 ]       ");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 상품 조회 중 오류 발생 ]      ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
