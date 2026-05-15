package com.wallet.admin.controller.strategy;

import java.util.ArrayList;
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
import com.wallet.strategy.domain.StrageyPositionInfo;
import com.wallet.strategy.service.IStrageyPositionInfoService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 策略仓位Controller
 * 
 * @author wallet
 * @date 2023-03-11
 */
@RestController
@RequestMapping("/strategy/strageyPositionInfo")
public class StrageyPositionInfoController extends BaseController
{
    @Autowired
    private IStrageyPositionInfoService strageyPositionInfoService;

    /**
     * 查询策略仓位列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:strageyPositionInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(StrageyPositionInfo strageyPositionInfo)
    {
        startPage();
        List<StrageyPositionInfo> list = strageyPositionInfoService.selectStrageyPositionInfoList(strageyPositionInfo);
        return getDataTable(list);
    }

    /**
     * 根据子账号查询策略仓位列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:strageyPositionInfo:list')")
    @GetMapping("/accountList")
    public TableDataInfo accountList(StrageyPositionInfo strageyPositionInfo)
    {
        List<StrageyPositionInfo> list = new ArrayList<>();
        if(strageyPositionInfo.getApiAccountId()!=null){
            list = strageyPositionInfoService.selectStrageyPositionInfoByAccount(strageyPositionInfo.getApiAccountId());
        }
        return getDataTable(list);
    }



    /**
     * 导出策略仓位列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:strageyPositionInfo:export')")
    @Log(title = "策略仓位", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StrageyPositionInfo strageyPositionInfo)
    {
        List<StrageyPositionInfo> list = strageyPositionInfoService.selectStrageyPositionInfoList(strageyPositionInfo);
        ExcelUtil<StrageyPositionInfo> util = new ExcelUtil<StrageyPositionInfo>(StrageyPositionInfo.class);
        return util.exportExcel(list, "strageyPositionInfo");
    }

    /**
     * 获取策略仓位详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:strageyPositionInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(strageyPositionInfoService.selectStrageyPositionInfoById(id));
    }

    /**
     * 新增策略仓位
     */
    @PreAuthorize("@ss.hasPermi('strategy:strageyPositionInfo:add')")
    @Log(title = "策略仓位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StrageyPositionInfo strageyPositionInfo)
    {
        return toAjax(strageyPositionInfoService.insertStrageyPositionInfo(strageyPositionInfo));
    }

    /**
     * 修改策略仓位
     */
    @PreAuthorize("@ss.hasPermi('strategy:strageyPositionInfo:edit')")
    @Log(title = "策略仓位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StrageyPositionInfo strageyPositionInfo)
    {
        return toAjax(strageyPositionInfoService.updateStrageyPositionInfo(strageyPositionInfo));
    }

    /**
     * 删除策略仓位
     */
    @PreAuthorize("@ss.hasPermi('strategy:strageyPositionInfo:remove')")
    @Log(title = "策略仓位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(strageyPositionInfoService.deleteStrageyPositionInfoByIds(ids));
    }
}
