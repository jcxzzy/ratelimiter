package com.yzcoding.ratelimiter;

import com.yzcoding.ratelimiter.alg.impl.FixedTimeWinRateLimitAlg;
import com.yzcoding.ratelimiter.exception.InternalErrorException;
import com.yzcoding.ratelimiter.bean.ApiLimit;
import com.yzcoding.ratelimiter.rule.RateLimitRule;
import com.yzcoding.ratelimiter.bean.RuleConfig;
import com.yzcoding.ratelimiter.rule.datasource.RuleConfigSource;
import com.yzcoding.ratelimiter.rule.datasource.impl.FileRuleConfigSource;
import com.yzcoding.ratelimiter.rule.impl.TireRateLimitRule;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 限流处理类
 *
 * @author 郑艺
 * @date 2021/6/10 10:41
 */
public class RateLimiter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RateLimiter.class);

    // 为每个api在内存中存储限流计数器
    private ConcurrentHashMap<String, FixedTimeWinRateLimitAlg> counters = new ConcurrentHashMap<String, FixedTimeWinRateLimitAlg>();

    private RateLimitRule rule;

    public RateLimiter(){
        // 调用RuleConfigSource类来实现配置加载
        RuleConfigSource configSource = new FileRuleConfigSource();
        RuleConfig ruleConfig = configSource.load();
        // 将限流规则构建成支持快速查找的数据结构RateLimitRule
        this.rule = new TireRateLimitRule(ruleConfig);
    }

    public boolean limit(String appId, String url) throws InternalErrorException {
        ApiLimit apiLimit = rule.getLimit(appId, url);
        if(null == apiLimit){
            return true;
        }
        // 获取api对应在内存中的限流计数器
        String countKey = StringUtils.join(appId, ":", apiLimit.getApi());
        FixedTimeWinRateLimitAlg rateLimitCounter = counters.get(countKey);
        if (null == rateLimitCounter){
            FixedTimeWinRateLimitAlg newRateLimitCounter = new FixedTimeWinRateLimitAlg(apiLimit.getLimit());
            rateLimitCounter = counters.putIfAbsent(countKey, newRateLimitCounter);
            if (null == rateLimitCounter){
                rateLimitCounter = newRateLimitCounter;
            }
        }
        // 判断是否限流
        return rateLimitCounter.tryAcquire();
    }


}
