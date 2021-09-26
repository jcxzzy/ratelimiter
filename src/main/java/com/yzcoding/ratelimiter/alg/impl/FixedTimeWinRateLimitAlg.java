package com.yzcoding.ratelimiter.alg.impl;

import com.yzcoding.ratelimiter.alg.RateLimitAlg;
import com.yzcoding.ratelimiter.exception.InternalErrorException;
import com.yzcoding.ratelimiter.rule.RateLimitRule;
import org.apache.commons.lang3.time.StopWatch;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 固定时间窗口限流算法
 *
 * @author 郑艺
 * @date 2021/6/10 10:47
 */
public class FixedTimeWinRateLimitAlg implements RateLimitAlg {

    /**
     * timeout for {@code Lock.tryLock() }.
     */
    private static final long TRY_LOCK_TIMEOUT = 200L;

    /**
     * 计时器
     */
    private StopWatch stopWatch;

    /**
     * 时间窗口内被访问次数
     */
    private AtomicInteger currentCount = new AtomicInteger(0);

    /**
     * 时间窗口内最大访问次数
     */
    private int limit;

    private Lock lock = new ReentrantLock();

    public FixedTimeWinRateLimitAlg(int limit){
        this(StopWatch.createStarted(), limit);
    }

    public FixedTimeWinRateLimitAlg(StopWatch stopWatch, int limit) {
        this.stopWatch = stopWatch;
        this.limit = limit;
    }

    /**
     * 判断是否被限流
     * @return 是否被限流
     * @throws InternalErrorException 限流异常类
     */
    public boolean tryAcquire() throws InternalErrorException {
        int updatedCount = currentCount.incrementAndGet();
        if(updatedCount <= limit){
            return true;
        }
        try {
            if(lock.tryLock(TRY_LOCK_TIMEOUT, TimeUnit.MILLISECONDS)){
                try {
                    if (stopWatch.getTime() > TimeUnit.SECONDS.toMillis(1)){
                        currentCount.set(0);
                        stopWatch.reset();
                    }
                    updatedCount = currentCount.incrementAndGet();
                    return updatedCount <= limit;
                }finally {
                    lock.unlock();
                }
            }else {
                throw new InternalErrorException("tryAcquire() wait lock too long:" );
            }
        }catch (InterruptedException e){
            throw new InternalErrorException("tryAcquire() is interrupted by lock");
        }
    }
}
