package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Discount {
    private int disId;          // 할인 ID (Primary Key)
    private String disName;     // 할인 이름
    private float disRate;      // 할인율
    private Date disStart;      // 할인 시작일
    private Date disEnd;        // 할인 종료일
}
