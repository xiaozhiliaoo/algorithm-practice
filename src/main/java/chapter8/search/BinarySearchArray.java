package chapter8.search;

/**
 * Created by lili on 2017/7/1.
 */
//8.2.2   基于有序顺序表的折半查找

public class BinarySearchArray
{
    public static void print(int[] value)                  //输出数组元素
    {
        for (int i=0; i<value.length; i++)
            System.out.print(" "+value[i]);
        System.out.println();
    }

    //在按升序排列的value数组中，折半查找关键字为key的元素，若查找成功返回下标，否则返回-1
    //若数组为空value==null，将抛出空对象异常
/*    public static int binarySearch(int[] value, int key)
    {
        int begin=0, end=value.length-1;                   //查找范围的下界和上界
        while (begin<=end)                                 //边界有效
        {
            int mid = (begin+end)/2;                       //中间位置，当前比较元素位置
            System.out.print(value[mid]+"? ");             //显示比较中间结果，可省略
            if (value[mid]==key)
                return mid;                                //查找成功
            if (value[mid]>key)                            //给定值小
                end = mid-1;                               //查找范围缩小到前半段
            else
                begin = mid+1;                             //查找范围缩小到后半段
        }
        return -1;                                         //查找不成功
    }  */

    //在按升序排列的value对象数组中，折半查找关键字为key的元素，若查找成功返回下标，否则返回-1
    public static <T> int binarySearch(Comparable<T>[] value, T key)
    {
        return binarySearch(value, 0, value.length-1, key);
    }
    //在从begin到end范围内、按升序排列的value数组中，折半查找关键字为key的元素
    //若查找成功返回元素下标，否则返回-1
    public static <T> int binarySearch(Comparable<T>[] value, int begin, int end, T key)
    {
        if (key!=null)
            while (begin<=end)                              //边界有效
            {   int mid = (begin+end)/2;                    //中间位置，当前比较元素位置
                System.out.print(value[mid]+"? ");
                if (value[mid].compareTo(key)==0)           //对象比较大小
                    return mid;                             //查找成功
                if (value[mid].compareTo(key)>0)            //给定对象小
                    end = mid-1;                            //查找范围缩小到前半段
                else begin = mid+1;                         //查找范围缩小到后半段
            }
        return -1;                                          //查找不成功
    }

    public static void main(String[] args)
    {
        int[] value ={8,17,26,32,40,72,87,99};             //已按升序排序
        System.out.print("已按升序排序的关键字序列: ");
        print(value);
        int key=99;
        System.out.println("折半查找 "+key+", "+((binarySearch(value,key)==-1)?"不":"")+"成功");
        key=25;
        System.out.println("折半查找 "+key+", "+((binarySearch(value,key)==-1)?"不":"")+"成功");
    }
/*
程序运行结果如下：
已按升序排序的关键字序列:  8 17 26 32 40 72 87 99
32? 72? 87? 99? 折半查找 99, 成功
32? 17? 26? 折半查找 25, 不成功

*/

    //第8章习题，递归算法
    //在已按升序排列的value数组中，折半查找关键字为key的元素，若查找成功返回下标，否则返回-1
    public static int binarySearch(int[] value, int key)
    {
        return binarySearch(value, key, 0, value.length-1);
    }
    //在已按升序排列的value数组中，由begin、end指定查找范围的下界和上界，折半查找元素x
    //若查找成功返回元素下标，否则返回-1，递归算法
    public static int binarySearch(int[] value, int key, int begin, int end)
    {
        if (begin<=end)                                    //边界有效
        {
            int mid = (begin+end)/2;                       //中间位置，当前比较元素位置
            System.out.print(value[mid]+"? ");             //显示比较中间结果，可省略
            if (value[mid]==key)
                return mid;                                //查找成功
            if (value[mid]>key)                                  //给定值小
                return binarySearch(value, key, begin, mid-1);   //查找范围缩小到前半段
            return binarySearch(value, key, mid+1, end);         //查找范围缩小到后半段
        }
        return -1;                                         //查找不成功
    }

    //第12章？？
    //在从begin到end范围内、已按升序排列的value数组中，折半查找关键字为key的元素
    //在已按升序排列的value对象数组中折半查找关键字为key的元素，由比较器对象comparator指定对象比较大小的规则
    //若查找成功返回下标，否则返回-1
    public static<T> int binarySearch(T[] value, T key, java.util.Comparator<? super T> comparator)
    {
        return binarySearch(value, 0, value.length-1, key, comparator);
    }
    //在已按升序排列的value数组中，由low、high指定查找范围的下界和上界，折半查找cobj
    //若查找成功返回元素下标，否则返回-1
    public static<T> int binarySearch(T[] value, int begin, int end, T key, java.util.Comparator<? super T> comparator)
    {
        if (value!=null && key!=null)
            while (begin<=end)                              //边界有效
            {
                int mid = (begin+end)/2;                    //中间位置，当前比较元素位置
                System.out.print(value[mid]+"? ");
                if (comparator.compare(value[mid],key)==0)  //对象比较大小
                    return mid;                             //查找成功
                if (comparator.compare(value[mid],key)>0)   //给定对象小
                    end = mid-1;                            //查找范围缩小到前半段
                else
                    begin = mid+1;                          //查找范围缩小到后半段
            }
        return -1;                                          //查找不成功
    }

}

