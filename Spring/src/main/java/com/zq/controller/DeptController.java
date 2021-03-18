package com.zq.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zq.dao.DeptDao;
import com.zq.entity.Dept;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @author zqian
 * @date 2020/12/15
 */
@RestController
@RequestMapping("dept")
public class DeptController {
    @Resource
    private DeptDao deptDao;

    @GetMapping("get/{id}")
    public Dept get(@PathVariable Integer id) {
        return deptDao.getById(1);
    }

    @PostMapping("create")
    public Dept create(@RequestBody Dept dept) {
        return dept;
    }

}
