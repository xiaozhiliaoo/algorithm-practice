package chapter6.threadbinarytree;

/**
 * Created by lili on 2017/7/1.
 */
//6.4   线索二叉树
//线索二叉树的二叉链表结点类，T指定结点的元素类型
public class ThreadNode<T>
{
    public T data;                               //数据元素
    public ThreadNode<T> left, right;            //分别指向左、右孩子结点
    public int ltag, rtag;                       //左、右线索标记

    public ThreadNode(T data, ThreadNode<T> left, int ltag, ThreadNode<T> right, int rtag)
    {
        this.data = data;
        this.left = left;
        this.ltag = ltag;
        this.right = right;
        this.rtag = rtag;
    }
    public ThreadNode(T data)                    //构造指定值结点
    {
        this(data, null, 0, null, 0);
    }
    public ThreadNode()
    {
        this(null, null, 0, null, 0);
    }

    //可声明以下方法
    public String toString()
    {
        return this.data.toString();
    }
    public boolean equals(Object obj)            //比较两个结点值是否相等，覆盖Object类的equals(obj)方法
    {
        return obj==this || obj instanceof ThreadNode && this.data.equals(((ThreadNode<T>)obj).data);
    }
    public boolean isLeaf()                      //判断是否叶子结点
    {
        return this.ltag==1 && this.rtag==1;
    }
}
/*
    public String toString()
    {
        return "("+this.data+","+this.ltag+","+this.rtag+")";
    }
*/


