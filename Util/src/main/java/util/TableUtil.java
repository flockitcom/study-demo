package util;

public class TableUtil {
    public static void main(String[] args) {

        String s = "E_FIN_DC_EMPLOYEE_RECEIVE\t优惠券员工领用\n" +
                "discount_coupon_def_id\t优惠券定义ID\tnumber(8)\t是\tPK\n" +
                "org_id\t机构ID\tnumber(8)\t是\t\n" +
                "uid_create\t创建者ID\tnumber(8)\t是\t\n" +
                "uname_create\t创建者\tvarchar2(50)\t是\t\n" +
                "gmt_create\t创建时间\tdate\t是\t\n" +
                "uid_modify\t修改者ID\tnumber(8)\t是\t\n" +
                "uname_modify\t修改者\tvarchar2(50)\t是\t\n" +
                "gmt_modify\t修改时间\tdate\t是\t\n" +
                "discount_coupon_name\t优惠券名称\tvarchar2(50)\t是\t\n" +
                "discount_coupon_carrier\t优惠券载体\tvarchar2(1)\t是\tenum 1-ELECTRONIC-电子,2-PAPER-纸质\n" +
                "discount_coupon_type\t优惠券类型\tvarchar2(1)\t是\tenum 1-MONEY-金额,2-DISCOUNT-折扣,3-SCENES-场景\n" +
                "discount_money_ratio\t折扣金额/比例\tnumber(12,4)\t是\tdefault 0\n" +
                "min_consume_money\t最低消费金额\tnumber(10,2)\t是\t\n" +
                "gmt_start\t开始时间\tdate\t是\t\n" +
                "gmt_stop\t结束时间\tdate\t是\t\n" +
                "valid_day\t有效天数\tnumber(8)\t是\t\n" +
                "distribute_way\t发放方式\tvarchar2(2)\t是\tenum 01-AUTO_REGISTER_USER-自动发放（注册用户）,02-AUTO_DRAINAGE_LIVE_CODE-自动发放（引流活码）,03-AUTO_FISSION-自动发放（裂变）,04-MANUAL_USER-手动发放（用户）,05-MANUAL_EMPLOYEE-手动发放（员工）\n" +
                "distribute_scenes\t发放场景\tvarchar2(1)\t是\tenum 1-UNIVERSAL-通用,2-ON_LINE-线上,3-OFF_LINE-线下\n" +
                "total_distribute_quantity\t总发放数量\tnumber(8)\t是\t\n" +
                "already_distribute_quantity\t已发放数量\tnumber(8)\t是\t\n" +
                "individual_limit_quantity\t单人限领数量\tnumber(8)\t是\t\n" +
                "stackable_type\t叠加类型\tvarchar2(1)\t是\tenum 0-NOT-不可叠加,1-THIS_COUPON-本券可叠加,3-DIFFERENT_COUPONS-不同券可叠加\n" +
                "stackable_limit_quantity\t叠加限量\tnumber(8)\t是\t\n" +
                "refund_return\t退款后可退回\tvarchar2(1)\t是\tenum 1-ON-是,0-OFF-否\n" +
                "unconditional_use\t无条件使用\tvarchar2(1)\t是\tenum 1-ON-是,0-OFF-否\n" +
                "record_status\t状态标志\tvarchar2(1)\t是\tenum 1-ON-在用,0-OFF-暂停\n" +
                "record_remark\t备注\tvarchar2(200)\t\t\n";
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