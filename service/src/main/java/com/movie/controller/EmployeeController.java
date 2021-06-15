package com.movie.controller;

import com.movie.module.employee.model.EsProduct;
import com.movie.module.employee.service.EsProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author: movie
 * @Date: 2021/5/2 13:31
 */
@RestController
public class EmployeeController {
    @Resource
    private EsProductService esProductService;

    @GetMapping("/search")
    @ResponseBody
    public List<EsProduct> search(String keywords, int page, int size) {
        return esProductService.search(keywords, page, size).getContent();
    }

    @GetMapping("/get")
    @ResponseBody
    public Iterable<EsProduct> get() {
        return esProductService.getAll();
    }

    @PostMapping("/save")
    public Iterable<EsProduct> save() {
        return esProductService.getAll();
    }

    @GetMapping("/del")
    @ResponseBody
    public Map<String, Object> del(@RequestParam(defaultValue = "0") Long id) {
        esProductService.delete(id);
        return Map.of("code", 0,
                "data", "",
                "msg", "");
    }


}
