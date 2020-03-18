package chapter2.cirdoublylinkedlist;

import chapter2.cirsinglylinkedlist.CirSinglyLinkedList;

/**
 * Created by lili on 2017/7/1.
 */
//排序循环双链表

public class SortedDoublyLinkedList_ex
{
    public static Integer[] random(int n)              //返回产生n个随机数的数组
    {
        Integer[] elements = new Integer[n];
        for (int i=0; i<n; i++)
            elements[i] = (int)(Math.random()*100);   //产生随机数
        return elements;
    }

    public static void main(String args[])
    {
/*		SortedDoublyLinkedList<Integer> list1 = new SortedDoublyLinkedList<Integer>(random(9));
//        list1.insert(-1, -1);                    //覆盖单链表类的方法，没有执行操作
//        list1.insert(-2);                        //插入指定值结点，调用排序单链表类的方法
        System.out.println("list1: "+list1.toString());
/*        SortedDoublyLinkedList<Integer> list2 = new SortedDoublyLinkedList<Integer>(list1);//深拷贝
        System.out.println("list2: "+list2.toString());
        list1.remove(list1.length()-1);          //删除最后结点，参数类型是int，调用单链表类的方法
        list1.remove(list1.get(0));              //删除首个结点，参数类型是Integer，调用排序单链表类的方法
        list1.remove(new Integer(50));           //删除指定值结点，可能没删除
        System.out.println("list1: "+list1.toString());
        list1.printPrevious();
        System.out.println("list2: "+list2.toString());
        list2.printPrevious();
*/
        //习题2
        CirSinglyLinkedList<Integer> list3 = new CirSinglyLinkedList<Integer>(random(9));//单链表
        System.out.println("list3: "+list3.toString());
        SortedDoublyLinkedList<Integer> list4=new SortedDoublyLinkedList<Integer>(list3);//由单链表构造排序循环双链表
        System.out.println("list4: "+list4.toString());
/*
        //第9章，
        list3.merge(list4);                           //归并两条排序循环双链表
        System.out.println("归并，list3: "+list3.toString());
        list3.printPrevious();
        System.out.println("list4: "+list4.toString());

/*       //第9章，  由循环双链表list构造排序循环双链表，直接选择排序
        CirDoublyLinkedList<Integer> list1 = new CirDoublyLinkedList<Integer>(random(9));
        System.out.println("list1: "+list1.toString());
        SortedDoublyLinkedList<Integer> list2 = new SortedDoublyLinkedList<Integer>(list1);
        System.out.println("list2: "+list2.toString());
        list2.printPrevious();*/
    }
}
/*
程序运行结果如下：
list1: (-2, 0, 1, 4, 8, 34, 38, 67, 86, 88)
list2: (-2, 0, 1, 4, 8, 34, 38, 67, 86, 88)
list1: (0, 1, 4, 8, 34, 38, 67, 86)
(86, 67, 38, 34, 8, 4, 1, 0)
list2: (-2, 0, 1, 4, 8, 34, 38, 67, 86, 88)
(88, 86, 67, 38, 34, 8, 4, 1, 0, -2)
list3: (45, 6, 37, 8, 19, 86, 26, 77, 22)
list4: (6, 8, 19, 22, 26, 37, 45, 77, 86)

list3: (71,53,51,53,20,0,74,34,94)
list4: (0,20,34,51,53,53,71,74,94)

*/