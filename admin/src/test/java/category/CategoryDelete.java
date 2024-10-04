package category;

import edu.sm.service.CategoryService;

public class CategoryDelete {
    public static void main(String[] args) {
        CategoryService categoryService = new CategoryService();

        int categoryId = 1; // 삭제할 카테고리 ID

        try {
            boolean result = categoryService.remove(categoryId);
            if (result) {
                System.out.println("=====================================");
                System.out.println("        [ 카테고리 삭제 성공 ]        ");
                System.out.println("-------------------------------------");
                System.out.println("  삭제된 카테고리 ID : " + categoryId);
                System.out.println("-------------------------------------");
                System.out.println("카테고리가 성공적으로 삭제되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("        [ 카테고리 삭제 실패 ]        ");
                System.out.println("-------------------------------------");
                System.out.println("해당 ID의 카테고리를 찾을 수 없습니다: " + categoryId);
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("     [ 카테고리 삭제 중 오류 발생 ]    ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
