package review;

import edu.sm.dto.Review;
import edu.sm.service.ReviewService;

public class ReviewInsert {
    public static void main(String[] args) {
        ReviewService reviewService = new ReviewService();

        // 리뷰 추가 테스트
        Review review = Review.builder()
                .pid(1)
                .cid(7)
                .rate(5)
                .title("정말 만족합니다!")
                .content("상품이 너무 좋아요. 강추합니다.")
                .img("review1.jpg")
                .build();

        try {
            reviewService.add(review);
            System.out.println("=====================================");
            System.out.println("          [ 리뷰 추가 성공 ]          ");
            System.out.println("-------------------------------------");
            System.out.println("  상품 ID     : " + review.getPid());
            System.out.println("  고객 ID     : " + review.getCid());
            System.out.println("  평점        : " + review.getRate() + "점");
            System.out.println("  제목        : " + review.getTitle());
            System.out.println("  내용        : " + review.getContent());
            System.out.println("  이미지      : " + review.getImg());
            System.out.println("-------------------------------------");
            System.out.println("리뷰가 성공적으로 추가되었습니다.");
            System.out.println("=====================================");
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 리뷰 추가 중 오류 발생 ]       ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지 : " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
