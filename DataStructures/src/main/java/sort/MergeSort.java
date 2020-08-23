package sort;

import java.util.Arrays;
import java.util.Random;

/**
 * 归并排序,使用的是分治策略
 * 将数组分解成n个有序的数组,然后将有序的数组合并
 *
 * @author: zhangqian
 * @date: 2020/8/22
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[5000000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(2000);
        }
        /*System.out.println("排序之前的数组");
        System.out.println(Arrays.toString(arr));*/
        long start = System.currentTimeMillis();
        mergeSort(arr);
        long end = System.currentTimeMillis();
        /*System.out.println("排序之后的数组");
        System.out.println(Arrays.toString(arr));*/

        System.out.println("共消耗时间="+(end-start));
    }

    private static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length-1, new int[arr.length]);
    }

    /**
     * 分+合的方法
     *
     * @param arr   数组
     * @param left  左标
     * @param right 右标
     * @param temp  临时数组
     */
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            //中间索引(实际上就是分解成的两个数组前一个数组最后一个元素的下标)
            int mid = (left + right) / 2;
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并
     *
     * @param arr   数组
     * @param left  左标
     * @param mid   中间索引(实际上就是分解成的两个数组前一个数组最后一个元素的下标)
     * @param right 右标
     * @param temp  临时数组
     */
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int l = left;
        //后一个数组第一个元素的下标
        int r = mid + 1;
        //临时数组的当前下标
        int t = 0;

        //一、只要左右两边的有序序列还有元素,就按照规则填充到temp数组中
        while (l <= mid && r <= right) {
            //如果左标元素小,将左边元素填充到temp数组中
            if (arr[l] <= arr[r]) {
                temp[t++] = arr[l++];
            } else {
                //反之,右标元素小,将右边元素填充到temp数组中
                temp[t++] = arr[r++];
            }
        }

        //二、把有剩余数据的有序数组的元素按顺序填充到temp中
        while (l <= mid) {
            temp[t++] = arr[l++];
        }
        while (r <= right) {
            temp[t++] = arr[r++];
        }

        //三、将temp数组的元素拷贝到arr数组中
        // 注意,并不一定要拷贝所有元素 (每次只需要拷贝left至right下标的元素)
        t = 0;
//        System.out.println("拷贝数组左标"+left+" 右标"+right);
        while (left<=right){
            arr[left++] = temp[t++];
        }

    }


}
