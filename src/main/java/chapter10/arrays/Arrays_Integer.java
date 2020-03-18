package chapter10.arrays;

/**
 * Created by lili on 2017/7/1.
 */
//10.1.1   Arrays数组类
// Arrays排序和查找算法。

import chapter1.Ex103_ArraySearch_Object;

import java.util.Arrays;

public class Arrays_Integer
{
    public static Integer[] random(int n)                  //返回产生n个随机数的数组
    {
        Integer[] list = new Integer[n];
        for (int i=0; i<n; i++)
            list[i]=new Integer((int)(Math.random()*100)); //产生随机数
        return list;
    }

    public static void main(String args[])
    {
        Integer value[]=random(10);                        //产生n个随机数，返回整型对象数组
        System.out.print("随机数序列： ");
        Ex103_ArraySearch_Object.print(value);
        java.util.Arrays.sort(value);                      //按升序排序
        System.out.print("排序序列： ");
        Ex103_ArraySearch_Object.print(value);
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
