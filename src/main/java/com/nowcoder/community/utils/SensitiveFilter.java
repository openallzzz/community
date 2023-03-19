package com.nowcoder.community.utils;

import org.apache.commons.lang3.CharUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Component
public class SensitiveFilter {

    private static final Logger logger = LoggerFactory.getLogger(SensitiveFilter.class);

    // 替换符：将敏感词替换为该字符串
    private static final String REPLACEMENT = "###";

    private static final TrieNode rootNode = new TrieNode();

    @PostConstruct
    public void init() {
        try (InputStream is = this.getClass().getClassLoader().getResourceAsStream("sensitive-words.txt");) {
            assert is != null;
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(is));) {
                String keyword;
                while ((keyword = reader.readLine()) != null) {
                    // 添加到前缀树
                    this.addKeyword(keyword);
                }
            }
        } catch (IOException e) {
            logger.error("加载敏感词失败：" + e.getMessage());
        }
    }

    // 将一个敏感词添加到前缀树中
    private void addKeyword(String keyword) {
        TrieNode p = rootNode;
        for (int i = 0; i < keyword.length(); i++) {
            char u = keyword.charAt(i);
            TrieNode subNode = p.getSubNode(u);
            if (subNode == null) {
                subNode = new TrieNode();
                p.addSubNode(u, subNode);
            }
            p = subNode;
        }
        p.setKeyWordEnd(true);
    }

    /**
     * 过滤敏感词
     *
     * @param text 待过滤的文本
     * @return 过滤后的文本
     */
    public String filter(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }

        TrieNode p = rootNode;
        int begin = 0, position = 0;

        StringBuilder sb = new StringBuilder();
        while (begin < text.length()) {
            if (position < text.length()) {
                char u = text.charAt(position);
                if (isSymbol(u)) {
                    if (p == rootNode) {
                        sb.append(u);
                        begin++;
                    }
                    position++;
                    continue;
                }

                // 检查下级节点
                p = p.getSubNode(u);
                if (p == null) {
                    sb.append(text.charAt(begin));
                    position = ++begin;
                    p = rootNode;
                } else if (p.isKeyWordEnd()) {
                    sb.append(REPLACEMENT);
                    begin = ++position;
                    p = rootNode;
                } else {
                    position++;
                }
            } else {
                sb.append(text.charAt(begin));
                position = ++begin;
                p = rootNode;
            }
        }
        return sb.toString();
    }

    private static boolean isSymbol(Character c) {
        // 0x2E80 ~ 0x9FFF 是东亚文字范围
        return !CharUtils.isAsciiAlphanumeric(c) && (c < 0x2E80 || c > 0x9FFF);
    }

    // 前缀树
    private static class TrieNode {

        // 敏感词结束标识
        private boolean isKeyWordEnd = false;

        // 子节点
        private Map<Character, TrieNode> subNodes = new HashMap<>();

        public boolean isKeyWordEnd() {
            return isKeyWordEnd;
        }

        public void setKeyWordEnd(boolean keyWordEnd) {
            isKeyWordEnd = keyWordEnd;
        }

        // 添加子节点
        public void addSubNode(Character c, TrieNode node) {
            subNodes.put(c, node);
        }

        // 获取子节点
        public TrieNode getSubNode(Character c) {
            return subNodes.get(c);
        }
    }

}
