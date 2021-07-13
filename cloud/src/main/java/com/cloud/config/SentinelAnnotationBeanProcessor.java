package com.cloud.config;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: movie
 * @Date: 2021/7/7 17:00
 */
@Slf4j
@Component
public class SentinelAnnotationBeanProcessor implements BeanPostProcessor, ApplicationListener<ContextRefreshedEvent> {
    @Resource
    private SentinelConfig sentinelConfig;

    private List<FlowRule> flowRules = new ArrayList<>();
    private List<DegradeRule> degradeRules = new ArrayList<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {

        String className = AopUtils.getTargetClass(bean).getSimpleName();
        for (Method method : AopUtils.getTargetClass(bean).getDeclaredMethods()) {
            if (method.isAnnotationPresent(SentinelResource.class)) {
                SentinelResource annotationSentinel = method.getAnnotation(SentinelResource.class);
                String flowControlHandler = annotationSentinel.blockHandler();
                if (!Strings.isNullOrEmpty(flowControlHandler)) {
                    SentinelConfig.Resource resource = sentinelConfig.getResources().get(annotationSentinel.value());
                    if (resource != null && resource.getFlow() != null) {
                        loadFlowRule(resource.getFlow(), annotationSentinel, className, method);
                    } else {
                        log.warn("SENTINEL   FLOW  ==> not found @FlowRuleDefine at [{}.{}], remove `blockHandler` or add @FlowRuleDefine", className, method.getName());
                    }
                }

                String degradeControlHandler = annotationSentinel.fallback();
                if (!Strings.isNullOrEmpty(degradeControlHandler)) {
                    SentinelConfig.Resource resource = sentinelConfig.getResources().get(annotationSentinel.value());
                    if (resource != null && resource.getDegrade() != null) {
                        loadDegradeRule(resource.getDegrade(), annotationSentinel, className, method);
                    } else {
                        log.warn("SENTINEL DEGRADE ==> not found @DegradeRuleDefine at [{}.{}], remove `fallback` or add @DegradeRuleDefine", className, method.getName());
                    }
                }
            }
        }
        return bean;
    }

    private void loadFlowRule(SentinelConfig.Flow flowDef, SentinelResource annotationSentinel, String className, Method method) {
        flowRules.clear();
        FlowRule rule = new FlowRule();
        rule.setResource(annotationSentinel.value());
        rule.setCount(flowDef.getCount());
        rule.setGrade(flowDef.getGrade());
        rule.setControlBehavior(flowDef.getBehavior());
        flowRules.add(rule);
        FlowRuleManager.loadRules(flowRules);
        log.info("SENTINEL   FLOW  ==> resource \"{}\" on METHOD: ({}.{}), rule: <grade:{}, count:{}, behavior:{}>",
                annotationSentinel.value(), className, method.getName(),
                rule.getGrade(), rule.getCount(),
                rule.getControlBehavior());
    }

    private void loadDegradeRule(SentinelConfig.Degrade degradeDef, SentinelResource annotationSentinel, String className, Method method) {
        degradeRules.clear();
        DegradeRule rule = new DegradeRule();
        rule.setResource(annotationSentinel.value());
        rule.setCount(degradeDef.getCount());
        rule.setGrade(degradeDef.getGrade());
        rule.setTimeWindow(degradeDef.getTimeWindow());
        degradeRules.add(rule);
        DegradeRuleManager.loadRules(degradeRules);
        log.info("SENTINEL DEGRADE ==> resource \"{}\" on METHOD: ({}.{}), rule: <grade:{}, count:{}, timeWindow:{}(s)>",
                annotationSentinel.value(), className, method.getName(),
                rule.getGrade(),
                rule.getCount(), rule.getTimeWindow());

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("context refreshed {}", event.getApplicationContext());
    }
}