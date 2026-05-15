package com.wallet.admin.controller.version;

import com.wallet.common.annotation.Log;
import com.wallet.common.core.controller.BaseController;
import com.wallet.common.core.domain.AjaxResult;
import com.wallet.common.core.page.TableDataInfo;
import com.wallet.common.enums.BusinessType;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.version.domain.VersionUpgrade;
import com.wallet.version.service.IVersionUpgradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * app版本升级Controller
 * 
 * @author wallet
 * @date 2021-11-19
 */
@RestController
@RequestMapping("/version/version")
public class VersionUpgradeController extends BaseController
{
    @Autowired
    private IVersionUpgradeService versionUpgradeService;

    /**
     * 查询app版本升级列表
     */
    @PreAuthorize("@ss.hasPermi('version:version:list')")
    @GetMapping("/list")
    public TableDataInfo list(VersionUpgrade versionUpgrade)
    {
        startPage();
        List<VersionUpgrade> list = versionUpgradeService.selectVersionUpgradeList(versionUpgrade);
        return getDataTable(list);
    }

    /**
     * 导出app版本升级列表
     */
    @PreAuthorize("@ss.hasPermi('version:version:export')")
    @Log(title = "app版本升级", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(VersionUpgrade versionUpgrade)
    {
        List<VersionUpgrade> list = versionUpgradeService.selectVersionUpgradeList(versionUpgrade);
        ExcelUtil<VersionUpgrade> util = new ExcelUtil<VersionUpgrade>(VersionUpgrade.class);
        return util.exportExcel(list, "version");
    }

    /**
     * 获取app版本升级详细信息
     */
    @PreAuthorize("@ss.hasPermi('version:version:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(versionUpgradeService.selectVersionUpgradeById(id));
    }




    /**
     * 新增app版本升级
     */
    @PreAuthorize("@ss.hasPermi('version:version:add')")
    @Log(title = "app版本升级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody VersionUpgrade versionUpgrade)
    {
        return toAjax(versionUpgradeService.insertVersionUpgrade(versionUpgrade));
    }

    /**
     * 修改app版本升级
     */
    @PreAuthorize("@ss.hasPermi('version:version:edit')")
    @Log(title = "app版本升级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody VersionUpgrade versionUpgrade)
    {
        return toAjax(versionUpgradeService.updateVersionUpgrade(versionUpgrade));
    }

    /**
     * 删除app版本升级
     */
    @PreAuthorize("@ss.hasPermi('version:version:remove')")
    @Log(title = "app版本升级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(versionUpgradeService.deleteVersionUpgradeByIds(ids));
    }
}
