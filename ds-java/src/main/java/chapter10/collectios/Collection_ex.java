package chapter10.collectios;

/**
 * Created by lili on 2017/7/1.
 */
//10.1.2   Java集合框架

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Collection_ex
{
    public static int sum(Collection<Integer> list)
    {
        Iterator<Integer> it = list.iterator();      //获得迭代器对象
        int s=0;
        while (it.hasNext())
        {
            int value=it.next().intValue();
            s += value;
            System.out.print(value);
            if (it.hasNext())
                System.out.print("+");
        }
        System.out.println("="+s);
        return s;
    }

    public static void main(String args[])
    {
        int n=10;
        ArrayList<Integer> list1 = new ArrayList(n*2);
        for (int i=0; i<n; i++)
            list1.add(new Integer((int)(Math.random()*100)));   //产生随机数
        System.out.print("list1:"+list1.toString()+"，");
        sum(list1);

        LinkedList<Integer> list2 = new LinkedList(list1);
        System.out.print("list2:"+list1.toString()+"，");
        sum(list2);
    }
}
/*
程序运行结果如下：
list1:[14, 91, 1, 4, 10, 67, 66, 42, 30, 76]，14+91+1+4+10+67+66+42+30+76=401
list2:[14, 91, 1, 4, 10, 67, 66, 42, 30, 76]，14+91+1+4+10+67+66+42+30+76=401

*/

