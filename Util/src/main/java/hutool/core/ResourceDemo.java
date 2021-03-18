package hutool.core;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.FileResource;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.ResourceUtil;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * 资源
 * @author zqian
 * @date 2021/1/25
 */
public class ResourceDemo {
    /**
     * 获取resource基本属性
     */
    @Test
    public void getResource() {
        FileResource fileResource = new FileResource("test.txt");
        String name = fileResource.getName();
        URL url = fileResource.getUrl();
        InputStream stream = fileResource.getStream();
        BufferedReader reader = fileResource.getReader(StandardCharsets.UTF_8);
        String readStr = fileResource.readStr(StandardCharsets.UTF_8);
        System.out.println();
    }

    /**
     * 资源工具-ResourceUtil.md
     */
    @Test
    public void useResourceUtil() {
        Resource resource = ResourceUtil.getResourceObj("test.txt");
        String name = resource.getName();
        URL url = resource.getUrl();
        InputStream stream = resource.getStream();
        BufferedReader reader = resource.getReader(StandardCharsets.UTF_8);
        String readStr = resource.readStr(StandardCharsets.UTF_8);
        System.out.println();

        String s = ResourceUtil.readUtf8Str("test.txt");
        System.out.println(s);
    }

    /**
     * ClassPath资源访问-ClassPathResource
     */
    @Test
    public void useClassPathResourceUtil() {

    }

    public static void main(String[] args) throws IOException {
        ClassPathResource resource = new ClassPathResource("mybatis.properties");
        Properties properties = new Properties();
        properties.load(resource.getStream());
        System.out.println(properties);
    }
}
