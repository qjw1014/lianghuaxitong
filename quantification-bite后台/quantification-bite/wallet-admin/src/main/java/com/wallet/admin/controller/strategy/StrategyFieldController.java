package com.wallet.admin.controller.strategy;

import java.util.List;

import com.wallet.strategy.domain.vo.StrategyFieldAddVo;
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
import com.wallet.strategy.domain.StrategyField;
import com.wallet.strategy.service.IStrategyFieldService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 策略字段关系Controller
 * 
 * @author wallet
 * @date 2023-03-14
 */
@RestController
@RequestMapping("/strategy/strategyField")
public class StrategyFieldController extends BaseController
{
    @Autowired
    private IStrategyFieldService strategyFieldService;

    /**
     * 查询策略字段关系列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyField:list')")
    @GetMapping("/list")
    public TableDataInfo list(StrategyField strategyField)
    {
        startPage();
        List<StrategyField> list = strategyFieldService.selectStrategyFieldList(strategyField);
        return getDataTable(list);
    }

    /**
     * 导出策略字段关系列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyField:export')")
    @Log(title = "策略字段关系", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StrategyField strategyField)
    {
        List<StrategyField> list = strategyFieldService.selectStrategyFieldList(strategyField);
        ExcelUtil<StrategyField> util = new ExcelUtil<StrategyField>(StrategyField.class);
        return util.exportExcel(list, "strategyField");
    }

    /**
     * 获取策略字段关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyField:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(strategyFieldService.selectStrategyFieldById(id));
    }

    /**
     * 新增策略字段关系
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyField:add')")
    @Log(title = "策略字段关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StrategyField strategyField)
    {
        return toAjax(strategyFieldService.insertStrategyField(strategyField));
    }

    /**
     * 批量新增
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyField:add')")
    @Log(title = "批量新增", businessType = BusinessType.INSERT)
    @PostMapping("/batchAdd")
    public AjaxResult batchAdd(@RequestBody StrategyFieldAddVo vo)
    {
        return toAjax(strategyFieldService.insertStrategyFieldBatch(vo));
    }

    /**
     * 修改策略字段关系
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyField:edit')")
    @Log(title = "策略字段关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StrategyField strategyField)
    {
        return toAjax(strategyFieldService.updateStrategyField(strategyField));
    }

    /**
     * 删除策略字段关系
     */
    @PreAuthorize("@ss.hasPermi('strategy:strategyField:remove')")
    @Log(title = "策略字段关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(strategyFieldService.deleteStrategyFieldByIds(ids));
    }
}
