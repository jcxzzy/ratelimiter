package com.yzcoding.ratelimiter.rule.datasource.impl;

import com.yzcoding.ratelimiter.bean.RuleConfig;
import com.yzcoding.ratelimiter.rule.datasource.RuleConfigSource;
import com.yzcoding.ratelimiter.rule.parser.RuleConfigParser;
import com.yzcoding.ratelimiter.rule.parser.impl.JsonRuleConfigParser;
import com.yzcoding.ratelimiter.rule.parser.impl.YamlRuleConfigParser;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于本地文件的配置类
 *
 * @author 郑艺
 * @date 2021/6/10 20:07
 */
public class FileRuleConfigSource implements RuleConfigSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileRuleConfigSource.class);

    private static final String API_LIMIT_CONFIG_NAME = "ratelimiter-rule";
    private static final String YAML_EXTENSION = "yaml";
    private static final String YML_EXTENSION = "yml";
    private static final String JSON_EXTENSION = "json";

    private static final String[] SUPPORT_EXTENSIONS = new String[]{
            YAML_EXTENSION, YML_EXTENSION, JSON_EXTENSION};

    private static final Map<String, RuleConfigParser> PARSER_MAP = new HashMap<>();

    static {
        PARSER_MAP.put(YAML_EXTENSION, new YamlRuleConfigParser());
        //PARSER_MAP.put(YML_EXTENSION, new Yml);
        PARSER_MAP.put(JSON_EXTENSION, new JsonRuleConfigParser());
    }
    @Override
    public RuleConfig load() {
        for(String extension :SUPPORT_EXTENSIONS){
            // 将限流规则配置文件ratelimiter-rule.yaml中内容读取到RuleConfig中
            RuleConfig ruleConfig = null;
            try (InputStream in = this.getClass().getResourceAsStream(getFileNameByExt(extension))){
                if(in != null){
                    RuleConfigParser parser = PARSER_MAP.get(extension);
                    return parser.parse(in);
//                  Yaml yaml = new Yaml();
//                  ruleConfig = yaml.loadAs(in, RuleConfig.class);
                }
            }catch (IOException e){
                LOGGER.info("close file error :", e);
            }
        }
        return null;
    }

    /**
     * 获取文件名
     *
     * @param extension 文件格式
     * @return 文件名
     */
    private String getFileNameByExt(String extension){
        return StringUtils.join(API_LIMIT_CONFIG_NAME, ".", extension);
    }
}

