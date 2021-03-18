package hutool.core;

import cn.hutool.core.io.file.FileAppender;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.ObjectUtil;
import org.junit.Test;

import java.io.File;

/**
 * 文件类型判断
 * @author zqian
 * @date 2021/1/25
 */
public class FileUtilDemo {

    @Test
    public void read(){
        File file = new File("src/main/resources/test.txt");
        FileReader fileReader = new FileReader(file);
        String result = fileReader.readString();
        System.out.println(result);
    }

    @Test
    public void write(){
        File file = new File("src/main/resources/test.txt");
        FileWriter writer = new FileWriter(file);
        writer.write("test");
        writer.write("dfgdsg");
        writer.write("sdgsdg");
    }

    @Test
    public void append(){
        File file = new File("src/main/resources/test.txt");
        FileAppender appender = new FileAppender(file, 16, true);
        appender.append("123");
        appender.append("abc");
        appender.append("xyz");

        appender.flush();
        String s = appender.toString();
        System.out.println(s);
    }
}
