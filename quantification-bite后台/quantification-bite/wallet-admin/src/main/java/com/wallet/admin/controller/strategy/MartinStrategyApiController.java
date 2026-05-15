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
import com.wallet.strategy.domain.MartinStrategyApi;
import com.wallet.strategy.service.IMartinStrategyApiService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * api对应策略信息Controller
 * 
 * @author wallet
 * @date 2022-09-05
 */
@RestController
@RequestMapping("/strategy/martinStrategyApi")
public class MartinStrategyApiController extends BaseController
{
    @Autowired
    private IMartinStrategyApiService martinStrategyApiService;

    /**
     * 查询api对应策略信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyApi:list')")
    @GetMapping("/list")
    public TableDataInfo list(MartinStrategyApi martinStrategyApi)
    {
        startPage();
        List<MartinStrategyApi> list = martinStrategyApiService.selectMartinStrategyApiList(martinStrategyApi);
        return getDataTable(list);
    }


    /**
     * 导出api对应策略信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyApi:export')")
    @Log(title = "api对应策略信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(MartinStrategyApi martinStrategyApi)
    {
        List<MartinStrategyApi> list = martinStrategyApiService.selectMartinStrategyApiList(martinStrategyApi);
        ExcelUtil<MartinStrategyApi> util = new ExcelUtil<MartinStrategyApi>(MartinStrategyApi.class);
        return util.exportExcel(list, "martinStrategyApi");
    }

    /**
     * 获取api对应策略信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyApi:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(martinStrategyApiService.selectMartinStrategyApiById(id));
    }

    /**
     * 新增api对应策略信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyApi:add')")
    @Log(title = "api对应策略信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MartinStrategyApi martinStrategyApi)
    {
        return toAjax(martinStrategyApiService.insertMartinStrategyApi(martinStrategyApi));
    }

    /**
     * 修改api对应策略信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyApi:edit')")
    @Log(title = "api对应策略信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MartinStrategyApi martinStrategyApi)
    {
        return toAjax(martinStrategyApiService.updateMartinStrategyApi(martinStrategyApi));
    }


    /**
     * 修改策略首单金额比例
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyApi:edit')")
    @Log(title = "修改策略首单金额比例", businessType = BusinessType.UPDATE)
    @PutMapping("/updateBaseRate")
    public AjaxResult updateBaseRate(@RequestBody MartinStrategyApi martinStrategyApi)
    {
        return toAjax(martinStrategyApiService.updateBaseRate(martinStrategyApi));
    }

    /**
     * 开启策略
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyApi:edit')")
    @Log(title = "开启策略", businessType = BusinessType.UPDATE)
    @PutMapping("/startMartinStrategy")
    public AjaxResult startMartinStrategy(@RequestBody MartinStrategyApi martinStrategyApi)
    {
        return toAjax(martinStrategyApiService.startMartinStrategy(martinStrategyApi));
    }

    /**
     * 停止策略
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyApi:edit')")
    @Log(title = "停止策略", businessType = BusinessType.UPDATE)
    @PutMapping("/stopMartinStrategy")
    public AjaxResult stopMartinStrategy(@RequestBody MartinStrategyApi martinStrategyApi)
    {
        return toAjax(martinStrategyApiService.stopMartinStrategy(martinStrategyApi));
    }


    /**
     * 删除api对应策略信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:martinStrategyApi:remove')")
    @Log(title = "api对应策略信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(martinStrategyApiService.deleteMartinStrategyApiByIds(ids));
    }
}
