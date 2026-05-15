package com.wallet.file.domain;

import java.io.Serializable;

public class FileDTO implements Serializable
{
    /**
     * id
     */
    
    private Integer id;
    
    /**
     * 保存图片路径
     */
    private String filePath;
    
    /**
     * 保存图片碎片目录
     */
    private String shardPath;
    
    /**
     * 文件名
     */
    private String name;
    
    /**
     * 后缀
     */
    private String suffix;
    
    /**
     * 大小|字节B
     */
    private Long size;
    
    /**
     * 创建时间
     */
    private Long createdAt;
    
    /**
     * 修改时间
     */
    private Long updatedAt;
    
    /**
     * 已上传分片
     */
    private Long shardIndex;
    
    /**
     * 分片大小|B
     */
    private Long shardSize;
    
    /**
     * 分片总数
     */
    private Long shardTotal;
    
    /**
     * 文件标识
     */
    private String fileKey;
    
    public Integer getId()
    {
        return id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public String getFilePath()
    {
        return filePath;
    }
    
    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }
    
    public String getShardPath()
    {
        return shardPath;
    }
    
    public void setShardPath(String shardPath)
    {
        this.shardPath = shardPath;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getSuffix()
    {
        return suffix;
    }
    
    public void setSuffix(String suffix)
    {
        this.suffix = suffix;
    }
    
    public Long getSize()
    {
        return size;
    }
    
    public void setSize(Long size)
    {
        this.size = size;
    }
    
    public Long getCreatedAt()
    {
        return createdAt;
    }
    
    public void setCreatedAt(Long createdAt)
    {
        this.createdAt = createdAt;
    }
    
    public Long getUpdatedAt()
    {
        return updatedAt;
    }
    
    public void setUpdatedAt(Long updatedAt)
    {
        this.updatedAt = updatedAt;
    }
    
    public Long getShardIndex()
    {
        return shardIndex;
    }
    
    public void setShardIndex(Long shardIndex)
    {
        this.shardIndex = shardIndex;
    }
    
    public Long getShardSize()
    {
        return shardSize;
    }
    
    public void setShardSize(Long shardSize)
    {
        this.shardSize = shardSize;
    }
    
    public Long getShardTotal()
    {
        return shardTotal;
    }
    
    public void setShardTotal(Long shardTotal)
    {
        this.shardTotal = shardTotal;
    }
    
    public String getFileKey()
    {
        return fileKey;
    }
    
    public void setFileKey(String fileKey)
    {
        this.fileKey = fileKey;
    }
    
}