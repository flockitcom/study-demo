package c01_singleton;

/**
 * 单例模式  枚举
 */
public enum T08 {

    /**
     * 属性
     */
    INSTANCE;

    public void m() {
        System.out.println("哈哈");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                T08.INSTANCE.m();
            }).start();
        }
    }
}
