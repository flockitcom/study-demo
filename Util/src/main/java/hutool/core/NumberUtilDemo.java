package hutool.core;

import cn.hutool.core.net.NetUtil;

public class NumberUtilDemo {
    public static void main(String[] args) {
        String ip = "127.0.0.1";
        long iplong = 2130706433L;

        //根据long值获取ip v4地址
        ip = NetUtil.longToIpv4(iplong);


        //根据ip地址计算出long型的数据
        iplong = NetUtil.ipv4ToLong(ip);

        //检测本地端口可用性
        boolean result = NetUtil.isUsableLocalPort(6379);

        //是否为有效的端口
        result = NetUtil.isValidPort(6379);

        //隐藏掉IP地址
        String resultStr = NetUtil.hideIpPart(ip);
    }
}
