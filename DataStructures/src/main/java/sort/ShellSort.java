package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 希尔排序(又称缩小增量排序,是直接插入排序的改进版本)
 * 将数组的长度不停除以2作为循环的增量
 * 移动法和交换法差不多,唯一的区别就是一个移动一个交换
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = new int[5000000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(2000);
        }
        /*System.out.println("排序之前的数组");
        System.out.println(Arrays.toString(arr));*/
        long start = System.currentTimeMillis();
        shellSortByMove(arr);
        long end = System.currentTimeMillis();
        /*System.out.println("排序之后的数组");
        System.out.println(Arrays.toString(arr));*/

        System.out.println("共消耗时间="+(end-start));
    }

    /**
     * 希尔排序(移动法)
     *
     * @param arr
     */
    private static void shellSortByMove(int[] arr) {
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            count += 1;
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;
                int insertVal = arr[i];
                while (insertIndex - gap >= 0 && insertVal < arr[insertIndex - gap]) {
                    //被插入值后移
                    arr[insertIndex] = arr[insertIndex - gap];
                    insertIndex -= gap;
                }
                if (insertIndex != i) {
                    arr[insertIndex] = insertVal;
                }
            }
            /*System.out.println("第" + count + "排序之后的数组");
            System.out.println(Arrays.toString(arr));*/
        }
    }

    /**
     * 希尔排序(交换法)
     *
     * @param arr
     */
    private static void shellSortByExchange(int[] arr) {
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            count += 1;
            for (int i = gap; i < arr.length; i++) {
                int insertIndex = i;
                int insertVal = arr[i];
                while (insertIndex - gap >= 0 && insertVal < arr[insertIndex - gap]) {
                    //将两个元素交换
                    arr[insertIndex] = arr[insertIndex] ^ arr[insertIndex - gap];
                    arr[insertIndex-gap] = arr[insertIndex] ^ arr[insertIndex - gap];
                    arr[insertIndex] = arr[insertIndex] ^ arr[insertIndex - gap];

                    insertIndex -= gap;
                }
            }
            /*System.out.println("第" + count + "排序之后的数组");
            System.out.println(Arrays.toString(arr));*/
        }
    }
}
