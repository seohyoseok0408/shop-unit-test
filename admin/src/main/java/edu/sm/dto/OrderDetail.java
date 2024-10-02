package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {
    private int orderDetailId;  // 주문 상세 ID (Primary Key)
    private int pid;            // 상품 ID (Foreign Key, Product)
    private int oid;            // 주문 ID (Foreign Key, Order)
    private int itemCnt;        // 주문 상품 개수
    private int odPrice;        // 주문 상품 가격
}
