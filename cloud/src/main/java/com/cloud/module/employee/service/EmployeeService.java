package com.cloud.module.employee.service;

import com.alibaba.fastjson.JSON;
import com.cloud.module.employee.model.Employee;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: movie
 * @Date: 2021/8/4 11:00
 */
@Service
public class EmployeeService {
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    public void zadd() {
        List<Employee> emps = IntStream.range(0, 50)
                                       .mapToObj(Employee::new)
                                       .collect(Collectors.toList());
        Set<ZSetOperations.TypedTuple<String>> empTuple = emps.stream()
                                                              .map(e -> new DefaultTypedTuple<>(JSON.toJSONString(e),
                                                                      Double.valueOf(Optional.ofNullable(e.getHireDate())
                                                                                             .map(h -> h.toEpochDay())
                                                                                             .orElse(0L))))
                                                              .collect(Collectors.toSet());
        Random random = new Random();
        redisTemplate.opsForZSet().add("emp" + random.nextInt(10), empTuple);
    }
}
