package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    private int categoryId;       // 카테고리 ID (Primary Key)
    private String categoryName;  // 카테고리 이름
    private String categoryDetail; // 카테고리 설명
}
