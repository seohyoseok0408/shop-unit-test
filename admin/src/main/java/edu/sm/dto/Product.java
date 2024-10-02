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
public class Product {
    private int pid;           // 상품 ID
    private int categoryId;    // 카테고리 ID
    private int disId;         // 할인 ID
    private String pname;      // 상품명
    private int price;         // 가격
    private int cnt;           // 재고 수량
    private String img1;       // 이미지 1
    private String img2;       // 이미지 2
    private String img3;       // 이미지 3
    private String img4;       // 이미지 4
    private String content;    // 상품 설명
    private Timestamp pdate;   // 상품 등록일
    private boolean isPublic;  // 상품 공개 여부
}
