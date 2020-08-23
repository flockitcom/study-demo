package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 选择排序(每一次循环选出最小值)
 *
 * @author: zhangqian
 * @date: 2020/8/22
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = new int[5000000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(2000);
        }
        /*System.out.println("排序之前的数组");
        System.out.println(Arrays.toString(arr));*/
        long start = System.currentTimeMillis();
        selectSort(arr);
        long end = System.currentTimeMillis();
        /*System.out.println("排序之后的数组");
        System.out.println(Arrays.toString(arr));*/

        System.out.println("共消耗时间="+(end-start));
    }

    private static void selectSort(int[] arr) {
        int minIndex;
        int min;
        for (int i = 0; i < arr.length - 1; i++) {
            minIndex = i;
            min = arr[minIndex];
            for (int j = i + 1; j < arr.length; j++) {
                //如果最小值大于arr[j],说明最小值并不是最小值
                if (min > arr[j]) {
                    minIndex = j;
                    min = arr[j];
                }
            }
            //如果minIndex发生变化才交换
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }
}
