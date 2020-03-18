package chapter6.tribinarytree;

import chapter4.queue.LinkedQueue;

/**
 * Created by lili on 2017/7/1.
 */
//三叉链表存储的二叉树类，实现BinaryTTree<T>接口，泛型T指定结点的元素类型
public class TriBinaryTree<T> //implements BinaryTTree<T,TriNode>
{
    public TriNode<T> root;                                //根结点

    public TriBinaryTree()                                 //构造空二叉树
    {
        this.root=null;
    }

    public boolean isEmpty()                               //判断二叉树是否空
    {
        return this.root==null;
    }

    //(1) 构造二叉树，算法有改动
    public TriBinaryTree(T[] prelist)                      //以标明空子树的先根序列构造二叉树
    {
        this.root = create(prelist,1,null);                //令根结点的层次为1
    }
    //以标明空子树的先根序列构造一棵子二叉树，子树的根值是prelist[i]，层次为level，
    //parent指向父母结点，返回所创建子树的根结点
    private int i=0;
    private TriNode<T> create(T[] prelist, int level, TriNode<T> parent)
    {
        TriNode<T> p = null;
        if (i<prelist.length)
        {
            T elem=prelist[i++];
            if (elem!=null)
            {
                p = new TriNode<T>(elem, parent, null, null, level);
                p.left = create(prelist, level+1, p);
                p.right = create(prelist, level+1, p);
            }
        }
        return p;
    }

    //(2) 二叉树的插入操作，算法要维护parent域和level
    //插入元素x作为根结点，原根结点作为其左孩子
    public void insertRoot(T x)
    {
        this.root = new TriNode<T>(x, null, this.root, null, 1);
        if (this.root.left!=null)
            this.root.left.parent = this.root;
        setLevel(this.root.left, 2);             //设置以原根结点为根的子树中所有结点的层次
    }
    //插入元素x作为p结点的孩子，若leftChild为true，插入结点作为左孩子，否则作为右孩子
    //返回插入结点
    public TriNode<T> insertChild(TriNode<T> p, T x, boolean leftChild)
    {
        if (p==null || x==null)
            return null;
        TriNode<T> q=null;
        if (leftChild)
        {                                        //插入x结点作为p的左孩子，p原左孩子成为x的左孩子
            q=new TriNode<T>(x, p, p.left,null, p.level+1);
            if (p.left!=null)
                p.left.parent = q;               //原p左孩子结点的新父母结点是q
            p.left=q;
        }
        else
        {                                        //插入x结点作为p的右孩子，p原右孩子成为x的右孩子
            q=new TriNode<T>(x, p, null, p.right, p.level+1);
            if (p.right!=null)
                p.right.parent = q;              //原p右孩子结点的新父母结点是q
            p.right=q;
        }
        setLevel(q, p.level+1);
        return q;                                //返回插入结点
    }
    //设置以p结点（层次为level）为根的子树中所有结点的层次
    public void setLevel(TriNode<T> p, int level)
    {
        if (p!=null)
        {
            p.level = level;
            setLevel(p.left, level+1);
            setLevel(p.right, level+1);
        }
    }

    //以下算法，同二叉链表
    //二叉树的先根、中根和后根次序遍历算法，同二叉链表
    public void preOrder()                       //先根次序遍历二叉树
    {
        System.out.print("先根次序遍历二叉树：  ");
        preOrder(root);
        System.out.println();
    }
    public void preOrder(TriNode<T> p)           //先根次序遍历以p结点为根的子二叉树，递归方法
    {
        if (p!=null)
        {
            System.out.print(p.level+p.data.toString()+" ");
            preOrder(p.left);
            preOrder(p.right);
        }
    }

    public void inOrder()                        //中根次序遍历二叉树
    {
        System.out.print("中根次序遍历二叉树：  ");
        inOrder(root);
        System.out.println();
    }
    public void inOrder(TriNode<T> p)            //中根次序遍历以p结点为根的子二叉树，递归方法
    {
        if (p!=null)
        {
            inOrder(p.left);
//            System.out.print(p.level+p.data.toString()+" ");
            System.out.print(p.data.toString()+" ");
            inOrder(p.right);
        }
    }

    public void postOrder()                      //后根次序遍历二叉树
    {
        System.out.print("后根次序遍历二叉树：  ");
        postOrder(root);
        System.out.println();
    }
    public void postOrder(TriNode<T> p)          //后根次序遍历以p结点为根的子二叉树，递归方法
    {
        if (p!=null)
        {
            postOrder(p.left);
            postOrder(p.right);
            System.out.print(p.level+p.data.toString()+" ");
        }
    }

    //基于遍历的操作，算法同二叉链表
    public int count()                           //返回二叉树的结点个数
    {
        return count(root);
    }
    public int count(TriNode<T> p)               //返回以p结点为根的子树的结点个数
    {
        if (p==null)
            return 0;
        return 1+count(p.left)+count(p.right);
    }

    public int height()                          //返回二叉树的高度
    {
        return height(root);
    }
    public int height(TriNode<T> p)              //返回以p结点为根的子树的高度
    {
        if (p==null)
            return 0;
        int lh = height(p.left);                 //返回左子树的高度
        int rh = height(p.right);                //返回右子树的高度
        return (lh>=rh) ? lh+1 : rh+1;           //当前子树高度为较高子树的高度加1
    }

    public T search(T x)                //查找值为x的结点
    {
        return searchNode(root, x).data;
    }
    public TriNode<T> searchNode(T x)                //查找值为x的结点
    {
        return searchNode(root, x);
    }
    //在以p为根的子树中查找值为x结点，先根次序遍历，返回首次出现的值为x的结点，若未找到返回null
    public TriNode<T> searchNode(TriNode<T> p, T x)
    {
        TriNode<T> find=null;                    //记载找到结点
        if (p!=null && x!=null)
        {
            if (p.data.equals(x))
                return p;                         //查找成功，返回指定结点
            find = searchNode(p.left, x);            //在左子树中查找，递归调用
            if (find==null)                      //若在左子树中未找到
                find=searchNode(p.right, x);          //则继续在右子树中查找，递归调用
        }
        return find;                             //返回查找结果
    }

    //比较两棵二叉树是否相等，算法同二叉链表
    public boolean equals(Object obj)
    {
        return obj==this || obj instanceof TriBinaryTree && equals(this.root, ((TriBinaryTree<T>)obj).root);
    }
    //判断以p和q结点为根的两棵子树是否相等，递归方法
    private boolean equals(TriNode<T> p, TriNode<T> q)
    {
        return p==null && q==null || p!=null && q!=null && p.data.equals(q.data) &&
                equals(p.left, q.left) && equals(p.right, q.right);
    }

    //按层次遍历二叉树，算法同二叉链表
    public void levelOrder()
    {
        LinkedQueue<TriNode<T>> que=new LinkedQueue<TriNode<T>>();
        TriNode<T> p=this.root;
        System.out.print("层次遍历：  ");
        while (p!=null)
        {
            System.out.print(p.level+p.data.toString()+ " ");
            if(p.left!=null)
                que.enqueue(p.left);             //p的左孩子结点入队
            if(p.right!=null)
                que.enqueue(p.right);            //p的右孩子结点入队
            p = que.dequeue();                   //p指向出队结点
        }
        System.out.println();
    }

    //删除二叉树，算法同二叉链表
    public void removeRoot()                     //删除二叉树
    {
        this.root = null;
    }
    //删除p结点的左或右子树，若leftChild为true，删除左子树，否则删除右子树
    public void removeChild(TriNode<T> p, boolean leftChild)
    {
        if (p!=null)
            if (leftChild)
                p.left = null;
            else
                p.right = null;
    }

    //习题6
    //深拷贝
    public TriBinaryTree(TriBinaryTree<T> bitree)          //深拷贝构造二叉树
    {
        this.root = copy(bitree.root, null);
    }

    //复制以p根的子二叉树，返回新建子二叉树的根结点，算法有改动
    private TriNode<T> copy(TriNode<T> p, TriNode<T> parent)
    {
        if (p==null)
            return null;
        TriNode<T> q = new TriNode<T>(p.data, parent, null, null, p.level);
        q.left = copy(p.left, q);                          //复制左子树，递归调用
        q.right = copy(p.right, q);                        //复制右子树，递归调用
        return q;                                          //返回建立子树的根结点
    }

}
