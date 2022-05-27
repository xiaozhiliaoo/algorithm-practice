package chapter4.stack;

import chapter2.singlylinkedlist.Node;

/**
 * Created by lili on 2017/7/1.
 */
//4.1.3   链式栈

public class LinkedStack<T> implements SStack<T>           //链式栈类，实现栈接口
{
    private Node<T> top;                                   //栈顶结点，单链表结点类声明见第2章

    public LinkedStack()                                   //构造空栈
    {
        this.top=null;
    }
    public boolean isEmpty()                               //判断栈是否空，若空返回true
    {
        return this.top==null;
    }
    public void push(T x)                                  //元素x入栈，空对象不能入栈
    {
        if (x!=null)
            this.top = new Node(x, this.top);              //头插入，x结点作为新的栈顶结点
    }
    public T pop()                                         //出栈，返回栈顶元素，若栈空返回null
    {
        if (this.top==null)
            return null;
        T temp = this.top.data;                            //取栈顶结点元素
        this.top = this.top.next;                          //删除栈顶结点
        return temp;
    }
    public T get()                                         //取栈顶元素，未出栈，若栈空返回null
    {
        return this.top==null ? null : this.top.data;
    }

    //返回栈所有元素的描述字符串，形式为“(,)”。算法同不带头结点的单链表
    public String toString()
    {
        String str="(";
        for (Node<T> p=this.top;  p!=null;  p=p.next)
        {   str += p.data.toString();
            if (p.next!=null)
                str += ", ";                               //不是最后一个结点时后加分隔符
        }
        return str+")";                                    //空表返回()
    }
}

