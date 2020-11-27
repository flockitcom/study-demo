package com.zq;

import cn.hutool.crypto.SecureUtil;
import org.junit.Test;

/**
 * md5测试
 * @author zqian
 * @date 2020/11/27
 */
public class MD5Test {
    @Test
    public void test01(){
        String enc = SecureUtil.md5("safsagdsgffggs");
        System.out.println(enc);
    }
}
