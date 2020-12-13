package com.movie.easyrpc.service;

/**
 * @Author: movie
 * @Date: 2020/3/27 12:28
 */
public class SchoolService {
    public String getName(Integer id) {
        System.out.println("getName called id " + id);
        return "Sunny school";
    }

}
