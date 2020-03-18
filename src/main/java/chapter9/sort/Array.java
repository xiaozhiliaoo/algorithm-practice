package chapter9.sort;

/**
 * Created by lili on 2017/7/1.
 */
//第9章   排序

public class Array {
    public static void print(int[] table)                  //输出数组元素
    {
        if (table != null)
            for (int i = 0; i < table.length; i++)
                System.out.print(" " + table[i]);
        System.out.println();
    }

    public static void print(Object[] table)               //输出对象数组元素
    {
        if (table != null)
            for (int i = 0; i < table.length; i++)
                System.out.print(" " + table[i].toString());
        System.out.println();
    }

    //直接插入排序，数组是引用类型，作为方法的参数，其元素值将被改变
    public static void insertSort(int[] table) {
        System.out.println("直接插入排序");
        for (int i = 1; i < table.length; i++)                 //n-1趟扫描
        {
            int temp = table[i], j;                          //每趟将table[i]插入到前面排序序列中
            for (j = i - 1; j >= 0 && temp < table[j]; j--)        //将前面较大元素向后移动
                table[j + 1] = table[j];
            table[j + 1] = temp;                             //temp值到达插入位置
            System.out.print("第" + i + "趟: ");
            print(table);                                  //输出排序中间结果，可省略
        }
    }

    public static void shellSort(int[] table)              //希尔排序
    {
        System.out.println("希尔排序");
        for (int delta = table.length / 2; delta > 0; delta /= 2)  //若干趟扫描，控制增量，增量减半
        {
            for (int i = delta; i < table.length; i++)         //一趟分若干组，每组进行直接插入排序
            {
                int temp = table[i], j;                      //table[i]是当前待插入元素
                for (j = i - delta; j >= 0 && temp < table[j]; j -= delta) //每组元素相距delta远，寻找插入位置
                    table[j + delta] = table[j];
                table[j + delta] = temp;                     //插入元素
            }
            System.out.print("delta=" + delta + "  ");
            print(table);
        }
    }

    private static void swap(int[] table, int i, int j)     //交换数组中下标为i、j的元素
    {
        if (i >= 0 && i < table.length && j >= 0 && j < table.length && i != j)   //判断i、j是否越界
        {
            int temp = table[j];
            table[j] = table[i];
            table[i] = temp;
        }
    }

    public static void bubbleSort(int[] table)             //冒泡排序
    {
        System.out.println("冒泡排序");
        boolean exchange = true;                             //是否交换的标记
        for (int i = 1; i < table.length && exchange; i++)     //有交换时再进行下一趟，最多n-1趟
        {
            exchange = false;                                //假定元素未交换
            for (int j = 0; j < table.length - i; j++)           //一趟比较、交换
                if (table[j] > table[j + 1])                   //反序时，交换
                {
                    int temp = table[j];
                    table[j] = table[j + 1];
                    table[j + 1] = temp;
                    exchange = true;                         //有交换
                }
            System.out.print("第" + i + "趟: ");
            print(table);
        }
    }

    public static void quickSort(int[] table)              //快速排序
    {
        System.out.println("快速排序");
        quickSort(table, 0, table.length - 1);
    }

    //一趟快速排序，begin、high指定序列的下界和上界，递归算法
    private static void quickSort(int[] table, int begin, int end) {
        if (begin < end)                                     //序列有效
        {
            int i = begin, j = end;
            int vot = table[i];                              //第一个值作为基准值
            while (i != j)                                   //一趟排序
            {
                while (i < j && vot <= table[j])               //从后向前寻找较小值
                    j--;
                if (i < j)
                    table[i++] = table[j];                   //较小元素向前移动
                while (i < j && table[i] <= vot)               //从前向后寻找较大值
                    i++;
                if (i < j)
                    table[j--] = table[i];                   //较大元素向后移动
            }
            table[i] = vot;                                  //基准值到达最终位置
            System.out.print(begin + ".." + end + ",  vot=" + vot + "  ");
            print(table);
            quickSort(table, begin, j - 1);                 //前端子序列再排序，递归调用
            quickSort(table, i + 1, end);                   //后端子序列再排序，递归调用
        }
    }

    public static void selectSort(int[] table)             //直接选择排序
    {
        System.out.println("直接选择排序");
        for (int i = 0; i < table.length - 1; i++)               //n-1趟排序
        {                                                  //每趟在从i开始的子序列中寻找最小元素
            int min = i;                                     //设第i个数据元素最小
            for (int j = i + 1; j < table.length; j++)           //在子序列中查找最小值
                if (table[j] < table[min])
                    min = j;                              //记住最小元素下标
            if (min != i)                                    //将本趟最小元素交换到前边
            {
                int temp = table[i];
                table[i] = table[min];
                table[min] = temp;
            }
            System.out.print("第" + (i + 1) + "趟: ");
            print(table);
        }
    }

    public static void heapSort_min(int[] table)           //堆排序（降序），最小堆
    {
        System.out.println("最小堆？ " + isMinHeap(table));
        System.out.println("建立最小堆序列");
        int n = table.length;
        for (int j = n / 2 - 1; j >= 0; j--) {                    //创建最小堆
            sift_min(table, j, n - 1);
        }
        System.out.println("最小堆？ " + isMinHeap(table));

        System.out.println("堆排序（降序）");
        for (int j = n - 1; j > 0; j--)                          //每趟将最小值交换到后面，再调整成堆
        {
            int temp = table[0];
            table[0] = table[j];
            table[j] = temp;
            sift_min(table, 0, j - 1);
        }
    }

    //将以begin为根的子树调整成最小堆，begin、end是序列下界和上界
    private static void sift_min(int[] table, int begin, int end) {
        int i = begin, j = 2 * i + 1;                               //i为子树的根，j为i结点的左孩子
        int temp = table[i];                                 //获得第i个元素的值
        while (j <= end)                                     //沿较小值孩子结点向下筛选
        {
            if (j < end && table[j] > table[j + 1])              //数组元素比较（改成<为最大堆）
                j++;                                       //j为左右孩子的较小者
            if (temp > table[j])                             //若父母结点值较大（改成<为最大堆）
            {
                table[i] = table[j];                         //孩子结点中的较小值上移
                i = j;                                       //i、j向下一层
                j = 2 * i + 1;
            } else break;                                    //退出循环
        }
        table[i] = temp;                                     //当前子树的原根值调整后的位置
        System.out.print("sift  " + begin + ".." + end + "  ");
        print(table);
    }

    public static void heapSort_max(int[] table)           //堆排序（升序），最大堆
    {
        System.out.println("最大堆？ " + isMaxHeap(table));
        System.out.println("建立最大堆序列");
        int n = table.length;
        for (int j = n / 2 - 1; j >= 0; j--)                       //创建最大堆
            sift_max(table, j, n - 1);
        System.out.println("最大堆？ " + isMaxHeap(table));

        System.out.println("堆排序（升序）");
        for (int j = n - 1; j > 0; j--)                          //每趟将最大值交换到后面，再调整成堆
        {
            int temp = table[0];
            table[0] = table[j];
            table[j] = temp;
            sift_max(table, 0, j - 1);
        }
    }

    //将以begin为根的子树调整成最大堆，begin、end是序列下界和上界
    private static void sift_max(int[] table, int begin, int end) {
        int i = begin, j = 2 * i + 1;                               //i为子树的根，j为i结点的左孩子
        int temp = table[i];                                 //获得第i个元素的值
        while (j <= end)                                     //沿较大值孩子结点向下筛选
        {
            if (j < end && table[j] < table[j + 1])              //数组元素比较
                j++;                                       //j为左右孩子的较大者
            if (temp < table[j])                             //若父母结点值较小
            {
                table[i] = table[j];                         //孩子结点中的较大值上移
                i = j;                                       //i、j向下一层
                j = 2 * i + 1;
            } else break;
        }
        table[i] = temp;                                     //当前子树的原根值调整后的位置
        System.out.print("sift  " + begin + ".." + end + "  ");
        print(table);
    }

    public static void mergeSort(int[] X)                  //归并排序
    {
        System.out.println("归并排序");
        int[] Y = new int[X.length];                       //Y数组长度同X数组
        int n = 1;                                           //已排序的子序列长度，初值为1
        while (n < X.length) {
            mergepass(X, Y, n);                            //一趟归并，将X数组中各子序列归并到Y中
            print(Y);
            n *= 2;                                          //子序列长度加倍
            if (n < X.length) {
                mergepass(Y, X, n);                        //将Y数组中各子序列再归并到X中
                print(X);
                n *= 2;
            }
        }
    }

    private static void mergepass(int[] X, int[] Y, int n) //一趟归并
    {
        System.out.print("子序列长度n=" + n + "  ");
        int i = 0;
        for (i = 0; i < X.length - 2 * n + 1; i += 2 * n)              //X中若干相邻子序列归并到Y中
            merge(X, Y, i, i + n, n);                        //将X中两个相邻子序列一次归并到Y数组中
        if (i + n < X.length)
            merge(X, Y, i, i + n, n);                        //再一次归并
        else
            for (int j = i; j < X.length; j++)                 //将X剩余元素复制到Y中
                Y[j] = X[j];
    }

    private static void merge(int[] X, int[] Y, int m, int r, int n)   //一次归并
    {
        int i = m, j = r, k = m;
        while (i < r && j < r + n && j < X.length)                 //将X中两个相邻子序列归并到Y中
            if (X[i] < X[j])                                 //较小值复制到Y中
                Y[k++] = X[i++];
            else
                Y[k++] = X[j++];

        while (i < r)                                        //将前一个子序列剩余元素复制到Y中
            Y[k++] = X[i++];
        while (j < r + n && j < X.length)                        //将后一个子序列剩余元素复制到Y中
            Y[k++] = X[j++];
    }

    //习题9
    public static void insertSort(Comparable[] value)      //对象数组的直接插入排序
    {
        System.out.println("直接插入排序");
        for (int i = 1; i < value.length; i++)                 //n-1趟扫描
        {
            Comparable temp = value[i];                      //每趟将value[i]插入到前面排序子序列中
            int j;
            for (j = i - 1; j >= 0 && temp.compareTo(value[j]) < 0; j--)  //将前面较大元素向后移动
                value[j + 1] = value[j];
            value[j + 1] = temp;                             //temp值到达插入位置
            System.out.print("第" + i + "趟: ");
            print(value);                                  //调用print(Object)输出排序中间结果，可省略
        }
    }

    //第9章习题
    public static boolean isMinHeap(int[] value)           //判断一个数据序列是否为最小堆
    {
        if (value.length == 0)                               //空序列不是堆。若无此句，则空序列是堆，定义不同
            return false;
        for (int i = value.length / 2 - 1; i >= 0; i--)            //i从最深一棵子树的根结点开始
        {
            int j = 2 * i + 1;                                   //j是i的左孩子，肯定存在
            if (value[i] > value[j] || j + 1 < value.length && value[i] > value[j + 1])
                return false;
        }
        return true;
    }

    public static boolean isMaxHeap(int[] value)           //判断一个数据序列是否为最大堆
    {
        if (value.length == 0)                               //空序列不是堆
            return false;
        for (int i = value.length / 2 - 1; i >= 0; i--)            //i从最深一棵子树的根结点开始
        {
            int j = 2 * i + 1;                                   //j是i的左孩子，肯定存在
            if (value[i] < value[j] || j + 1 < value.length && value[i] < value[j + 1])
                return false;
        }
        return true;
    }
}

