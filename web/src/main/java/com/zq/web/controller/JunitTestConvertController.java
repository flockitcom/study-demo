package com.zq.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * junit测试转换
 */
@CrossOrigin
@Api(tags = "junit测试转换 数据接口")
@RestController
@RequestMapping("/junit-test-convert")
public class JunitTestConvertController {

    @ApiOperation(value = "ORACLE SQL转换", notes = "")
    @RequestMapping(value = "/junit-test-convert", method = RequestMethod.POST)
    public String junitTestConvert(@RequestBody String str) {
        return junitTestConvertStr(str);
    }

    /**
     * junit测试转换
     */
    private String junitTestConvertStr(String str) {
        StringBuilder sb = new StringBuilder();

        // 清空泛型
        Pattern pattern = Pattern.compile("<[^<>]*>", Pattern.DOTALL);
        str = replace(str, pattern, e -> "");

        String[] s = str.split(",");
        for (int i = 0; i < s.length; i++) {
            s[i] = s[i].replaceAll("\n", " ");
            String[] s1 = s[i].trim().split(" ");
            // 泛型
            String[] s2 = s1[0].split("<");
            if (i == s.length - 1) {
                sb.append(s2[0]).append(".class");
            } else {
                sb.append(s2[0]).append(".class,");
            }
        }
        return sb.toString();
    }

    /**
     * 替换字符串中符合正则表达式的片段
     *
     * @param string            原始字符串
     * @param pattern           确定需要被替换片段的正则表达式Pattern
     * @param newStringFunction 构造每个片段新值的函数，入参为上述Pattern匹配到的Matcher
     * @return 替换后的字符串
     */
    public static String replace(String string, Pattern pattern, Function<Matcher, String> newStringFunction) {
        // 原始字符串为空时直接返回
        if (StringUtils.isBlank(string)) {
            return string;
        }
        // 扫描进度设置为0
        int progress = 0;
        // 替换后的字符串
        StringBuilder sb = new StringBuilder();
        // 匹配正则
        Matcher mt = pattern.matcher(string);
        // 扫描正则
        while (mt.find()) {
            // 将扫描进度与新扫描到的位置中间的所有内容添加到替换后的字符串中
            sb.append(string, progress, mt.start());
            // 执行函数，获取正则表达式替换后的值
            String newString = newStringFunction.apply(mt);
            // 将该值放入替换后的字符串中
            sb.append(newString);
            // 更新进度
            progress = mt.end();
        }
        // 将字符串最后一段放入替换后的字符串中
        sb.append(string.substring(progress));
        return sb.toString();
    }
}
