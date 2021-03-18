package hutool.core;

import cn.hutool.core.util.RuntimeUtil;

/**
 * 剪贴板工具
 * @author zqian
 * @date 2021/1/26
 */
public class ClipboardUtilDemo {
    public static void main(String[] args) {
        String str = RuntimeUtil.execForStr("ipconfig");
        System.out.println(str);
    }
}
