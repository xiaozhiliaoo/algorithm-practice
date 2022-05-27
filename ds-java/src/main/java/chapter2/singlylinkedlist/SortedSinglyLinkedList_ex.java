package chapter2.singlylinkedlist;

/**
 * Created by lili on 2017/7/1.
 */
//例2.5 排序单链表

public class SortedSinglyLinkedList_ex
{
    public static Integer[] random(int n)              //返回产生n个随机数的数组，同例2.3
    {
        Integer[] elements = new Integer[n];
        for (int i=0; i<n; i++)
            elements[i] = (int)(Math.random()*100);   //产生随机数
        return elements;
    }

    public static void main(String args[])
    {
/*        //例2.5
        SortedSinglyLinkedList<Integer> list1 = new SortedSinglyLinkedList<Integer>(random(9));
//        list1.insert(-1, -1);                    //覆盖单链表类的方法，抛出异常
        list1.insert(-2);                        //插入指定值结点，调用排序单链表类的方法
        System.out.println("list1: "+list1.toString());
        SortedSinglyLinkedList<Integer> list2=new SortedSinglyLinkedList<Integer>(list1);//深拷贝
        System.out.println("list2: "+list2.toString());
        list1.remove(list1.length()-1);          //删除最后结点，参数类型是int，调用单链表类的方法
        list1.remove(list1.get(0));              //删除首个结点，参数类型是Integer，调用排序单链表类的方法
        list1.remove(new Integer(50));           //删除指定值结点，可能没删除
        System.out.println("list1: "+list1.toString());
        System.out.println("list2: "+list2.toString());

        //习题2
        SinglyLinkedList<Integer> list3 = new SinglyLinkedList<Integer>(random(9));//单链表
        System.out.println("list3: "+list3.toString());
        SortedSinglyLinkedList<Integer> list4=new SortedSinglyLinkedList<Integer>(list3);//由单链表构造排序单链表
        System.out.println("list4: "+list4.toString());*/


        //第9章，由单链表list构造排序单链表，直接选择排序
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<Integer>(random(7));
        System.out.println("单链表list1: "+list1.toString());
        SortedSinglyLinkedList<Integer> list2 = new SortedSinglyLinkedList<Integer>(list1);
        System.out.println("排序单链表list2: "+list2.toString());
//        list1.set(0, new Integer(list1.get(0).intValue()+100));
//        System.out.println("list1: "+list1.toString());
//        System.out.println("list2: "+list2.toString());
//      list1.merge(list2);                           //归并两条排序单链表
//      System.out.println("归并，list1: "+list1.toString());
//      System.out.println("list2: "+list2.toString());
    }
}
/*
程序运行结果如下：
list1: (-2, 1, 21, 21, 23, 44, 59, 65, 83, 95)
list2: (-2, 1, 21, 21, 23, 44, 59, 65, 83, 95)
list1: (1, 21, 21, 23, 44, 59, 65, 83)
list2: (-2, 1, 21, 21, 23, 44, 59, 65, 83, 95)
//习题2
list3: (92, 98, 45, 74, 66, 0, 87, 45, 2)
list4: (0, 2, 45, 45, 66, 74, 87, 92, 98)



归并，list1: (11, 12, 20, 24, 29, 34, 35, 40, 51, 55, 78, 86, 89)
list2: ()

list1: (1, 7, 8, 34, 36)
list2: (27, 78, 85, 90)
归并，list1: (1, 7, 8, 27, 34, 36, 78, 85, 90)
list2: ()

//第9章，由单链表list构造排序单链表，选择排序
list1: (95, 22, 99, 9, 28, 67, 71, 82, 86)
list2: (9, 22, 28, 67, 71, 82, 86, 95, 99)

list1: (70, 45, 53, 48, 11, 1, 4)
list2: (1, 4, 11, 45, 48, 53, 70)
list1: (170, 45, 53, 48, 11, 1, 4)
list2: (1, 4, 11, 45, 48, 53, 70)

//第4版改进程序
单链表list1: (95,79,41,26,45,92,82)
(26,79,41,95,45,92,82)
(26,41,79,95,45,92,82)
(26,41,45,95,79,92,82)
(26,41,45,79,95,92,82)
(26,41,45,79,82,92,95)
(26,41,45,79,82,92,95)
(26,41,45,79,82,92,95)
排序单链表list2: (26,41,45,79,82,92,95)

程序调试情况如下：
        SinglyLinkedList<Object> list3 = new SinglyLinkedList<Object>();
//        SortedSinglyLinkedList<Object> list4 = new SortedSinglyLinkedList<Object>();
                                         //编译错，Object类不能作为E的实际参数，没有实现实现Comparable<E>接口
        SortedSinglyLinkedList<Integer> list4 = new SortedSinglyLinkedList<Integer>();
//        list4.insert(new Object());     //编译错，参数类型不匹配
*/

