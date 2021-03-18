package sort;

import java.util.Random;

/**
 * 基数排序,桶排序的扩展,经典的空间换时间算法
 * 根据各个位上的值,分配在相应的桶,达到排序的作用
 *
 * @author: zhangqian
 * @date: 2020/8/22
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = new int[5000000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(2000);
        }
        /*System.out.println("排序之前的数组");
        System.out.println(Arrays.toString(arr));*/
        long start = System.currentTimeMillis();
        radixSort(arr);
        long end = System.currentTimeMillis();
        /*System.out.println("排序之后的数组");
        System.out.println(Arrays.toString(arr));*/

        System.out.println("共消耗时间="+(end-start));
    }

    private static void radixSort(int[] arr) {
        //一、找出最大的值,以及最大值得位数
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        int len = (max + "").length();

        //二维桶的大小
        int[][] bucket = new int[10][arr.length];

        //每个桶所含元素的个数
        int[] bucketCount;

        //根据最大值位数循环
        for (int i = 0; i < len; i++) {
            //每次循环将桶中元素个数初始化
            bucketCount = new int[10];

            for (int j = 0; j < arr.length; j++) {
                int num = arr[j] / ((int)Math.pow(10, i)) % 10;
                bucket[num][bucketCount[num]] = arr[j];
                bucketCount[num]++;
            }
            //根据每个桶中元素的个数取出数据
            int index = 0;
            for (int k = 0; k < bucketCount.length; k++) {
                //说明桶中有数据,bucketCount[k]为元素的个数
                if (bucketCount[k]>0){
                    //循环读取数据
                    for (int l = 0; l < bucketCount[k]; l++) {
                        arr[index++] = bucket[k][l];
                    }
                }
            }
        }
    }


}
