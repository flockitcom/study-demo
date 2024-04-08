package com.zq.reflection;

import lombok.Data;
import lombok.ToString;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射
 *
 * @author zqian
 * @date 2021/1/15
 */
public class ReflectionDemo {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        //三种获取Class对象的方式
        Class aClass1 = Class.forName("com.zq.reflection.Student");//通过Class类的静态方法获取
        Class aClass2 = Student.class;//通过类的class属性
        Class aClass3 = new Student().getClass();//通过类的实例对象的getClass()方法获取

        //获取父类的Class对象
        Class superclass = aClass1.getSuperclass();
        System.out.println(superclass);

        Class userClass = User.class;
        User user = (User) userClass.newInstance();
        Method setName = userClass.getDeclaredMethod("setName", String.class);

        setName.invoke(user, "张三");
        System.out.println(user);

    }
}

@Data
class Person {
    private String name;
    private Integer age;
}

class Student extends Person {
    @Override
    public void setName(String name) {
        super.setName("学生");
    }
}

class Teacher extends Person {
    @Override
    public void setName(String name) {
        super.setName("老师");
    }
}

@ToString
class User {
    private String name;

    public void setName(String name) {
        this.name = name;
    }
}