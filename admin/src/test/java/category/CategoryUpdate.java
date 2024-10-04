package category;

import edu.sm.dto.Category;
import edu.sm.service.CategoryService;

public class CategoryUpdate {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();

        Category category = Category.builder()
                .categoryId(1) // 기존 ID로 수정
                .categoryName("가전제품")
                .categoryDetail("수정된 가전 및 전자 기기")
                .build();

        try {
            Category isUpdated = categoryService.modify(category);

            if (isUpdated != null) {
                System.out.println("=====================================");
                System.out.println("         [ 카테고리 수정 성공 ]        ");
                System.out.println("-------------------------------------");
                System.out.println("  카테고리 ID    : " + category.getCategoryId());
                System.out.println("  카테고리 이름  : " + category.getCategoryName());
                System.out.println("  카테고리 설명  : " + category.getCategoryDetail());
                System.out.println("-------------------------------------");
                System.out.println("카테고리가 성공적으로 수정되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("         [ 카테고리 수정 실패 ]        ");
                System.out.println("-------------------------------------");
                System.out.println("해당 ID의 카테고리를 찾을 수 없습니다: " + category.getCategoryId());
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("     [ 카테고리 수정 중 오류 발생 ]    ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
