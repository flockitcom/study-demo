package util;

public class TableUtil {
    public static void main(String[] args) {

        String s = "E_FIN_DC_DEF_RECEIVE\t优惠券定义领用\n" +
                "语雀复制语句";
        createTable(s, false);

    }

    /**
     * 创建表sql语句
     *
     * @param table        表结构语句
     * @param oldTableFlag 是否为旧表结构 true - 是，false - 否
     */
    public static void createTable(String table, boolean oldTableFlag) {

        String[] tableSplit = table.split("\n");
        String[] tableInfo = tableSplit[0].split("\t");

        // 获取表名和表名注释
        String tableName = tableInfo[0].toUpperCase();
        String tableComment = tableInfo[1];

        StringBuilder columnBuilder = new StringBuilder();
        StringBuilder commentBuilder = new StringBuilder();
        StringBuilder pkBuilder = new StringBuilder();

        columnBuilder.append("create table ").append(tableName).append("\n(\n");
        handleCommentSqlCode(tableName, tableComment, null, commentBuilder);

        for (int i = 1; i < tableSplit.length; i++) {

            String[] column = tableSplit[i].split("\t");
            if (column.length < 1) {
                continue;
            }

            // 处理字段sql语句
            handleColumnSqlCode(column, columnBuilder, oldTableFlag);

            // 处理主键
            handlePrimaryKey(column, pkBuilder);

            // 拼接字段注释sql语句
            handleCommentSqlCode(tableName, null, column, commentBuilder);
        }

        columnBuilder.append("constraint PK_").append(tableName).append(" primary key (").append(pkBuilder).append(")\n" +
                "         using index tablespace ts_e_pindex\n" +
                ");");

        System.out.println(columnBuilder);
        System.out.println(commentBuilder);

    }

    /**
     * 处理字段Sql语句
     *
     * @param column        字段
     * @param stringBuilder 字段拼接
     * @param oldTableFlag  旧表标志
     */
    public static void handleColumnSqlCode(String[] column, StringBuilder stringBuilder, boolean oldTableFlag) {
        stringBuilder.append(column[0]).append("\t").append(oldTableFlag ? column[3] : column[2]);
        if (column.length > 3 && "是".equals(oldTableFlag ? column[2] : column[3])) {
            stringBuilder.append("\t").append("not null");
        }
        stringBuilder.append(",\n");
    }

    /**
     * 处理字段备注Sql语句
     *
     * @param tableName      表名
     * @param column         字段
     * @param commentBuilder 备注
     */
    public static void handleCommentSqlCode(String tableName, String tableComment, String[] column, StringBuilder commentBuilder) {
        if (column != null) {
            commentBuilder.append("comment on column ").append(tableName);
            commentBuilder.append(".").append(column[0]).append(" is '").append(columnComment(column)).append("\n");
        } else {
            commentBuilder.append("comment on table ").append(tableName);
            commentBuilder.append(" is '").append(tableComment).append("';").append("\n");
        }
    }

    /**
     * 获取单个字段的注释
     *
     * @param column 字段
     * @return 注释
     */
    public static String columnComment(String[] column) {
        if (column.length > 4 && column[4].contains("enum")) {
            return column[1] + " " + column[4] + "';";
        }
        return column[1] + "';";
    }

    /**
     * 处理主键
     *
     * @param column    字段
     * @param pkBuilder pkBuilder
     */
    public static void handlePrimaryKey(String[] column, StringBuilder pkBuilder) {
        if (column.length < 5) {
            return;
        }
        if (!column[4].contains("pk") && !column[4].contains("PK")
                && !column[4].contains("Pk") && !column[4].contains("pK")) {
            return;
        }

        if (pkBuilder.length() < 1) {
            pkBuilder.append(column[0]);
        } else {
            pkBuilder.append(",").append(column[0]);
        }
    }
}