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
public class Pay {
    private int payId;         // 결제 ID (Primary Key)
    private int oid;           // 주문 ID (Foreign Key)
    private int payPrice;      // 결제 금액
    private String payMethod;  // 결제 방법 (ex: 신용카드, 계좌이체 등)
    private long card;         // 결제 카드 번호
    private Timestamp payDate; // 결제 일자 (Timestamp 형식으로 저장)
}
