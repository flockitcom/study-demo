package web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * 挂号收费 控制层
 *
 * @author yiyaninfo-autocode-v1.3    zhangqian
 */
@CrossOrigin
@Api(tags = "test2 数据接口")
@RestController
@RequestMapping("/test")
public class Test2Controller {

    @ApiOperation(value = "测试", notes = "")
    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public String test() {
        return UUID.randomUUID().toString();
    }

    //---------------------------------------------------------

}