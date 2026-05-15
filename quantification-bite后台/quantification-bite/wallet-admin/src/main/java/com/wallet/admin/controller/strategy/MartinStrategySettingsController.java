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
import com.wallet.strategy.domain.MartinStrategySettings;
import com.wallet.strategy.service.IMartinStrategySettingsService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 策略比例设置Controller
 * 
 * @author wallet
 * @date 2022-08-31
 */
@RestController
@RequestMapping("/strategy/martinStrategySettings")
public class MartinStrategySettingsController extends BaseController
{
    @Autowired
    private IMartinStrategySettingsService martinStrategySettingsService;

    /**
     * 查询策略比例设置列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategySettings:list')")
    @GetMapping("/list")
    public TableDataInfo list(MartinStrategySettings martinStrategySettings)
    {
        startPage();
        List<MartinStrategySettings> list = martinStrategySettingsService.selectMartinStrategySettingsList(martinStrategySettings);
        return getDataTable(list);
    }

    /**
     * 导出策略比例设置列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategySettings:export')")
    @Log(title = "策略比例设置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MartinStrategySettings martinStrategySettings)
    {
        List<MartinStrategySettings> list = martinStrategySettingsService.selectMartinStrategySettingsList(martinStrategySettings);
        ExcelUtil<MartinStrategySettings> util = new ExcelUtil<MartinStrategySettings>(MartinStrategySettings.class);
        return util.exportExcel(list, "martinStrategySettings");
    }

    /**
     * 获取策略比例设置详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategySettings:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(martinStrategySettingsService.selectMartinStrategySettingsById(id));
    }

    /**
     * 新增策略比例设置
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategySettings:add')")
    @Log(title = "策略比例设置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MartinStrategySettings martinStrategySettings)
    {
        return toAjax(martinStrategySettingsService.insertMartinStrategySettings(martinStrategySettings));
    }

    /**
     * 修改策略比例设置
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategySettings:edit')")
    @Log(title = "策略比例设置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MartinStrategySettings martinStrategySettings)
    {
        return toAjax(martinStrategySettingsService.updateMartinStrategySettings(martinStrategySettings));
    }

    /**
     * 删除策略比例设置
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategySettings:remove')")
    @Log(title = "策略比例设置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(martinStrategySettingsService.deleteMartinStrategySettingsByIds(ids));
    }
}
