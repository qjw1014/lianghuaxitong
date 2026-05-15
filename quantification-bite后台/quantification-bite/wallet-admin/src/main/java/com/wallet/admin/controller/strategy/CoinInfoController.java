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
import com.wallet.strategy.domain.CoinInfo;
import com.wallet.strategy.service.ICoinInfoService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 币种Controller
 * 
 * @author wallet
 * @date 2022-09-01
 */
@RestController
@RequestMapping("/strategy/coinInfo")
public class CoinInfoController extends BaseController
{
    @Autowired
    private ICoinInfoService coinInfoService;

    /**
     * 查询币种列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:coinInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(CoinInfo coinInfo)
    {
        startPage();
        List<CoinInfo> list = coinInfoService.selectCoinInfoList(coinInfo);
        return getDataTable(list);
    }

    /**
     * 查询币种列表全部
     */
    @PreAuthorize("@ss.hasPermi('strategy:coinInfo:list')")
    @GetMapping("/listAll")
    public AjaxResult listAll(CoinInfo coinInfo)
    {
        coinInfo.setIsEnabled(Enum.rule_validate.Y.getCode());
        List<CoinInfo> list = coinInfoService.selectCoinInfoList(coinInfo);
        return AjaxResult.success(list);
    }



    /**
     * 导出币种列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:coinInfo:export')")
    @Log(title = "币种", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(CoinInfo coinInfo)
    {
        List<CoinInfo> list = coinInfoService.selectCoinInfoList(coinInfo);
        ExcelUtil<CoinInfo> util = new ExcelUtil<CoinInfo>(CoinInfo.class);
        return util.exportExcel(list, "coinInfo");
    }

    /**
     * 获取币种详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:coinInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(coinInfoService.selectCoinInfoById(id));
    }

    /**
     * 新增币种
     */
    @PreAuthorize("@ss.hasPermi('strategy:coinInfo:add')")
    @Log(title = "币种", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CoinInfo coinInfo)
    {
        coinInfo.setCreateDate(new Date());
        return toAjax(coinInfoService.insertCoinInfo(coinInfo));
    }

    /**
     * 修改币种
     */
    @PreAuthorize("@ss.hasPermi('strategy:coinInfo:edit')")
    @Log(title = "币种", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CoinInfo coinInfo)
    {
        coinInfo.setUpdateDate(new Date());
        return toAjax(coinInfoService.updateCoinInfo(coinInfo));
    }

    /**
     * 删除币种
     */
    @PreAuthorize("@ss.hasPermi('strategy:coinInfo:remove')")
    @Log(title = "币种", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(coinInfoService.deleteCoinInfoByIds(ids));
    }
}
