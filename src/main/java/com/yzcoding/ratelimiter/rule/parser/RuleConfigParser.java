package com.yzcoding.ratelimiter.rule.parser;

import com.yzcoding.ratelimiter.bean.RuleConfig;

import java.io.InputStream;

/**
 * 配置文件解析接口
 * @author 郑艺
 * @date 2021/6/10 20:03
 */
public interface RuleConfigParser {

    /**
     * 解析字符串获取配置信息
     * @param configText 配置信息字符串
     * @return 配置信息
     */
    RuleConfig parse(String configText);

    /**
     * 解析输入流获取配置信息
     * @param in 输入流
     * @return 配置信息
     */
    RuleConfig parse(InputStream in);
}
