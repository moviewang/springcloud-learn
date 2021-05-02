package com.movie.module.employee.dao;

import com.movie.module.employee.model.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: movie
 * @Date: 2021/5/1 18:13
 */
public interface EsProductDao extends ElasticsearchRepository<EsProduct, Long> {
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keyword, Pageable pageable);
}
