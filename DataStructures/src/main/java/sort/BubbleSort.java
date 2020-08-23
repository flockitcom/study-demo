package sort;

import java.util.Random;

/**
 * 冒泡排序(相邻两个比较)
 *
 * @author: zhangqian
 * @date: 2020/8/22
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = new int[50000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(2000);
        }
        /*System.out.println("排序之前的数组");
        System.out.println(Arrays.toString(arr));*/
        long start = System.currentTimeMillis();
        bubbleSort(arr);
        long end = System.currentTimeMillis();
        /*System.out.println("排序之后的数组");
        System.out.println(Arrays.toString(arr));*/

        System.out.println("共消耗时间="+(end-start));
    }


    public static void bubbleSort(int[] arr) {
        boolean flag = false;
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j+1]){
                    flag = true;
                    arr[j] = arr[j] ^ arr[j+1];
                    arr[j+1] = arr[j] ^ arr[j+1];
                    arr[j] = arr[j] ^ arr[j+1];
                }
            }
            //未进行排序操作,直接跳出循环
            if (!flag){
                break;
            }else {
                flag = false;
            }

        }

    }
}
