package util.serialize;

import base.Student;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * gson
 *
 * @author zhangqian
 */
public class GsonUtil {
    /**
     * gson
     */
    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public static void main(String[] args) {
        Student student = new Student("张三", "18");
        // 序列化
        String json = gson.toJson(student);
        System.out.println(json);
        // 反序列化
        Student object = gson.fromJson(json, Student.class);
    }


}
