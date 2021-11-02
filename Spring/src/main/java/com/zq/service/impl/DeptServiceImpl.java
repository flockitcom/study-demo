package com.zq.service.impl;

import com.zq.dao.DeptDao;
import com.zq.dao.LogsDao;
import com.zq.dao.TouristDao;
import com.zq.entity.Dept;
import com.zq.entity.Logs;
import com.zq.entity.Tourist;
import com.zq.mapper.DeptMapper;
import com.zq.service.DeptService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author zqian
 * @date 2020/12/15
 */
@Service
public class DeptServiceImpl implements DeptService {

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private DeptServiceImpl deptServiceImpl;

    @Resource
    private DeptDao deptDao;
    @Resource
    private LogsDao logsDao;
    @Resource
    private DeptMapper deptMapper;

    @Override
    @Transactional
    public void create(String name) {
        createDept(name);
        try {
            deptServiceImpl.createLogs();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createDept(String name) {
        Dept dept = new Dept();
        dept.setName(name);
        deptDao.save(dept);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void createLogs() {
        Logs logs = new Logs();
        logs.setNum(3);
        logsDao.save(logs);

        throw new RuntimeException("invalid status");
//        throw new MyException(MyExceptionEnum.DEFAULT_ERROR);
    }

    @Override
    public void bean() {
//        TouristDao touristDao = (TouristDao) applicationContext.getBean("touristDaoImpl");
//        Tourist byId = touristDao.getById(1);
//        System.out.println(byId);
//        Dept dept = deptMapper.getByIdAge(4, 18);
        Dept dept = deptMapper.selectById(4);
        System.out.println(dept);
    }
}
