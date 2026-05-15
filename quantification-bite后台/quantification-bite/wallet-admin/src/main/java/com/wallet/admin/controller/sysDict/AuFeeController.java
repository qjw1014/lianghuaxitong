package com.wallet.admin.controller.sysDict;

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
import com.wallet.sysDict.domain.AuFee;
import com.wallet.sysDict.service.IAuFeeService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 手续费配置Controller
 * 
 * @author wallet
 * @date 2022-02-28
 */
@RestController
@RequestMapping("/sysDict/AuFee")
public class AuFeeController extends BaseController
{
    @Autowired
    private IAuFeeService auFeeService;

    /**
     * 查询手续费配置列表
     */
    @PreAuthorize("@ss.hasPermi('sysDict:AuFee:list')")
    @GetMapping("/list")
    public TableDataInfo list(AuFee auFee)
    {
        startPage();
        List<AuFee> list = auFeeService.selectAuFeeList(auFee);
        return getDataTable(list);
    }

    /**
     * 导出手续费配置列表
     */
    @PreAuthorize("@ss.hasPermi('sysDict:AuFee:export')")
    @Log(title = "手续费配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(AuFee auFee)
    {
        List<AuFee> list = auFeeService.selectAuFeeList(auFee);
        ExcelUtil<AuFee> util = new ExcelUtil<AuFee>(AuFee.class);
        return util.exportExcel(list, "AuFee");
    }

    /**
     * 获取手续费配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('sysDict:AuFee:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(auFeeService.selectAuFeeById(id));
    }

    /**
     * 新增手续费配置
     */
    @PreAuthorize("@ss.hasPermi('sysDict:AuFee:add')")
    @Log(title = "手续费配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AuFee auFee)
    {
        return toAjax(auFeeService.insertAuFee(auFee));
    }

    /**
     * 修改手续费配置
     */
    @PreAuthorize("@ss.hasPermi('sysDict:AuFee:edit')")
    @Log(title = "手续费配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AuFee auFee)
    {
        return toAjax(auFeeService.updateAuFee(auFee));
    }

    /**
     * 删除手续费配置
     */
    @PreAuthorize("@ss.hasPermi('sysDict:AuFee:remove')")
    @Log(title = "手续费配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(auFeeService.deleteAuFeeByIds(ids));
    }
}
