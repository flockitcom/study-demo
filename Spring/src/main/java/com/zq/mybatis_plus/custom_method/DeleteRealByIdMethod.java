package com.zq.mybatis_plus.custom_method;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

public class DeleteRealByIdMethod extends AbstractMethod {
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        //执行的sql
        String sql = "delete from " + tableInfo.getTableName();
        //mapper接口方法名
        String method = "deleteRealById";
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
        return addDeleteMappedStatement(mapperClass, method, sqlSource);
    }
}
