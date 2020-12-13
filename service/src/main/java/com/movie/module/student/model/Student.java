package com.movie.module.student.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: movie
 * @Date: 2020/4/29 17:07
 */
@Data
public class Student {
    private Integer id;
    private String name;
    private Integer age;
    private LocalDateTime ctime;
    private LocalDateTime mtime;
}
