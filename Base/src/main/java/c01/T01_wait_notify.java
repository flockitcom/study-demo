package c01;

public class T01_wait_notify {
    static char[] A = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    static final Object o = new Object();

    public static void main(String[] args) {
        Thread m1 = new Thread(() -> {
            synchronized (o) {
                for (char a : A) {
                    System.out.print(a);
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    o.notify();
                }
            }
        }, "m1");

        Thread m2 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (o) {
                for (int i = 1; i <= 26; i++) {
                    System.out.println(i);
                    try {
                        o.notify();
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "m2");

        m2.start();
        m1.start();
    }
}
