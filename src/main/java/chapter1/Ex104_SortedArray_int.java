//【例1.4】 排序数组的顺序查找算法。
//① 对基本数据类型的排序数组进行顺序查找
package chapter1;
public class Ex104_SortedArray_int
{
    //在排序（升序）的整数数组value中顺序查找key，若查找成功返回元素下标，否则返回-1
    public static int indexOf(int[] value, int key)
    {
//        if (isSorted(value))
        for(int i=0; i<value.length && value[i]<=key; i++) //数组未按升序排序的错误未处理
        {   System.out.print(value[i]+"? ");               //输出中间结果，可省略
            if (value[i]==key)
                return i;
        }
        return -1;
    }

    public static void main(String[] args)
    {
//      int[] value ={8,17,26,32,40,72,87,99};            //按升序排序
        int[] value = Ex106_int.randomDifferentSorted(10, 100);   //例1.6
        System.out.print("按升序排序的关键字序列: ");
        Ex103_ArraySearch_int.print(value);
        int key=25;
        System.out.println("顺序查找 "+key+", "+((indexOf(value, key)==-1)?"不":"")+"成功");

        //习题1
        System.out.println("习题1，排序? "+isSorted(value));

        value = Ex103_ArraySearch_int.random(10);         //无序随机数，例1.3
        System.out.print("关键字序列: ");
        Ex103_ArraySearch_int.print(value);
        System.out.println("排序? "+isSorted(value));
    }

    //习题1
    //判断value数组是否已按升序排序，若已排序返回true，否则返回false
    public static boolean isSorted(int[] value)
    {
        for (int i=0; i<value.length-1; i++)
            if (value[i]>value[i+1])
                return false;
        return true;
    }
}

/*
程序运行结果如下：
按升序排序的关键字序列:  8 17 26 32 40 72 87 99
8? 17? 顺序查找 25, 不成功
习题1，排序? true
关键字序列:  99 46 84 28 16 39 38 79 48 24
排序? false

//例1.6
按升序排序的关键字序列:  18 19 23 34 35 39 49 49 60 91
18? 19? 23? 顺序查找 25, 不成功
习题1，排序? true
关键字序列:  83 95 22 42 72 92 56 64 47 42
排序? false

*/
