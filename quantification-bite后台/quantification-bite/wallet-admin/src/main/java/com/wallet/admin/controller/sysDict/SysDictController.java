package com.wallet.admin.controller.sysDict;

import java.util.List;

import com.wallet.sysDict.domain.SysDict;
import com.wallet.sysDict.service.ISysDictService;
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
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 参数配置Controller
 * 
 * @author wallet
 * @date 2022-01-25
 */
@RestController
@RequestMapping("/sysDict/sysDict")
public class SysDictController extends BaseController
{
    @Autowired
    private ISysDictService sysDictService;

    /**
     * 查询参数配置列表
     */
    @PreAuthorize("@ss.hasPermi('SysDict:sysDict:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysDict sysDict)
    {
        startPage();
        List<SysDict> list = sysDictService.selectSysDictListBy(sysDict);
        return getDataTable(list);
    }

    /**
     * 查询参数配置列表父级
     */
    @PreAuthorize("@ss.hasPermi('SysDict:sysDict:list')")
    @GetMapping("/parentList")
    public TableDataInfo parentList(SysDict sysDict)
    {
        sysDict.setPid(0L);
        List<SysDict> list = sysDictService.selectSysDictListParentBy(sysDict);
        return getDataTable(list);
    }

    /**
     * 导出参数配置列表
     */
    @PreAuthorize("@ss.hasPermi('SysDict:sysDict:export')")
    @Log(title = "参数配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysDict sysDict)
    {
        List<SysDict> list = sysDictService.selectSysDictList(sysDict);
        ExcelUtil<SysDict> util = new ExcelUtil<SysDict>(SysDict.class);
        return util.exportExcel(list, "sysDict");
    }

    /**
     * 获取参数配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('SysDict:sysDict:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysDictService.selectSysDictById(id));
    }

    /**
     * 新增参数配置
     */
    @PreAuthorize("@ss.hasPermi('SysDict:sysDict:add')")
    @Log(title = "参数配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysDict sysDict)
    {
        return toAjax(sysDictService.insertSysDict(sysDict));
    }

    /**
     * 修改参数配置
     */
    @PreAuthorize("@ss.hasPermi('SysDict:sysDict:edit')")
    @Log(title = "参数配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysDict sysDict)
    {
        return toAjax(sysDictService.updateSysDict(sysDict));
    }

    /**
     * 删除参数配置
     */
    @PreAuthorize("@ss.hasPermi('SysDict:sysDict:remove')")
    @Log(title = "参数配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysDictService.deleteSysDictByIds(ids));
    }
}
