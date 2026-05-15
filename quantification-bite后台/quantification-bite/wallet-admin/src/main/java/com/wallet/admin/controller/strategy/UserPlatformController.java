package com.wallet.admin.controller.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.wallet.common.enums.Enum;
import com.wallet.common.exception.BaseException;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.JsonUtils;
import com.wallet.common.utils.MessageUtils;
import com.wallet.common.utils.StringUtils;
import com.wallet.strategy.domain.MartinStrategyApi;
import com.wallet.strategy.domain.StrategyType;
import com.wallet.strategy.domain.dto.MartinStrategyApiDto;
import com.wallet.strategy.domain.dto.UserPlatformDto;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import com.wallet.strategy.service.IMartinStrategyApiService;
import com.wallet.strategy.service.IMartinStrategyOrderService;
import com.wallet.strategy.service.IStrategyTypeService;
import com.wallet.system.service.ISysConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wallet.common.annotation.Log;
import com.wallet.common.core.controller.BaseController;
import com.wallet.common.core.domain.AjaxResult;
import com.wallet.common.enums.BusinessType;
import com.wallet.strategy.domain.UserPlatform;
import com.wallet.strategy.service.IUserPlatformService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

import javax.json.JsonObject;

/**
 * 用户平台私钥信息Controller
 * 
 * @author wallet
 * @date 2022-09-01
 */
@RestController
@RequestMapping("/strategy/userPlatform")
public class UserPlatformController extends BaseController
{
    @Autowired
    private IUserPlatformService userPlatformService;
    @Autowired
    private IMartinStrategyApiService martinStrategyApiService;
    @Autowired
    private IStrategyTypeService strategyTypeService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private IMartinStrategyOrderService martinStrategyOrderService;
    @Autowired
    private ISysConfigService sysConfigService;

    /**
     * 查询用户平台私钥信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:userPlatform:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserPlatform userPlatform)
    {
        List<UserPlatformDto> result = new ArrayList<>();
        startPage();
        userPlatform.setIsDelete(Enum.rule_validate.N.getCode());
        List<UserPlatform> list = userPlatformService.selectUserPlatformList(userPlatform);
        if(list!=null && list.size()>0){
            for(UserPlatform platform:list){
                UserPlatformDto dto = new UserPlatformDto();
                BeanUtils.copyProperties(platform,dto);
                dto.setAppsecret(dto.getAppsecret().substring(0,3)+"..."+dto.getAppsecret().substring(dto.getAppsecret().length()-3));
                if(StringUtils.isNotBlank(dto.getTradePassword())){
                    dto.setTradePassword(dto.getTradePassword().substring(0,3)+"..."+dto.getTradePassword().substring(dto.getTradePassword().length()-3));
                }
                List<MartinStrategyApiDto> strategyApiList = new ArrayList<>();
                //查询api信息
                MartinStrategyApi query = new MartinStrategyApi();
                query.setIsDelete(Enum.rule_validate.N.getCode());
                query.setAccountId(dto.getId());
                List<MartinStrategyApi> listApi = martinStrategyApiService.selectMartinStrategyApiList(query);
                if(listApi!=null && listApi.size()>0){
                    for(MartinStrategyApi api:listApi){
                        MartinStrategyApiDto apiDto = new MartinStrategyApiDto();
                        BeanUtils.copyProperties(api,apiDto);
                        StrategyType strategyType = strategyTypeService.selectStrategyTypeById(api.getStrategyType());
                        if(strategyType!=null){
                            apiDto.setStrategyTypeName(strategyType.getName());
                        }
                        strategyApiList.add(apiDto);
                    }
                }
                dto.setStrategyApiList(strategyApiList);
                //读取redis,获取当前资金
                Object object = redisUtil.hget(StrategyRedisConfig.balances,String.valueOf(dto.getId()));
                if(ObjectUtil.isNotNull(object)){
                    JSONObject json = JSONObject.parseObject(object.toString());
                    dto.setCurrentUsdt(json.containsKey("current_usdt")?json.getBigDecimal("current_usdt"): BigDecimal.ZERO);
                }
                //统计账号总收益
                String totalIncome = martinStrategyOrderService.findOrderTotalIncome(platform.getId(),null);//总收益
                //收益
                String currentIncome = martinStrategyOrderService.findOrderTotalIncome(platform.getId(),Enum.rule_validate.N.getCode());//未分润收益
                //要分润收益
                BigDecimal shareIncome = new BigDecimal(currentIncome).multiply(platform.getShareRate());
                if(shareIncome.compareTo(BigDecimal.ZERO)==1){
                    dto.setShareIncome(shareIncome);
                }else{
                    dto.setShareIncome(BigDecimal.ZERO);
                }
                dto.setTotalIncome(new BigDecimal(totalIncome));
                dto.setCurrentIncome(new BigDecimal(currentIncome));
                result.add(dto);
            }
        }
        return getDataTable(result,new PageInfo(list).getTotal());
    }


    /**
     * 查询用户平台私钥信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:userPlatform:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(UserPlatform userPlatform)
    {
        userPlatform.setIsDelete(Enum.rule_validate.N.getCode());
        List<UserPlatform> list = userPlatformService.selectUserPlatformList(userPlatform);
        if(list!=null&&list.size()>0){
            for(UserPlatform platform:list){
                platform.setAppsecret(null);
                platform.setTradePassword(null);
            }
        }
        return AjaxResult.success(list);
    }
    /**
     * 导出用户平台私钥信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:userPlatform:export')")
    @Log(title = "用户平台私钥信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(UserPlatform userPlatform)
    {
        List<UserPlatform> list = userPlatformService.selectUserPlatformList(userPlatform);
        ExcelUtil<UserPlatform> util = new ExcelUtil<UserPlatform>(UserPlatform.class);
        return util.exportExcel(list, "userPlatform");
    }

    /**
     * 获取用户平台私钥信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:userPlatform:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        UserPlatformDto dto = new UserPlatformDto();
        UserPlatform userPlatform = userPlatformService.selectUserPlatformById(id);
        if(userPlatform!=null){
            BeanUtils.copyProperties(userPlatform,dto);
            dto.setAppsecret(dto.getAppsecret().substring(0,3)+"..."+dto.getAppsecret().substring(dto.getAppsecret().length()-3));
            if(StringUtils.isNotBlank(dto.getTradePassword())){
                dto.setTradePassword(dto.getTradePassword().substring(0,3)+"..."+dto.getTradePassword().substring(dto.getTradePassword().length()-3));
            }
        }
        return AjaxResult.success(dto);
    }

    /**
     * 新增用户平台私钥信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:userPlatform:add')")
    @Log(title = "用户平台私钥信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserPlatform userPlatform)
    {
        userPlatform.setCreateDate(new Date());
        userPlatform.setUpdateDate(new Date());
        return toAjax(userPlatformService.insertUserPlatform(userPlatform));
    }

    /**
     * 修改用户平台私钥信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:userPlatform:edit')")
    @Log(title = "用户平台私钥信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserPlatform userPlatform)
    {
        return toAjax(userPlatformService.updateUserPlatform(userPlatform));
    }

    /**
     * 确定已分润
     */
    @PreAuthorize("@ss.hasPermi('strategy:userPlatform:edit')")
    @Log(title = "用户平台私钥信息", businessType = BusinessType.UPDATE)
    @PutMapping("/share" )
    public AjaxResult share(@RequestBody UserPlatform userPlatform)
    {

        UserPlatform platform = userPlatformService.selectUserPlatformById(userPlatform.getId());
        if(platform==null){
            throw new BaseException(MessageUtils.message("info.does.not.exist"));
        }
        return toAjax(userPlatformService.deterShare(userPlatform.getId(),platform.getShareRate()));
    }

    /**
     * 删除用户平台私钥信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:userPlatform:remove')")
    @Log(title = "用户平台私钥信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(userPlatformService.deleteUserPlatformByIds(ids));
    }


    /**
     * 修改用户平台私钥信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:userPlatform:remove')")
    @Log(title = "用户平台私钥信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/updateDelete/{id}")
    public AjaxResult updateDelete(@PathVariable Long id)
    {
        return toAjax(userPlatformService.updateUserPlatformByDelete(id));
    }
}
