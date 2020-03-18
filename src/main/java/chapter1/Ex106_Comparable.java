//【例1.6】 排序算法及其必要性。
//(2) 直接插入排序算法，对象
//习题1
package chapter1;
public class Ex106_Comparable
{
    //将元素x插入到value对象数组前n个元素中，插入位置由x值大小决定
    public static<T> void insert(Comparable<T> value[], int n, T x)
    {
        int i=0;
        while (i<n && value[i].compareTo(x)<=0)            //顺序查找x的插入位置
            i++;
        for (int j=n-1; j>=i; j--)
            value[j+1]=value[j];                           //元素向后移动
        value[i]=(Comparable<T>)x;                         //i是x的插入位置
    }

    public static Integer[] randomSortedInteger(int n)     //返回由n个随机数组成的排序整数对象数组
    {
        Integer[] value = new Integer[n];
        for (int i=0; i<value.length; i++)
            insert(value, i, new Integer((int)(Math.random()*100)));
        //将一个随机整数对象按升序插入到value数组前i个元素中
        return value;
    }

    public static String[] randomSortedString(int n)       //返回由n个随机字符组成的排序字符串数组
    {
        String[] value = new String[n];
        for (int i=0; i<value.length; i++)
            insert(value, i, (char)(Math.random()*26+'A')+"");  //大写字母字符串
        return value;
    }

    public static void main(String[] args)
    {
        Comparable value[] = randomSortedInteger(10);     //按升序排序整数对象数组
        System.out.print("按升序排序的关键字序列: ");
        Ex103_ArraySearch_Object.print(value);
        Object key = new Integer(49);
        System.out.println("顺序查找 "+key+", "+((Ex104_SortedArray_Comparable.indexOf(value,key)==-1)?"不":"")+"成功");

        value = randomSortedString(10);                   //按升序排序字符串数组
        System.out.print("按升序排序的关键字序列: ");
        Ex103_ArraySearch_Object.print(value);
        key = "Q";
        System.out.println("顺序查找 "+key+", "+((Ex104_SortedArray_Comparable.indexOf(value,key)==-1)?"不":"")+"成功");
    }

    //返回由n个随机数组成的一个排序整数对象数组
    public static Integer[] randomDifferentSortedInteger(int n)
    {
        Integer[] value = new Integer[n];
        for (int i=0; i<value.length; i++)
            insert(value, i, new Integer((int)(Math.random()*100)));
        //将一个随机整数对象按升序插入到value数组前i个元素中
        return value;
    }
}
/*
程序运行结果如下：
按升序排序的关键字序列:  2 7 24 35 48 50 60 71 82 87
2? 7? 24? 35? 48? 顺序查找 49, 不成功
按升序排序的关键字序列:  A C C D J J R T T Y
A? C? C? D? J? J? 顺序查找 Q, 不成功

*/
/*也可
Comparable<Integer> value[] = randomSortedInteger(10); //按升序排序
System.out.print("按升序排序的关键字序列: ");
Ex103_ArraySearch_Object.print(value);
Integer key = new Integer(49);
System.out.println("顺序查找 "+key+", "+((Ex104_SortedArray_Comparable.indexOf(value,key)==-1)?"不":"")+"成功");
*/
