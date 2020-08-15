package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class T01 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
    }
}
