package com.zq.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import util.SqlConvertUtil;

/**
 * 挂号收费 控制层
 *
 * @author yiyaninfo-autocode-v1.3    zhangqian
 */
@CrossOrigin
@Api(tags = "SQL转换 数据接口")
@RestController
@RequestMapping("/test")
public class TestController {

    @ApiOperation(value = "test", notes = "")
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String listPrintVoucher(@RequestBody String s) {
        return SqlConvertUtil.sqlConvert(s);
    }

    //---------------------------------------------------------

}