package sort;

import java.util.Arrays;

/**
 * 堆排序()
 * 大顶堆:从小到大排序使用大顶堆,特点是当前节点大于左节点和右节点,即arr[i]>arr[2i+1]&&arr[i]>arr[2i+2]
 * 小顶堆:从大到小排序使用小顶堆,特点是当前节点小于左节点和右节点,即arr[i]<arr[2i+1]&&arr[i]<arr[2i+2]
 * @author: zhangqian
 * @date: 2020/8/27
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9,13,3,7};
        /*int[] arr = new int[5];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(2000);
        }*/
        System.out.println("排序之前的数组");
        System.out.println(Arrays.toString(arr));
        long start = System.currentTimeMillis();
        bigHeapSort(arr);
        long end = System.currentTimeMillis();
        System.out.println("排序之后的数组");
        System.out.println(Arrays.toString(arr));

//        System.out.println("共消耗时间=" + (end - start));
    }

    private static void bigHeapSort(int[] arr) {
        //将无序序列构成堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            bigHeapSort(arr, i, arr.length);
        }

        //将堆顶元素与末尾元素交换,使最大元素移动到数组末端
        for (int j = arr.length - 1; j > 0; j--) {
            arr[0] = arr[0] ^ arr[j];
            arr[j] = arr[0] ^ arr[j];
            arr[0] = arr[0] ^ arr[j];
            bigHeapSort(arr, 0, j);
        }
    }

    /**
     * 大顶堆排序
     * @param arr
     */
    private static void bigHeapSort(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {//左子节点小于右子节点
                k++;
            }
            if (arr[k] > temp) { // 说明子节点大
                arr[i] = arr[k];//将子节点的值赋给当前节点
                i = k;// !!! i指向k,继续循环比较
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到调整后的位置
    }
}
