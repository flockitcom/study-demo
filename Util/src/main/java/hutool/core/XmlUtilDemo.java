package hutool.core;

import cn.hutool.core.util.XmlUtil;
import org.w3c.dom.Document;

import javax.xml.xpath.XPathConstants;
import java.io.File;

/**
 * Xml
 * @author zqian
 * @date 2021/1/25
 */
public class XmlUtilDemo {
    public static void main(String[] args) {
        File file = new File("Util/src/main/resources/xmlFile.xml");
        Document docResult = XmlUtil.readXML(file);
        //结果为“ok”
        Object value = XmlUtil.getByXPath("/returnsms/message", docResult, XPathConstants.STRING);
        System.out.println(value);
    }
}
