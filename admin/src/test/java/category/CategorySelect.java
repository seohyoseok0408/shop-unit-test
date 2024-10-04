package category;

import edu.sm.dto.Category;
import edu.sm.service.CategoryService;

public class CategorySelect {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();

        int categoryId = 5; // 조회할 카테고리 ID

        try {
            Category category = categoryService.get(categoryId);

            if (category != null) {
                System.out.println("=====================================");
                System.out.println("       [ 카테고리 정보 조회 ]         ");
                System.out.println("-------------------------------------");
                System.out.println("  카테고리 ID    : " + category.getCategoryId());
                System.out.println("  카테고리 이름  : " + category.getCategoryName());
                System.out.println("  카테고리 설명  : " + category.getCategoryDetail());
                System.out.println("-------------------------------------");
                System.out.println("카테고리 정보가 성공적으로 조회되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("         [ 카테고리 조회 실패 ]       ");
                System.out.println("-------------------------------------");
                System.out.println("해당 ID의 카테고리를 찾을 수 없습니다: " + categoryId);
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("     [ 카테고리 조회 중 오류 발생 ]    ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
