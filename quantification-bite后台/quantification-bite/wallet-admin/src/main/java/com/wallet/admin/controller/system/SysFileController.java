package com.wallet.admin.controller.system;

import com.wallet.file.domain.IpfsLink;
import com.wallet.file.service.IIpfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.wallet.common.core.domain.AjaxResult;
import com.wallet.file.domain.FtpLink;
import com.wallet.file.service.IFtpService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.io.File;


@RestController
@RequestMapping("/system/ftpfile")
public class SysFileController
{

    @Autowired
    private FtpLink ftpLink;
    
    @Autowired
    private IFtpService ftpService;

    @Autowired
    private IpfsLink ipfsLink;

    @Autowired
    private IIpfsService ipfsService;
    
    /***
     * 上传目录
     * 
     * @return content 代表存放的文件夹名称
     */
    @ApiOperation(value = "图片上传", notes = "图片上传")
    @PostMapping("/uploadFile")
    public AjaxResult uploadImg(@RequestParam("file") MultipartFile file, String path)
    {
        String rePath = ftpService.uploadFile(file, path, ftpLink);
        String url = ftpLink.getFtpUrl()+ File.separator+ ftpLink.getFtpMainPath()+File.separator+ rePath;
        JSONObject json = new JSONObject();
        json.put("url", url);
        json.put("path", rePath);
        return AjaxResult.success(json);
    }

    /***
     * ipfs上传图片
     *
     * @return content 代表存放的文件夹名称
     */
    @ApiOperation(value = "ipfs上传图片", notes = "ipfs上传图片")
    @PostMapping("/ipfsUploadFile")
    public AjaxResult uploadImg(@RequestParam("file") MultipartFile file) {
        String url = ipfsService.ipfsUploadFile(file, ipfsLink);
        JSONObject json = new JSONObject();
        json.put("url", url);
        return AjaxResult.success(json);
    }
}
