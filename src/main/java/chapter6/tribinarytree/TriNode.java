package chapter6.tribinarytree;

/**
 * Created by lili on 2017/7/1.
 */
//二叉树的三叉链表结点类，泛型T指定结点的元素类型
public class TriNode<T>
{
    public T data;                               //数据域，存储数据元素
    public TriNode<T> parent, left, right;       //链域，分别指向父母结点、左和右孩子结点
    public int level;                            //结点的层次

    //构造结点，参数分别指定元素、父母结点、左和右孩子结点
    public TriNode(T data, TriNode<T> parent, TriNode<T> left, TriNode<T> right, int level)
    {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
        this.level = level;
    }
    public TriNode(T data)                   //构造指定值的叶子结点
    {
        this(data, null, null, null, 0);
    }
    public TriNode()
    {
        this(null, null, null, null, 0);
    }

    //可声明以下方法
    public String toString()
    {
        return this.data.toString();
    }
    public boolean equals(Object obj)            //比较两个结点值是否相等，覆盖Object类的equals(obj)方法
    {
        return obj==this || obj instanceof TriNode && this.data.equals(((TriNode<T>)obj).data);
    }
    public boolean isLeaf()                      //判断是否叶子结点
    {
        return this.left==null && this.right==null;
    }
}

