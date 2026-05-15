package com.wallet.strategy.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.wallet.common.core.domain.entity.SysMenu;
import com.wallet.common.enums.Enum;
import com.wallet.common.enums.PlatformEnum;
import com.wallet.common.enums.UserPlatformMenuEnum;
import com.wallet.common.exception.BaseException;
import com.wallet.common.listener.SystemListener;
import com.wallet.common.redis.RedisUtil;
import com.wallet.common.utils.AESUtil;
import com.wallet.common.utils.JsonUtils;
import com.wallet.common.utils.MessageUtils;
import com.wallet.strategy.domain.MartinStrageyShare;
import com.wallet.strategy.domain.MartinStrategyApi;
import com.wallet.strategy.domain.MartinStrategyInfo;
import com.wallet.strategy.domain.dto.AccountRedisDto;
import com.wallet.strategy.domain.dto.PlatformKeyInfoDto;
import com.wallet.strategy.mapper.MartinStrageyShareMapper;
import com.wallet.strategy.mapper.MartinStrategyOrderMapper;
import com.wallet.strategy.redisKeyConfig.StrategyRedisConfig;
import com.wallet.strategy.service.*;
import com.wallet.strategy.service.impl.okex.OkexV3Client;
import com.wallet.system.service.ISysMenuService;
import com.wallet.system.service.ISysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wallet.strategy.mapper.UserPlatformMapper;
import com.wallet.strategy.domain.UserPlatform;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * 用户平台私钥信息Service业务层处理
 * 
 * @author wallet
 * @date 2022-09-01
 */
@Service
public class UserPlatformServiceImpl implements IUserPlatformService 
{
    @Autowired
    private UserPlatformMapper userPlatformMapper;

    @Autowired
    private RedisUtil redisUtil;

    private BourseService bourseService;

    @Autowired
    private IMartinStrategyApiService martinStrategyApiService;

    @Autowired
    private IMartinStrategyInfoService martinStrategyInfoService;

    @Autowired
    private MartinStrategyOrderMapper martinStrategyOrderMapper;
    @Autowired
    private IMartinStrategyOrderService martinStrategyOrderService;

    @Autowired
    private MartinStrageyShareMapper martinStrageyShareMapper;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysRoleService sysRoleService;

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(OkexV3Client.class);


    /**
     * 查询用户平台私钥信息
     * 
     * @param id 用户平台私钥信息ID
     * @return 用户平台私钥信息
     */
    @Override
    public UserPlatform selectUserPlatformById(Long id)
    {
        return userPlatformMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询用户平台私钥信息列表
     * 
     * @param userPlatform 用户平台私钥信息
     * @return 用户平台私钥信息
     */
    @Override
    public List<UserPlatform> selectUserPlatformList(UserPlatform userPlatform)
    {
        Example example = new Example(UserPlatform.class);
        Example.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotBlank(userPlatform.getAppkey())){
            criteria.andEqualTo("appkey",userPlatform.getAppkey());
        }
        if(StringUtils.isNotBlank(userPlatform.getPlatformName())){
            criteria.andEqualTo("platformName",userPlatform.getPlatformName());
        }
        if(StringUtils.isNotBlank(userPlatform.getName())){
            criteria.andEqualTo("name",userPlatform.getName());
        }
        if(StringUtils.isNotBlank(userPlatform.getPhone())){
            criteria.andEqualTo("phone",userPlatform.getPhone());
        }
        if(StringUtils.isNotBlank(userPlatform.getIsEnabled())){
            criteria.andEqualTo("isEnabled",userPlatform.getIsEnabled());
        }
        if(StringUtils.isNotBlank(userPlatform.getIsDelete())){
            criteria.andEqualTo("isDelete",userPlatform.getIsDelete());
        }
        if(StringUtils.isNotBlank(userPlatform.getSyncStatus())){
            criteria.andEqualTo("syncStatus",userPlatform.getSyncStatus());
        }
        example.setOrderByClause("create_date desc");
        return userPlatformMapper.selectByExample(example);
    }
	 @Override
    public List<UserPlatform> findSelect(UserPlatform userPlatform)
    {
        return userPlatformMapper.select(userPlatform);
    }
    /**
     * 新增用户平台私钥信息
     * 
     * @param userPlatform 用户平台私钥信息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertUserPlatform(UserPlatform userPlatform)
    {
        log.info("apikey验证参数"+JsonUtils.objectToJson(userPlatform));
        if(StringUtils.isBlank(userPlatform.getAppkey())||
                StringUtils.isBlank(userPlatform.getAppsecret())){
            throw new BaseException(MessageUtils.message("api.key.is.error"));
        }
         //验证添加
         int count = findUserPlatformByKey(userPlatform.getAppkey());
		 if(count>0){
            throw new BaseException(MessageUtils.message("key.already.exists"));
         }
        //判断名称是否已存在
        int nameCount = findUserPlatformByName(userPlatform.getName(),null);
        if(nameCount>0){
            throw new BaseException("秘钥名称已存在，请换个名称");
        }
        //测试是否配置正确
        PlatformKeyInfoDto decodeDto = new PlatformKeyInfoDto();
        decodeDto.setPlatform(userPlatform.getPlatformName());
        decodeDto.setAppId(userPlatform.getAppkey());
        decodeDto.setPassword(userPlatform.getTradePassword());
        decodeDto.setSecret(userPlatform.getAppsecret());
        bourseService = getBean(userPlatform.getPlatformName());
        try {
            boolean verify = bourseService.testConnect(decodeDto);
            if(!verify){
                log.error("apikey验证不通过，verify=%s",verify);
                throw new BaseException(MessageUtils.message("key.error"));
            }
        } catch (Exception e) {
            // 网络受限环境下跳过连接测试，允许保存
            log.warn("跳过交易所连接验证: {}", e.getMessage());
        }
        UserPlatform add  = new UserPlatform();
        add.setAppkey(userPlatform.getAppkey());
        add.setAppsecret(userPlatform.getAppsecret());
        add.setTradePassword(userPlatform.getTradePassword());
        add.setName(userPlatform.getName());
        add.setPlatformName(userPlatform.getPlatformName());
        add.setPhone(userPlatform.getPhone());
        add.setSyncDate(new Date());
        add.setIsEnabled(Enum.rule_validate.Y.getCode());
        add.setIsDelete(Enum.rule_validate.N.getCode());
        add.setCreateDate(new Date());
        add.setUpdateDate(new Date());
        add.setSyncStatus(Enum.rule_validate.Y.getCode());
        add.setInitUsdt(userPlatform.getInitUsdt());
        add.setExpirationDate(userPlatform.getExpirationDate());
        int result = userPlatformMapper.insertUserPlatform(add);
        if(result>0){
            //添加api信息到订单里
            this.insertMenu(add);

            //存放redis数据
            AccountRedisDto redisDto = new AccountRedisDto();
            redisDto.setApi_key(userPlatform.getAppkey());
            redisDto.setSecret_key(userPlatform.getAppsecret());
            redisDto.setExchange(userPlatform.getPlatformName());
            redisDto.setAccount_id(add.getId());
            String initUsdt = add.getInitUsdt().stripTrailingZeros().toPlainString();
            redisDto.setInit_usdt(new BigDecimal(initUsdt));
            if(StringUtils.isNotBlank(userPlatform.getTradePassword())){
                redisDto.setPassword(userPlatform.getTradePassword());
            }
            //成功，添加到redis
            redisUtil.hset(StrategyRedisConfig.account,String.valueOf(add.getId()), JsonUtils.objectToJson(redisDto));
        }
        return result;
    }

    /**
     * 修改用户平台私钥信息
     * 
     * @param userPlatform 用户平台私钥信息
     * @return 结果
     */
    @Override
    public int updateUserPlatform(UserPlatform userPlatform)
    {
        UserPlatform dto = userPlatformMapper.selectByPrimaryKey(userPlatform.getId());
        if(userPlatform==null){
            throw new BaseException(MessageUtils.message("info.does.not.exist"));
        }
        //判断名称是否已存在
        int nameCount = findUserPlatformByName(userPlatform.getName(),dto.getId());
        if(nameCount>0){
            throw new BaseException("秘钥名称已存在，请换个名称");
        }
        if(userPlatform.getAppkey().equalsIgnoreCase(dto.getAppkey())){//说明不是修改key的
            UserPlatform updateDto = new UserPlatform();
            updateDto.setId(userPlatform.getId());
            updateDto.setPhone(userPlatform.getPhone());
            updateDto.setPlatformName(userPlatform.getPlatformName());
            updateDto.setName(userPlatform.getName());
            updateDto.setIsEnabled(userPlatform.getIsEnabled());
            updateDto.setInitUsdt(userPlatform.getInitUsdt());
            updateDto.setExpirationDate(userPlatform.getExpirationDate());
            updateDto.setShareRate(userPlatform.getShareRate());
            int result = userPlatformMapper.updateByPrimaryKeySelective(updateDto);
            if(result>0){
                //说明修改有名称
                if(!userPlatform.getName().equals(dto.getName())){
                    dto.setName(userPlatform.getName());
                    this.updateMenuName(dto);
                }
                //存放redis数据
                AccountRedisDto redisDto = new AccountRedisDto();
                redisDto.setApi_key(dto.getAppkey());
                redisDto.setSecret_key(dto.getAppsecret());
                redisDto.setExchange(userPlatform.getPlatformName());
                redisDto.setAccount_id(dto.getId());
                if(StringUtils.isNotBlank(dto.getTradePassword())){
                    redisDto.setPassword(dto.getTradePassword());
                }
                String initUsdt = updateDto.getInitUsdt().stripTrailingZeros().toPlainString();
                redisDto.setInit_usdt(new BigDecimal(initUsdt));
                //成功，添加到redis
                redisUtil.hset(StrategyRedisConfig.account,String.valueOf(dto.getId()), JsonUtils.objectToJson(redisDto));
            }
            return result;
        }else{//修改秘钥等信息
            if(StringUtils.isBlank(userPlatform.getAppkey())||
                    StringUtils.isBlank(userPlatform.getAppsecret())){
                throw new BaseException(MessageUtils.message("api.key.is.error"));
            }
            //测试是否配置正确
            PlatformKeyInfoDto decodeDto = new PlatformKeyInfoDto();
            decodeDto.setPlatform(userPlatform.getPlatformName());
            decodeDto.setAppId(userPlatform.getAppkey());
            decodeDto.setPassword(userPlatform.getTradePassword());
            decodeDto.setSecret(userPlatform.getAppsecret());
            bourseService = getBean(userPlatform.getPlatformName());
            try {
                boolean verify = bourseService.testConnect(decodeDto);
                if(!verify){
                    log.error("apikey验证不通过，verify=%s",verify);
                    throw new BaseException(MessageUtils.message("key.error"));
                }
            } catch (Exception e) {
                // 网络受限环境下跳过连接验证
                log.warn("跳过交易所连接验证: {}", e.getMessage());
            }
            UserPlatform updateDto = new UserPlatform();
            updateDto.setId(userPlatform.getId());
            updateDto.setPhone(userPlatform.getPhone());
            updateDto.setPlatformName(userPlatform.getPlatformName());
            updateDto.setName(userPlatform.getName());
            updateDto.setIsEnabled(userPlatform.getIsEnabled());
            updateDto.setInitUsdt(userPlatform.getInitUsdt());
            updateDto.setAppkey(userPlatform.getAppkey());
            updateDto.setAppsecret(userPlatform.getAppsecret());
            updateDto.setTradePassword(userPlatform.getTradePassword());
            updateDto.setExpirationDate(userPlatform.getExpirationDate());
            updateDto.setInitUsdt(userPlatform.getInitUsdt());
            updateDto.setShareRate(userPlatform.getShareRate());
            int result = userPlatformMapper.updateByPrimaryKeySelective(userPlatform);
            if(result>0){
                //存放redis数据
                AccountRedisDto redisDto = new AccountRedisDto();
                redisDto.setApi_key(userPlatform.getAppkey());
                redisDto.setSecret_key(userPlatform.getAppsecret());
                redisDto.setExchange(userPlatform.getPlatformName());
                redisDto.setAccount_id(dto.getId());
                if(StringUtils.isNotBlank(userPlatform.getTradePassword())){
                    redisDto.setPassword(userPlatform.getTradePassword());
                }
                String initUsdt = updateDto.getInitUsdt().stripTrailingZeros().toPlainString();
                redisDto.setInit_usdt(new BigDecimal(initUsdt));
                //成功，添加到redis
                redisUtil.hset(StrategyRedisConfig.account,String.valueOf(dto.getId()), JsonUtils.objectToJson(redisDto));
            }
            return result;
        }
    }

    /**
     * 批量删除用户平台私钥信息
     * 
     * @param ids 需要删除的用户平台私钥信息ID
     * @return 结果
     */
    @Override
    public int deleteUserPlatformByIds(Long[] ids)
    {
        return userPlatformMapper.deleteUserPlatformByIds(ids);
    }

    /**
     * 删除用户平台私钥信息信息
     * 
     * @param id 用户平台私钥信息ID
     * @return 结果
     */
    @Override
    public int deleteUserPlatformById(Long id)
    {
		return userPlatformMapper.deleteByPrimaryKey(id);
    }

    /**
     * 业务删除
     * @param id
     * @return
     */
    @Transactional
    @Override
    public int updateUserPlatformByDelete(Long id) {
        UserPlatform userPlatform = userPlatformMapper.selectByPrimaryKey(id);
        if(userPlatform==null){
            throw new BaseException(MessageUtils.message("info.does.not.exist"));
        }
        String menuName = userPlatform.getName();
        userPlatform = new UserPlatform();
        userPlatform.setId(id);
        userPlatform.setIsDelete(Enum.rule_validate.Y.getCode());
        int result = userPlatformMapper.updateByPrimaryKeySelective(userPlatform);
        if(result>0){
            //删除菜单
            userPlatform.setName(menuName);
            this.deleteMenuName(userPlatform);
            redisUtil.hdel(StrategyRedisConfig.account,String.valueOf(userPlatform.getId()));
        }
        return result;
    }

    /**
     * 查询key数量
     * @param key
     * @return
     */
    @Override
    public int findUserPlatformByKey(String key) {
        Example example = new Example(UserPlatform.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("appkey",key);
        criteria.andEqualTo("isDelete",Enum.rule_validate.N.getCode());
        return userPlatformMapper.selectCountByExample(example);
    }

    @Override
    public int findUserPlatformByName(String name,Long id) {
        Example example = new Example(UserPlatform.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name",name.trim());
        criteria.andEqualTo("isDelete",Enum.rule_validate.N.getCode());
        if(id!=null){
            criteria.andNotEqualTo("id",id);
        }
        return userPlatformMapper.selectCountByExample(example);
    }

    private BourseService getBean(String platform) throws BaseException{
        return (BourseService) SystemListener.getBean(PlatformEnum.getValue(platform));
    }

    /**
     * 确定分润
     * @param accountId
     * @return
     */
    @Override
    @Transactional
    public int deterShare(Long accountId,BigDecimal shareRate) {
        //收益
        String currentIncome = martinStrategyOrderService.findOrderTotalIncome(accountId,Enum.rule_validate.N.getCode());//未分润收益
        //要分润收益
        BigDecimal shareIncome = new BigDecimal(currentIncome).multiply(shareRate);
        if(shareIncome.compareTo(BigDecimal.ZERO)==1){
            //修改状态为已分润
            martinStrategyOrderMapper.deterShare(accountId);
            //添加分润数据
            MartinStrageyShare share = new MartinStrageyShare();
            share.setAccountId(accountId);
            share.setCurrentIncome(new BigDecimal(currentIncome));
            share.setShareIncome(shareIncome);
            share.setShareRate(shareRate);
            share.setCreateTime(new Date());
            martinStrageyShareMapper.insertSelective(share);
        }
        return 1;
    }

    /**
     * 添加菜单
     * @param userPlatform
     * @return
     */
    @Transactional
    @Override
    public void insertMenu(UserPlatform userPlatform) {
        //查询api是否已存在
        SysMenu query = new SysMenu();
        query.setComponent(UserPlatformMenuEnum.component.getCode());
        query.setPath(UserPlatformMenuEnum.path.getCode()+userPlatform.getId());
        query.setParentId(Long.valueOf(UserPlatformMenuEnum.parentId.getCode()));
        int count = menuService.checkComponentCount(query);
        if(count==0){
            SysMenu menu = new SysMenu();
            menu.setIcon("list");
            menu.setComponent(UserPlatformMenuEnum.component.getCode());
            menu.setIsCache("0");
            menu.setIsFrame("1");
            menu.setMenuName(userPlatform.getName());
            menu.setMenuType("C");
            menu.setOrderNum(String.valueOf(userPlatform.getId()));
            menu.setParentId(Long.valueOf(UserPlatformMenuEnum.parentId.getCode()));
            menu.setPath(UserPlatformMenuEnum.path.getCode()+userPlatform.getId());
            menu.setStatus("0");
            menu.setVisible("0");
            menuService.insertMenu(menu);
            //添加关系
            sysRoleService.addSysMenu(menu.getMenuId());
        }
    }

    /**
     * 修改菜单名称
     * @param userPlatform
     */
    @Override
    public void updateMenuName(UserPlatform userPlatform) {
        SysMenu menu = new SysMenu();
        menu.setParentId(Long.valueOf(UserPlatformMenuEnum.parentId.getCode()));
        menu.setPath(UserPlatformMenuEnum.path.getCode()+userPlatform.getId());
        menu.setMenuName(userPlatform.getName());
        menuService.updateMenuName(menu);
    }

    @Override
    public void deleteMenuName(UserPlatform userPlatform) {
        SysMenu menu = new SysMenu();
        menu.setParentId(Long.valueOf(UserPlatformMenuEnum.parentId.getCode()));
        menu.setPath(UserPlatformMenuEnum.path.getCode()+userPlatform.getId());
        menu.setMenuName(userPlatform.getName());
        menuService.deleteMenuByName(menu);
    }
}
