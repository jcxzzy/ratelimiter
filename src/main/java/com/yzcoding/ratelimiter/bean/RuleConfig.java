package com.yzcoding.ratelimiter.bean;

import java.util.List;

/**
 * 限流规则存储类
 *
 * @author 郑艺
 * @date 2021/6/10 10:45
 */
public class RuleConfig {

    /**
     * 应用配置信息数组
     */
    private List<AppRuleConfig> configs;

    public List<AppRuleConfig> getConfigs(){
        return configs;
    }

    public void setConfigs(List<AppRuleConfig> configs){
        this.configs = configs;
    }

    /**
     * 应用配置信息类
     *
     */
    public static class AppRuleConfig{
        /**
         * 应用id
         */
        private String appId;

        /**
         * 限定规则数组
         */
        private List<ApiLimit> limits;

        public AppRuleConfig() { }

        public AppRuleConfig(String appId, List<ApiLimit> limits) {
            this.appId = appId;
            this.limits = limits;
        }

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public List<ApiLimit> getLimits() {
            return limits;
        }

        public void setLimits(List<ApiLimit> limits) {
            this.limits = limits;
        }
    }
}



