package category;

import edu.sm.dto.Category;
import edu.sm.service.CategoryService;

import java.util.List;

public class CategorySelectAll {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();

        try {
            List<Category> categories = categoryService.get();  // 모든 카테고리 조회

            if (categories.isEmpty()) {
                System.out.println("=====================================");
                System.out.println("       [ 조회된 카테고리가 없습니다 ]   ");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("       [ 전체 카테고리 목록 조회 ]     ");
                System.out.println("=====================================");

                for (Category category : categories) {
                    System.out.println("카테고리 ID    : " + category.getCategoryId());
                    System.out.println("카테고리 이름  : " + category.getCategoryName());
                    System.out.println("카테고리 설명  : " + category.getCategoryDetail());
                    System.out.println("-------------------------------------");
                }

                System.out.println("총 " + categories.size() + "개의 카테고리가 조회되었습니다.");
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
