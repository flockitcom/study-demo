package hutool.core;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.io.resource.FileResource;
import org.junit.Test;

import java.io.File;

/**
 * 文件名
 * @author zqian
 * @date 2021/1/25
 */
public class FileNameUtilDemo {
    /**
     * 获取文件名,扩展名
     */
    @Test
    public void getFileName(){
        File file = FileUtil.file("src/main/resources/test.txt");
        String name = FileNameUtil.getName(file);//test.txt
        String mainName = FileNameUtil.mainName(file);//test
        String extName = FileNameUtil.extName(file);//txt
    }
}
