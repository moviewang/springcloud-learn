package com.movie.module.employee.service;

import com.movie.module.employee.dao.EsProductDao;
import com.movie.module.employee.model.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: movie
 * @Date: 2021/5/1 18:21
 */
@Service
public class EsProductService {
    @Resource
    private EsProductDao esProductDao;

    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        PageRequest page = PageRequest.of(pageNum, pageSize);
        return esProductDao.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, page);
    }

}
