package com.wallet.admin.controller.strategy;

import java.util.ArrayList;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wallet.strategy.domain.UserPlatform;
import com.wallet.strategy.domain.vo.RevenueDetailVO;
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
import com.wallet.strategy.domain.RevenueDetail;
import com.wallet.strategy.service.IRevenueDetailService;
import com.wallet.common.utils.poi.ExcelUtil;
import com.wallet.common.core.page.TableDataInfo;

/**
 * 收益明细	Controller
 * 
 * @author wallet
 * @date 2023-03-11
 */
@RestController
@RequestMapping("/strategy/revenueDetail")
public class RevenueDetailController extends BaseController
{
    @Autowired
    private IRevenueDetailService revenueDetailService;
    @Autowired
    private IUserPlatformService userPlatformService;

    /**
     * 查询收益明细	列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:revenueDetail:list')")
    @GetMapping("/list")
    public TableDataInfo list(RevenueDetail revenueDetail)
    {
        startPage();
        List<RevenueDetailVO> result = new ArrayList<>();
        List<RevenueDetail> list = revenueDetailService.selectRevenueDetailList(revenueDetail);
        if(list!=null&&list.size()>0){
            for(RevenueDetail detail:list){
                RevenueDetailVO vo = new RevenueDetailVO();
                BeanUtils.copyProperties(detail,vo);
                Long accountId = detail.getApiAccountId();
                UserPlatform userPlatform = userPlatformService.selectUserPlatformById(accountId);
                if(userPlatform!=null){
                    vo.setName(userPlatform.getName());
                }
                result.add(vo);
            }
        }
        return getDataTable(result,new PageInfo(list).getTotal());
    }

    /**
     * 导出收益明细	列表
     */
    @PreAuthorize("@ss.hasPermi('strategy:revenueDetail:export')")
    @Log(title = "收益明细	", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(RevenueDetail revenueDetail)
    {
        List<RevenueDetail> list = revenueDetailService.selectRevenueDetailList(revenueDetail);
        ExcelUtil<RevenueDetail> util = new ExcelUtil<RevenueDetail>(RevenueDetail.class);
        return util.exportExcel(list, "revenueDetail");
    }

    /**
     * 获取收益明细	详细信息
     */
    @PreAuthorize("@ss.hasPermi('strategy:revenueDetail:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(revenueDetailService.selectRevenueDetailById(id));
    }

    /**
     * 新增收益明细	
     */
    @PreAuthorize("@ss.hasPermi('strategy:revenueDetail:add')")
    @Log(title = "收益明细	", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RevenueDetail revenueDetail)
    {
        return toAjax(revenueDetailService.insertRevenueDetail(revenueDetail));
    }

    /**
     * 修改收益明细	
     */
    @PreAuthorize("@ss.hasPermi('strategy:revenueDetail:edit')")
    @Log(title = "收益明细	", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RevenueDetail revenueDetail)
    {
        return toAjax(revenueDetailService.updateRevenueDetail(revenueDetail));
    }

    /**
     * 删除收益明细	
     */
    @PreAuthorize("@ss.hasPermi('strategy:revenueDetail:remove')")
    @Log(title = "收益明细	", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(revenueDetailService.deleteRevenueDetailByIds(ids));
    }
}
