package com.wallet.admin.controller.strategy;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wallet.strategy.domain.UserPlatform;
import com.wallet.strategy.domain.vo.ChargeHistoryVO;
import com.wallet.strategy.service.IUserPlatformService;
import org.springframework.beans.BeanUtils;
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
import com.wallet.strategy.domain.ChargeHistory;
import com.wallet.strategy.service.IChargeHistoryService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 充值记录	Controller
 * 
 * @author wallet
 * @date 2023-03-11
 */
@RestController
@RequestMapping("/strategy/chargeHistory")
public class ChargeHistoryController extends BaseController
{
    @Autowired
    private IChargeHistoryService chargeHistoryService;
    @Autowired
    private IUserPlatformService userPlatformService;

    /**
     * 查询充值记录	列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:chargeHistory:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChargeHistory chargeHistory)
    {
        startPage();
        List<ChargeHistoryVO> result = new ArrayList<>();
        List<ChargeHistory> list = chargeHistoryService.selectChargeHistoryList(chargeHistory);
        if(list!=null&&list.size()>0){
            for(ChargeHistory detail:list){
                ChargeHistoryVO vo = new ChargeHistoryVO();
                BeanUtils.copyProperties(detail,vo);
                Long accountId = detail.getApiAccountId();
                UserPlatform userPlatform = userPlatformService.selectUserPlatformById(accountId);
                if(userPlatform!=null){
                    vo.setName(userPlatform.getName());
                }
                result.add(vo);
            }
        }
        return getDataTable(result,new PageInfo<>(list).getTotal());
    }

    /**
     * 导出充值记录	列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:chargeHistory:export')")
    @Log(title = "充值记录	", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(ChargeHistory chargeHistory)
    {
        List<ChargeHistory> list = chargeHistoryService.selectChargeHistoryList(chargeHistory);
        ExcelUtil<ChargeHistory> util = new ExcelUtil<ChargeHistory>(ChargeHistory.class);
        return util.exportExcel(list, "chargeHistory");
    }

    /**
     * 获取充值记录	详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:chargeHistory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(chargeHistoryService.selectChargeHistoryById(id));
    }

    /**
     * 新增充值记录	
     */
    @PreAuthorize("@ss.hasPermi('strategy:chargeHistory:add')")
    @Log(title = "充值记录	", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChargeHistory chargeHistory)
    {
        return toAjax(chargeHistoryService.insertChargeHistory(chargeHistory));
    }

    /**
     * 修改充值记录	
     */
    @PreAuthorize("@ss.hasPermi('strategy:chargeHistory:edit')")
    @Log(title = "充值记录	", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChargeHistory chargeHistory)
    {
        return toAjax(chargeHistoryService.updateChargeHistory(chargeHistory));
    }

    /**
     * 删除充值记录	
     */
    @PreAuthorize("@ss.hasPermi('strategy:chargeHistory:remove')")
    @Log(title = "充值记录	", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(chargeHistoryService.deleteChargeHistoryByIds(ids));
    }
}
