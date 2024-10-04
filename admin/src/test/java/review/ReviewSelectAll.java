package review;

import edu.sm.dto.Review;
import edu.sm.service.ReviewService;

import java.util.List;

public class ReviewSelectAll {
    public static void main(String[] args) {
        ReviewService reviewService = new ReviewService();

        try {
            List<Review> reviews = reviewService.get();

            if (reviews.isEmpty()) {
                System.out.println("=====================================");
                System.out.println("       [ 조회된 리뷰가 없습니다 ]      ");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("         [ 전체 리뷰 목록 조회 ]       ");
                System.out.println("=====================================");

                for (Review review : reviews) {
                    System.out.println("리뷰 ID     : " + review.getRid());
                    System.out.println("상품 ID     : " + review.getPid());
                    System.out.println("고객 ID     : " + review.getCid());
                    System.out.println("평점        : " + review.getRate() + "점");
                    System.out.println("제목        : " + review.getTitle());
                    System.out.println("내용        : " + review.getContent());
                    System.out.println("이미지      : " + review.getImg());
                    System.out.println("작성 날짜   : " + review.getRdate());
                    System.out.println("-------------------------------------");
                }

                System.out.println("총 " + reviews.size() + "개의 리뷰가 조회되었습니다.");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 리뷰 조회 중 오류 발생 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
