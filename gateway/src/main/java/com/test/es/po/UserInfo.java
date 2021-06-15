package com.test.es.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: movie
 * @Date: 2021/6/15 15:27
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    private String name;
    private int age;
    private float salary;
    private String address;
    private String remark;
    private LocalDateTime createTime;
    private LocalDate birthday;
}
