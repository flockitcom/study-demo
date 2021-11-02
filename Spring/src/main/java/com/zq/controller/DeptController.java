package com.zq.controller;

import com.zq.annotation.login.LoginRequired;
import com.zq.controller.base.BaseController;
import com.zq.response.MyResponse;
import com.zq.service.DeptService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zqian
 * @date 2020/12/15
 */
@RestController
@RequestMapping("dept")
public class DeptController extends BaseController {
    @Resource
    private DeptService deptService;

    @GetMapping("create/{name}")
    public MyResponse create(@PathVariable String name) {
        deptService.create(name);
        return MyResponse.returnSuccess();
    }

    @GetMapping("bean")
    public MyResponse bean() {
        deptService.bean();
        return MyResponse.returnSuccess();
    }

    @GetMapping("login")
    public long login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        if (username.equals("admin") && password.equals("admin")) {
            session.setAttribute("currentUser", 1L);
            return 1L;
        }
        return 0L;
    }

    @GetMapping("user")
    public String right(@LoginRequired Long userId) {
        return "当前用户Id：" + userId;
    }

    @GetMapping("test")
    public String test(Map<String, Object>[] map) {
        for (Map<String, Object> stringObjectMap : map) {
            System.out.println(stringObjectMap.get("key") + ":" + stringObjectMap.get("value"));
        }
        return null;
    }

    @GetMapping("test1")
    public Map[] test1(String[] map) {
        Map[] maps = Arrays.stream(map).map(e -> gson.fromJson(e, HashMap.class)).toArray(HashMap[]::new);
        return maps;
    }

    @GetMapping("test2")
    public Map[] test1(String map) {
        Map[] strings = gson.fromJson(map, HashMap[].class);
//        System.out.println(Arrays.toString(strings));
//        Map[] maps = Arrays.stream(strings).map(e -> gson.fromJson(e, HashMap.class)).toArray(HashMap[]::new);
        return strings;
    }
}


