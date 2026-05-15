package com.wallet.admin.framework.config;

import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * 修复 SpringBootVFS 在扫描 JAR 包时的 StringIndexOutOfBoundsException 问题
 */
public class FixedSpringBootVFS extends SpringBootVFS {

    @Override
    public List<String> list(URL url, String path) throws IOException {
        try {
            return super.list(url, path);
        } catch (StringIndexOutOfBoundsException e) {
            // 忽略这个已知的 bug，返回空列表
            return java.util.Collections.emptyList();
        }
    }
}
