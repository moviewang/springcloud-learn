package com.movie.controller;

import com.movie.module.employee.model.EsProduct;
import com.movie.module.employee.service.EsProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: movie
 * @Date: 2021/5/2 13:31
 */
@RestController("employee")
public class EmployeeController {
    @Resource
    private EsProductService esProductService;

    @GetMapping("/search")
    @ResponseBody
    public List<EsProduct> search(String keywords, int page, int size) {
        return esProductService.search(keywords, page, size).getContent();
    }
}
