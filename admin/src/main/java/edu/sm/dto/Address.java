package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private int aid;           // 주소 ID (Primary Key)
    private int cid;           // 고객 ID (Foreign Key)
    private String aname;      // 수령인 이름
    private String address;    // 주소
    private String addressDetail; // 상세 주소
    private String zipCode;    // 우편번호
    private String phone;      // 연락처
}
