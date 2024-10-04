package review;

import edu.sm.dto.Review;
import edu.sm.service.ReviewService;

import java.util.List;

public class ReviewSelectByPid {
    public static void main(String[] args) {
        ReviewService reviewService = new ReviewService();
        int pid = 5;  // 조회할 상품 ID

        try {
            List<Review> reviews = reviewService.getByProductId(pid);

            if (reviews.isEmpty()) {
                System.out.println("=====================================");
                System.out.println("   [ 해당 상품에 대한 리뷰가 없습니다 ]   ");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("   [ 상품 ID: " + pid + "에 대한 리뷰 목록 조회 ]   ");
                System.out.println("=====================================");

                for (Review review : reviews) {
                    System.out.println("리뷰 ID     : " + review.getRid());
                    System.out.println("회원 ID     : " + review.getCid());
                    System.out.println("평점        : " + review.getRate() + "점");
                    System.out.println("제목        : " + review.getTitle());
                    System.out.println("내용        : " + review.getContent());
                    System.out.println("이미지      : " + review.getImg());
                    System.out.println("작성일      : " + review.getRdate());
                    System.out.println("-------------------------------------");
                }

                System.out.println("총 " + reviews.size() + "개의 리뷰가 조회되었습니다.");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("     [ 리뷰 조회 중 오류 발생 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
