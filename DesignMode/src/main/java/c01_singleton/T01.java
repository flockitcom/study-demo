package c01_singleton;

/**
 * 单例模式 饿汉式(推荐使用)
 */
public class T01 {

    private static final T01 INSTANCE = new T01();

    private T01() {
    }

    public static T01 getInstance(){
        return INSTANCE;
    }

    public void m(){
        System.out.println("m");
    }

    public static void main(String[] args) {
        T01 t1 = T01.getInstance();
        T01 t2 = T01.getInstance();
        System.out.println(t1 == t2);
    }
}
