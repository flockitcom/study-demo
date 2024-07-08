package util;

import org.apache.commons.lang3.StringUtils;

/**
 * sql转换
 *
 * @author zhangqian
 */
public class SqlConvertUtil {

    public static final String PREPARING = "Preparing:";
    public static final String PARAMTERS = "Parameters:";

    public static final String TYPE_STRING = "String";
    public static final String TYPE_DATE = "Timestamp";

    public static final String DATABASE_TYPE_ORACLE = "1";
    public static final String DATABASE_TYPE_MYSQL = "2";

    public static String sqlConvert(String sql, String databaseType) {
        sql = sql.trim();
        //1.分行
        String[] split = sql.split("\n");
        int paramters = -1;
        for (int i = 0; i < split.length; i++) {
            if (split[i].contains(PARAMTERS)) {
                paramters = i;
            }
        }
        if (paramters == -1) {
            throw new RuntimeException("语句异常");
        }

        //2.sql和变量分开
        String preparingStr = null;
        for (int i = 0; i < paramters; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(split[i]);
            preparingStr = sb.toString();
        }
        String paramtersStr = null;
        for (int i = paramters; i < split.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(split[i]);
            paramtersStr = sb.toString();
        }

        //3.获取变量列表
        int paramtersIndex = Math.min(paramtersStr.indexOf(PARAMTERS) + PARAMTERS.length() + 1, paramtersStr.length());
        paramtersStr = paramtersStr.substring(paramtersIndex);

        //4.获取sql语句
        int preparingIndex = preparingStr.indexOf(PREPARING) + PREPARING.length() + 1;
        preparingStr = preparingStr.substring(preparingIndex);
        // 3.1 判断空参数
        if (StringUtils.isBlank(paramtersStr)) {
            return preparingStr;
        }
        String[] parArr = paramtersStr.split("\\),");
        for (int i = 0; i < parArr.length; i++) {
            int leftBrackets = parArr[i].indexOf("(");
            String type = parArr[i].substring(leftBrackets + 1);
            String par = parArr[i].substring(0, leftBrackets).trim();
            // 最后一个去掉右括号
            if (i == parArr.length - 1) {
                type = type.substring(0, type.length() - 1);
            }

            if (TYPE_DATE.equals(type) && DATABASE_TYPE_ORACLE.equals(databaseType)) {
                parArr[i] = String.format("TO_DATE('%s', 'yyyy-mm-dd hh24:mi:ss')", par.substring(0, par.lastIndexOf(".")));
            } else if (TYPE_DATE.equals(type) && DATABASE_TYPE_MYSQL.equals(databaseType)) {
                parArr[i] = "'" + par.substring(0, par.lastIndexOf(".")) + "'";
            } else if (TYPE_STRING.equals(type)) {
                parArr[i] = "'" + par + "'";
            } else {
                parArr[i] = par;
            }
        }

        //5.处理语句
        for (String s : parArr) {
            int i = findFirstUnquotedQuestionMark(preparingStr);
            preparingStr = preparingStr.substring(0, i) + s + preparingStr.substring(i + 1);
        }
        System.out.println(preparingStr);
        return preparingStr;
    }

    /**
     * 查找字符串中第一个不在单引号内的?的下标
     *
     * @param str 输入字符串
     * @return 第一个不在单引号内的?的下标，如果未找到则返回-1
     */
    public static int findFirstUnquotedQuestionMark(String str) {
        boolean inSingleQuote = false; // 用于跟踪当前字符是否在单引号内

        // 遍历字符串中的每个字符
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            if (currentChar == '\'') {
                // 遇到单引号字符时切换inSingleQuote的值
                inSingleQuote = !inSingleQuote;
            } else if (currentChar == '?' && !inSingleQuote) {
                // 如果遇到字符?且当前不在单引号内，则返回当前下标
                return i;
            }
        }

        // 如果遍历完字符串后未找到符合条件的?，则返回-1
        return -1;
    }
}
