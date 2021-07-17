package com.cloud.common;

import com.cloud.constants.UserConstant;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

/**
 * @Author: movie
 * @Date: 2021/6/29 11:12
 */
@ToString
@Getter
public enum ResultStatusEnum {
    SUCCESS(HttpStatus.OK, 200, "OK"),
    NOT_LOGIN(HttpStatus.OK, UserConstant.NOT_LOGIN, "未登录"),
    OTHER_COMP_RETURN_NULL(HttpStatus.OK, UserConstant.OTHER_COMP_RETURN_NULL, "激活接口返回空"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, 400, "Bad Request"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 500, "Internal Server Error");

    /**
     * 返回的HTTP状态码,  符合http请求
     */
    private HttpStatus httpStatus;
    /**
     * 业务异常码
     */
    private Integer code;
    /**
     * 业务异常信息描述
     */
    private String message;

    ResultStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    ResultStatusEnum(HttpStatus httpStatus, Integer code, String message) {
        this.httpStatus = httpStatus;
        this.code = code;
        this.message = message;
    }
}
