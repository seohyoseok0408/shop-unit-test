package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    private int rid;            // 리뷰 ID (Primary Key)
    private int pid;            // 상품 ID (Foreign Key)
    private int cid;            // 고객 ID (Foreign Key)
    private int rate;           // 리뷰 평점 (1-5)
    private String title;       // 리뷰 제목
    private String content;     // 리뷰 내용
    private String img;         // 리뷰 이미지
    private Timestamp rdate;    // 리뷰 작성일 (Timestamp 형식)
}
