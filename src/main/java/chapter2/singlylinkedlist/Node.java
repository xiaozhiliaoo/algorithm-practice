package chapter2.singlylinkedlist;

/**
 * Created by lili on 2017/7/1.
 */
//单链表结点类

public class Node<T>                             //单链表结点类，T指定结点的元素类型
{
    public T data;                               //数据域，保存数据元素
    public Node<T> next;                         //地址域，引用后继结点

    public Node(T data, Node<T> next)            //构造结点，data指定数据元素，next指定后继结点
    {
        this.data = data;
        this.next = next;
    }
    public Node()
    {
        this(null, null);
    }

    //4、Node类可声明以下方法：
    public String toString()                     //返回结点元素值对应的字符串
    {
        return this.data.toString();
    }
    public boolean equals(Object obj)            //比较两个结点值是否相等，覆盖Object类的equals(obj)方法
    {
        return obj==this || obj instanceof Node && this.data.equals(((Node<T>)obj).data);
    }
}

/*问题讨论
1、  当一个类没有声明构造方法时，Java提供默认构造方法。例如
    public Node()                                  //提供默认构造方法
    {
        super();                                   //默认调用语句
    }
          当一个类声明了构造方法时，Java不再提供默认构造方法。
例如，当Node类声明了Node(T data, Node<T> next)构造方法时，Java不再提供默认构造方法Node()。
如果Node类需要Node()构造方法，必须自己声明。

2、  Java方法参数没有默认值。例如，Node类可以声明以下构造方法
    public Node(T data)
    {
        this(data, null);
    }
    但不能将Node(T data, Node<T> next)构造方法声明为如下形式：
    public Node(T data, Node<T> next=null)

3、Java不提供默认拷贝构造方法。
  Node类不需要拷贝构造方法。若拷贝构造方法以下，复制p引用的结点，
    public Node(Node<T> p)            //拷贝构造方法
    {
        this(p.data, p.next);
    }
相当于
    public Node(Node<T> p)
    {
        this.data = p.data;
        this.next = p.next;        //将p结点作为当前结结点的后继结点，含义不对了
    }

5、不能声明如下，比较结点值大小
public class Node<T> implements Comparable<Node<T>>   //单链表结点类
{
    public int compareTo(Node<T> p)                  //比较相等，比较大小
    {
        return this.data.compareTo(p.data);
    }
}
排序单链表应该要求比较T对象大小，不能要求比较结点大小。
*/

