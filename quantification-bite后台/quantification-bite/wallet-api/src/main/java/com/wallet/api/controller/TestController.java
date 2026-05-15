package com.wallet.api.controller;

import com.wallet.common.core.controller.BaseController;
import com.wallet.common.core.domain.AjaxResult;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@Api(value = "测试",tags = "测试")
@RestController
@RequestMapping("api/website")
public class TestController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    @ApiOperation(value = "测试")
    @ApiImplicitParams({@ApiImplicitParam(name = "name",value ="名称",required = true,dataType = "String",paramType = "query")})
    @PostMapping("/test")
    public AjaxResult addContact(@RequestParam(value = "name") String name){
        return AjaxResult.success(name);
    }
}
