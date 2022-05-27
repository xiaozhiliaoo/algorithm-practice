package chapter10.collectios;

/**
 * Created by lili on 2017/7/1.
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class TreeSet_ex
{
    public static TreeSet<Integer> random(int n)              //返回产生n个随机数的数组
    {
        TreeSet<Integer> treeset = new TreeSet<Integer>();
        System.out.print("随机数：");
        for (int i=0; i<n; i++)
        {
            int value = (int)(Math.random()*100); // //产生随机数通过列表迭代器对象添加元素，可以连续添加
            System.out.print(value+" ");
            treeset.add(new Integer(value));
        }
        System.out.println();
        return treeset;
    }
    public static void ptint(Collection<Integer> coll)
    {
        Iterator<Integer> it = coll.iterator();      //迭代器对象
        System.out.print("集合：");
        while (it.hasNext())
            System.out.print(it.next().toString()+" ");
        System.out.println();
    }
    public static void main(String args[])
    {
        TreeSet<Integer> treeset = random(10);
        ptint(treeset);                     //TreeSet<Integer>是Collection<Integer>的子类
    }
}
/*
程序运行结果如下：
随机数：63 46 86 35 64 80 37 39 29 70
集合：29 35 37 39 46 63 64 70 80 86                       //排序

*/