//【例1.4】 排序数组的顺序查找算法。
//② 对引用数据类型的排序数组进行顺序查找
package chapter1;
public class Ex104_SortedArray_Comparable
{
    //在排序（升序）对象数组value中顺序查找key对象，若查找成功返回元素下标，否则返回-1
    public static<T> int indexOf(Comparable<T>[] value, T key)
    {
//      if (isSorted(value))
        for (int i=0; i<value.length && value[i].compareTo(key)<=0; i++)        //数组未按升序排序的错误未处理
        {   System.out.print(value[i]+"? ");               //输出中间结果，可省略
            if (value[i].compareTo(key)==0)
                return i;
        }
        return -1;
    }

    public static void main(String[] args)
    {
//        Integer value[] ={new Integer(15),new Integer(32),new Integer(78),new Integer(99)}; //按升序排序
        Integer value[] = Ex106_Comparable.randomSortedInteger(10); //按升序排序
        System.out.print("按升序排序的关键字序列: ");
        Ex103_ArraySearch_Object.print(value);
        Integer key = new Integer(50);
        System.out.println("顺序查找 "+key+", "+((indexOf(value, key)==-1)?"不":"")+"成功");
        //习题1
        System.out.println("习题1，排序? "+isSorted(value));

        //问题研究：Comparable[]没有泛型可以存储混合类型对象，比较时抛出异常
        Comparable[] table ={"aaa","abc",new String("xyz"),new Integer(123)};//混合类型对象
        Ex103_ArraySearch_Object.print(table);             //运行时多态
        System.out.println("顺序查找 "+key+", "+((indexOf(table, key)==-1)?"不":"")+"成功"); //抛出异常
    }

    //习题1
    //判断value对象数组是否已按升序排序，若已排序返回true，否则返回false
    public static boolean isSorted(Comparable[] value)
    {
        for (int i=0; i<value.length-1; i++)
            if (value[i].compareTo(value[i+1])>0)
                return false;
        return true;
    }
    //不行    public static<T> boolean isSorted(Comparable<T>[] value)
    //因为，不能compareTo(Comparable<T>)，应该compareTo(T)。
}
/*
程序运行结果如下：
按升序排序的关键字序列:  15 32 78 99
15? 32? 顺序查找 50, 不成功
习题1，排序? true
 aaa abc xyz 123
Exception in thread "main" java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
	at java.lang.String.compareTo(String.java:92)
	at Ex104_SortedArray_Comparable.indexOf(Ex104_SortedArray_Comparable.java:12)
	at Ex104_SortedArray_Comparable.main(Ex104_SortedArray_Comparable.java:35)

按升序排序的关键字序列:  7 21 32 39 43 48 66 77 78 79
*/
