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
import com.wallet.sysDict.domain.WalletConfig;
import com.wallet.sysDict.service.IWalletConfigService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 钱包配置信息Controller
 * 
 * @author wallet
 * @date 2022-03-02
 */
@RestController
@RequestMapping("/sysDict/WalletConfig")
public class WalletConfigController extends BaseController
{
    @Autowired
    private IWalletConfigService walletConfigService;

    /**
     * 查询钱包配置信息列表
     */
    @PreAuthorize("@ss.hasPermi('sysDict:WalletConfig:list')")
    @GetMapping("/list")
    public TableDataInfo list(WalletConfig walletConfig)
    {
        startPage();
        List<WalletConfig> list = walletConfigService.selectWalletConfigList(walletConfig);
        return getDataTable(list);
    }

    /**
     * 导出钱包配置信息列表
     */
    @PreAuthorize("@ss.hasPermi('sysDict:WalletConfig:export')")
    @Log(title = "钱包配置信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(WalletConfig walletConfig)
    {
        List<WalletConfig> list = walletConfigService.selectWalletConfigList(walletConfig);
        ExcelUtil<WalletConfig> util = new ExcelUtil<WalletConfig>(WalletConfig.class);
        return util.exportExcel(list, "WalletConfig");
    }

    /**
     * 获取钱包配置信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('sysDict:WalletConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(walletConfigService.selectWalletConfigById(id));
    }

    /**
     * 新增钱包配置信息
     */
    @PreAuthorize("@ss.hasPermi('sysDict:WalletConfig:add')")
    @Log(title = "钱包配置信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WalletConfig walletConfig)
    {
        return toAjax(walletConfigService.insertWalletConfig(walletConfig));
    }

    /**
     * 修改钱包配置信息
     */
    @PreAuthorize("@ss.hasPermi('sysDict:WalletConfig:edit')")
    @Log(title = "钱包配置信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WalletConfig walletConfig)
    {
        return toAjax(walletConfigService.updateWalletConfig(walletConfig));
    }

    /**
     * 删除钱包配置信息
     */
    @PreAuthorize("@ss.hasPermi('sysDict:WalletConfig:remove')")
    @Log(title = "钱包配置信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(walletConfigService.deleteWalletConfigByIds(ids));
    }
}
