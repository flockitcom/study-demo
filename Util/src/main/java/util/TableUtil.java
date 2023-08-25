package util;

public class TableUtil {
    public static void main(String[] args) {

        String s = "表英文名\t表中文名\n" +
                "字段\t含义\t类型\t是否必填\t备注";
        createTable(s);

    }

    public static void createTable(String table) {

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

            handleColumnSqlCode(column, columnBuilder);

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
     */
    public static void handleColumnSqlCode(String[] column, StringBuilder stringBuilder) {
        stringBuilder.append(column[0]).append("\t").append(column[2]);
        if (column.length > 3 && "是".equals(column[3])) {
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

        commentBuilder.append("comment on table ").append(tableName);

        if (column != null) {
            commentBuilder.append(".").append(column[0]).append(" is '").append(columnComment(column)).append("\n");
        } else {
            commentBuilder.append(" is ").append(tableComment).append("\n");
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
            return column[1] + "\t" + column[4] + ";";
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
        if (column.length < 5 || !column[4].contains("PK")) {
            return;
        }

        if (pkBuilder.length() < 1) {
            pkBuilder.append(column[0]);
        } else {
            pkBuilder.append(",").append(column[0]);
        }
    }
}