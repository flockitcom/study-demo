package utils;

import java.math.BigDecimal;

//计算工具类
public class OperationUtil {

    /**
     * 精确的加法运算.
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的乘法运算，并对运算结果截位.
     *
     * @param scale 运算结果小数后精确的位数,默认两位
     */
    public static double multiply(double v1, double v2, Integer scale) {
        if (null == scale) {
            scale = 2;
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        //四舍五入
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 保留两位小数
     *
     * @param numStr
     */
    public static String retainTwoNum(String numStr) {
        BigDecimal b = BigDecimal.ZERO;
        if (null != numStr) {
            double v = Double.parseDouble(numStr);
            b = new BigDecimal(v);
        }
        //四舍五入
        return String.valueOf(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
    }

}
