package com.yzcoding.ratelimiter.alg;

import com.yzcoding.ratelimiter.exception.InternalErrorException;

/**
 * 限流算法接口
 *
 * @author 郑艺
 * @date 2021/6/10 20:08
 */
public interface RateLimitAlg  {

    /**
     * 判断是否被限流
     * @return 是否被限流
     * @throws InternalErrorException 限流异常类
     */
    boolean tryAcquire() throws InternalErrorException;
}
