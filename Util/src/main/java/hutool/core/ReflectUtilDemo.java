package hutool.core;

import cn.hutool.core.util.ReflectUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * 反射
 * @author zqian
 * @date 2021/1/25
 */
public class ReflectUtilDemo {
    public static void main(String[] args) {
        Method getName = ReflectUtil.getMethod(Person.class, "getName");
        System.out.println(getName);

        Person instance = ReflectUtil.newInstance(Person.class, "张三", 11);
        System.out.println(instance);
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person {
    private String name;
    private Integer age;
}
