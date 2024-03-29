package util.serialize;

import base.Student;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class SerializableXmlUtil {
    /**
     * 把XML数据反序列化为 JavaBean
     */
    public static <T> T parseXML(String xmlText) {
        XMLDecoder decoder = null;
        try {
            ByteArrayInputStream in =
                    new ByteArrayInputStream(xmlText.getBytes());
            decoder = new XMLDecoder(new BufferedInputStream(in));
            return (T) decoder.readObject();
        } finally {
            decoder.close();
        }
    }

    /**
     * 把JavaBean序列化为XML数据
     */
    public static <T> String formatXML(T entity) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(out));
        encoder.writeObject(entity);
        encoder.close();
        return out.toString();
    }

    public static void main(String[] args) throws Exception {
        Student student = new Student("张三", "18");
        String xmlText = formatXML(student);
        System.out.println("序列化为XML数据:\n" + xmlText);
        student = parseXML(xmlText);
        System.out.println("反序列化生成JavaBean:\n" + student);
    }
}