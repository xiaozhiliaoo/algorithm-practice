package chapter2.singlylinkedlist;

/**
 * Created by lili on 2017/7/1.
 */
//【例2.4】  建立按升序排序的单链表。

//按升序排序的单链表类，继承单链表类，T必须实现Comparable<T>接口，支持对象可比较大小
public class SortedSinglyLinkedList<T extends Comparable<T>> extends SinglyLinkedList<T>
{
    public SortedSinglyLinkedList()                        //默认构造方法
    {
        super();                                           //调用父类默认构造方法，默认调用，可省略
    }

    //将elements数组中所有对象插入构造排序的单链表，直接插入排序
    public SortedSinglyLinkedList(T[] element)
    {
        super();                                           //创建空单链表，调用父类默认构造方法，默认调用，可省略
        for (int i=0; i<element.length; i++)               //若element==null，抛出空对象异常
            this.insert(element[i]);                       //插入一个结点，根据值的大小决定插入位置
    }

    //插入值为x结点，根据x值大小插入在合适位置
    //重载父类的insert(i,x)方法，因参数不同没有覆盖父类的insert(i,x)方法
    public void insert(T x)
    {
        if (x==null)
            return;                                         //不能插入空对象
        Node<T> front=this.head, p=front.next;             //front是p的前驱结点
        while (p!=null && p.data.compareTo(x)<0)           //寻找插入位置
        {
            front = p;
            p = p.next;
        }
        front.next = new Node<T>(x, p);                    //创建结点插入在front之后，p之前
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

    //删除首次出现的值为x结点，若没找到指定结点则不删除。O(n)
    //重载父类的remove(T x)方法，因参数不同没有覆盖父类的remove(T x)方法
    public void remove(T x)
    {
        if (x==null)
            return;
        Node<T> front=this.head, p=front.next;        //front是p的前驱结点
        while (p!=null && p.data.compareTo(x)<0)      //寻找待删除的结点
        {
            front = p;
            p = p.next;
        }
        if (p!=null && p.data.compareTo(x)==0)
            front.next = p.next;                      //删除p结点
    }

    //深拷贝构造方法，复制单链表
    public SortedSinglyLinkedList(SortedSinglyLinkedList<T> list)
    {
        super(list);                             //调用父类同参数的构造方法，不可省略
    }

    //习题2，第9章，深拷贝构造方法，由单链表list构造排序单链表，直接插入排序
/*    public SortedSinglyLinkedList(SinglyLinkedList<T> list)
    {
        super();                                 //创建空单链表
        for (Node<T> p=list.head.next;  p!=null;  p=p.next)
            this.insert(p.data);                 //根据值的大小决定插入位置，调用排序单链表类的插入方法
    }*/


    //以下第5章 5.2.2 顺序查找，稀疏矩阵行的单链表用

    //顺序查找关键字为key元素，返回首次出现的元素，若查找不成功返回null
    //key可以只包含关键字数据项，由T类的compareTo()提供比较对象大小和相等的依据
    //覆盖父类SinglyLinkedList的同名方法
    public T search(T key)
    {
        if (key==null)
            return null;
        for (Node<T> p=this.head.next;  p!=null && p.data.compareTo(key)<=0;  p=p.next)
            if (p.data.compareTo(key)==0)        //由compareTo()提供比较对象大小和相等的依据
                return p.data;
        return null;
    }

    //第9章，深拷贝构造方法，由单链表list构造排序单链表，直接选择排序
/*    //第3版程序，每趟寻找最小值结点，交换到前面，通过删除、插入结点方式实现交换
    public SortedSinglyLinkedList(SinglyLinkedList<T> list)
    {
        super(list);                                       //深拷贝list单链表
        Node<T> srear=head;                                //指向排序单链表尾
        while (srear.next!=null)                           //原单链表不空
        {
            Node<T> mfront=srear, min=mfront.next;         //min指向最小值结点，mfront指向min的前驱
            Node<T> pfront=min, p=min.next;                //p遍历单链表，pfront指向p的前驱结点
            while (p!=null)
            {
                if (p.data.compareTo(min.data)<0)          //比较，min记住最小值结点
                {   mfront = pfront;                       //mfront是min的前驱结点
                    min = p;
                }
                pfront = p;                                //pfront是p的前驱结点
                p = p.next;
            }
            if (mfront!=srear)
            {
                mfront.next = min.next;                    //从链表原位置删除min结点
                min.next=srear.next;                       //将min结点插入srear之后
                srear.next = min;
            }
            srear = min;                                   //srear指向排序单链表尾
        }
    }*/

    //第9章，深拷贝构造方法，由单链表list构造排序单链表，直接选择排序
    //第4版改进程序，n-1趟，每趟遍历单链表寻找到最小值结点，交换结点元素到前面，不删除和插入结点
    public SortedSinglyLinkedList(SinglyLinkedList<T> list)
    {
        super(list);                                       //深拷贝list单链表
        for (Node<T> first=head.next;  first!=null;  first=first.next) //first指向待排序单链表第一个结点
        {                              //n-1趟，每趟遍历单链表寻找到最小值结点，交换结点元素到前面
            Node<T> min=first;                             //min指向最小值结点
            for (Node<T> p=min.next;  p!=null;  p=p.next)  //遍历单链表一趟，找出最小值结点
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

    //第9章，归并两条排序的单链表，将list中所有结点归并到当前排序单链表中，合并后设置list为空
    public void merge(SortedSinglyLinkedList<T> list)
    {
        Node<T> front=this.head, p=front.next;   //p指向this单链表的第一个结点
        Node<T> q=list.head.next;                //q指向list单链表的第一个结点
        while (p!=null && q!=null)
            if ((p.data).compareTo(q.data)<0)    //比较两个单链表当前结点值
            {
                front = p;                       //front指向p的前驱结点
                p = p.next;
            }
            else
            {                                    //将q结点插入到front结点之后
                front.next = q;
                q = q.next;
                front = front.next;
                front.next = p;
            }
        if (q!=null)                             //将list链表中剩余结点并入当前链表尾
            front.next=q;
        list.head.next=null;                     //设置list单链表设置为空链表
    }
}

