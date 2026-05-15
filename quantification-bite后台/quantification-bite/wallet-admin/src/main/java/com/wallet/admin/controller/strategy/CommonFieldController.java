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
import com.wallet.strategy.domain.CommonField;
import com.wallet.strategy.service.ICommonFieldService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 策略公共字段描述Controller
 * 
 * @author wallet
 * @date 2023-03-14
 */
@RestController
@RequestMapping("/strategy/commonField")
public class CommonFieldController extends BaseController
{
    @Autowired
    private ICommonFieldService commonFieldService;

    /**
     * 查询策略公共字段描述列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:commonField:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommonField commonField)
    {
        startPage();
        List<CommonField> list = commonFieldService.selectCommonFieldList(commonField);
        return getDataTable(list);
    }

    /**
     * 根据类型关系表查询对应信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:commonField:list')")
    @GetMapping("/typeList/{strategyId}")
    public AjaxResult typeList(@PathVariable("strategyId") Long strategyId)
    {
        List<CommonField> list = commonFieldService.selectCommonFieldListByType(strategyId);
        return AjaxResult.success(list);
    }

    /**
     * 导出策略公共字段描述列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:commonField:export')")
    @Log(title = "策略公共字段描述", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CommonField commonField)
    {
        List<CommonField> list = commonFieldService.selectCommonFieldList(commonField);
        ExcelUtil<CommonField> util = new ExcelUtil<CommonField>(CommonField.class);
        return util.exportExcel(list, "commonField");
    }

    /**
     * 获取策略公共字段描述详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:commonField:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(commonFieldService.selectCommonFieldById(id));
    }

    /**
     * 新增策略公共字段描述
     */
    @PreAuthorize("@ss.hasPermi('strategy:commonField:add')")
    @Log(title = "策略公共字段描述", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommonField commonField)
    {
        return toAjax(commonFieldService.insertCommonField(commonField));
    }

    /**
     * 修改策略公共字段描述
     */
    @PreAuthorize("@ss.hasPermi('strategy:commonField:edit')")
    @Log(title = "策略公共字段描述", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommonField commonField)
    {
        return toAjax(commonFieldService.updateCommonField(commonField));
    }

    /**
     * 删除策略公共字段描述
     */
    @PreAuthorize("@ss.hasPermi('strategy:commonField:remove')")
    @Log(title = "策略公共字段描述", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(commonFieldService.deleteCommonFieldByIds(ids));
    }
}
