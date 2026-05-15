package com.wallet.strategy.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.CoinInfoMapper;
import com.wallet.strategy.domain.CoinInfo;
import com.wallet.strategy.service.ICoinInfoService;

/**
 * 币种Service业务层处理
 * 
 * @author wallet
 * @date 2022-09-01
 */
@Service
public class CoinInfoServiceImpl implements ICoinInfoService 
{
    @Autowired
    private CoinInfoMapper coinInfoMapper;

    /**
     * 查询币种
     * 
     * @param id 币种ID
     * @return 币种
     */
    @Override
    public CoinInfo selectCoinInfoById(Long id)
    {
        return coinInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询币种列表
     * 
     * @param coinInfo 币种
     * @return 币种
     */
    @Override
    public List<CoinInfo> selectCoinInfoList(CoinInfo coinInfo)
    {
        return coinInfoMapper.select(coinInfo);
    }
	 @Override
    public List<CoinInfo> findSelect(CoinInfo coinInfo)
    {
        return coinInfoMapper.select(coinInfo);
    }
    /**
     * 新增币种
     * 
     * @param coinInfo 币种
     * @return 结果
     */
    @Override
    public int insertCoinInfo(CoinInfo coinInfo)
    {
		 return coinInfoMapper.insertSelective(coinInfo);
    }

    /**
     * 修改币种
     * 
     * @param coinInfo 币种
     * @return 结果
     */
    @Override
    public int updateCoinInfo(CoinInfo coinInfo)
    {

        return coinInfoMapper.updateByPrimaryKeySelective(coinInfo);
    }

    /**
     * 批量删除币种
     * 
     * @param ids 需要删除的币种ID
     * @return 结果
     */
    @Override
    public int deleteCoinInfoByIds(Long[] ids)
    {
        return coinInfoMapper.deleteCoinInfoByIds(ids);
    }

    /**
     * 删除币种信息
     * 
     * @param id 币种ID
     * @return 结果
     */
    @Override
    public int deleteCoinInfoById(Long id)
    {
		return coinInfoMapper.deleteByPrimaryKey(id);
        
    }
}
