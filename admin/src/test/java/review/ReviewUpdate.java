package review;

import edu.sm.dto.Review;
import edu.sm.service.ReviewService;

public class ReviewUpdate {
    public static void main(String[] args) {
        ReviewService reviewService = new ReviewService();

        // 리뷰 수정 테스트
        Review review = Review.builder()
                .rid(5)  // 기존 리뷰 ID
                .pid(3)
                .cid(3)
                .rate(3)  // 평점을 3점으로 변경
                .title("조금 아쉬웠어요.")
                .content("상품이 괜찮긴 한데, 생각보다 배송이 늦었습니다.")
                .img("review1_updated.jpg")
                .build();

        try {
            Review result = reviewService.modify(review);
            if (result != null) {
                System.out.println("=====================================");
                System.out.println("          [ 리뷰 수정 성공 ]          ");
                System.out.println("-------------------------------------");
                System.out.println("  리뷰 ID     : " + review.getRid());
                System.out.println("  상품 ID     : " + review.getPid());
                System.out.println("  고객 ID     : " + review.getCid());
                System.out.println("  평점        : " + review.getRate() + "점");
                System.out.println("  제목        : " + review.getTitle());
                System.out.println("  내용        : " + review.getContent());
                System.out.println("  이미지      : " + review.getImg());
                System.out.println("-------------------------------------");
                System.out.println("리뷰 정보가 성공적으로 수정되었습니다.");
                System.out.println("=====================================");
            } else {
                System.out.println("=====================================");
                System.out.println("          [ 리뷰 수정 실패 ]          ");
                System.out.println("-------------------------------------");
                System.out.println("해당 리뷰 ID(" + review.getRid() + ")를 찾을 수 없습니다.");
                System.out.println("=====================================");
            }
        } catch (Exception e) {
            System.out.println("=====================================");
            System.out.println("       [ 리뷰 수정 중 오류 발생 ]      ");
            System.out.println("-------------------------------------");
            System.out.println("에러 메시지: " + e.getMessage());
            System.out.println("=====================================");
            e.printStackTrace();
        }
    }
}
