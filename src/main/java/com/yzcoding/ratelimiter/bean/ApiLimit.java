package com.yzcoding.ratelimiter.bean;

/**
 * api限流配置信息
 *
 * @author 郑艺
 * @date 2021/6/10 10:45
 */
public class ApiLimit {

    /**
     * 默认限流时间窗口大小为1分钟（1 second）
     */
    private static final int DEFAULT_TIME_UNIT = 1;

    /**
     * 接口api
     */
    private String api;
    /**
     * 限流次数
     */
    private int limit;

    /**
     * 限流时间窗口大小
     */
    private int unit = DEFAULT_TIME_UNIT;

    public ApiLimit() {
    }

    public ApiLimit(String api, int limit) {
        this(api, limit, DEFAULT_TIME_UNIT);
    }

    public ApiLimit(String api, int limit, int unit) {
        this.api = api;
        this.limit = limit;
        this.unit = unit;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
