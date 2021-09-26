package com.yzcoding.ratelimiter.rule.impl;

import com.yzcoding.ratelimiter.bean.ApiLimit;
import com.yzcoding.ratelimiter.bean.RuleConfig;
import com.yzcoding.ratelimiter.rule.RateLimitRule;

/**
 * 基于Trie树实现的查询api限流配置信息类
 *
 * @author 郑艺
 * @date 2021/6/10 10:46
 */
public class TireRateLimitRule implements RateLimitRule {

    public TireRateLimitRule(RuleConfig ruleConfig){
        // 加载url信息生成前缀树
    }

    /**
     * 查询api限流配置信息
     *
     * @param appId 应用id
     * @param api 接口api
     * @return api限流配置信息
     */
    public ApiLimit getLimit(String appId, String api){
        //查询前缀树
        return null;
    }
}

/**
 * 前缀树
 */
class Trie {
    private Trie[] children;
    private boolean isEnd;

    public Trie() {
        children = new Trie[26];
        isEnd = false;
    }

    /**
     * 插入单词
     *
     * @param word 单词
     */
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    /**
     * 进行单词查询
     *
     * @param word 单词
     * @return 是否查询到
     */
    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node != null && node.isEnd;
    }

    /**
     * 进行前缀查询
     *
     * @param prefix 前缀字符串
     * @return 是否查询到
     */
    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    /**
     *
     * @param prefix 查询
     * @return 树节点
     */
    private Trie searchPrefix(String prefix) {
        Trie node = this;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
        }
        return node;
    }
}