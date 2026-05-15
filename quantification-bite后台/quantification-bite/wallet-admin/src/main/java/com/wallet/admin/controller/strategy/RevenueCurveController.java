package com.wallet.admin.controller.strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
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
import com.wallet.strategy.domain.RevenueCurve;
import com.wallet.strategy.service.IRevenueCurveService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 收益曲线Controller
 * 
 * @author wallet
 * @date 2023-03-11
 */
@RestController
@RequestMapping("/strategy/revenueCurve")
public class RevenueCurveController extends BaseController
{
    @Autowired
    private IRevenueCurveService revenueCurveService;

    /**
     * 查询收益曲线列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:revenueCurve:list')")
    @GetMapping("/list")
    public TableDataInfo list(RevenueCurve revenueCurve)
    {
        startPage();
        List<RevenueCurve> list = revenueCurveService.selectRevenueCurveList(revenueCurve);
        return getDataTable(list);
    }

    /**
     * 导出收益曲线列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:revenueCurve:export')")
    @Log(title = "收益曲线", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RevenueCurve revenueCurve)
    {
        List<RevenueCurve> list = revenueCurveService.selectRevenueCurveList(revenueCurve);
        ExcelUtil<RevenueCurve> util = new ExcelUtil<RevenueCurve>(RevenueCurve.class);
        return util.exportExcel(list, "revenueCurve");
    }

    /**
     * 获取收益曲线详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:revenueCurve:query')")
    @GetMapping(value = "listByAccount/{accountId}")
    public AjaxResult listByAccount(@PathVariable("accountId") Long accountId)
    {
        JSONObject object = new JSONObject();
        //日期
        List<String> timeData = new ArrayList<>();
        //数据
        List<BigDecimal> data = new ArrayList<>();
        List<RevenueCurve> list = revenueCurveService.selectRevenueCurveListOrderByTime(accountId);
        if(!CollectionUtils.isEmpty(list)){
            for(RevenueCurve curve :list){
                timeData.add(curve.getDataTime());
                data.add(curve.getNetValue());
            }
        }
        object.put("data",data);
        object.put("datas",timeData);
        return AjaxResult.success(object);
    }


    /**
     * 获取收益曲线列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:revenueCurve:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(revenueCurveService.selectRevenueCurveById(id));
    }

    /**
     * 新增收益曲线
     */
    @PreAuthorize("@ss.hasPermi('strategy:revenueCurve:add')")
    @Log(title = "收益曲线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RevenueCurve revenueCurve)
    {
        return toAjax(revenueCurveService.insertRevenueCurve(revenueCurve));
    }

    /**
     * 修改收益曲线
     */
    @PreAuthorize("@ss.hasPermi('strategy:revenueCurve:edit')")
    @Log(title = "收益曲线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RevenueCurve revenueCurve)
    {
        return toAjax(revenueCurveService.updateRevenueCurve(revenueCurve));
    }

    /**
     * 删除收益曲线
     */
    @PreAuthorize("@ss.hasPermi('strategy:revenueCurve:remove')")
    @Log(title = "收益曲线", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(revenueCurveService.deleteRevenueCurveByIds(ids));
    }
}
