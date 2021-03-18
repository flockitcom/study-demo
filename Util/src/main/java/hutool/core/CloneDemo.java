package hutool.core;

import cn.hutool.core.util.ObjectUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.Test;

import java.io.Serializable;

/**
 * 克隆
 * @author zqian
 * @date 2021/1/25
 */
public class CloneDemo {
    /**
     * 浅拷贝
     */
    @Test
    public void shallowCopy() {
        /*Food food = new Food("小狗粮");
        Dog dog = new Dog();
        dog.setName("来福")
                .setAge(5)
                .setFood(food);

        Dog clone = dog.clone();

        food.setName("大狗粮");
        System.out.println(dog);
        System.out.println(clone);*/
    }

    /**
     * 深拷贝
     */
    @Test
    public void deepCopy() {
        Food food = new Food("小狗粮");
        Dog dog = new Dog();
        dog.setName("来福")
                .setAge(5)
                .setFood(food);

        Dog dogClone = ObjectUtil.clone(dog);

        food.setName("大狗粮");
        System.out.println(dog);
        System.out.println(dogClone);
    }

}

@Data
@Accessors(chain = true)
class Dog implements Serializable {
    private static final long serialVersionUID = 5526613323650243932L;

    private String name;
    private int age;
    private Food food;
}

@Data
@Accessors(chain = true)
class Cat implements Serializable {
    private static final long serialVersionUID = 4663876540535125741L;

    private String name;
    private int age;
    private Food food;
}

@Data
@AllArgsConstructor
class Food implements Serializable {
    private static final long serialVersionUID = -7237454215776882192L;

    private String name;
}
