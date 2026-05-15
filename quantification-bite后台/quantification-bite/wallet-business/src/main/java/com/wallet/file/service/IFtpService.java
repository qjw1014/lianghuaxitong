package com.wallet.file.service;

import org.springframework.web.multipart.MultipartFile;

import com.wallet.file.domain.FtpLink;

import java.io.InputStream;

public interface IFtpService {

	public String uploadFile(MultipartFile file,String catalogue, FtpLink ftpLink);

	public boolean deleteFile(FtpLink ftpLink, String pathname);

	public String uploadStream(String fileName, InputStream inputStream , String catalogue, FtpLink ftpLink);
}
