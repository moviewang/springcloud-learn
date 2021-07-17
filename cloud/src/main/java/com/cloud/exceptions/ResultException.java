package com.cloud.exceptions;

import com.cloud.common.ResultStatusEnum;
import lombok.Getter;

/**
 * @Author: movie
 * @Date: 2021/6/29 11:57
 */
@Getter
public class ResultException extends RuntimeException {
    private ResultStatusEnum resultStatus;

    public ResultException(ResultStatusEnum result) {
        super(result.getMessage());
    }
}
