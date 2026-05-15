package com.wallet.file.domain;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ftp")
public class FtpLink {
	private String ftpUrl;
	private String ftpHost;
	private Integer ftpPort;
	private String ftpUserName;
	private String ftpPassword;
	private String ftpMainPath;
	public String getFtpUrl() {
		return ftpUrl;
	}
	public void setFtpUrl(String ftpUrl) {
		this.ftpUrl = ftpUrl;
	}
	public String getFtpHost() {
		return ftpHost;
	}
	public void setFtpHost(String ftpHost) {
		this.ftpHost = ftpHost;
	}
	
	public Integer getFtpPort() {
		return ftpPort;
	}
	public void setFtpPort(Integer ftpPort) {
		this.ftpPort = ftpPort;
	}
	public String getFtpUserName() {
		return ftpUserName;
	}
	public void setFtpUserName(String ftpUserName) {
		this.ftpUserName = ftpUserName;
	}
	public String getFtpPassword() {
		return ftpPassword;
	}
	public void setFtpPassword(String ftpPassword) {
		this.ftpPassword = ftpPassword;
	}
	public String getFtpMainPath() {
		return ftpMainPath;
	}
	public void setFtpMainPath(String ftpMainPath) {
		this.ftpMainPath = ftpMainPath;
	}
	public FtpLink(String ftpUrl, String ftpHost, Integer ftpPort, String ftpUserName, String ftpPassword
			) {
		super();
		this.ftpUrl = ftpUrl;
		this.ftpHost = ftpHost;
		this.ftpPort = ftpPort;
		this.ftpUserName = ftpUserName;
		this.ftpPassword = ftpPassword;
	}
	public FtpLink() {
		super();
	}
}
