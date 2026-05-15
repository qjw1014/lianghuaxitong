package com.wallet.admin.framework.web.service;

import javax.annotation.Resource;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.wallet.common.core.domain.AjaxResult;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.ServletUtils;
import com.wallet.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.wallet.admin.framework.manager.AsyncManager;
import com.wallet.admin.framework.manager.factory.AsyncFactory;
import com.wallet.common.constant.Constants;
import com.wallet.common.core.domain.entity.SysUser;
import com.wallet.common.core.domain.model.LoginUser;
import com.wallet.common.core.redis.RedisCache;
import com.wallet.common.exception.CustomException;
import com.wallet.common.exception.user.CaptchaException;
import com.wallet.common.exception.user.CaptchaExpireException;
import com.wallet.common.exception.user.UserPasswordNotMatchException;
import com.wallet.common.utils.GoogleAuthenticator;
import com.wallet.common.utils.MessageUtils;

import java.util.Random;

/**
 * 登录校验方法
 *
 * @author wallet
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Value("${admin.google.secret}")
    private String adminGoogleSecret;


    @Autowired
    private RedisUtil redis;
    @Autowired
    private ISysUserService sysUserService;
    /**
     * 登录验证
     *
     * @param username 用户名
     * @param password 密码
     * @param code 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    public String login(String username, String password, String code, String uuid)
    {
        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
//        String captcha = redisCache.getCacheObject(verifyKey);
//        redisCache.deleteObject(verifyKey);
//        if (captcha == null)
//        {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
//            throw new CaptchaExpireException();
//        }
        Boolean  isfalse=false;
        // 用户验证
        Authentication authentication = null;
        try
        {
//            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
//        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //验证邮箱验证码
//        Object obj = redis.get(username);
//        if(code.equalsIgnoreCase(obj.toString())){
//            isfalse=true;
//        }
//
//        if(!isfalse){
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
//            throw new CaptchaException();
//        }
//        GoogleAuthenticator ga = new GoogleAuthenticator();
//		long t = System.currentTimeMillis();
//		ga.setWindowSize(5);
//        boolean r = ga.check_code(loginUser.getUser().getGoogleCaptcha(), Long.parseLong(code), t);
//        if (!r)
//        {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
//            throw new CaptchaException();
//        }
        // 生成token



        return tokenService.createToken(loginUser);
    }

    /**
     * 根据用户名称发送邮件
     * @param accountNum
     * @return
     */
    public AjaxResult checkMailbox(String accountNum) {
        SysUser sysUser = sysUserService.selectUserByUserName(accountNum);
        if (sysUser == null) {
            return AjaxResult.error("用户不存在");
        }
        MailAccount account = new MailAccount();
        account.setHost("smtppro.zoho.eu");
        account.setPort(465);
        account.setAuth(true);
        account.setFrom("bitleynet@bitcooo.com");
        account.setUser("bitleynet@bitcooo.com");
        account.setPass("Bitnet0903@"); //密码
        account.setSocketFactoryClass("javax.net.ssl.SSLSocketFactory");
        account.setSslEnable(true);
        String code = getSixNum();
        MailUtil.send(account, CollUtil.newArrayList(sysUser.getEmail()), "bitcooo后台验证", "邮件来自BITCOOO,后台登录验证码为："+code+",两分钟有效", false);
        redis.set(accountNum,code,360L);
        return AjaxResult.success();
    }

    public static String getSixNum() {
        String str = "0123456789";
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 6; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }
    /**
     * 验证谷歌验证码
     * @param code
     * @return
     */
    public AjaxResult checkGoogleAuth(String code){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        GoogleAuthenticator ga = new GoogleAuthenticator();
        long t = System.currentTimeMillis();
        ga.setWindowSize(5);
        boolean r = ga.check_code(adminGoogleSecret, Long.parseLong(code), t);
        if(r){
            return AjaxResult.success();
        }else{
            return AjaxResult.error("验证失败");
        }
    }
}
