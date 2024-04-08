package util.serialize;

import base.Student;
import com.google.common.collect.Lists;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;

import java.io.IOException;
import java.util.List;

/**
 * MessagePack 序列化工具类
 *
 * @author zhangqian
 */
public class MessagePackUtil {
    public static void main(String[] args) throws IOException {
        List<Student> src = Lists.newArrayList(
                new Student("张三", "18"),
                new Student("李四", "20")
        );

        // 序列化
        MessagePack msgpack = new MessagePack();
        byte[] raw = msgpack.write(src);

        // 反序列化 directly using a template
        List<Student> dst1 = msgpack.read(raw, Templates.tList(msgpack.lookup(Student.class)));
        System.out.println(dst1);
    }
}
