package chapter2.cirdoublylinkedlist;

/**
 * Created by lili on 2017/7/1.
 */
//双链表结点类

public class DLinkNode<T>                        //双链表结点类
{
    public T data;                               //数据元素
    public DLinkNode<T> pred, next;              //pred指向前驱结点，next指向后继结点

    //构造结点，data指定元素，pred指向前驱结点，next指向后继结点
    public DLinkNode(T data, DLinkNode<T> pred, DLinkNode<T> next)
    {
        this.data = data;
        this.pred = pred;
        this.next = next;
    }
    public DLinkNode()
    {
        this(null, null, null);
    }
}

/*
    public DLinkNode(T data)
    {
        this(data, null, null);
    }
    public String toString()                     //返回结点元素值对应的字符串
    {
        return this.data.toString();
    }
}*/
/*
程序设计设计说明。
1、DLinkNode<T>不能声明如下，因为next类型为Node<T>，而不是DLinkNode<T>。
public class DLinkNode<T> extends Node<T>
{
    public DLinkNode<T> pred;                    //prev指向前驱结点

    public DLinkNode(T data, DLinkNode<T> pred, DLinkNode<T> next)
    {
        super(data, next);                       //错，父类中next类型为Node<T>，结点结构不对
//        this.data = data;
//        this.next = next;                        //错，this.next类型为Node<T>，结点结构不对
        this.pred = pred;
    }
    public DLinkNode()
    {
//        super();                          //错，父类中next类型为Node<T>，结点结构不对，且没有prev域
        this(null, null, null);
    }
}

/*
2、从语法上DLinkNode<T>可以声明如下，但双链表结点与单链表结点，并不是两个具有包含关系的概念。
public class DLinkNode<T> extends Node<T>
{
    public DLinkNode<T> pred, next;              //prev指向前驱结点，next指向后继结点
    public DLinkNode(T data, DLinkNode<T> pred, DLinkNode<T> next)
    {
//        super(data, next);                     //错，父类中next类型为Node<T>，结点结构不对
      super(data, null);                         //对
//        this.data = data;
        this.next = next;                        //对，this.next隐藏super.next（类型为Node<T>）
        this.pred = pred;
    }
    public DLinkNode()
    {
        this(null, null, null);
    }
}
*/
