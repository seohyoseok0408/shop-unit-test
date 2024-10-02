package edu.sm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    private int cid; // PK
    private String pwd;
    private String cname;
    private String email;
    private String phone;
    private Date birth_date;
    private String nick_name;
    private int grade;
    private Timestamp join_date;
}
