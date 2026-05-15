package com.wallet.file.service.impl;

import com.wallet.common.utils.IPFSUtis;
import com.wallet.file.domain.IpfsLink;
import com.wallet.file.service.IIpfsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * ipfs上传文件
 */
@Service
public class IpfsServiceImpl implements IIpfsService {

    private static final Logger logger = LoggerFactory.getLogger(IpfsServiceImpl.class);

    /**
     * 上传ipfs
     * @param file
     * @param ipfsLink
     * @return
     */
    @Override
    public String ipfsUploadFile(MultipartFile file, IpfsLink ipfsLink) {
        try {
            //连接 /ip4/43.129.71.63/tcp/5001
            String url = "/ip4/"+ipfsLink.getIpfsHost()+"/tcp/"+ipfsLink.getIpfsPort();
            IPFSUtis ipfsUtis = new IPFSUtis(url);
            String hash = ipfsUtis.upload(file);
            String imageUrl = ipfsLink.getIpfsUrl()+"/"+ipfsLink.getIpfsMainPath()+"/"+hash;
            return imageUrl;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("上传ipfs文件异常",e);
        }

        return null;
    }

    @Override
    public String ipfsUploadFileIpuut(InputStream inputStream, IpfsLink ipfsLink,String name) {
        try {
            //连接 /ip4/43.129.71.63/tcp/5001
            String url = "/ip4/"+ipfsLink.getIpfsHost()+"/tcp/"+ipfsLink.getIpfsPort();
            IPFSUtis ipfsUtis = new IPFSUtis(url);
            String hash = ipfsUtis.uploadInputStream(inputStream,name);
            String imageUrl = ipfsLink.getIpfsUrl()+"/"+ipfsLink.getIpfsMainPath()+"/"+hash;
            return imageUrl;
        }catch (Exception e){
            e.printStackTrace();
            logger.error("上传ipfs文件异常",e);
        }
        return null;
    }
}
