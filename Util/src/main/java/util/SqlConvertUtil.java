package util;

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
        int paramtersIndex = paramtersStr.indexOf(PARAMTERS) + PARAMTERS.length() + 1;
        paramtersStr = paramtersStr.substring(paramtersIndex);
        String[] parArr = paramtersStr.split(",");
        for (int i = 0; i < parArr.length; i++) {
            int leftBrackets = parArr[i].indexOf("(");
            int rightBrackets = parArr[i].indexOf(")");
            String type = parArr[i].substring(leftBrackets + 1, rightBrackets);
            String par = parArr[i].substring(0, leftBrackets).trim();

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

        //4.获取sql语句
        int preparingIndex = preparingStr.indexOf(PREPARING) + PREPARING.length() + 1;
        preparingStr = preparingStr.substring(preparingIndex);

        //5.处理语句
        for (String s : parArr) {
            int i = preparingStr.indexOf("?");
            preparingStr = preparingStr.substring(0, i) + s + preparingStr.substring(i + 1);
        }
        System.out.println(preparingStr);
        return preparingStr;
    }
}
