package chapter9.sort;

import java.util.Arrays;

/**
 * @packgeName: chapter9.keys
 * @ClassName: Sort
 * @copyright: CopyLeft
 * @description:<描述>
 * @author: lili
 * @date: 2017/10/3-17:35
 * @version: 1.0
 * @since: JDK 1.8
 */
public class Sort {

    /**
     * 插入排序
     */
    public static void insertSort(int[] keys) {
        for (int i = 0; i < keys.length; i++) {
            int temp = keys[i];
            int j;
            for (j = i - 1; j >= 0 && temp < keys[j]; j--) {
                keys[j + 1] = keys[j];
            }
            keys[j + 1] = temp;
            System.out.print("第" + i + "趟 temp=" + temp + "\t");
            System.out.println(Arrays.toString(keys));
        }
    }

    /**
     * 插入排序
     */
    public static void insertSort2(int[] keys) {
        for (int i = 0; i < keys.length - 1; i++) {
            int temp = keys[i + 1];
            int j = i;
            while (j > -1 && temp < keys[j]) {
                keys[j + 1] = keys[j];
                j--;
            }
            keys[j + 1] = temp;
            System.out.print("第" + i + "趟 temp=" + temp + "\t");
            System.out.println(Arrays.toString(keys));
        }
    }


    /**
     * 希尔排序
     */
    public static void shellSort1(int[] keys) {
        //每次增量减半，初始为数组长度
        for (int delta = keys.length / 2; delta > 0; delta /= 2) {
            for (int i = delta; i < keys.length; i++) {
                int temp = keys[i], j;
                for (j = i - delta; j >= 0 && temp < keys[j]; j -= delta) {
                    keys[j + delta] = keys[j];
                }
                keys[j + delta] = temp;
            }
            System.out.print("delta=" + delta + "  ");
            System.out.println(Arrays.toString(keys));
        }
    }

    /**
     * 希尔排序
     */
    /**
     * @param keys   数组
     * @param n      数组个数
     * @param d      增量数组 [6,3,1]
     * @param numOfD 增量数组个数 3
     */
    public static void shellSort2(int[] keys, int n, int[] d, int numOfD) {
        int i, j, k, m, span, temp;
        for (m = 0; m < numOfD; m++) {
            span = d[m];
            for (k = 0; k < span; k++) {
                for (i = k; i < n - span; i += span) {
                    temp = keys[i + span];
                    j = i;
                    while (j > -1 && temp <= keys[j]) {
                        keys[j + span] = keys[j];
                        j = j - span;
                    }
                    keys[j + span] = temp;
                }
            }
            System.out.print("delta=" + span + "  ");
            System.out.println(Arrays.toString(keys));
        }
    }

    /**
     * 冒泡排序
     */
    public static void bubbleSort(int[] keys) {
        int flag = 1;
        int n = keys.length;
        for (int i = 1; i < n && flag == 1; i++) {
            flag = 0;
            for (int j = 0; j < n - i; j++) {
                if (keys[j] > keys[j + 1]) {
                    int temp = keys[j + 1];
                    keys[j + 1] = keys[j];
                    keys[j] = temp;
                    flag = 1;
                }
            }
            System.out.print("第" + i + "趟: ");
            System.out.println(Arrays.toString(keys));
        }
    }

    /**
     * 冒泡排序
     */
    public static void bubbleSort2(int[] keys) {
        boolean exchange = true;
        for (int i = 1; i < keys.length && exchange; i++) {
            exchange = false;
            for (int j = 0; j < keys.length - i; j++) {
                if (keys[j] > keys[j + 1]) {
                    int temp = keys[j];
                    keys[j] = keys[j + 1];
                    keys[j + 1] = temp;
                    exchange = true;
                }
            }
            System.out.print("第" + i + "趟: ");
            System.out.println(Arrays.toString(keys));
        }
    }

    /**
     * 普通冒泡排序
     */
    public static void bubbleSort3(int[] keys) {
        boolean exchange = true;
        for (int i = 1; i < keys.length ; i++) {

            for (int j = 0; j < keys.length - i; j++) {
                if (keys[j] > keys[j + 1]) {
                    int temp = keys[j];
                    keys[j] = keys[j + 1];
                    keys[j + 1] = temp;
                }
            }
            System.out.print("第" + i + "趟: ");
            System.out.println(Arrays.toString(keys));
        }
    }


    /**
     * 快速排序
     */
    public static void quickSort(int[] keys, int begin, int end) {
        if (begin < end) {
            int i = begin;
            int j = end;
            int vot = keys[i]; //标准元素
            while (i != j) {
                while (i < j && vot <= keys[j]) j--;
                if (i < j) {
                    keys[i++] = keys[j];
                }
                while (i < j && keys[i] <= vot) i++;
                if (i < j) {
                    keys[j--] = keys[i];
                }
            }
            keys[i] = vot;
            System.out.print("[" + begin + "-" + end + "],  vot=" + vot + "  ");
            System.out.println(Arrays.toString(keys));
            quickSort(keys, begin, j - 1);
            quickSort(keys, i + 1, end);
        }
    }

    /**
     * 快速排序
     */
    public static void quickSort2(int[] keys, int begin, int end) {
        if (begin < end) {
            int i = begin;
            int j = end;
            int temp = keys[i]; //标准元素
            while (i < j) {
                while (i < j && temp <= keys[j]) j--;
                if (i < j) {
                    keys[i] = keys[j];
                    i++;
                }
                while (i < j && keys[i] < temp) i++;
                if (i < j) {
                    keys[j] = keys[i];
                    j--;
                }
            }
            keys[i] = temp;  //插入标准元素
            System.out.print("[" + begin + "-" + end + "],  temp=" + temp + "  ");
            System.out.println(Arrays.toString(keys));
            quickSort2(keys, begin, i - 1);
            quickSort2(keys, j + 1, end);
        }
    }

    /**
     * 选择排序
     * */
    public static void selectSort(int[] keys) {
        for (int i = 0; i < keys.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < keys.length; j++)
                if (keys[j] < keys[min]) {
                    min = j;
                }
            if (min != i) {
                int temp = keys[i];
                keys[i] = keys[min];
                keys[min] = temp;
            }
            System.out.print("第" + (i + 1) + "趟: ");
            System.out.println(Arrays.toString(keys));
        }
    }

    /**
     * 堆排序
     *
     */
    public static void heapSort(int[] keys) {
        heapSort(keys, true);
    }

    /**
     * 堆排序
     * @param keys
     * @param minHeap  是否为最小堆
     */
    public static void heapSort(int[] keys, boolean minHeap) {
        for (int i = keys.length / 2 - 1; i >= 0; i--) {
            //创建最大堆or最小堆
            sift(keys, i, keys.length - 1, minHeap);
        }
        for (int i = keys.length - 1; i > 0; i--) {
            //交换顶部和底部元素
            int temp = keys[0];
            keys[0] = keys[i];
            keys[i] = temp;
            sift(keys, 0, i - 1, minHeap);
            System.out.println("第" + (keys.length - i) + "趟" + Arrays.toString(keys));
        }
    }

    /**
     * 调整堆
     * @param keys
     * @param parent
     * @param end
     * @param minHeap
     */
    private static void sift(int[] keys, int parent, int end, boolean minHeap) {
        //System.out.println("sift " + parent + "..." + end + "  ");
        int child = 2 * parent + 1;
        int value = keys[parent];
        while (child <= end) {
            if (child < end && (minHeap ? keys[child] > keys[child + 1] : keys[child] < keys[child + 1])) {
                child++;
            }
            if (minHeap ? value > keys[child] : value < keys[child]) {
                keys[parent] = keys[child];
                parent = child;
                child = 2 * parent + 1;
            } else {
                break;
            }
        }
        keys[parent] = value;
    }

    /**
     * 是否是最小堆
     * @param value
     * @return
     */
    public static boolean isMinHeap(int[] value) {
        if (value.length == 0) {
            return false;
        }
        for (int i = value.length / 2 - 1; i >= 0; i--) {
            int j = 2 * i + 1;
            if (value[i] > value[j] || j + 1 < value.length && value[i] > value[j + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否是最大堆
     * @param value
     * @return
     */
    public static boolean isMaxHeap(int[] value) {
        if (value.length == 0) {
            return false;
        }
        for (int i = value.length / 2 - 1; i >= 0; i--) {
            int j = 2 * i + 1;
            if (value[i] < value[j] || j + 1 < value.length && value[i] < value[j + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断堆类型
     */
    public static void whatKindOfHeap(int[] value) {
        if (isMinHeap(value)) {
            System.out.println("最小堆");
        } else if (isMaxHeap(value)) {
            System.out.println("最大堆");
        } else {
            System.out.println("非最大堆或者最小堆");
        }
    }

    /**
     * 归并排序
     */
    public static void mergeSort(int[] X) {
        System.out.println("归并排序");
        int[] Y = new int[X.length];
        int n = 1;
        while (n < X.length) {
            mergepass(X, Y, n);
            System.out.println(Arrays.toString(Y));
            n *= 2;
            if (n < X.length) {
                mergepass(Y, X, n);
                System.out.println(Arrays.toString(X));
                n *= 2;
            }
        }
    }

    private static void mergepass(int[] X, int[] Y, int n) {
        System.out.print("子序列长度n=" + n + "  ");
        int i = 0;
        for (i = 0; i < X.length - 2 * n + 1; i += 2 * n) {
            merge(X, Y, i, i + n, n);
        }
        if (i + n < X.length) {
            merge(X, Y, i, i + n, n);
        } else {
            for (int j = i; j < X.length; j++) {
                Y[j] = X[j];
            }
        }
    }

    private static void merge(int[] X, int[] Y, int m, int r, int n) {
        int i = m, j = r, k = m;
        while (i < r && j < r + n && j < X.length) {
            if (X[i] < X[j]) {
                Y[k++] = X[i++];
            } else {
                Y[k++] = X[j++];
            }
            while (i < r) {
                Y[k++] = X[i++];
            }
            while (j < r + n && j < X.length) {
                Y[k++] = X[j++];
            }
        }
    }


    public static void main(String[] args) throws ClassNotFoundException {
//        int[] keys = {32,26,87,72,26,17};
//        insertSort(keys);
//        insertSort2(keys);
//        int [] keys = {38,55,65,97,27,76,27,13,19};
//        shellSort1(keys);
//        int[] keys = {65,34,25,87,12,38,56,46,14,77,92,23};
//        shellSort2(keys,keys.length,new int[]{6,3,1},3);
//        int[] keys = {38,5,19,26,49,97,1,66};
//        bubbleSort(keys);
//        int[] keys = {60,55,48,37,10,90,84,36};
//        quickSort2(keys,0,keys.length-1);
//        int[] keys = {19,38,19,49,97,76,81,13};
//        heapSort(keys,true);
//        int[] keys = {100,86,48,73,35,39,42,57,66,21};
//        int[] keys = {100,98,65,32,21,18,14,12,8,6,4,1};
//        int [] keys = {12,70,33,65,24,56,48,92,86,33};
//        int [] keys = {103,97,56,38,66,23,42,12,30,6};
//        int [] keys = {5,56,20,23,40,38,29,61,35,76};
//        heapSort(keys,false);
//        whatKindOfHeap(keys);
//        int [] keys = {97,82,75,53,17,61,70,12,61,58,26};
//        mergeSort(keys);

        //叶核亚的课后习题
        int[] keys = {3, 17, 12, 61, 8, 70, 97, 75, 53, 26, 54, 61};
        //插入排序
//        insertSort(keys);
//        shellSort1(keys);
        //选择排序
//        selectSort(keys);
//        heapSort(keys,false);
        //交换排序
//        bubbleSort(keys);
//        quickSort(keys,0,keys.length-1);
        //归并排序
//        mergeSort(keys);
        int oldCapacity = 100 ;// 100 * 100*1/2  扩大为原来的一半
        System.out.println(oldCapacity + (oldCapacity >> 1));
    }

}
