package collection;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ToArray {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>(2);
        list.add("guan");
        list.add("bao");
        for (String item : list) {
            if ("guan".equals(item)) {
                list.remove(item);
            }
        }
        for (String s : list) {
            System.out.println(s);
        }
        ThreadLocalRandom current = ThreadLocalRandom.current();
        int i = current.nextInt(100, 101);
        System.out.println(i);
    }
}
