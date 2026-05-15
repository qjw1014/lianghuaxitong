package com.wallet.admin.controller.strategy;

import java.util.List;

import com.wallet.common.enums.Enum;
import com.wallet.common.enums.StrategyStatusEnum;
import com.wallet.strategy.domain.vo.MartinStrategyInfoVO;
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
import com.wallet.strategy.domain.MartinStrategyInfo;
import com.wallet.strategy.service.IMartinStrategyInfoService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 策略信息Controller
 * 
 * @author wallet
 * @date 2022-08-31
 */
@RestController
@RequestMapping("/strategy/martinStrategyInfo")
public class MartinStrategyInfoController extends BaseController
{
    @Autowired
    private IMartinStrategyInfoService martinStrategyInfoService;

    /**
     * 查询策略信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(MartinStrategyInfo martinStrategyInfo)
    {
        startPage();
        List<MartinStrategyInfo> list = martinStrategyInfoService.selectMartinStrategyInfoListOrderBy(martinStrategyInfo);
        return getDataTable(list);
    }

    /**
     * 查询策略信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyInfo:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(MartinStrategyInfo martinStrategyInfo)
    {
        martinStrategyInfo.setIsDelete(Enum.rule_validate.N.getCode());
        martinStrategyInfo.setStrategyStatus(StrategyStatusEnum.VALUE_3.getValue());
        List<MartinStrategyInfo> list = martinStrategyInfoService.selectMartinStrategyInfoList(martinStrategyInfo);
        return AjaxResult.success(list);
    }

    /**
     * 导出策略信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyInfo:export')")
    @Log(title = "策略信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MartinStrategyInfo martinStrategyInfo)
    {
        List<MartinStrategyInfo> list = martinStrategyInfoService.selectMartinStrategyInfoList(martinStrategyInfo);
        ExcelUtil<MartinStrategyInfo> util = new ExcelUtil<MartinStrategyInfo>(MartinStrategyInfo.class);
        return util.exportExcel(list, "martinStrategyInfo");
    }

    /**
     * 获取策略信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyInfo:query')")
    @GetMapping(value = "/{strategyId}")
    public AjaxResult getInfo(@PathVariable("strategyId") Long strategyId)
    {
        return AjaxResult.success(martinStrategyInfoService.selectMartinStrategyInfoById(strategyId));
    }

    /**
     * 新增策略信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyInfo:add')")
    @Log(title = "策略信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MartinStrategyInfoVO martinStrategyInfo)
    {
        martinStrategyInfo.setStrategyStatus(StrategyStatusEnum.VALUE_0.getValue());
        return toAjax(martinStrategyInfoService.insertMartinStrategyInfo(martinStrategyInfo));
    }

    /**
     * 修改策略信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyInfo:edit')")
    @Log(title = "策略信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MartinStrategyInfoVO martinStrategyInfo)
    {
        return toAjax(martinStrategyInfoService.updateMartinStrategyInfo(martinStrategyInfo));
    }

    /**
     * 删除策略信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyInfo:remove')")
    @Log(title = "策略信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{strategyIds}")
    public AjaxResult remove(@PathVariable Long[] strategyIds)
    {
        return toAjax(martinStrategyInfoService.deleteMartinStrategyInfoByIds(strategyIds));
    }

    /**
     * 删除策略信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyInfo:remove')")
    @Log(title = "策略信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/updateDelete/{strategyId}")
    public AjaxResult updateDelete(@PathVariable Long strategyId)
    {
        return toAjax(martinStrategyInfoService.updateDelete(strategyId));
    }
}
