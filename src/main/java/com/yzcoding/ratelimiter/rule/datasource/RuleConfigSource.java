package com.yzcoding.ratelimiter.rule.datasource;

import com.yzcoding.ratelimiter.bean.RuleConfig;

/**
 * 配置来源接口类
 *
 * @author 郑艺
 * @date 2021/6/10 20:05
 */
public interface RuleConfigSource {

    /**
     * 加载过滤规则配置信息
     * @return
     */
    RuleConfig load();
}
