package com.zq;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zq.bean.User;
import com.zq.mapper.UserMapper;
import date.LocalDateTime.DateTimeUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class MyTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void test00() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        LocalDateTime date1 = DateTimeUtil.getDateByString("2020-09-04 17:33:03", null);
        LocalDateTime date2 = DateTimeUtil.getDateByString("2020-09-04 17:39:14", null);
        wrapper.between("create_at", date1, date2);
        List<User> userList = userMapper.selectList(wrapper);
    }

    @Test
    public void test01() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.apply("date_format(create_at,'%Y-%m-%d') = {0}","2020-09-04")
                .inSql("id", "select id from user where age >= 20");
        List<User> userList = userMapper.selectList(wrapper);
    }

    /**
     * nested相当于()
     */
    @Test
    public void test02() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.nested(w->w.lt("age", 20).or().isNotNull("email"))
                .like("name", "张");
        List<User> userList = userMapper.selectList(wrapper);
    }

    /**
     * 不显示哪一列
     */
    @Test
    public void test03() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select(User.class, i->!i.getColumn().equals("update_at"));
        List<User> userList = userMapper.selectList(wrapper);
    }

    /**
     * lambda条件构造器(三种方式)
     */
    @Test
    public void test04() {
//        LambdaQueryWrapper<User> wrapper = new QueryWrapper<User>().lambda();
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class);
        wrapper.like(User::getAge,18);
        List<User> userList = userMapper.selectList(wrapper);
    }

    /**
     * 自定义sql
     */
//    @Test
//    public void test05() {
//        LambdaQueryWrapper<User> wrapper = Wrappers.lambdaQuery(User.class);
//        wrapper.eq(User::getId,3);
//        int i = userMapper.deleteReal(wrapper);
//    }

    /**
     * 分页
     */
    @Test
    public void test06() {

    }


}
