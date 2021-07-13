package com.cloud.config;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Author: movie
 * @Date: 2021/7/7 16:32
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "sentinel")
public class SentinelConfig {
    private Map<String, Resource> resources = Maps.newHashMap();

    @Setter
    @Getter
    public static class Resource {
        private Flow flow;
        private Degrade degrade;
    }

    @Getter
    @Setter
    public static final class Flow {
        private double count = 5;
        private Integer grade = RuleConstant.FLOW_GRADE_QPS;
        private Integer behavior = RuleConstant.CONTROL_BEHAVIOR_DEFAULT;
    }

    @Getter
    @Setter
    public static final class Degrade {
        private double count = 1000;
        private int timeWindow = 10;
        private int RtSlowRequestAmount = 60;
        private int minRequestAmount = 100;
        private int grade = RuleConstant.DEGRADE_GRADE_RT;
    }
}
