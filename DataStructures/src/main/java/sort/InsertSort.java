package sort;

import java.util.Random;

/**
 * 插入排序(将待排序元素分为有序表和无序表,依次循环将无序表的一个数据插入到有序表相应的位置)
 *
 * @author: zhangqian
 * @date: 2020/8/22
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = new int[5000000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(2000);
        }
        /*System.out.println("排序之前的数组");
        System.out.println(Arrays.toString(arr));*/
        long start = System.currentTimeMillis();
        insertSort(arr);
        long end = System.currentTimeMillis();
        /*System.out.println("排序之后的数组");
        System.out.println(Arrays.toString(arr));*/

        System.out.println("共消耗时间="+(end-start));
    }

    private static void insertSort(int[] arr) {
        int insertVal;
        int insertIndex;
        for (int i = 1; i < arr.length; i++) {
            insertIndex = i;
            insertVal = arr[i];
            while (insertIndex -1 >= 0 && insertVal < arr[insertIndex-1]) {
                //被插入值后移
                arr[insertIndex] = arr[insertIndex-1];
                insertIndex--;
            }
            if (insertIndex != i) {
                arr[insertIndex] = insertVal;
            }
        }
    }
}
