package com.yzcoding.ratelimiter.rule;

import com.yzcoding.ratelimiter.bean.ApiLimit;

/**
 * 查询api限流配置信息接口类
 *
 * @author 郑艺
 * @date 2021/6/10 19:52
 */
public interface RateLimitRule {

    /**
     * 查询api限流配置信息
     *
     * @param appId 应用id
     * @param api 接口api
     * @return api限流配置信息
     */
    ApiLimit getLimit(String appId, String api);
}
