package basic;

public class T07_TryCatch {
    public static void main(String[] args) {
        new Thread(()-> System.out.println("1111"),"thread01").start();
        new Thread(()-> System.out.println("2222"),"thread02").start();
        System.out.println("33333333333");
        System.out.println("44444444");
    }
}
