package review;

import edu.sm.service.ReviewService;

public class ReviewDelete {
    public static void main(String[] args) {
        ReviewService reviewService = new ReviewService();
        int rid = 4;  // 삭제할 리뷰 ID

        try {
            boolean result = reviewService.remove(rid);
            if (result) {
                System.out.println("=====================================");
                System.out.println("          [ 리뷰 삭제 성공 ]          ");
                System.out.println("-------------------------------------");
                System.out.println("리뷰 ID : " + rid);
                System.out.println("해당 리뷰가 성공적으로 삭제되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("          [ 리뷰 삭제 실패 ]          ");
                System.out.println("-------------------------------------");
                System.out.println("해당 리뷰 ID(" + rid + ")를 찾을 수 없습니다.");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 리뷰 삭제 중 오류 발생 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
