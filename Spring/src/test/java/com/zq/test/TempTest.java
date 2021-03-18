package com.zq.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zq.Spring;
import com.zq.dao.DeptDao;
import com.zq.entity.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @author zqian
 * @date 2021/1/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.config.location=classpath:application.yml", classes = Spring.class)
public class TempTest {

    @Resource
    private DeptDao deptDao;

    @Test
    public void testNull() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Dept dept = deptDao.getById(1);
        String s = objectMapper.writeValueAsString(dept);
        System.out.println(s);
        Dept dept1 = objectMapper.readValue(s, Dept.class);
        System.out.println(dept1);
    }

    @Test
    public void testDate() throws JsonProcessingException {
        LocalDate date = LocalDate.of(2018, 5, 5);

        ObjectMapper objectMapper = new ObjectMapper();
        String dateStr = objectMapper.writeValueAsString(date);
        System.out.println(dateStr);
    }
}
