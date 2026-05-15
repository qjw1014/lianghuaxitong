package com.wallet.file.service;

import com.wallet.file.domain.FtpLink;
import com.wallet.file.domain.IpfsLink;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface IIpfsService {

	public String ipfsUploadFile(MultipartFile file, IpfsLink ipfsLink);


	public String ipfsUploadFileIpuut(InputStream inputStream, IpfsLink ipfsLink,String name);

}
