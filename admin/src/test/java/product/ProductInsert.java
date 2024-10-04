package product;

import edu.sm.dto.Product;
import edu.sm.service.ProductService;

public class ProductInsert {
    public static void main(String[] args) {
        try {
            ProductService productService = new ProductService();

            // 새로운 상품 생성
            Product product = Product.builder()
                    .categoryId(15)
                    .disId(4)
                    .pname("닌텐도")
                    .price(500000)
                    .cnt(10)
                    .img1("img1.jpg")
                    .img2("img2.jpg")
                    .img3("img3.jpg")
                    .img4("img4.jpg")
                    .content("마리오카트")
                    .isPublic(true)
                    .build();

            // 상품을 추가
            Product insertedProduct = productService.add(product);

            if (insertedProduct != null) {
                System.out.println("=====================================");
                System.out.println("          [ 상품 추가 성공 ]           ");
                System.out.println("-------------------------------------");
                System.out.println("  상품 ID       : " + insertedProduct.getPid());
                System.out.println("  카테고리 ID   : " + insertedProduct.getCategoryId());
                System.out.println("  할인 ID       : " + insertedProduct.getDisId());
                System.out.println("  상품명        : " + insertedProduct.getPname());
                System.out.println("  가격          : " + insertedProduct.getPrice() + "원");
                System.out.println("  수량          : " + insertedProduct.getCnt());
                System.out.println("  이미지1       : " + insertedProduct.getImg1());
                System.out.println("  이미지2       : " + insertedProduct.getImg2());
                System.out.println("  이미지3       : " + insertedProduct.getImg3());
                System.out.println("  이미지4       : " + insertedProduct.getImg4());
                System.out.println("  상세 설명     : " + insertedProduct.getContent());
                System.out.println("  공개 여부     : " + (insertedProduct.isPublic() ? "공개" : "비공개"));
                System.out.println("-------------------------------------");
                System.out.println("상품이 성공적으로 추가되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("          [ 상품 추가 실패 ]           ");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 상품 추가 중 오류 발생 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
