package com.wallet.admin.controller.system;

import java.util.List;
import java.util.Set;

import com.wallet.common.core.domain.model.CheckGoogleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wallet.admin.framework.web.service.SysLoginService;
import com.wallet.admin.framework.web.service.SysPermissionService;
import com.wallet.admin.framework.web.service.TokenService;
import com.wallet.common.constant.Constants;
import com.wallet.common.core.domain.AjaxResult;
import com.wallet.common.core.domain.entity.SysMenu;
import com.wallet.common.core.domain.entity.SysUser;
import com.wallet.common.core.domain.model.LoginBody;
import com.wallet.common.core.domain.model.LoginUser;
import com.wallet.common.utils.ServletUtils;
import com.wallet.system.service.ISysMenuService;

/**
 * 登录验证
 *
 * @author wallet
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     *
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }

    /**
     * 验证用户账号发送邮件
     * @param accountNum
     * @return
     */
    @GetMapping("/sendMessage")
    public AjaxResult sendMessage(@RequestParam(value = "accountNum") String accountNum){
        return loginService.checkMailbox(accountNum);
    }


    /**
     * 验证谷歌验证码
     * @return
     */
    @PostMapping("/checkGoogleAuth")
    public AjaxResult checkGoogleAuth(@RequestBody CheckGoogleDto dto){
        String code = dto.getGoogleCode();
        return loginService.checkGoogleAuth(code);
    }
}
