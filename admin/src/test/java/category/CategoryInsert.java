package category;

import edu.sm.dto.Category;
import edu.sm.service.CategoryService;

public class CategoryInsert {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();

        Category category = Category.builder()
                .categoryName("전자제품")
                .categoryDetail("가전 및 전자 기기")
                .build();

        try {
            categoryService.add(category);

            System.out.println("=====================================");
            System.out.println("         [ 카테고리 추가 성공 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("  카테고리 이름    : " + category.getCategoryName());
            System.out.println("  카테고리 설명    : " + category.getCategoryDetail());
            System.out.println("-------------------------------------");
            System.out.println("카테고리가 성공적으로 추가되었습니다.");
            System.out.println("=====================================");

        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("     [ 카테고리 추가 중 오류 발생 ]    ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
