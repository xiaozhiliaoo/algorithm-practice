package chapter5.genlist;

/**
 * Created by lili on 2017/7/1.
 */
//5.3   广义表
//5.3.2   广义表的存储结构
//2.  广义表的双链表示

//没有表名
public class GenList<T> implements GGenList<T>    //双链表示的广义表类
{
    public GenListNode<T> head;                            //头指针，指向（引用）头结点

    public GenList()                                       //构造空广义表
    {
        this.head = new GenListNode<T>();                  //创建头结点，3个域值均为null
    }

    //构造广义表，由数组提供原子初值，结点次序与数组元素次序相同，采用尾插入构造，算法同单链表
    public GenList(T[] atoms)
    {
        this();                                            //创建空广义表，只有头结点
        GenListNode<T> rear=this.head;
        for (int i=0; i<atoms.length; i++)
        {
            rear.next=new GenListNode<T>(atoms[i]);        //尾插入
            rear = rear.next;
        }
    }

    public boolean isEmpty()                               //判断广义表是否空
    {
        return head.next==null;
    }

    public String toString()                               //返回广义表所有元素的描述字符串
    {
        return this.toString("");
    }
    //返回广义表所有元素值对应的字符串，形式为“(,)”，广义表遍历算法，递归方法
    public String toString(String str)
    {
        str+="(";
        for (GenListNode<T> p=this.head.next;  p!=null;  p=p.next)
        {
            if (p.child==null)
                str += p.data.toString();
            else
                str += p.child.toString();                 //递归调用，遍历子表添加子表描述字符串
            if (p.next!=null)
                str += ",";
        }
        return str+")";                                    //空表返回()
    }

    public int length()                                    //返回广义表长度，算法同单链表
    {
        int i=0;
        for (GenListNode<T> p=this.head.next;  p!=null;  p=p.next)
            i++;
        return i;
    }

    public int depth()                                     //返回广义表深度，递归方法
    {
        int max=1;
        for (GenListNode<T> p=this.head.next;  p!=null;  p=p.next)
            if (p.child!=null)
            {
                int d=p.child.depth();                     //递归调用，返回子表深度
                if (max<=d)                                //记住最大子表深度
                    max=d+1;                               //当前广义表深度为子表深度加1
            }
        return max;
    }

    public GenListNode<T> insert(int i, T x)               //插入原子x作为第i个元素，算法同单链表
    {
        if (x==null)
            return null;                                   //不能插入空对象
        GenListNode<T> p=this.head;
        for (int j=0; p.next!=null && j<i; j++)            //寻找插入位置
            p = p.next;
        p.next=new GenListNode<T>(x, null, p.next);        //插入在p结点之后，包括头插入、中间插入
        return p.next;
    }

    public GenListNode<T> insert(int i, GenList<T> glist)  //插入子表作为第i个元素，算法同单链表插入结点
    {
        if (glist==null)
            return null;                                   //不能插入空对象
        GenListNode<T> p=this.head;
        for (int j=0; p.next!=null && j<i; j++)
            p = p.next;
        p.next=new GenListNode<T>(null, glist, p.next);
        return p.next;
    }

    public void append(T x)                                //在广义表最后添加原子结点，算法同单链表
    {
        insert(Integer.MAX_VALUE, x);
    }
    public void append(GenList<T> glist)                   //在广义表最后添加子表
    {
        insert(Integer.MAX_VALUE, glist);
    }
    public void remove(int i)                         //删除第i个元素
    {}

//    void removeAll();                            //删除广义表所有元素

}

