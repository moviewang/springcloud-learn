package com.cloud.common;

import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

/**
 * @Author: movie
 * @Date: 2021/6/29 11:17
 */
@Getter
@ToString
public class Result<T> {
    /**
     * 业务错误码
     */
    private Integer code;
    /**
     * 信息描述ap
     */
    private String message;
    /**
     * 返回参数
     */
    private T body;

    private long timeStamp = Instant.now().toEpochMilli();

    private Result(ResultStatusEnum resultStatusEnum, T body) {
        this.code = resultStatusEnum.getCode();
        this.message = resultStatusEnum.getMessage();
        this.body = body;
    }

    private Result(Integer code, String message, T body) {
        this.code = code;
        this.message = message;
        this.body = body;
    }

    /**
     * 业务成功返回业务代码和描述信息
     */
    public static Result<Void> success() {
        return new Result<Void>(ResultStatusEnum.SUCCESS, null);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultStatusEnum.SUCCESS, data);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> success(ResultStatusEnum resultStatusEnum, T data) {
        if (resultStatusEnum == null) {
            return success(data);
        }
        return new Result<T>(resultStatusEnum, data);
    }

    /**
     * 业务成功返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> success(int code, String message, T data) {
        return new Result<T>(code, message, data);
    }

    /**
     * 业务异常返回业务代码和描述信息
     */
    public static <T> Result<T> failure() {
        return new Result<T>(ResultStatusEnum.INTERNAL_SERVER_ERROR, null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> failure(ResultStatusEnum resultStatusEnum) {
        return failure(resultStatusEnum, null);
    }

    /**
     * 业务异常返回业务代码,描述和返回的参数
     */
    public static <T> Result<T> failure(ResultStatusEnum resultStatusEnum, T data) {
        if (resultStatusEnum == null) {
            return new Result<T>(ResultStatusEnum.INTERNAL_SERVER_ERROR, null);
        }
        return new Result<T>(resultStatusEnum, data);
    }
}
