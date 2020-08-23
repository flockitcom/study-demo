package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序(选一个标点,比它小放坐标,比他大的放右边,左右在递归)
 * 下列代码使用的是移位法
 * @author: zhangqian
 * @date: 2020/8/22
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[5000000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(2000);
        }
        /*System.out.println("排序之前的数组");
        System.out.println(Arrays.toString(arr));*/
        long start = System.currentTimeMillis();
        quickSort(arr);
        long end = System.currentTimeMillis();
        /*System.out.println("排序之后的数组");
        System.out.println(Arrays.toString(arr));*/

        System.out.println("共消耗时间="+(end-start));
    }

    private static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int l = left;
            int r = right;
            int temp = arr[right];
            while (l < r) {
                while (l < r && arr[l] < temp) {
                    l++;
                }

                //左边的值大于标点
                if (l < r) {
                    //将左标的值复制到右标
                    arr[r--] = arr[l];
                }

                while (l < r && arr[r] > temp) {
                    r--;
                }

                //右边的值小于标点
                if (l < r) {
                    //将右标的值复制到左标
                    arr[l++] = arr[r];
                }


            }
            arr[r] = temp;
            //此时l和R在同一下标(相遇) 左右递归
            quickSort(arr, left, r - 1);
            quickSort(arr, l + 1, right);
        }
    }
}
