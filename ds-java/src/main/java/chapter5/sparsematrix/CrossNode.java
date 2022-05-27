package chapter5.sparsematrix;

/**
 * Created by lili on 2017/7/1.
 */
//5.2.2 稀疏矩阵的压缩存储
//5.  稀疏矩阵十字链表
//十字链表结点类

public class CrossNode                           //十字链表结点类
{
    Triple data;                                 //数据域表示三元组，默认访问权限
    CrossNode right, down;                       //right指向行的下一个结点，down指向列的下一个结点

    //构造结点，data指定元素，right指向行的下一个结点，down指向列的下一个结点
    public CrossNode(Triple data, CrossNode right, CrossNode down)
    {
        this.data = data;
        this.right = right;
        this.down = down;
    }
}
/*
    没有用到
    public CrossNode()
    {
        this(null, null, null);
    }*/


/*
public class CrossNode<T>                        //十字链表结点类

{
    public T data;                               //数据元素
    public CrossNode<T> right, down;              //next指向行的后继结点，down指向列的后继结点

    //构造结点，data指定元素，next指向行的后继结点，down指向列的后继结点
    public CrossNode(T data, CrossNode<T> right, CrossNode<T> down)
    {
        this.data = data;
        this.right = right;
        this.down = down;
    }
    public CrossNode()
    {
        this(null, null, null);
    }
}
*/
