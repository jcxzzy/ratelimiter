package com.yzcoding.ratelimiter.rule.parser.impl;

import com.yzcoding.ratelimiter.bean.RuleConfig;
import com.yzcoding.ratelimiter.rule.parser.RuleConfigParser;

import java.io.InputStream;

/**
 * json格式配置文件解析类
 *
 * @author 郑艺
 * @date 2021/6/10 20:04
 */
public class JsonRuleConfigParser implements RuleConfigParser {
    @Override
    public RuleConfig parse(String configText) {
        return null;
    }

    @Override
    public RuleConfig parse(InputStream in) {
        return null;
    }
}
