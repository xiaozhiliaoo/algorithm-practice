package chapter2.singlylinkedlist;

/**
 * Created by lili on 2017/7/1.
 */
//单链表

import java.util.Iterator;

public class SinglyLinkedList_ex
{
    public static void main(String args[])
    {
/*    	SinglyLinkedList<String> lista = new SinglyLinkedList<String>();
//        for (int i=5; i>=0; i--)
        for (int i=0; i<=5; i++)
            lista.insert(i, new String((char)('A'+i)+""));
        System.out.println("lista: "+lista.toString()+"，length()="+lista.length());
        lista.set(3, new String((char)(lista.get(0).charAt(0)+32)+""));
        lista.remove(0);
        lista.remove(3);
//        lista.remove(10);                             //抛出异常
        System.out.println("lista: "+lista.toString());
*/

        //深拷贝与比较相等
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<Integer>();   //空表
        System.out.println("list1: "+list1.toString());
        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<Integer>();   //空表
        System.out.println("list2: "+list2.toString());
        System.out.println("list1.equals(list2)? "+list1.equals(list2));

        list1 = new SinglyLinkedList<Integer>(SinglyLinkedList_average.random(5));  //返回产生n个随机数的数组
        System.out.println("list1: "+list1.toString());
        list2 = new SinglyLinkedList<Integer>(list1);               //拷贝构造方法
        System.out.println("list2: "+list2.toString());
        System.out.println("list1.equals(list2)? "+list1.equals(list2));

        System.out.println("list1: "+list1.toString());
        list2.set(0, new Integer(list1.get(0).intValue()+100));
        list2.remove(list2.length()-1);                    //删除最后一个元素
        list2.remove(100);                                 //序号越界，没删除
        System.out.println("list2: "+list2.toString());
        System.out.println("list1.equals(list2)? "+list1.equals(list2));

        //10.2   实现迭代器
        Iterator<Integer> it = list1.iterator();      //获得单链表迭代器对象
        int sum=0;
        while (it.hasNext())
        {
            int value=it.next().intValue();
            sum += value;
            System.out.print(value);
            if (it.hasNext())
                System.out.print("+");
        }
        System.out.println("="+sum);
    }
}

/*
程序运行结果如下：
lista: (A, F, B, E, C, D)
lista: (F, B, e, D)

        //深拷贝与比较相等
list1: ()
list2: ()
list1.equals(list2)? true
list1: (44, 10, 11, 20, 72)
list2: (44, 10, 11, 20, 72)
list1.equals(list2)? true
list1: (44, 10, 11, 20, 72)
list2: (144, 10, 11, 20)
list1.equals(list2)? false
44+10+11+20+72=157


*/

