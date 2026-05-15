package com.wallet.admin.controller.strategy;

import java.util.List;
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
import com.wallet.strategy.domain.StrategyInfo;
import com.wallet.strategy.service.IStrategyInfoService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 策略信息Controller
 * 
 * @author wallet
 * @date 2023-03-14
 */
@RestController
@RequestMapping("/strategy/strategyInfo")
public class StrategyInfoController extends BaseController
{
    @Autowired
    private IStrategyInfoService strategyInfoService;

    /**
     * 查询策略信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(StrategyInfo strategyInfo)
    {
        startPage();
        List<StrategyInfo> list = strategyInfoService.selectStrategyInfoList(strategyInfo);
        return getDataTable(list);
    }

    /**
     * 导出策略信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyInfo:export')")
    @Log(title = "策略信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StrategyInfo strategyInfo)
    {
        List<StrategyInfo> list = strategyInfoService.selectStrategyInfoList(strategyInfo);
        ExcelUtil<StrategyInfo> util = new ExcelUtil<StrategyInfo>(StrategyInfo.class);
        return util.exportExcel(list, "strategyInfo");
    }

    /**
     * 获取策略信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyInfo:query')")
    @GetMapping(value = "/{strategyId}")
    public AjaxResult getInfo(@PathVariable("strategyId") Long strategyId)
    {
        return AjaxResult.success(strategyInfoService.selectStrategyInfoById(strategyId));
    }

    /**
     * 新增策略信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyInfo:add')")
    @Log(title = "策略信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StrategyInfo strategyInfo)
    {
        return toAjax(strategyInfoService.insertStrategyInfo(strategyInfo));
    }


    /**
     * 修改策略状态
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyInfo:edit')")
    @Log(title = "修改策略状态", businessType = BusinessType.INSERT)
    @PostMapping("/updateStatus")
    public AjaxResult updateStatus(@RequestBody StrategyInfo strategyInfo)
    {
        return toAjax(strategyInfoService.updateStrategyStatus(strategyInfo));
    }



    /**
     * 修改策略信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyInfo:edit')")
    @Log(title = "策略信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StrategyInfo strategyInfo)
    {
        return toAjax(strategyInfoService.updateStrategyInfo(strategyInfo));
    }

    /**
     * 删除策略信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyInfo:remove')")
    @Log(title = "策略信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{strategyIds}")
    public AjaxResult remove(@PathVariable Long[] strategyIds)
    {
        return toAjax(strategyInfoService.deleteStrategyInfoByIds(strategyIds));
    }

    /**
     * 删除策略信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyInfo:remove')")
    @Log(title = "策略信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/updateDelete/{strategyId}")
    public AjaxResult updateDelete(@PathVariable Long strategyId)
    {
        return toAjax(strategyInfoService.updateDelete(strategyId));
    }

}
