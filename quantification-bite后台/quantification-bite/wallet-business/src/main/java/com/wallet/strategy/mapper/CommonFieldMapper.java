package com.wallet.strategy.mapper;

import java.util.List;
import com.wallet.strategy.domain.CommonField;
import tk.mybatis.mapper.common.Mapper;
/**
 * 策略公共字段描述Mapper接口
 * 
 * @author wallet
 * @date 2023-03-14
 */
public interface CommonFieldMapper extends Mapper<CommonField>
{
    
    /**
     * 批量删除策略公共字段描述
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommonFieldByIds(Long[] ids);
}
