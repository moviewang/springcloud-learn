package com.cloud.module.employee.http;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: movie
 * @Date: 2021/10/31 20:14
 */
@FeignClient(value = "name", url = "http://predaditspws.oristarcloud.com/services/tsp/cinema?wsdl", configuration = MySoapClientConfiguration.class)
public interface NameApi {
    @GetMapping("/")
    String getName();

    @RequestMapping(value = "QueryCinemaList", consumes = {MediaType.TEXT_XML_VALUE}, produces = {MediaType.TEXT_XML_VALUE})
    DadiCinemasReponse cinemas(@RequestBody DadiCinemaParam dadiCinemaParam);
}
