package chapter2.singlylinkedlist;

/**
 * Created by lili on 2017/7/1.
 */
//例2.4 单链表逆转

public class SinglyLinkedList_reverse
{
    //将单链表逆转，泛型方法，返回值类型前声明类型参数T
    public static <T> void reverse(SinglyLinkedList<T> list)
    {
        Node<T> p=list.head.next, succ=null, front=null;   //head必须声明为public
        while (p!=null)
        {
            succ = p.next;                                 //设置succ是p结点的后继结点
            p.next = front;                                //使p.next指向p结点的前驱结点
            front = p;
            p = succ;                                      //p向后走一步
        }
        list.head.next = front;
    }

    public static void main(String args[])
    {
        String value[]={"A","B","C","D","E","F"};
        SinglyLinkedList<String> list = new SinglyLinkedList<String>(value);
        System.out.println("list: "+list.toString());
        reverse(list);
        System.out.println("逆转后 "+list.toString());
    }
}
/*
程序运行结果如下：
list: (A, B, C, D, E, F)
逆转后 (F, E, D, C, B, A)

*/

