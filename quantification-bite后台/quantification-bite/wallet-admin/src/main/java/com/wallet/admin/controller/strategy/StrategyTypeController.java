package com.wallet.admin.controller.strategy;

import java.util.List;

import com.wallet.common.enums.Enum;
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
import com.wallet.strategy.domain.StrategyType;
import com.wallet.strategy.service.IStrategyTypeService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 策略类型Controller
 * 
 * @author wallet
 * @date 2022-09-01
 */
@RestController
@RequestMapping("/strategy/strategyType")
public class StrategyTypeController extends BaseController
{
    @Autowired
    private IStrategyTypeService strategyTypeService;

    /**
     * 查询策略类型列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyType:list')")
    @GetMapping("/list")
    public TableDataInfo list(StrategyType strategyType)
    {
        startPage();
        List<StrategyType> list = strategyTypeService.selectStrategyTypeList(strategyType);
        return getDataTable(list);
    }

    /**
     * 查询策略类型列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyType:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(StrategyType strategyType)
    {
        strategyType.setIsEnabled(Enum.rule_validate.Y.getCode());
        List<StrategyType> list = strategyTypeService.selectStrategyTypeList(strategyType);
        return AjaxResult.success(list);
    }

    /**
     * 导出策略类型列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyType:export')")
    @Log(title = "策略类型", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StrategyType strategyType)
    {
        List<StrategyType> list = strategyTypeService.selectStrategyTypeList(strategyType);
        ExcelUtil<StrategyType> util = new ExcelUtil<StrategyType>(StrategyType.class);
        return util.exportExcel(list, "strategyType");
    }

    /**
     * 获取策略类型详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyType:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(strategyTypeService.selectStrategyTypeById(id));
    }

    /**
     * 新增策略类型
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyType:add')")
    @Log(title = "策略类型", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StrategyType strategyType)
    {
        return toAjax(strategyTypeService.insertStrategyType(strategyType));
    }

    /**
     * 修改策略类型
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyType:edit')")
    @Log(title = "策略类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StrategyType strategyType)
    {
        return toAjax(strategyTypeService.updateStrategyType(strategyType));
    }

    /**
     * 删除策略类型
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyType:remove')")
    @Log(title = "策略类型", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(strategyTypeService.deleteStrategyTypeByIds(ids));
    }
}
