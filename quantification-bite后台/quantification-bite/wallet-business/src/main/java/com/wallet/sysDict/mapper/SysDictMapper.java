package com.wallet.sysDict.mapper;

import com.wallet.sysDict.domain.SysDict;
import tk.mybatis.mapper.common.Mapper;
/**
 * 参数配置Mapper接口
 * 
 * @author wallet
 * @date 2022-01-06
 */
public interface SysDictMapper extends Mapper<SysDict>
{
    
    /**
     * 批量删除参数配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysDictByIds(Long[] ids);
}
