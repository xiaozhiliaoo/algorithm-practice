package chapter2.polynomial;

import chapter2.singlylinkedlist.Node;
import chapter2.singlylinkedlist.SortedSinglyLinkedList;

/**
 * Created by lili on 2017/7/1.
 */
//2.4   线性表的应用：多项式的表示及运算
//2.4.1   一元多项式的表示及运算

//多项式排序单链表类，继承排序单链表类，提供排序单链表的多项式相加运算，
//要求泛型参数T实现java.lang.Comparable可比较接口和Addible可相加接口
public class PolySLinkedList<T extends Comparable<T> & Addible<T>> extends SortedSinglyLinkedList<T>
{
    public PolySLinkedList()                               //默认构造方法
    {
        super();                                           //创建空单链表，执行排序单链表默认构造方法
    }
    public PolySLinkedList(T terms[])                      //由项数组指定多项式各项值
    {
        super(terms);
    }
    public PolySLinkedList(PolySLinkedList<T> polylist)    //深拷贝构造方法
    {
        super(polylist);                                   //单链表深拷贝，复制所有结点，没有复制元素对象
    }

    public void add(PolySLinkedList<T> polylist)           //多项式相加，this＋=polylist
    {
        Node<T> front=this.head, p=front.next;
        Node<T> q=polylist.head.next;
        while (p!=null && q!=null)
            if (p.data.compareTo(q.data)==0)               //两项大小相同
            {
                p.data.add(q.data);                        //两项相加，add()方法由Addible接口约定
                if (p.data.removable())                    //相加后元素满足删除条件
                {                                          //removable()方法由Addible接口约定
                    front.next=p.next;                     //相加后元素不需要存储，删除p结点
                    p=front.next;
                }
                else
                {
                    front = p;                             //front是p的前驱结点
                    p = p.next;
                }
                q = q.next;
            }
            else if (p.data.compareTo(q.data)<0)
            {
                front = p;
                p = p.next;
            }
            else
            {
                front.next = new Node<T>(q.data, p);  //复制q结点并插入到front结点之后
                q = q.next;
            }
        while (q!=null)                                    //将polylist单链表中剩余结点复制并插入到当前链表尾
        {
            front.next = new Node<T>(q.data, null);
            front = front.next;
            q = q.next;
        }
    }
}
/*
public void insert(Term term)                          //插入项
{
    list.insert(term);                                 //在排序单链表中插入结点，插入位置由term项指数决定
}*/
/*
//（2） 多项式相加算法
public Polynomial plus(Polynomial pol)                 //加法＋，C=this＋pol
{
    Polynomial cpol = new Polynomial();
    Node<Term> p=this.list.head.next;
    Node<Term> q=pol.list.head.next;
    Node<Term> rear=cpol.list.head;
    while (p!=null && q!=null)
    {
        if (p.data.compareTo(q.data)==0)               //两项指数相同时
        {
            double sum=p.data.coef + q.data.coef;      //两项系数相加
            if (Math.abs(sum)>0.00001)                 //浮点数是否为0由精度确定
            {
                rear.next=new Node<Term>(new Term(sum, p.data.exp), null);//创建并链接非0系数结点
                rear=rear.next;
            }
            p = p.next;
            q = q.next;
        }
        else if (p.data.compareTo(q.data)<0)
        {
            rear.next = new Node<Term>(p.data, null);  //复制p结点并插入到rear结点之后
            rear=rear.next;
            p = p.next;
        }
        else
        {
            rear.next = new Node<Term>(q.data, null);  //复制q结点并插入到rear结点之后
            rear=rear.next;
            q = q.next;
        }
    }
    if (p==null)
        p=q;
    while (p!=null)                                    //将当前链表或pol链表中剩余结点复制并插入到cpol链表尾
    {
        rear.next = new Node<Term>(p.data, null);
        rear=rear.next;
        p = p.next;
    }
    return cpol;
}
    public PolySLinkedList<T> plus(PolySLinkedList<T> polylist)    //加法＋，C=this＋polylist
    {
    	PolySLinkedList<T> polyc=new PolySLinkedList<T>(this);   //深拷贝
        polyc.add(polylist);
        return polyc;                                      //返回对象引用
    }*/

