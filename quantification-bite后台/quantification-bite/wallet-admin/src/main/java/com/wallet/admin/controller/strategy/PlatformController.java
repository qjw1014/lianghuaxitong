package com.wallet.admin.controller.strategy;

import java.util.Date;
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
import com.wallet.strategy.domain.Platform;
import com.wallet.strategy.service.IPlatformService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 交易平台Controller
 * 
 * @author wallet
 * @date 2022-09-01
 */
@RestController
@RequestMapping("/strategy/platform")
public class PlatformController extends BaseController
{
    @Autowired
    private IPlatformService platformService;

    /**
     * 查询交易平台列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:platform:list')")
    @GetMapping("/list")
    public TableDataInfo list(Platform platform)
    {
        startPage();
        List<Platform> list = platformService.selectPlatformList(platform);
        return getDataTable(list);
    }

    /**
     * 查询交易平台列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:platform:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(Platform platform)
    {
        platform.setIsEnabled(Enum.rule_validate.Y.getCode());
        List<Platform> list = platformService.selectPlatformList(platform);
        return AjaxResult.success(list);
    }

    /**
     * 导出交易平台列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:platform:export')")
    @Log(title = "交易平台", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Platform platform)
    {
        List<Platform> list = platformService.selectPlatformList(platform);
        ExcelUtil<Platform> util = new ExcelUtil<Platform>(Platform.class);
        return util.exportExcel(list, "platform");
    }

    /**
     * 获取交易平台详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:platform:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(platformService.selectPlatformById(id));
    }

    /**
     * 新增交易平台
     */
    @PreAuthorize("@ss.hasPermi('strategy:platform:add')")
    @Log(title = "交易平台", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Platform platform)
    {
        platform.setCreateDate(new Date());
        return toAjax(platformService.insertPlatform(platform));
    }

    /**
     * 修改交易平台
     */
    @PreAuthorize("@ss.hasPermi('strategy:platform:edit')")
    @Log(title = "交易平台", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Platform platform)
    {
        platform.setUpdateDate(new Date());
        return toAjax(platformService.updatePlatform(platform));
    }

    /**
     * 删除交易平台
     */
    @PreAuthorize("@ss.hasPermi('strategy:platform:remove')")
    @Log(title = "交易平台", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(platformService.deletePlatformByIds(ids));
    }
}
