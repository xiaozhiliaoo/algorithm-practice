package chapter2.cirdoublylinkedlist;

import chapter2.cirsinglylinkedlist.CirSinglyLinkedList;
import chapter2.singlylinkedlist.Node;
import chapter2.singlylinkedlist.SinglyLinkedList;

/**
 * Created by lili on 2017/7/1.
 */

//按升序排序的循环双链表类，继承循环双链表类，E必须实现Comparable<T>接口，支持对象可比较大小
public class SortedDoublyLinkedList<T extends Comparable<T>> extends CirDoublyLinkedList<T>
{
    public SortedDoublyLinkedList()              //默认构造方法，构造空循环双链表
    {
        super();                                 //调用父类默认构造方法，构造空循环双链表，默认调用，可省略
    }
    public SortedDoublyLinkedList(T[] element)   //将数组中所有对象插入构造排序单链表
    {
        super();
        for (int i=0; i<element.length; i++)
            this.insert(element[i]);             //插入，根据值的大小决定插入位置
    }

    public void insert(T x)                      //根据指定对象的大小插入在合适位置
    {
        if (x==null)  return;                    //不能插入空对象
        if (this.head.pred!=head && this.head.pred.data.compareTo(x)<0)
        {                                        //最大值插入在头结点之前，即插入在链尾，O(1)
            DLinkNode<T> q = new DLinkNode<T>(x, head.pred, head);
            head.pred.next = q;
            head.pred = q;
        }
        DLinkNode<T> p=this.head.next;
        while (p!=head && p.data.compareTo(x)<0)           //寻找插入位置
            p = p.next;
        DLinkNode<T> q = new DLinkNode<T>(x, p.pred, p);   //插入在p结点之前
        p.pred.next = q;
        p.pred = q;
    }

    //不支持父类的insert(int i, T x)和append(T x)方法，将其覆盖并抛出异常。
    public void insert(int i, T x)
    {
        throw new UnsupportedOperationException("insert(int i, T x)"); //本类不支持该方法
    }
    public void append(T x)
    {
        throw new UnsupportedOperationException("append(T x)");    //本类不支持该方法
    }

    public void remove(T x)                      //删除首次出现的值为x结点，若没找到指定结点则不删除。O(n)
    {
        if (x==null)
            return;
        DLinkNode<T> p=this.head.next;
        while (p!=head && p.data.compareTo(x)<0) //将p定位到待删除的结点
            p = p.next;
        if (p!=head && p.data.compareTo(x)==0)
        {
            p.pred.next = p.next;                //删除p结点自己
            p.next.pred = p.pred;
        }
    }

    //深拷贝构造方法，复制排序双链表list
    public SortedDoublyLinkedList(SortedDoublyLinkedList<T> list)
    {
        super(list);                             //调用父类同参数的构造方法，不可省略
    }

    //习题2
    //深拷贝构造方法，由单链表list构造排序循环双链表。直接插入排序，算法同由单链表构造排序单链表
    public SortedDoublyLinkedList(SinglyLinkedList<T> list)
    {
        super();                                           //创建空循环双链表
        for (Node<T> p = list.head.next; p!=null; p=p.next)
            this.insert(p.data);                           //插入，根据值的大小决定插入位置
    }

    //深拷贝构造方法，由循环单链表list构造排序循环双链表。
    //算法不调用insert(x)方法，从双链表中某个结点p开始查找插入位置，如果插入值较大，则p向后走；否则向前走。
    public SortedDoublyLinkedList(CirSinglyLinkedList<T> list)
    {
        super();                                           //创建空循环双链表
        DLinkNode<T> p=this.head;
        for (Node<T> q=list.head.next;  q!=list.head;  q=q.next)//插入q.data，根据值的大小决定插入位置
        {   //将list单链表中结点依次逐个复制插入到this单链表中，根据q.data值的大小决定插入位置
            DLinkNode<T> t;
            if (p!=this.head && q.data.compareTo(p.data)>0)
            {   while (p!=this.head && q.data.compareTo(p.data)>0)   //q.data较大，p向后走
                p=p.next;
                t = new DLinkNode<T>(q.data, p.pred, p);             //t插入在p结点之前
                p.pred.next = t;
                p.pred = t;
            }
            else
            {   while (p!=this.head && q.data.compareTo(p.data)<0)   //q.data较小，p向前走
                p=p.pred;
                t = new DLinkNode<T>(q.data, p, p.next);             //t插入在p结点之后
                p.next.pred = t;
                p.next = t;
            }
            p=t;
//            System.out.println(this.toString()+"，p="+p.data.toString());
        }
    }

    //习题9，深拷贝构造方法，由循环双链表list构造排序循环双链表，直接选择排序，删除再插入算法
/*    public SortedDoublyLinkedList(CirDoublyLinkedList<T> list)
    {
        super(list);                                       //深拷贝list循环双链表
        DLinkNode<T> srear=head;                           //指向排序循环双链表尾
        while (srear.next!=head)                           //原循环双链表不空
        {
            DLinkNode<T> min=srear.next;                   //min指向最小值结点
            for (DLinkNode<T> p=min.next;  p!=head;  p=p.next) //p遍历循环双链表
                if (p.data.compareTo(min.data)<0)          //比较，min记住最小值结点
                    min = p;
            if (min.pred!=srear)
            {
                min.pred.next = min.next;                  //从链表原位置删除min结点
                min.next.pred = min.pred;
                min.next=srear.next;                       //将min结点插入srear之后
                min.pred=srear;
                srear.next.pred = min;
                srear.next = min;
            }
            srear = min;                                   //srear指向排序循环双链表尾
        }
    }*/

    //习题9，深拷贝构造方法，由循环双链表list构造排序循环双链表，直接选择排序，交换元素算法
    //n-1趟，每趟遍历单链表寻找到最小值结点，交换结点元素到前面，不删除和插入结点。算法同排序单链表
    public SortedDoublyLinkedList(CirDoublyLinkedList<T> list)
    {
        super(list);                                       //深拷贝list循环双链表
        for (DLinkNode<T> first=head.next;  first!=head;  first=first.next) //first指向待排序双链表第一个结点
        {                              //n-1趟，每趟遍历双链表寻找到最小值结点，交换结点元素到前面
            DLinkNode<T> min=first;                        //min指向最小值结点
            for (DLinkNode<T> p=min.next;  p!=head;  p=p.next) //p遍历循环双链表一趟，找出最小值结点
                if (p.data.compareTo(min.data)<0)          //比较，min记住最小值结点
                    min = p;
            if (min!=first)                                //交换min结点元素到前面
            {
                T temp = min.data;
                min.data = first.data;
                first.data = temp;
            }
            System.out.println(this.toString());
        }
    }

    //习题9，归并两条排序循环双链表，将list中所有结点归并到当前排序循环双链表中，合并后设置list为空
    public void merge(SortedDoublyLinkedList<T> list)
    {
        DLinkNode<T> p=this.head.next;
        DLinkNode<T> q=list.head.next;
        while (p!=this.head && q!=list.head)
            if ((p.data).compareTo(q.data)<0)         //比较两个链表当前结点值
                p = p.next;
            else
            {                                         //将q结点插入到结点之前
                q.pred = p.pred;
                p.pred.next = q;
                p.pred = q;
                q = q.next;
                q.pred.next = p;
            }
        if (q!=list.head)                             //将list链表中剩余结点并入当前链表尾
        {
            this.head.pred.next = q;
            q.pred = this.head.pred;
            while (q.next!=list.head)
                q = q.next;
            q.next = this.head;
            this.head.pred = q;
        }
        list.head.next=list.head;                     //合并后设置list为空
        list.head.pred=list.head;
    }
}
