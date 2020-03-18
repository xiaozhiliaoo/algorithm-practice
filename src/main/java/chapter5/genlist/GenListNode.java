package chapter5.genlist;

/**
 * Created by lili on 2017/7/1.
 */
//5.3   广义表
//5.3.2   广义表的存储结构
//2.  广义表的双链表示
//广义表双链表示的结点类

public class GenListNode<T>                                //广义表双链表示的结点类，T指定结点的元素类型
{
    public T data;                                         //数据域
    public GenList<T> child;                               //地址域，指向子表
    public GenListNode<T> next;                            //地址域，指向后继结点

    //构造结点，data指定元素，child指向子表，next指向后继结点
    public GenListNode(T data, GenList<T> child, GenListNode<T> next)
    {
        this.data = data;
        this.child = child;
        this.next = next;
    }
    public GenListNode(T data)
    {
        this(data, null, null);
    }
    public GenListNode()
    {
        this(null, null, null);
    }
}
