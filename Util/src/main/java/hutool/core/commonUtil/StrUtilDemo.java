import cn.hutool.core.util.StrUtil;

/**
 * 字符串工具类
 * @author zqian
 * @date 2021/1/25
 */
public class StrUtilDemo {
    public static void main(String[] args) {
        String template = "{}爱{}，就像{}爱大米";
        String str = StrUtil.format(template, "我", "你","还好"); //str -> 我爱你，就像老鼠爱大米
        System.out.println(str);

        int b = 97;
        char b1 = (char) b;
        System.out.println(b1);
    }
}
