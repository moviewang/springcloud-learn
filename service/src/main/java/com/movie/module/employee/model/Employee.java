package com.movie.module.employee.model;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: movie
 * @Date: 2020/5/20 19:13
 */
@Data
public class Employee {
    private Integer empNo;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate hireDate;
}
