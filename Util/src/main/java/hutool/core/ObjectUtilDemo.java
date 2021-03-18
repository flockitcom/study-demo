package hutool.core;

import cn.hutool.core.util.ObjectUtil;

/**
 * 对象工具
 * @author zqian
 * @date 2021/1/26
 */
public class ObjectUtilDemo {
    public static void main(String[] args) {
        String[] s = new String[]{"a","b","c"};
        boolean a = ObjectUtil.contains(s, "a");
        System.out.println(a);

        ObjectUtil.isEmpty(s);
    }
}
