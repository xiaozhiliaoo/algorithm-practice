package chapter2.cirdoublylinkedlist;

import chapter10.iterator.AbstractLList;
import chapter2.cirsinglylinkedlist.CirSinglyLinkedList;
import chapter2.seqlist.LList;
import chapter2.singlylinkedlist.Node;
import chapter2.singlylinkedlist.SinglyLinkedList;

/**
 * Created by lili on 2017/7/1.
 */
//2.3.3   双链表
//3. 循环双链表
//public class CirDoublyLinkedList<T> implements LList<T>    //循环双链表类
public class CirDoublyLinkedList<T> extends AbstractLList<T> implements LList<T>//第10章，10.2 实现迭代器
{
    public DLinkNode<T> head;                    //头指针

    public CirDoublyLinkedList()                 //默认构造方法，构造空循环双链表
    {
        this.head = new DLinkNode<T>();          //创建头结点，3个域值均为null
        this.head.pred = head;
        this.head.next = head;
    }
    public boolean isEmpty()                     //判断循环双链表是否空
    {
        return head.next==head;
    }

    //以下成员方法的功能同单链表，算法有差别

    //插入第i（≥0）个元素值为x。若x==null，不插入。
    //若i<0，插入x作为第0个元素；若i大于表长，插入x作为最后一个元素。O(n)
    public void insert(int i, T x)                         //将x对象插入在序号为i结点，O(n)
    {
        if (x==null)  return;                              //不能插入空对象
        DLinkNode<T> p=this.head;
        for (int j=0; p.next!=this.head && j<i; j++)       //寻找插入位置
            p = p.next;                                    //循环停止时，p指向第i-1个结点
        DLinkNode<T> q=new DLinkNode<T>(x, p, p.next);     //插入在p结点之后，包括头插入、中间插入
        p.next.pred = q;
        p.next = q;
    }
    public void append(T x)                                //在循环双链表最后添加结点，O(1)
    {
        if (x==null)  return;                              //不能添加空对象
        DLinkNode<T> q = new DLinkNode<T>(x, head.pred, head);
        head.pred.next = q;                                //插入在头结点之前，相当于尾插入
        head.pred = q;
    }

    //删除第i（≥0）个元素，返回被删除对象。若i<0或i大于表长，不删除，返回null。O(n)
    public T remove(int i)
    {
        if (i>=0)
        {
            DLinkNode<T> p=this.head.next;
            for (int j=0; p!=head && j<i; j++)             //定位到待删除结点
                p = p.next;
            if (p!=head)
            {
                T old = p.data;                            //获得原对象
                p.pred.next = p.next;                      //删除p结点自己
                p.next.pred = p.pred;
                return old;
            }
        }
        return null;                                       //当i<0或大于表长时
//        throw new IndexOutOfBoundsException(i+"");         //抛出序号越界异常
    }
    public void removeAll()                                //删除循环双链表所有元素
    {
        this.head.pred = head;
        this.head.next = head;
    }

    //以下toString()、length()、get(i)、set(i,x)、equals()和深拷贝方法声明及算法同单链表，书稿省略
    //习题2
    public T get(int i)                          //返回第i（≥0）个元素，若i<0或大于表长则返回null，O(n)
    {
        if (i>=0)
        {
            DLinkNode<T> p=this.head.next;
            for (int j=0; p!=null && j<i; j++)
                p = p.next;
            if (p!=null)
                return p.data;                             //p指向第i个结点
        }
        return null;                                       //当i<0或大于表长时
    }

    //设置第i（≥0）个元素值为x。若i<0或大于表长则抛出序号越界异常；若x==null，不操作。O(n)
    public void set(int i, T x)
    {
        if (x==null)  return;                              //不能设置元素为空对象
        DLinkNode<T> p=this.head.next;
        for (int j=0; p!=null && j<i; j++)
            p = p.next;
        if (i>=0 && p!=null)
            p.data = x;                                    //p指向第i个结点
        else throw new IndexOutOfBoundsException(i+"");    //抛出序号越界异常
    }

    public int length()                                    //返回循环双链表长度
    {
        int i=0;
        for (DLinkNode<T> p=this.head.next;  p!=this.head;  p=p.next)//循环条件与单链表不同
            i++;
        return i;
    }

    public String toString()    //返回循环双链表所有元素的描述字符串，循环双链表遍历算法，O(n)
    {
        String str="(";
        for (DLinkNode<T> p=this.head.next;  p!=this.head;  p=p.next)
        {
            str += p.data.toString();
            if (p.next!=this.head)
                str += ",";
        }
        return str+")";                               //空表返回()
    }

    //由指定数组中的多个对象构造循环双链表，采用尾插入构造循环双链表
    public CirDoublyLinkedList(T[] element)
    {
        this();                                  //创建空循环双链表，只有头结点
        DLinkNode<T> rear=this.head;
        for (int i=0; i<element.length; i++)
        {
            rear.next=new DLinkNode<T>(element[i], rear, this.head);   //尾插入
            rear = rear.next;
        }
        this.head.pred = rear;
    }

    //比较两条循环双链表是否相等，覆盖Object类的equals(obj)方法
    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        if (!(obj instanceof CirDoublyLinkedList))
            return false;
        DLinkNode<T> p=this.head.next;
        CirDoublyLinkedList<T> list = (CirDoublyLinkedList<T>)obj;
        DLinkNode<T> q=list.head.next;
        while (p!=head && q!=list.head && p.data.equals(q.data))
        {
            p=p.next;
            q=q.next;
        }
        return p==head && q==list.head;
    }

    //深拷贝构造方法，复制循环双链表
    public CirDoublyLinkedList(CirDoublyLinkedList<T> list)
    {
        this();                                  //创建空循环双链表，只有头结点
        DLinkNode<T> rear = this.head;
        for (DLinkNode<T> p=list.head.next;  p!=list.head;  p=p.next)
        {
            rear.next = new DLinkNode<T>(p.data, rear, this.head);
            rear = rear.next;
        }
        this.head.pred = rear;
    }

    //第8章
    //顺序查找关键字为key元素，返回首次出现的元素，若查找不成功返回null
    //key可以只包含关键字数据项，由T类的equals()方法提供比较对象相等的依据
    public T search(T key)
    {
        if (key==null)
            return null;
        for (DLinkNode<T> p=this.head.next;  p!=this.head;  p=p.next)
            if (key.equals(p.data))
                return p.data;
        return null;
    }
    public boolean contain(T key)                          //判断线性表是否包含关键字为key元素
    {
        return this.search(key)!=null;                     //以查找结果获得判断结果
    }
    //以上实现LList接口

    //以下为循环双链表增加的成员方法
    public void printPred()                                //输出循环双链表，从后向前沿着前驱域
    {
        System.out.print("从后向前沿着前驱域遍历循环双链表，(");
        for (DLinkNode<T> p=this.head.pred; p!=this.head;  p=p.pred)
        {
            System.out.print(p.data.toString());
            if (p.pred!=this.head)
                System.out.print(", ");
        }
        System.out.println(")");                           //空表返回()
    }

    //习题2
    //以下4个方法提供迭代遍历循环双链表方式
    public DLinkNode<T> getFirst()               //返回循环双链表第一个结点（非头结点），O(1)
    {
        if (this.head.next==head)
            return null;
        return this.head.next;
    }
    public DLinkNode<T> getNext(DLinkNode<T> p)  //返回p的后继结点，O(1)
    {
        if (this.head.next==head || p==null)
            return null;
        return p.next;
    }
    public DLinkNode<T> getPred(DLinkNode<T> p)  //返回p的前驱结点，O(1)
    {
        if (this.head.next==head || p==null || this.head.next==p)
            return null;
        return p.pred;
    }
    public DLinkNode<T> getLast()                //返回循环双链表最后一个结点，O(1)
    {
        if (this.head.pred==head)
            return null;
        return this.head.pred;
    }

    //深拷贝构造方法，由单链表list构造循环双链表
    public CirDoublyLinkedList(SinglyLinkedList<T> list)
    {
        this();
        DLinkNode<T> rear = this.head;
        for (Node<T> p = list.head.next; p!=null; p=p.next)
        {
            rear.next = new DLinkNode<T>(p.data, rear, this.head);
            rear = rear.next;
        }
        this.head.pred = rear;
    }

    //深拷贝构造方法，由循环单链表list构造循环双链表
    public CirDoublyLinkedList(CirSinglyLinkedList<T> list)
    {
        this();
        DLinkNode<T> rear = this.head;
        for (Node<T> p=list.head.next;  p!=list.head;  p=p.next)
        {
            rear.next = new DLinkNode<T>(p.data, rear, this.head);
            rear = rear.next;
        }
        this.head.pred = rear;
    }

    //习题2，子表操作
    //将list循环双链表中所有结点链接在当前循环双链表之后，并设置list为空
    public void concat(CirDoublyLinkedList<T> list)
    {
        DLinkNode<T> rear=head.pred;
        rear.next = list.head.next;
        list.head.next.pred = rear;
        rear=list.head.pred;
        rear.next = this.head;
        this.head.pred = rear;
        list.head.pred = list.head;
        list.head.next = list.head;
    }

    //返回从第i(≥0)个结点开始、长度为n(≥0)的子表（深拷贝）
    //若i<0或n<0，抛出异常；若i>表长或n=0，返回空链表；若n超长，返回至表尾的子表
    public CirDoublyLinkedList<T> sub(int i, int n)
    {
        if (i<0)  throw new IndexOutOfBoundsException(i+"");    //抛出序号越界异常
        if (n<0)  throw new IllegalArgumentException(i+"");     //抛出无效参数异常
        DLinkNode<T> p=this.head.next;
        for (int j=0;  j<i && p!=this.head;  j++)          //寻找子表首结点
            p = p.next;                                    //循环停止时，p指向第i结点。若i>表长，则p==this.head
        CirDoublyLinkedList<T> list=new CirDoublyLinkedList<T>();//空循环双链表
        DLinkNode<T> rear=list.head;                       //复制子表到list链表rear结点之后
        for (int j=0;  p!=this.head && j<n;  j++, p=p.next)
        {
            rear.next.pred = new DLinkNode<T>(p.data, rear, list.head);
            rear.next = rear.next.pred;
            rear=rear.next;
        }
        return list;                                       //返回对象引用
    }

    //插入子表（深拷贝），将list循环双链表中的所有结点复制插入到当前循环双链表front结点之后
    public void insert(DLinkNode<T> front, CirDoublyLinkedList<T> list)
    {
        for (DLinkNode<T> q=list.head.next;  q!=list.head;  q=q.next)
        {
            front.next.pred = new DLinkNode<T>(q.data, front, front.next);
            front.next = front.next.pred;
            front = front.next;
        }
    }
    //插入子表（深拷贝），将list循环双链表中的所有结点复制插入到当前循环双链表第i个结点之前
    public void insert(int i, CirDoublyLinkedList<T> list)
    {
        DLinkNode<T> p=this.head;
        for (int j=0;  j<i && p.next!=this.head;  j++)     //寻找插入位置
            p = p.next;                                    //循环停止时，p指向第i-1结点或最后一个结点
        this.insert(p,list);                               //复制插入list链表到p结点之后
    }

    //将list循环双链表中所有结点复制添加到当前循环双链表最后
    public void append(CirDoublyLinkedList<T> list)
    {
        this.insert(this.head.pred, list);                  //复制插入list链表到链尾结点之后
    }

    //删除从第i（≥0）个结点开始、长度为n（≥1）的子表
    //若i<0或n<0，抛出异常；若i>表长或n=0，不删除；若n超长，删除至表尾的子表
    //算法类似单链表
    public void remove(int i, int n)
    {
        if (i<0)  throw new IndexOutOfBoundsException(i+"");    //抛出序号越界异常
        if (n<0)  throw new IllegalArgumentException(i+"");     //抛出无效参数异常
        DLinkNode<T> front=this.head.next;
        for (int j=0; j<i && front!=this.head; j++)        //寻找待删除子表首结点的前驱结点
            front = front.next;
        if (front==this.head)                              //i越界，不删除
            return;
        DLinkNode<T> p=front.next;
        for (int j=0;  j<n && p!=this.head;  j++)          //寻找待删除子表之后的结点
            p = p.next;
        front.next = p;                                    //删除front后继结点至p前驱结点之间的子表
        p.pred = front;
    }

    //返回this单链表首个与patten匹配子表的首结点，查找不成功时返回null
    public DLinkNode<T> index(CirDoublyLinkedList<T> pattern)
    {
        return index(head.next, pattern);
    }
    //返回this单链表从start结点开始首个与pattern匹配子表的首结点，查找不成功时返回null，BF模式匹配
    public DLinkNode<T> index(DLinkNode<T> start, CirDoublyLinkedList<T> pattern)
    {
        if (start==null || pattern.isEmpty())
            return null;
        DLinkNode<T> p=start, q=pattern.head.next;
        while (p!=this.head && q!=pattern.head)
        {
            if (p.data.equals(q.data))                     //继续比较下一个结点
            {
                p=p.next;
                q=q.next;
            }
            else                                           //回退
            {   start=start.next;                          //目标表继续从下个子表
                p=start;
                q=pattern.head.next;                       //模式表回退至表头
            }
        }
        if (q==pattern.head)                               //存在匹配的子表
            return start;                                  //返回子表首结点地址
        return null;
    }

/*    //删除所有与pattern匹配的子表。算法调用index(pattern)查找到再删除。
    public void removeAll(CirDoublyLinkedList<T> pattern)
    {
        System.out.print("将"+this.toString()+"中"+pattern.toString()+"全部删除");
        DLinkNode<T> p=this.index(pattern);      //返回从头结点开始首个与pattern匹配子表的首结点
                                                 //若pattern为空，返回null
        while (p!=null)
        {
            DLinkNode<T> q=pattern.head.next;
            while (q!=pattern.head)              //删除从p开始与pattern匹配的子表。不能调用remove(i,n)方法，效率低
            {
                p.pred.next = p.next;            //删除p结点，但未释放p结点，p.next和p.pred仍然有效
                p.next.pred = p.pred;
                p=p.next;                        //p指向被删除结点的原后继结点
                q=q.next;
            }
            p=this.index(p,pattern);             //再次匹配，返回从p结点开始首个与pattern匹配子表的首结点
        }
        System.out.println("的结果是"+this.toString());
    }*/

    //将this单链表中首个与pattern匹配的子表替换为dest子表。
    //算法类似replaceAll(pattern, dest)，只将while改为if，只做一次匹配替换
    public void replaceFirst(CirDoublyLinkedList<T> pattern, CirDoublyLinkedList<T> dest)
    {
        System.out.print("将"+this.toString()+"中首个"+pattern.toString()+"替换为"+dest.toString());
        DLinkNode<T> p=this.index(pattern);      //返回从头开始首个与pattern匹配子表的首结点
        if (p!=null)                             //匹配成功，进行替换
        {
            DLinkNode<T> q=pattern.head.next;
            while (q!=pattern.head)              //删除从p开始与pattern匹配的子表
            {
                p.pred.next = p.next;            //删除p结点，但未释放p结点，p.next和p.pred仍然有效
                p.next.pred = p.pred;
                p=p.next;
                q=q.next;
            }
            p=p.pred;
            q=dest.head.next;
            while (q!=dest.head)                 //将dest中所有结点深拷贝插入到当前单链表p结点之后
            {
                p.next.pred = new DLinkNode<T>(q.data,p,p.next);  //将q结点深拷贝插入到p结点之后
                p.next=p.next.pred;
                p=p.next;
                q=q.next;
            }
            p=p.next;
        }
        System.out.println("的结果是"+this.toString());
    }

    //将this单链表中所有与pattern匹配的子表替换为dest子表。
    //算法调用index(pattern)查找到再删除和插入。
/*    public void replaceAll(CirDoublyLinkedList<T> pattern, CirDoublyLinkedList<T> dest)
    {
        System.out.print("将"+this.toString()+"中"+pattern.toString()+"全部替换为"+dest.toString());
        DLinkNode<T> p=this.index(pattern);      //返回从头开始首个与pattern匹配子表的首结点
        while (p!=null)                          //匹配成功，进行一次替换
        {
            DLinkNode<T> q=pattern.head.next;
            while (q!=pattern.head)              //删除从p开始与pattern匹配的子表
            {
                p.pred.next = p.next;            //删除p结点，但未释放p结点，p.next和p.pred仍然有效
                p.next.pred = p.pred;
                p=p.next;
                q=q.next;
            }
            p=p.pred;
            q=dest.head.next;
            while (q!=dest.head)                 //将dest中所有结点深拷贝插入到当前单链表p结点之后
            {
                p.next.pred = new DLinkNode<T>(q.data,p,p.next);  //将q结点深拷贝插入到p结点之后
                p.next=p.next.pred;
                p=p.next;
                q=q.next;
            }
            p=p.next;
            p=this.index(p,pattern);             //再次匹配，返回从p结点开始首个与pattern匹配子表的首结点
        }
        System.out.println("的结果是"+this.toString());
    }*/

    //删除所有与pattern匹配的子表。BF模式匹配算法查找到再删除，没有调用index(pattern)。算法同单链表。
    public void removeAll(CirDoublyLinkedList<T> pattern)
    {
        System.out.print("将"+this.toString()+"中"+pattern.toString()+"全部删除");
        if (pattern.isEmpty())                             //若无此句，则死循环，错误
            return;
        DLinkNode<T> start=this.head.next;
        while (start!=this.head)
        {
            DLinkNode<T> p=start, q=pattern.head.next;
            while (p!=this.head && q!=pattern.head && p.data.equals(q.data)) //一次匹配
            {   p=p.next;
                q=q.next;
            }
            if (q!=pattern.head)                           //匹配失败，进行下次匹配
                start=start.next;
            else                                           //匹配成功，删除该匹配子表
            {   start.pred.next = p;
                p.pred = start.pred;
                start=p;
            }
        }
        System.out.println("的结果是"+this.toString());
    }

    //将this单链表中所有与pattern匹配的子表替换为dest子表。当dest为空时，相当于删除匹配子表。
    //包含BF模式匹配、删除匹配子表、复制插入子表算法
    public void replaceAll(CirDoublyLinkedList<T> pattern, CirDoublyLinkedList<T> dest)
    {
        System.out.print("将"+this.toString()+"中"+pattern.toString()+"全部替换为"+dest.toString()+"的结果是");
        if (pattern.isEmpty())                             //若无此句，则将dest插入到start结点之后，错误
            return;
        DLinkNode<T> start=this.head.next;
        while (start!=this.head)                           //start指向每次匹配的起始结点
        {
            DLinkNode<T> p=start, q=pattern.head.next;
            while (p!=this.head && q!=pattern.head && p.data.equals(q.data))//一次匹配的多次比较
            {   p=p.next;
                q=q.next;
            }
            if (q!=pattern.head)                           //匹配失败，进行下次匹配
                start=start.next;
            else                                           //匹配成功，替换该匹配子表
            {   start.pred.next = p;                       //删除该匹配子表
                p.pred = start.pred;
                start=p;
                DLinkNode<T> d=dest.head.next;
                while (d!=dest.head)                       //将dest剩余结点深拷贝插入到this单链表p之前
                {   DLinkNode<T> t = new DLinkNode<T>(d.data, p.pred, p);
                    p.pred.next = t;
                    p.pred = t;
                    d = d.next;
                }
            }
        }
        System.out.println(this.toString());
    }

    //第10章习题，10.2 实现迭代器
    public java.util.Iterator<T> iterator()                //返回Java迭代器对象
    {
        return new DoublyIterator();
    }

    private class DoublyIterator implements java.util.Iterator<T> //私有内部类，实现迭代器接口
    {
        DLinkNode<T> current=CirDoublyLinkedList.this.head;//当前结点
        boolean removable=false;                           //是否可删除状态
//        DLinkNode<T> front=null;                           //当前结点的前驱结点

        public boolean hasNext()                           //若有后继元素，返回true
        {
            return //this.current!=CirDoublyLinkedList.this.head &&
                    this.current.next!=CirDoublyLinkedList.this.head;
        }

        public T next()                                    //返回后继元素
        {
            if (this.hasNext())
            {
                this.removable=true;
//                this.front = this.current;
                this.current = this.current.next;
                return this.current.data;
            }
            else throw new java.util.NoSuchElementException();  //抛出无此元素异常
        }

        public void remove()                               //删除迭代器对象表示的集合当前元素
        {
            if (this.removable)//(this.front!=null)
            {
                this.current.next.pred = this.current.pred;//删除当前结点
                this.current.pred.next = this.current.next;
                this.current = this.current.pred;
                this.removable=false;
//                this.front=null;                           //设置不能连续删除
            }
            else throw new java.lang.IllegalStateException(); //抛出无效状态异常
        }
    }//DoublyIterator内部类结束


    public java.util.ListIterator<T> listIterator()        //返回Java列表迭代器对象
    {
        return new DoublyListIterator(0);
    }
    public java.util.ListIterator<T> listIterator(final int index)        //返回Java列表迭代器对象
    {
        return new DoublyListIterator(index);
    }
    //??
    private class DoublyListIterator extends DoublyIterator implements java.util.ListIterator<T>   //私有内部类，实现列表迭代器接口
    {
        int succ=0;                                    //当前元素序号

        public DoublyListIterator(int index)//??
        {
            this.succ=index;
            //        current=CirDoublyLinkedList.this.head;//当前结点
            int i=-1;
            while (i<index && this.hasNext())
            {
                i++;
                this.current = this.current.next;
            }
            if (index<0 || !this.hasNext())
                throw new IndexOutOfBoundsException("Index: "+index);
        }

        public boolean hasPrevious()                       //若有前驱元素，返回true
        {
            return this.current.pred!=CirDoublyLinkedList.this.head;
        }

        public T previous()                                //返回前驱元素
        {
            if (this.hasPrevious())
            {
                this.current = this.current.pred;
                this.succ--;
                return this.current.data;
            }
            else throw new java.util.NoSuchElementException();  //抛出无此元素异常
        }

        public int nextIndex()                             //返回后继元素序号
        {
            return this.succ;
        }
        public int previousIndex()                         //返回前驱元素序号
        {
            return this.succ-1;
        }

        public void set(T x)                               //将集合当前元素替换为x
        {
            if (x!=null && this.current!=CirDoublyLinkedList.this.head)
                this.current.data = x;
        }
        public void add(T x)                               //增加元素x，在当前结点之后插入x
        {
            if (x==null)
                return;                                    //不能添加空对象
            DLinkNode<T> q = new DLinkNode<T>(x, this.current, this.current.next);
            this.current.next.pred = q;                    //在当前结点之后插入x
            this.current.next = q;
            this.current = this.current.next;
            this.succ++;                                  //插入结点为当前结点
        }
    }//DoublyListIterator内部类结束
}


/*
？？删除子表、替换子表想调用之前声明的插入、删除方法，未成功。
    //插入子表（深拷贝），将list循环双链表中的所有结点复制插入到当前循环双链表front结点之后
    public DLinkNode<T> insert(DLinkNode<T> front, CirDoublyLinkedList<T> list)
    {
        DLinkNode<T> q=list.head.next;                      //复制插入list链表到front结点之后
        while (q!=list.head)
        {
            front.next.pred = new DLinkNode<T>(q.data, front, front.next);
            front.next = front.next.pred;
            front = front.next;
            q=q.next;
        }
        return front;  //返回插入子表后的结点，有可能是头结点，为了供replaceAll调用，含义勉强
    }
    //删除this单链表从头开始首个与pattern匹配的子表
    public DLinkNode<T> removeFirst(CirDoublyLinkedList<T> pattern)
    {
    	return removeFirst(this.head.next, pattern);
    }
    //删除this单链表从start结点开始首个与pattern匹配的子表。
    //算法调用index(pattern)查找到再删除。不能调用remove(i,n)方法，效率低
    public DLinkNode<T> removeFirst(DLinkNode<T> start, CirDoublyLinkedList<T> pattern)
    {
        DLinkNode<T> p=this.index(start,pattern); //返回从start结点开始首次出现的与pattern匹配的子表首结点
                                                 //若pattern为空，返回null
        if (p==null)
        	return null;
        DLinkNode<T> q=pattern.head.next;
        while (q!=pattern.head)               //删除从p开始与pattern匹配的子表。不能调用remove(i,n)方法，效率低
        {
            p.pred.next = p.next;            //删除p结点，但未释放p结点，p.next和p.pred仍然有效
            p.next.pred = p.pred;
            p=p.next;                        //p指向被删除结点的原后继结点
            q=q.next;
        }
        return p=p.pred;        //??声明返回值能够被removeAll()调用，但这个返回值的含义说不清楚。
    }
    //删除所有与pattern匹配的子表。算法调用removeFirst(start,pattern)逐个查找并删除子表。
    public void removeAll(CirDoublyLinkedList<T> pattern)
    {
        DLinkNode<T> p=this.removeFirst(pattern);
        while (p!=null && p!=this.head)
        	p=this.removeFirst(p,pattern);
    }
    //将this单链表中首个与pattern匹配的子表替换为dest子表。算法调用删除和插入方法。
    public DLinkNode<T> replaceFirst(DLinkNode<T> start, CirDoublyLinkedList<T> pattern, CirDoublyLinkedList<T> dest)
    {
        System.out.print("将"+this.toString()+"中首个"+pattern.toString()+"替换为"+dest.toString());
        DLinkNode<T> p=this.removeFirst(pattern);
        if (p!=null)
        	p=this.insert(p, dest);
        System.out.println("的结果是"+this.toString());
        return p;
    }
    //将this单链表中所有与pattern匹配的子表替换为dest子表。算法调用index(pattern)查找到再删除和插入。
    //没有调用replaceFirst(pattern)方法，当其没有返回值时，无法定位，从头再找效率低
    //算法调用removeFirst(start,pattern)逐个查找并删除子表。
    public void replaceAll(CirDoublyLinkedList<T> pattern, CirDoublyLinkedList<T> dest)
    {
        DLinkNode<T> p=this.head.next;
        while (p!=null && p!=this.head)
        {
//        	p=p.next;
            p=this.replaceFirst(p, pattern, dest);     //？？
        }
    }
 */

