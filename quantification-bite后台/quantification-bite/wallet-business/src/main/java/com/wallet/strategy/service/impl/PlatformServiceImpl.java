package com.wallet.strategy.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.PlatformMapper;
import com.wallet.strategy.domain.Platform;
import com.wallet.strategy.service.IPlatformService;

/**
 * 交易平台Service业务层处理
 * 
 * @author wallet
 * @date 2022-09-01
 */
@Service
public class PlatformServiceImpl implements IPlatformService 
{
    @Autowired
    private PlatformMapper platformMapper;

    /**
     * 查询交易平台
     * 
     * @param id 交易平台ID
     * @return 交易平台
     */
    @Override
    public Platform selectPlatformById(Long id)
    {
        return platformMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询交易平台列表
     * 
     * @param platform 交易平台
     * @return 交易平台
     */
    @Override
    public List<Platform> selectPlatformList(Platform platform)
    {
        return platformMapper.select(platform);
    }
	 @Override
    public List<Platform> findSelect(Platform platform)
    {
        return platformMapper.select(platform);
    }
    /**
     * 新增交易平台
     * 
     * @param platform 交易平台
     * @return 结果
     */
    @Override
    public int insertPlatform(Platform platform)
    {
		 return platformMapper.insertSelective(platform);
    }

    /**
     * 修改交易平台
     * 
     * @param platform 交易平台
     * @return 结果
     */
    @Override
    public int updatePlatform(Platform platform)
    {

        return platformMapper.updateByPrimaryKeySelective(platform);
    }

    /**
     * 批量删除交易平台
     * 
     * @param ids 需要删除的交易平台ID
     * @return 结果
     */
    @Override
    public int deletePlatformByIds(Long[] ids)
    {
        return platformMapper.deletePlatformByIds(ids);
    }

    /**
     * 删除交易平台信息
     * 
     * @param id 交易平台ID
     * @return 结果
     */
    @Override
    public int deletePlatformById(Long id)
    {
		return platformMapper.deleteByPrimaryKey(id);
        
    }
}
