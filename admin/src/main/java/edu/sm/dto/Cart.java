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
public class Cart {
    private int cartKey;
    private int cid;
    private int pid;
    private int cnt;
    private int price; // 가격 필드 추가
}
