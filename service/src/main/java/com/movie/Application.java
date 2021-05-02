package com.movie;

import com.movie.module.employee.dao.EsProductDao;
import com.movie.module.employee.model.EsProduct;
import com.movie.module.employee.service.EsProductService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.Page;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: movie
 * @Date: 2020/3/27 13:56
 */
@SpringBootApplication
@EnableConfigurationProperties
@EnableDubbo
//@EnableDiscoveryClient
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Resource
    private EsProductService esProductService;
    @Resource
    private EsProductDao esProductDao;

    //    @PostConstruct
    public void init() {
        EsProduct esProduct = EsProduct.builder()
                                       .id(System.currentTimeMillis())
                                       .keywords("小米")
                                       .name("小米")
                                       .subTitle("小米 上次")
                                       .build();
        esProductDao.saveAll(List.of(esProduct));
        Page<EsProduct> products = esProductService.search("小米", 1, 10);
        System.out.println("result:" + products.getTotalElements());
        List<EsProduct> content = products.getContent();
        System.out.println(content.size());
        for (EsProduct product : content) {
            System.out.println("product:" + product);
        }
        products.forEach(p -> {
            System.out.println(p.getSubTitle());
        });
    }
}
