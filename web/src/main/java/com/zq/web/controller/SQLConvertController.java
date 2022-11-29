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
@RequestMapping("/sql")
public class SQLConvertController {

    @ApiOperation(value = "ORACLE SQL转换", notes = "")
    @RequestMapping(value = "/oracle-convert", method = RequestMethod.POST)
    public String oracleConvert(@RequestBody String sql) {
        return SqlConvertUtil.sqlConvert(sql, "1");
    }

    @ApiOperation(value = "MySQL SQL转换", notes = "")
    @RequestMapping(value = "/mysql-convert", method = RequestMethod.POST)
    public String mysqlConvert(@RequestBody String sql) {
        return SqlConvertUtil.sqlConvert(sql, "2");
    }

    //---------------------------------------------------------

}