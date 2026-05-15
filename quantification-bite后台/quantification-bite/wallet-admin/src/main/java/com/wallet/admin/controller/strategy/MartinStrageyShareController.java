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
import com.wallet.strategy.domain.MartinStrageyShare;
import com.wallet.strategy.service.IMartinStrageyShareService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 分润统计Controller
 * 
 * @author wallet
 * @date 2022-10-31
 */
@RestController
@RequestMapping("/strategy/martinStrageyShare")
public class MartinStrageyShareController extends BaseController
{
    @Autowired
    private IMartinStrageyShareService martinStrageyShareService;

    /**
     * 查询分润统计列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrageyShare:list')")
    @GetMapping("/list")
    public TableDataInfo list(MartinStrageyShare martinStrageyShare)
    {
        startPage();
        List<MartinStrageyShare> list = martinStrageyShareService.selectMartinStrageyShareList(martinStrageyShare);
        return getDataTable(list);
    }

    /**
     * 导出分润统计列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrageyShare:export')")
    @Log(title = "分润统计", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MartinStrageyShare martinStrageyShare)
    {
        List<MartinStrageyShare> list = martinStrageyShareService.selectMartinStrageyShareList(martinStrageyShare);
        ExcelUtil<MartinStrageyShare> util = new ExcelUtil<MartinStrageyShare>(MartinStrageyShare.class);
        return util.exportExcel(list, "martinStrageyShare");
    }

    /**
     * 获取分润统计详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrageyShare:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(martinStrageyShareService.selectMartinStrageyShareById(id));
    }

    /**
     * 新增分润统计
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrageyShare:add')")
    @Log(title = "分润统计", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MartinStrageyShare martinStrageyShare)
    {
        return toAjax(martinStrageyShareService.insertMartinStrageyShare(martinStrageyShare));
    }

    /**
     * 修改分润统计
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrageyShare:edit')")
    @Log(title = "分润统计", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MartinStrageyShare martinStrageyShare)
    {
        return toAjax(martinStrageyShareService.updateMartinStrageyShare(martinStrageyShare));
    }

    /**
     * 删除分润统计
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrageyShare:remove')")
    @Log(title = "分润统计", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(martinStrageyShareService.deleteMartinStrageyShareByIds(ids));
    }
}
