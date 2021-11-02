package com.zq.aop;

import com.zq.util.SqlUtils;
import org.apache.ibatis.session.SqlSessionFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Aspect
@Component
public class MybatisSqlAop {

    @Value("${printSql.active}")
    private Boolean PRINT_SQL_ACTIVE;

    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Pointcut(value = "@annotation(com.zq.annotation.PrintSql)")
    public void print() {
    }

    @Around("print()")
    public Object recordSysLog(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        if (PRINT_SQL_ACTIVE) {
            String sql = SqlUtils.getMybatisSql(pjp, sqlSessionFactory);
            System.out.println(sql);
        }
        return result;
    }

}