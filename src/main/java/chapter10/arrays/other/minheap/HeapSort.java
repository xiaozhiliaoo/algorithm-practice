package chapter10.arrays.other.minheap;

/**
 * Created by lili on 2017/7/1.
 */
//堆排序
public class HeapSort
{
    public static void print(Object[] table)               //输出对象数组元素
    {
        if (table!=null)
            for (int i=0; i<table.length; i++)
                System.out.print(" "+table[i].toString());
        System.out.println();
    }
    public static Integer[] random(int n)                  //产生n个随机数，返回整型数组
    {
        if (n<=0)
            return null;
        Integer table[] = new Integer[n];
        for (int i=0; i<table.length; i++)
            table[i] = new Integer((int)(Math.random()*100)); //产生一个0～99之间的随机数
        return table;                                      //返回一个数组
    }

    public static void heapsort(Integer table[])        //数组堆排序，空间复杂度为O(n)
    {
        MinHeap<Integer> minheap = new MinHeap<Integer>(table);
        System.out.print("最小堆序列："+minheap.toString());
        for (int i=0; !minheap.isEmpty(); i++)
            table[i]=minheap.removeMin();               //返回最小值元素（根），删除根结点并调整为最小堆
        System.out.println();
    }
    public static void main(String args[])
    {
        System.out.print("关键字序列: ");
        Integer table[] = random(9);
        print(table);
        heapsort(table);
        System.out.print("排序：");
        print(table);
    }
}
/*
关键字序列:  57 97 36 12 64 7 31 40 76 61
最小堆序列：(7, 12, 31, 40, 61, 57, 36, 97, 76, 64)
排序： 7 12 31 36 40 57 61 64 76 97

关键字序列:  48 12 56 18 58 8 57 0 81 41
最小堆序列：(0, 8, 12, 18, 41, 56, 57, 48, 81, 58)
排序： 0 8 12 18 41 48 56 57 58 81

关键字序列:  89 13 27 40 2 88 59 11 39 37
最小堆序列：(2, 11, 27, 13, 37, 88, 59, 89, 39, 40)
排序： 2 11 13 27 37 39 40 59 88 89

关键字序列:  18 4 16 0 35 84 48 91 97 2
最小堆序列：(0, 4, 16, 18, 2, 84, 48, 91, 97, 35)
排序： 0 4 2 16 18 35 48 84 91 97

关键字序列:  49 16 90 87 31 99 47 11 4
最小堆序列：(4, 11, 47, 16, 31, 99, 90, 87, 49)
排序： 4 11 16 31 47 49 87 90 99


*/

