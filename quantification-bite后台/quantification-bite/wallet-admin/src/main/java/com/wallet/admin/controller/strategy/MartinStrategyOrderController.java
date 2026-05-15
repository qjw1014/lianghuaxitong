package com.wallet.admin.controller.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.wallet.common.enums.Enum;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.StringUtils;
import com.wallet.strategy.domain.UserPlatform;
import com.wallet.strategy.domain.dto.StatisticsDto;
import com.wallet.strategy.domain.dto.UserPlatformDto;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import com.wallet.strategy.service.IMartinStrageyShareService;
import com.wallet.strategy.service.IUserPlatformService;
import com.wallet.system.service.ISysConfigService;
import io.swagger.annotations.ApiModelProperty;
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
import com.wallet.strategy.domain.MartinStrategyOrder;
import com.wallet.strategy.service.IMartinStrategyOrderService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 策略订单信息Controller
 * 
 * @author wallet
 * @date 2022-10-28
 */
@RestController
@RequestMapping("/strategy/martinStrategyOrder")
public class MartinStrategyOrderController extends BaseController
{
    @Autowired
    private IMartinStrategyOrderService martinStrategyOrderService;
    @Autowired
    private IMartinStrageyShareService martinStrageyShareService;
    @Autowired
    private ISysConfigService sysConfigService;
    @Autowired
    private IUserPlatformService userPlatformService;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询策略订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyOrder:list')")
    @GetMapping("/list")
    public TableDataInfo list(MartinStrategyOrder martinStrategyOrder)
    {
        startPage();
        List<MartinStrategyOrder> list = martinStrategyOrderService.selectMartinStrategyOrderList(martinStrategyOrder);
        return getDataTable(list);
    }

    /**
     * 导出策略订单信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyOrder:export')")
    @Log(title = "策略订单信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MartinStrategyOrder martinStrategyOrder)
    {
        List<MartinStrategyOrder> list = martinStrategyOrderService.selectMartinStrategyOrderList(martinStrategyOrder);
        ExcelUtil<MartinStrategyOrder> util = new ExcelUtil<MartinStrategyOrder>(MartinStrategyOrder.class);
        return util.exportExcel(list, "martinStrategyOrder");
    }

    /**
     * 获取策略订单信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyOrder:query')")
    @GetMapping(value = "/{serialId}")
    public AjaxResult getInfo(@PathVariable("serialId") Long serialId)
    {
        return AjaxResult.success(martinStrategyOrderService.selectMartinStrategyOrderById(serialId));
    }

    /**
     * 统计所有订单总收益（总收益，总分润，未分润）
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyOrder:query')")
    @GetMapping(value = "/getCountOrder")
    public AjaxResult getCountOrder()
    {
        StatisticsDto dto = new StatisticsDto();
        //未分润收益
        BigDecimal totalShare = BigDecimal.ZERO;
        //需要分润收益
        BigDecimal notShareIncome = BigDecimal.ZERO;
        //所有账号usdt资产余额
        BigDecimal totalUsdt =BigDecimal.ZERO;
        UserPlatform userPlatform = new UserPlatform();
        userPlatform.setIsDelete(Enum.rule_validate.N.getCode());
        List<UserPlatform> list = userPlatformService.selectUserPlatformList(userPlatform);
        if(list!=null&& list.size()>0){
            for(UserPlatform platform:list){
                //未分润收益
                String currentIncome = martinStrategyOrderService.findOrderTotalIncome(platform.getId(),Enum.rule_validate.N.getCode());//未分润收益
                BigDecimal shareIncome = new BigDecimal(currentIncome).multiply(platform.getShareRate());
                if(shareIncome.compareTo(BigDecimal.ZERO)==1){
                    notShareIncome = notShareIncome.add(shareIncome);
                }
                totalShare = totalShare.add(new BigDecimal(currentIncome));

                //读取redis,获取当前资金
                Object object = redisUtil.hget(StrategyRedisConfig.balances,String.valueOf(platform.getId()));
                if(ObjectUtil.isNotNull(object)){
                    JSONObject json = JSONObject.parseObject(object.toString());
                    BigDecimal currentUsdt =json.containsKey("current_usdt")?json.getBigDecimal("current_usdt"): BigDecimal.ZERO;
                    totalUsdt = totalUsdt.add(currentUsdt);
                }
            }
        }
        //统计账号总收益
        String totalIncome = martinStrategyOrderService.findOrderTotalIncome(null,null);//总收益
        dto.setTotalIncome(new BigDecimal(totalIncome).setScale(2, BigDecimal.ROUND_DOWN));
        //已分润收益
        BigDecimal income = martinStrageyShareService.sumShareIncome();
        dto.setCurrentIncome(income.setScale(2, BigDecimal.ROUND_DOWN));
        dto.setShareIncome(notShareIncome);
        dto.setCurrentUsdt(totalUsdt);
        return AjaxResult.success(dto);
    }

    /**
     * 新增策略订单信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyOrder:add')")
    @Log(title = "策略订单信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MartinStrategyOrder martinStrategyOrder)
    {
        return toAjax(martinStrategyOrderService.insertMartinStrategyOrder(martinStrategyOrder));
    }

    /**
     * 修改策略订单信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyOrder:edit')")
    @Log(title = "策略订单信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MartinStrategyOrder martinStrategyOrder)
    {
        return toAjax(martinStrategyOrderService.updateMartinStrategyOrder(martinStrategyOrder));
    }

    /**
     * 删除策略订单信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyOrder:remove')")
    @Log(title = "策略订单信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{serialIds}")
    public AjaxResult remove(@PathVariable Long[] serialIds)
    {
        return toAjax(martinStrategyOrderService.deleteMartinStrategyOrderByIds(serialIds));
    }
}
