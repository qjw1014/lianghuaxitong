package com.wallet.file.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * ipfs上传文件配置信息
 */
@Component
@ConfigurationProperties(prefix = "ipfs")
public class IpfsLink {
    /**
     * 服务器地址
     */
    private String ipfsUrl;

    /**
     * ip地址
     */
    private String ipfsHost;

    /**
     * 端口
     */
    private Integer ipfsPort;

    /**
     * 文件路径
     */
    private String ipfsMainPath;

    public String getIpfsUrl() {
        return ipfsUrl;
    }

    public void setIpfsUrl(String ipfsUrl) {
        this.ipfsUrl = ipfsUrl;
    }

    public String getIpfsHost() {
        return ipfsHost;
    }

    public void setIpfsHost(String ipfsHost) {
        this.ipfsHost = ipfsHost;
    }

    public Integer getIpfsPort() {
        return ipfsPort;
    }

    public void setIpfsPort(Integer ipfsPort) {
        this.ipfsPort = ipfsPort;
    }

    public String getIpfsMainPath() {
        return ipfsMainPath;
    }

    public void setIpfsMainPath(String ipfsMainPath) {
        this.ipfsMainPath = ipfsMainPath;
    }
}
