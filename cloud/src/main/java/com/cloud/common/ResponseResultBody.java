package com.cloud.common;

import java.lang.annotation.*;

/**
 * @Author: movie
 * @Date: 2021/6/29 11:20
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
public @interface ResponseResultBody {
}
