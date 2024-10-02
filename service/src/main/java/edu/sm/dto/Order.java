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
public class Order {
    private int oid;
    private int cid;
    private String ostatus;
    private String oname;
    private String address;
    private String addressDetail;
    private String zipCode;
    private String phone;
    private String msg;
    private Timestamp odate;
    private int price;
}
