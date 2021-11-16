package com.cloud.module.employee.http;

import com.cloud.utils.JaxbBinder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: movie
 * @Date: 2021/11/12 18:12
 */
@Service
public class HttpService {
    @Resource
    private NameApi nameApi;

    public DadiCinemasReponse cinemas() {
        DadiCinemaParam dadiCinemaParam = new DadiCinemaParam();
        dadiCinemaParam.setPAppCode("TESTUSER");
        dadiCinemaParam.setPCompress("0");
        dadiCinemaParam.setPVerifyInfo("c03349af4edeb7c75266a1cee5d980e8");
        DadiCinemasReponse response = nameApi.cinemas(dadiCinemaParam);
        JaxbBinder jaxbBinder = new JaxbBinder(DadiCinemasReponse.QueryCinemaListResult.class);
        DadiCinemasReponse.QueryCinemaListResult queryCinemaListResult = jaxbBinder.fromXml(response.getRtn());
        return response;
    }
}
