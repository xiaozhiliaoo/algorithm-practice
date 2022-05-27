package chapter2.cirsinglylinkedlist;

import chapter2.singlylinkedlist.Node;
import chapter2.singlylinkedlist.SinglyLinkedList;

/**
 * Created by lili on 2017/7/1.
 */
//import java.util.Iterator;                       //导入迭代器接口

//循环单链表类，实现线性表接口
public class CirSinglyLinkedList<T> //extends AbstractList<T> implements LList<T>
{
    public Node<T> head;                              //头指针，指向循环单链表的头结点

    public CirSinglyLinkedList()                      //默认构造方法，构造空循环单链表
    {
        this.head = new Node<T>();                    //创建头结点
        this.head.next = this.head;
    }
    public boolean isEmpty()                          //判断循环单链表是否空
    {
        return this.head.next==this.head;
    }

    public String toString()                          //返回循环单链表所有元素的描述字符串
    {
        String str="(";
        Node<T> p = this.head.next;
        while (p!=this.head)                          //遍历单链表的循环条件改变了
        {
            str += p.data.toString();
            if (p!=this.head)
                str += ", ";                          //不是最后一个结点时后加分隔符
            p = p.next;
        }
        return str+")";                               //空表返回()
    }

    //习题2
    //由element数组中的多个对象构造单链表。采用尾插入构造单链表
    public CirSinglyLinkedList(T[] element)
    {
        this();                                            //创建空单链表，只有头结点
        Node<T> rear=this.head;                            //rear指向单链表最后一个结点
        for (int i=0; i<element.length; i++)               //若element==null，抛出空对象异常
        {                                                  //若element.length==0，构造空链表
            rear.next=new Node<T>(element[i], this.head);  //尾插入，创建结点链入rear结点之后
            rear = rear.next;                              //rear指向新的链尾结点
        }
    }

    /*不能，因为this.head未初始化，不是null
    public CirSinglyLinkedList()                      //默认构造方法，构造空循环单链表
    {
        this.head = new Node<T>(null, this.head);     //创建头结点
    }*/

    public int length()                               //返回循环单链表长度，单链表遍历算法，O(n)
    {
        int i=0;
        for (Node<T> p=this.head.next;  p!=this.head;  p=p.next)
            i++;
        return i;
    }

    public T get(int i)                  //返回第i（≥0）个元素，若i<0或大于表长则返回null，O(n)
    {
        if (i>=0)
        {
            Node<T> p=this.head.next;
            for (int j=0; p!=this.head && j<i; j++)
                p = p.next;
            if (p!=this.head)
                return p.data;                             //p指向第i个结点
        }
        return null;                                       //当i<0或大于表长时
    }

    //设置第i（≥0）个元素值为x。若i<0或大于表长则抛出序号越界异常；若x==null，不操作。O(n)
    public void set(int i, T x)
    {
        if (x==null)  return;                              //不能设置空对象
        Node<T> p=this.head.next;
        for (int j=0; p!=this.head && j<i; j++)
            p = p.next;
        if (i>=0 && p!=this.head)
            p.data = x;                                    //p指向第i个结点
        else throw new IndexOutOfBoundsException(i+"");    //抛出序号越界异常
    }

    //以下方法对两条循环单链表进行操作
    public CirSinglyLinkedList(CirSinglyLinkedList<T> list)//循环单链表深拷贝构造方法
    {
        this();                                            //创建空循环单链表，只有头结点
        Node<T> rear = this.head;
        for (Node<T> p=list.head.next;  p!=list.head;  p=p.next)
        {
            rear.next = new Node<T>(p.data, this.head);
            rear = rear.next;
        }
//      rear.next = this.head;
    }
    public CirSinglyLinkedList(SinglyLinkedList<T> list)   //深拷贝，以单链表list构造循环单链表
    {
        this();                                            //创建空循环单链表，只有头结点
        Node<T> rear = this.head;
        for (Node<T> p=list.head.next;  p!=list.head;  p=p.next)
        {
            rear.next = new Node<T>(p.data, this.head);
            rear = rear.next;
        }
    }
}


