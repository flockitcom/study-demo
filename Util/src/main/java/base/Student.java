package base;

import org.msgpack.annotation.Message;

import java.io.Serializable;

/**
 * 学生
 */
@Message
public class Student implements Serializable {
    private String name;
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Student() {
    }

    public Student(String name, String age) {
        this.name = name;
        this.age = age;
    }
}