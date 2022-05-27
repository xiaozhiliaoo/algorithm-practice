package chapter2.seqlist;

/**
 * Created by lili on 2017/7/1.
 */
//10.2   实现迭代器

import java.util.ListIterator;

public class SeqList_iterator
{
    public static SeqList<Integer> random(int n)              //返回产生n个随机数的数组
    {
        SeqList<Integer> list = new SeqList<Integer>(n*2);
        ListIterator<Integer> it = list.listIterator();      //获得列表迭代器对象
        for (int i=0; i<n; i++)
        {
//          System.out.println("nextIndex="+it.nextIndex());
            it.add(new Integer((int)(Math.random()*100)));   // //产生随机数通过列表迭代器对象添加元素，可以连续添加
        }
        return list;
    }

    public static void sum(SeqList<Integer> list)
    {
        ListIterator<Integer> it = list.listIterator();      //获得列表迭代器对象
        System.out.print("nextIndex="+it.nextIndex()+"，");
        int sum=0, value=0;
        while (it.hasNext())
        {
            value = it.next().intValue();
            sum += value;
            System.out.print(value);
            if (it.hasNext())
                System.out.print("+");
        }
        System.out.println("="+sum+"，nextIndex="+it.nextIndex());
//        it.next();                    //抛出java.util.NoSuchElementException

        sum=0;
        System.out.print("nextIndex="+it.previousIndex()+"，");
        while (it.hasPrevious())                           //仅作用于列表迭代器对象
        {
            value=it.previous().intValue();
            sum += value;
            System.out.print(value);
            if (it.hasPrevious())
                System.out.print("+");
        }
        System.out.println("="+sum+"，nextIndex="+it.previousIndex());
//        it.previous();                    //抛出java.util.NoSuchElementException
    }

    public static void main(String args[])
    {
        SeqList<Integer> list1 = new SeqList<Integer>();             //空表
        System.out.println("list1:"+list1.toString()+"，");
        sum(list1);

        list1 = random(10);
//        list1 =  new SeqList(SeqList_ex.random(10));
        System.out.println("list1:"+list1.toString()+"，");
        sum(list1);

        SeqList<Integer> list2 = new SeqList<Integer>(list1);    	//深拷贝
        System.out.println("list2:"+list2.toString()+"，");
        ListIterator<Integer> lit = list2.listIterator();  //获得列表迭代器对象
        System.out.print("nextIndex="+lit.nextIndex()+"，");
        Integer value = lit.next();
        lit.remove();                                       //删除第一个元素
        //若不执行it.next();，则抛出java.lang.IllegalStateException异常
        System.out.println("删除"+value+"，list2:"+list2.toString()+"， ");
        System.out.print("nextIndex="+lit.nextIndex()+"，");
        value = lit.next();
        lit.remove();                                       //删除第一个元素
        System.out.println("删除"+value+"，list2:"+list2.toString());
        sum(list2);                              //同时有两个迭代器

        System.out.print("nextIndex="+lit.nextIndex()+"，");
//        lit.previous();
        lit.add(value);                                    //插入第一个元素
        //若先执行it.previous();，则抛出java.lang.IllegalStateException异常
        System.out.println("插入"+value+"，list2:"+list2.toString());

        System.out.print("nextIndex="+lit.nextIndex()+"，");
        value = new Integer(100);
        lit.next();
        lit.set(value);                    //将集合当前元素替换为x
        System.out.println("替换"+value+"，list2:"+list2.toString());
        value = new Integer(200);
        lit.set(value);                    //可以连续替换
        System.out.println("替换"+value+"，list2:"+list2.toString());
    }
}
/*
程序运行结果如下：
list1:() ，
nextIndex=0，=0，nextIndex=0
nextIndex=-1，=0，nextIndex=-1
list1:(86, 47, 24, 31, 62, 16, 23, 42, 34, 29) ，
nextIndex=0，86+47+24+31+62+16+23+42+34+29=394，nextIndex=10
nextIndex=9，29+34+42+23+16+62+31+24+47+86=394，nextIndex=-1
list2:(86, 47, 24, 31, 62, 16, 23, 42, 34, 29) ，
nextIndex=0，删除86，list2:(47, 24, 31, 62, 16, 23, 42, 34, 29) ，
nextIndex=0，删除47，list2:(24, 31, 62, 16, 23, 42, 34, 29)
nextIndex=0，24+31+62+16+23+42+34+29=261，nextIndex=8
nextIndex=7，29+34+42+23+16+62+31+24=261，nextIndex=-1
nextIndex=0，插入47，list2:(47, 24, 31, 62, 16, 23, 42, 34, 29)
nextIndex=1，替换100，list2:(47, 100, 31, 62, 16, 23, 42, 34, 29)
替换200，list2:(47, 200, 31, 62, 16, 23, 42, 34, 29)

*/


