package com.yzcoding.ratelimiter.exception;

/**
 * 限流异常类
 * @author 郑艺
 * @date 2021/6/10 19:17
 */
public class InternalErrorException extends Exception {

    public InternalErrorException() {
    }

    public InternalErrorException(String message) {
        super(message);
    }
}
