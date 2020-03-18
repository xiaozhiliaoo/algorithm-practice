package chapter8.binarysorttree;

import chapter6.binarytree.BinaryNode;
import chapter6.binarytree.BinaryTree;

/**
 * Created by lili on 2017/7/1.
 */
//8.4.1   二叉排序树
//SortedBinaryTree??

//二叉排序树类，继承二叉树类，要求泛型T的实际参数类实现可比较接口
public class BinarySortTree<T extends Comparable<T>> extends BinaryTree<T>
{
    public BinarySortTree()                                //构造空二叉排序树
    {
        super();                                           //调用父类的默认构造方法
    }
    public BinarySortTree(T[] values)            //将values数组元素依次插入构造一棵二叉排序树
    {
        super();
        for (int i=0; i<values.length; i++)
            this.insert(values[i]);                        //将元素插入到当前的二叉排序树中
    }
    //没有继承父类的构造方法

    public String toString()                     //返回中根次序遍历二叉树所有结点的描述字符串
    {
        return toString(root);
    }
    private String toString(BinaryNode<T> p)     //返回中根次序遍历以p为根的子树描述字符串，递归算法
    {
        if (p==null)
            return "";
        return  toString(p.left) +p.data.toString()+" " + toString(p.right); //递归调用
    }

    //查找并返回关键字为key元素结点，若查找不成功返回null，非递归算法
    public BinaryNode<T> searchNode(T key)
    {
        if (key==null)
            return null;
        BinaryNode<T> p=this.root;
        while (p!=null && p.data.compareTo(key)!=0)        //当没有相等者
        {
//            System.out.print(p.data+"? ");                 //显示查找经过的结点值，可省略
            if (p.data.compareTo(key)>0)                   //若key较小
                p=p.left;                                  //进入左子树
            else
                p=p.right;                                 //进入右子树
        }
        return p;
    }

    public void insert(T x)                      //插入元素x结点，不插入关键字重复元素和空对象
    {
        if (x==null)  return;                              //不能插入空对象
        if (root==null)
            root=new BinaryNode<T>(x);                     //建立根结点
        else                                               //将x插入到二叉排序树中
        {   BinaryNode<T> p=this.root, parent=null;
            while (p!=null)                                //查找插入位置
            {   parent = p;
                if (x.compareTo(p.data)==0)
                    return;                                //不插入关键字重复的元素
                if (x.compareTo(p.data)<0)
                    p=p.left;
                else p=p.right;
            }
            p=new BinaryNode<T>(x);                        //建立叶子结点p
            if(x.compareTo(parent.data)<0)
                parent.left = p;                           //插入p作为parent的左孩子
            else parent.right = p;                         //插入p作为parent的右孩子
        }
    }

/*    public BinaryNode<T> remove(T x)             //删除元素为x的结点，返回删除结点，若没删除返回null
    {
        if (root==null || x==null)
            return null;
        return remove(x, root, null);                  //在以root为根的二叉排序树中删除值为x的结点
    }
    //在以p为根的子树中删除元素为x的结点，parent是p的父母结点，返回删除结点，递归算法
    private BinaryNode<T> remove(T x, BinaryNode<T> p, BinaryNode<T> parent)
    {
        if (p==null)
            return null;
        if (x.compareTo(p.data)<0)
            return remove(x, p.left, p);                //在p的左子树中删除x，递归调用，查找
        if (x.compareTo(p.data)>0)
            return remove(x, p.right, p);               //在p的右子树中删除x，递归调用，查找
        if (p.left!=null && p.right!=null)              //找到待删除结点p，p是2度结点
        {
            BinaryNode<T> insucc = p.right;             //寻找p在中根次序下的后继结点insucc
            while (insucc.left!=null)
                insucc = insucc.left;
            p.data = insucc.data;                       //以后继结点值替换
            return remove(p.data, p.right, p);
        }
        if (parent==null)                               //p是1度或叶子结点，删除根结点，即p==root
        {
            if (p.left!=null)
                root = p.left;
            else
                root = p.right;
            return p;                            //返回删除结点p
        }
        if (p==parent.left)                      //p是1度或叶子结点，p是parent的左孩子
            if (p.left!=null)
                parent.left = p.left;            //以p的左子树填补
            else
                parent.left = p.right;
        else                                     //p是parent的右孩子
            if (p.left!=null)
                parent.right = p.left;
            else
                parent.right = p.right;
        return p;
    }*/

    //不支持父类的insertRoot()、insertChild()、removeChild()方法，将其覆盖并抛出异常。
    public void insertRoot(T x)
    {
        throw new UnsupportedOperationException("insertRoot(T x)");
    }
    public BinaryNode<T> insertChild(BinaryNode<T> p, T x, boolean leftChild)
    {
        throw new UnsupportedOperationException("insertChild()");
    }
    public void removeChild(BinaryNode<T> p, boolean leftChild)
    {
        throw new UnsupportedOperationException("removeChild()");
    }

    //第4版改进程序
    public T search(T key)
    {
        if (key==null)
            return null;
        BinaryNode<T> p=this.root;
        while (p!=null)                                    //查找经过一条从根到结点的路径，非递归
        {
//            System.out.print(p.data+"? ");                 //显示查找经过的结点值，可省略
            if (p.data.compareTo(key)==0)                  //若关键字相等，查找成功，返回元素
                return p.data;
            if (p.data.compareTo(key)>0)                   //若key较小
                p=p.left;                                  //进入左子树
            else
                p=p.right;                                 //进入右子树
        }
        return null;                                       //查找不成功，经过一条从根到叶子的路径
    }

    //删除关键字为key元素结点。若没找到则不删除。非递归算法
    public void remove(T key)
    {
        if (key==null || root==null)
            return;
        BinaryNode<T> p=this.root, parent=null;            //p的父母是parent
        while (p!=null && p.data.compareTo(key)!=0)        //查找关键字为key元素结点
        {
            parent = p;
            if (p.data.compareTo(key)>0)
                p=p.left;
            else
                p=p.right;
        }
        if (p!=null && p.left!=null && p.right!=null)      //找到待删除结点p，p是2度结点
        {
            BinaryNode<T> insucc = p.right;                //寻找p在中根次序下的后继结点insucc
            parent = p;
            while (insucc.left!=null)
            {
                parent = insucc;
                insucc = insucc.left;
            }
            p.data = insucc.data;                          //以后继结点值替换p结点值
            p = insucc;                                    //删除p原后继结点，转化为删除1、0度结点
        }
        if (p!=null && parent==null)                       //p是1度或叶子结点，删除根结点，即p==root
        {
            if (p.left!=null)
                root = p.left;
            else
                root = p.right;
            return;
        }
        if (p!=null && p==parent.left)                     //p是1度或叶子结点，p是parent的左孩子
            if (p.left!=null)
                parent.left = p.left;                      //以p的左子树填补
            else
                parent.left = p.right;
        if (p!=null && p==parent.right)                    //p是1度或叶子结点，p是parent的右孩子
            if (p.left!=null)
                parent.right = p.left;
            else
                parent.right = p.right;
    }

    //习题8
    public T max()                                        //返回所有元素的最大值
    {                                                     //算法寻找根结点的最右边的一个子孙结点，其值最大
        BinaryNode<T> p = root;
        while (p!=null && p.right!=null)
            p=p.right;
        return p.data;
    }
    public T min()                                        //返回所有元素的最小值
    {                                                     //算法寻找根结点的最左边的一个子孙结点，其值最小
        BinaryNode<T> p = root;
        while (p!=null && p.left!=null)
            p=p.left;
        return p.data;
    }

/*    public BinaryNode<T> searchNode(T x)                   //查找值为x的结点，若查找成功返回结点，否则返回null
    {
        return searchNode(x, root);
    }
    //在以p为根的子树中查找值为x的结点，递归算法
    private BinaryNode<T> searchNode(T x, BinaryNode<T> p)
    {
        if (p==null || x==null)
            return null;
        if (x.compareTo(p.data)==0)                        //若两个对象相等，查找成功
            return p;
        System.out.print(p.data+"? ");
        if (x.compareTo(p.data)<0)
            return searchNode(x, p.left);                  //在左子树中查找
        return searchNode(x, p.right);                     //在右子树中查找
    }*/

    public void insertNode(T x)                            //插入元素x结点，不插入关键字重复元素和空对象
    {
        if (x==null)
            return;                                        //不能插入空对象
        if (root==null)
            root=new BinaryNode<T>(x);                     //建立根结点
        else insertNode(x, root);                          //插入x到以root为根的二叉排序树中
    }

    private void insertNode(T x, BinaryNode<T> p)          //将x插入到以p为根的子树中。递归算法
    {
        if (p==null || p.data.compareTo(x)==0)
            return;                                        //不插入关键字重复的元素
        if (x.compareTo(p.data)<0)
            if (p.left==null)
                p.left = new BinaryNode<T>(x);             //建立叶子结点作为p的左孩子
            else  insertNode(x, p.left);                   //将x插入到p的左子树中
        else
        if (p.right==null)
            p.right = new BinaryNode<T>(x);            //建立叶子结点作为p的右孩子
        else  insertNode(x, p.right);                  //将x插入到p的右子树中
    }
}


