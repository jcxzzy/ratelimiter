package com.yzcoding.ratelimiter.rule.parser.impl;

import com.yzcoding.ratelimiter.bean.RuleConfig;
import com.yzcoding.ratelimiter.rule.parser.RuleConfigParser;

import java.io.InputStream;

/**
 * yml格式配置文件解析类
 *
 * @author 郑艺
 * @date 2021/6/11 15:20
 */
public class YmlRuleConfigParser implements RuleConfigParser {

    /**
     * 解析字符串获取配置信息
     * @param configText 配置信息字符串
     * @return 配置信息
     */
    @Override
    public RuleConfig parse(String configText) {
        return null;
    }

    /**
     * 解析输入流获取配置信息
     * @param in 输入流
     * @return 配置信息
     */
    @Override
    public RuleConfig parse(InputStream in) {
        return null;
    }
}
