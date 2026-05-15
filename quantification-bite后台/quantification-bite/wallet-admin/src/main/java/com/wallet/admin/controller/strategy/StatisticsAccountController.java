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
import com.wallet.strategy.domain.StatisticsAccount;
import com.wallet.strategy.service.IStatisticsAccountService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 统计账户信息Controller
 * 
 * @author wallet
 * @date 2023-03-11
 */
@RestController
@RequestMapping("/strategy/statisticsAccount")
public class StatisticsAccountController extends BaseController
{
    @Autowired
    private IStatisticsAccountService statisticsAccountService;

    /**
     * 查询统计账户信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:statisticsAccount:list')")
    @GetMapping("/list")
    public TableDataInfo list(StatisticsAccount statisticsAccount)
    {
        startPage();
        List<StatisticsAccount> list = statisticsAccountService.selectStatisticsAccountList(statisticsAccount);
        return getDataTable(list);
    }

    /**
     * 导出统计账户信息列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:statisticsAccount:export')")
    @Log(title = "统计账户信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(StatisticsAccount statisticsAccount)
    {
        List<StatisticsAccount> list = statisticsAccountService.selectStatisticsAccountList(statisticsAccount);
        ExcelUtil<StatisticsAccount> util = new ExcelUtil<StatisticsAccount>(StatisticsAccount.class);
        return util.exportExcel(list, "statisticsAccount");
    }

    /**
     * 获取统计账户信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:statisticsAccount:query')")
    @GetMapping(value = "/{apiAccountId}")
    public AjaxResult getInfo(@PathVariable("apiAccountId") Long apiAccountId)
    {
        return AjaxResult.success(statisticsAccountService.selectStatisticsAccountById(apiAccountId));
    }

    /**
     * 新增统计账户信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:statisticsAccount:add')")
    @Log(title = "统计账户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StatisticsAccount statisticsAccount)
    {
        return toAjax(statisticsAccountService.insertStatisticsAccount(statisticsAccount));
    }

    /**
     * 修改统计账户信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:statisticsAccount:edit')")
    @Log(title = "统计账户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StatisticsAccount statisticsAccount)
    {
        return toAjax(statisticsAccountService.updateStatisticsAccount(statisticsAccount));
    }

    /**
     * 删除统计账户信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:statisticsAccount:remove')")
    @Log(title = "统计账户信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{apiAccountIds}")
    public AjaxResult remove(@PathVariable Long[] apiAccountIds)
    {
        return toAjax(statisticsAccountService.deleteStatisticsAccountByIds(apiAccountIds));
    }
}
