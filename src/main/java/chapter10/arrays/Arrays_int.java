package chapter10.arrays;

/**
 * Created by lili on 2017/7/1.
 */
//10.1.1   Arrays数组类
// Arrays排序和查找算法。

import chapter1.Ex103_ArraySearch_int;

import java.util.Arrays;

public class Arrays_int
{
    public static void main(String args[])
    {
        int value[]= Ex103_ArraySearch_int.random(10);                //产生n个随机数，返回整型数组，见第1章
        System.out.print("随机数序列： ");
        Ex103_ArraySearch_int.print(value);
        Arrays.sort(value);                                //按升序排序
        System.out.print("排序序列： ");
        Ex103_ArraySearch_int.print(value);
        int key=100;
        int i=Arrays.binarySearch(value, key);
        System.out.println("二分法查找"+key+"结果是"+i+"，查找"+(i>=0 && i<value.length?"":"不")+"成功");
    }
}
/*
程序运行结果如下：
随机数序列：  49 99 24 46 93 68 67 35 28 94
排序序列：  24 28 35 46 49 67 68 93 94 99
二分法查找100结果是-11，查找不成功

*/
