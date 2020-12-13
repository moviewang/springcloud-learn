package com.test.controller;

import com.test.sevice.SchoolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: movie
 * @Date: 2020/3/27 13:00
 */
@RestController
public class MainController {
    @Resource
    private SchoolService schoolService;

    @GetMapping("school/name")
    public String getSchoolName() {
        return schoolService.getName(5);
    }
}
