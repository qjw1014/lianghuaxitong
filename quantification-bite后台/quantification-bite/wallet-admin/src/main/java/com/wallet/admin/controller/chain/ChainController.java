package com.wallet.admin.controller.chain;

import com.wallet.chain.domain.Chain;
import com.wallet.chain.service.IChainService;
import com.wallet.common.annotation.Log;
import com.wallet.common.core.controller.BaseController;
import com.wallet.common.core.domain.AjaxResult;
import com.wallet.common.core.page.TableDataInfo;
import com.wallet.common.enums.BusinessType;
import com.wallet.common.utils.poi.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 链Controller
 * 
 * @author wallet
 * @date 2021-10-25
 */
@RestController
@RequestMapping("/chain/chain")
public class ChainController extends BaseController
{
    @Autowired
    private IChainService chainService;

    /**
     * 查询链列表
     */
    @PreAuthorize("@ss.hasPermi('chain:chain:list')")
    @GetMapping("/list")
    public TableDataInfo list(Chain chain)
    {
        startPage();
        List<Chain> list = chainService.selectChainList(chain);
        return getDataTable(list);
    }

    /**
     * 查询链列表查询全部
     */
    @PreAuthorize("@ss.hasPermi('chain:chain:list')")
    @GetMapping("/listAll")
    public TableDataInfo listAll(Chain chain)
    {
        List<Chain> list = chainService.selectChainListAll(chain);
        return getDataTable(list);
    }

    /**
     * 导出链列表
     */
    @PreAuthorize("@ss.hasPermi('chain:chain:export')")
    @Log(title = "链", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Chain chain)
    {
        List<Chain> list = chainService.selectChainList(chain);
        ExcelUtil<Chain> util = new ExcelUtil<Chain>(Chain.class);
        return util.exportExcel(list, "chain");
    }

    /**
     * 获取链详细信息
     */
    @PreAuthorize("@ss.hasPermi('chain:chain:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(chainService.selectChainById(id));
    }

    /**
     * 新增链
     */
    @PreAuthorize("@ss.hasPermi('chain:chain:add')")
    @Log(title = "链", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Chain chain)
    {
        return toAjax(chainService.insertChain(chain));
    }

    /**
     * 修改链
     */
    @PreAuthorize("@ss.hasPermi('chain:chain:edit')")
    @Log(title = "链", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Chain chain)
    {
        return toAjax(chainService.updateChain(chain));
    }

    /**
     * 删除链
     */
    @PreAuthorize("@ss.hasPermi('chain:chain:remove')")
    @Log(title = "链", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(chainService.deleteChainByIds(ids));
    }
}
