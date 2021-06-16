package com.test.es;

import com.alibaba.fastjson.JSON;
import com.test.es.po.UserInfo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: movie
 * @Date: 2021/6/15 15:31
 */
@Slf4j
@Service
public class UserService {
    @Resource
    private RestHighLevelClient restHighLevelClient;

    private static final String INDEX_NAME = "mydlq-user";
    private static final String INDEX_TYPE = "doc";

    public void addDoc() {
//        IndexRequest indexRequest = new IndexRequest(INDEX_NAME, INDEX_TYPE, "1");
        IndexRequest indexRequest = new IndexRequest(INDEX_NAME);
        indexRequest.id("1");
        UserInfo userInfo = UserInfo.builder()
                                    .name("张三")
                                    .age(29)
                                    .salary(100.00f)
                                    .address("北京市")
                                    .remark("来自北京市的张先生")
                                    .createTime(LocalDateTime.now())
                                    .birthday(LocalDate.of(1990, 01, 10))
                                    .build();
        byte[] bytes = JSON.toJSONBytes(userInfo);
        indexRequest.source(bytes, XContentType.JSON);
        try {
            IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            log.info("创建状态：" + response.status());
        } catch (IOException e) {
            log.error("addDoc ", e);
            e.printStackTrace();
        }
    }
}

