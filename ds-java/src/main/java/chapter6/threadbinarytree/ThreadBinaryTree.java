package chapter6.threadbinarytree;

/**
 * Created by lili on 2017/7/1.
 */
public class ThreadBinaryTree<T>                 //中序线索二叉树类，泛型T指定结点的元素类型
{
    public ThreadNode<T> root;

    public ThreadBinaryTree()                    //构造空中序线索二叉树
    {
        this.root = null;
    }

    public boolean isEmpty()                     //判断中序线索二叉树是否空
    {
        return root==null;
    }

    public ThreadBinaryTree(T[] prelist)         //以标明空子树的先根序列构造二叉树并进行中序线索化
    {
        this.root = create(prelist);
        inorderThread(this.root);
    }
    //以标明空子树的先根序列构造一棵子二叉树，子树的根值是prelist[i]，返回所创建子树的根结点
    private int i=0;
    private ThreadNode<T> create(T[] prelist)
    {
        ThreadNode<T> p = null;
        if (i<prelist.length)
        {
            T elem=prelist[i++];
            if (elem!=null)
            {
                p = new ThreadNode<T>(elem);
                p.left = create(prelist);
                p.right = create(prelist);
            }
        }
        return p;
    }

    //中序线索化以p结点为根的子树，p的前驱结点是front
    private ThreadNode<T> front=null;
    private void inorderThread(ThreadNode<T> p)
    {
        if (p!=null)
        {
            inorderThread(p.left);               //中序线索化p的左子树
            if (p.left==null)                    //若p的左子树为空
            {
                p.ltag=1;                        //设置左线索标记
                p.left=front;                    //设置p.left为指向前驱front的线索
            }
            if (p.right==null)                   //若p的右子树为空
                p.rtag=1;                        //设置右线索标记
            if (front!=null && front.rtag==1)
                front.right=p;                   //设置前驱front.right为指向后继p的线索
            front=p;
            inorderThread(p.right);              //中序线索化p的右子树
        }
    }

    public ThreadNode<T> inNext(ThreadNode<T> p) //返回p在中根次序下的后继结点
    {
        if (p.rtag==1)                           //若右子树为空
            p=p.right;                           //p.right就是指向后继结点的线索
        else
        {
            p=p.right;                           //若右子树非空，进入右子树
            while (p.ltag==0)                    //找到最左边的后代结点
                p=p.left;
        }
        return p;
    }
    public void inOrder()                                  //中根次序遍历中序线索二叉树，非递归算法
    {
        System.out.print("中根次序遍历中序线索二叉树：  ");
        ThreadNode<T> p=this.root;
        while (p!=null && p.ltag==0)                       //寻找根的最左边的后代结点，即第一个访问结点
            p=p.left;
        while (p!=null)
        {
            System.out.print(p.data.toString()+" ");
            p = this.inNext(p);                            //返回p在中根次序下的后继结点
        }
        System.out.println();
    }

    public ThreadNode<T> preNext(ThreadNode<T> p)          //返回p在先根次序下的后继结点
    {
        if (p.ltag==0)                                     //若左子树非空
            p=p.left;                                      //左孩子就是p的后继结点
        else                                               //否则，后继是右兄弟或某个中序祖先的右孩子
        {
            while(p.rtag==1 && p.right!=null)              //寻找某个中序祖先
                p=p.right;                                 //后继是其某个中序祖先的右孩子
            p=p.right;                                     //右孩子是p的后继结点
        }
        return p;
    }
    public void preOrder()                       //先根次序遍历中序线索二叉树，非递归算法
    {
        System.out.print("先根次序遍历中序线索二叉树：  ");
        for (ThreadNode<T> p=this.root;  p!=null;  p=preNext(p))  //返回p在先根次序下的后继结点
            System.out.print(p.data.toString()+" ");
        System.out.println();
    }

    //6. 中序线索二叉树的插入操作
    public void insertRoot(T x)                  //插入作为根结点
    {
        if (this.root==null)
            this.root=new ThreadNode<T>(x, null,1, null,1);
        else
        {
            ThreadNode<T> p=this.root;
            while (p!=null && p.rtag==0)         //寻找原根的最右边子孙结点p，即最后访问结点
                p=p.right;
            this.root=new ThreadNode<T>(x, this.root,0, null,1);
            p.right=this.root;                   //修改p的后继线索
        }
    }

    //插入x作为p结点的孩子结点，若leftChild为true，插入左孩子，否则插入右孩子，返回插入的孩子结点
    public ThreadNode<T> insertChild(ThreadNode<T> p, T x, boolean leftChild)
    {
        ThreadNode<T> q=null;
        if (leftChild)                           //插入左孩子
        {
            q=new ThreadNode<T>(x, p.left, p.ltag, p,1);
            p.left=q;
            p.ltag=0;
            if (q.ltag==0)
            {
                ThreadNode<T> pred=inPred(q);    //pred是q在中根次序下的前驱
                pred.right=q;                    //修改前驱结点pred的后继线索
            }
        }
        else                                     //插入右孩子
        {
            q=new ThreadNode<T>(x, p,1,p.right, p.rtag);
            p.right=q;
            p.rtag=0;
            if (q.rtag==0)
            {
                ThreadNode<T> succ=inNext(q);    //succ是q在中根次序下的后继
                succ.left=q;                     //修改后继结点succ的前驱线索
            }
        }
        return q;
    }

    //7. 中序线索二叉树删除操作，用左孩子顶替，叶算法
    public void removeRightChild(ThreadNode<T> p)//删除指定结点p的右孩子结点
    {
        if (root==null || p==null || p.rtag==1)  //若p无右孩子，则不删除
            return;
        ThreadNode<T> q=p.right;                 //q为待删除结点
        if (q.ltag==0)                           //q有左孩子
        {
            p.right = q.left;                    //用q的左孩子顶替q，p.rtag未变
            ThreadNode<T> pred=inPred(q);        //pred指向q在中根次序下的前驱
            pred.right=q.right;                  //修改前驱结点pred的后继线索
            pred.rtag=q.rtag;
            if (q.rtag==0)                       //q也有右孩子，q是2度结点
                inNext(q).left=pred;             //修改q后继结点inNext(q)的前驱线索
        }
        else                                     //q或是只有右孩子的1度结点，或是叶子结点
        {
            p.right = q.right;                   //用q的右孩子（或后继线索）顶替q
            p.rtag = q.rtag;
            if (q.rtag==0)                       //q有右孩子没有左孩子
                inNext(q).left=p;                //修改q后继结点inNext(q)的前驱线索
        }
    }

    //删除根结点，用左孩子顶替
    public void removeRoot()
    {
        if (root==null)                          //若空树，则不删除
            return;
        ThreadNode<T> pred=inPred(root);         //pred指向根的前驱
        ThreadNode<T> succ=inNext(root);         //succ指向根的前驱
        if (pred!=null)                          //根有前驱时
        {
            pred.right=root.right;               //使前驱pred的后继域指向根的右孩子
            pred.rtag=root.rtag;
        }
        if (succ!=null)                          //根有后继时
            succ.left=pred;                      //修改后继succ的前驱线索，succ.ltag未变
        if (root.ltag==0)                        //根有左孩子时
            root = root.left;                    //用左孩子顶替根
        else
            root = root.right;                   //用右孩子顶替根
    }

    //习题6
    public ThreadNode<T> inPred(ThreadNode<T> p) //返回p在中根次序下的前驱结点
    {
        if (p.ltag==1)                           //若左子树为空
            p=p.left;                            //p.left就是指向p前驱结点的线索
        else                                     //若左子树非空
        {
            p=p.left;                            //进入左子树
            while (p.rtag==0)                    //找到最右边的子孙结点
                p=p.right;
        }
        return p;
    }
    public void inOrder_pred()                   //中根次序遍历中序线索二叉树，非递归算法
    {
        System.out.print("中根次序（反序）遍历中序线索二叉树：  ");
        ThreadNode<T> p=this.root;
        while (p!=null && p.rtag==0)             //寻找根的最右边的后代结点
            p=p.right;
        while (p!=null)
        {
            System.out.print(p.data.toString()+" ");
            p=inPred(p);                         //返回p在中根次序下的前驱结点
        }
        System.out.println();
    }

    public ThreadNode<T> postPred(ThreadNode<T> p) //返回p在后根次序下的前驱结点
    {
        if(p.rtag==0)                            //右子树非空
            p=p.right;                           //右孩子是p的前驱结点
        else                                     //否则，前驱是左兄弟或其某个中序祖先的左孩子
        {
            while (p.ltag==1 && p.left!=null)    //寻找其某个中序祖先
                p=p.left;
            p=p.left;                            //左孩子是p的前驱结点
        }
        return p;
    }
    public void postOrder_pred()                 //后根次序遍历中序线索二叉树，非递归算法
    {
        System.out.print("后根次序（反序）遍历中序线索二叉树：  ");
        for (ThreadNode<T> p=this.root;  p!=null;  p=postPred(p))  //返回p在后根次序下的前驱结点
            System.out.print(p.data.toString()+" ");
        System.out.println();
    }

    public void removeLeftChild(ThreadNode<T> p) //删除指定结点p的左孩子结点
    {
        if (root==null || p==null || p.ltag==1)  //若p无左孩子，则不删除
            return;
        ThreadNode<T> q=p.left;                  //q为待删除结点
        if (q.ltag==0)                           //q有左孩子
        {
            p.left = q.left;                     //用q的左孩子顶替q，p.rtag未变
            ThreadNode<T> pred=inPred(q);        //pred指向q在中根次序下的前驱
            pred.right=q.right;                  //修改前驱结点pred的后继线索
            pred.rtag=q.rtag;
            if (q.rtag==0)                       //q也有右孩子，q是2度结点
                inNext(q).left=pred;             //修改q后继结点inNext(q)的前驱线索
        }
        else                                     //q或是只有右孩子的1度结点，或是叶子结点
            if (q.rtag==1)                       //q是叶子结点
            {
                p.left = q.left;                 //用q的前驱线索顶替q
                p.ltag = 1;
            }
            else                                 //q是只有右孩子的1度结点
            {
                p.left = q.right;                //用q的右孩子顶替q
                inNext(q).left=q.left;           //修改q后继结点inNext(q)的前驱线索
            }
    }

    //习题6
    ThreadNode<T> getParent(ThreadNode<T> node)            //返回node结点的父母结点
    {
        if (root==null || node==null || node==root)
            return null;                                   //根的父母结点为null
        ThreadNode<T> p=node;
        while (p.ltag==0)                                  //先从左边找，寻找node结点最左边的子孙结点
            p=p.left;
        if (p.left!=null)                                  //前驱线索不空时
        {
            p=p.left;                                      //沿前驱线索到达node的一个祖先结点
            if (p.right==node)
                return p;                                  //该祖先是所求父母结点
            p=p.right;                                     //沿着右孩子链逐层向下寻找
            while (p.left!=node)
                p=p.left;                                  //沿着左孩子链逐层向下寻找
        }
        else                                               //前驱线索空时，再向右边寻找
        {   p=node;
            while (p.rtag==0)                              //找到node结点最右边的子孙结点
                p=p.right;
            p=p.right;                                     //沿后继线索到达node的一个祖先结点
            if (p.left==node)
                return p;
            p=p.left;
            while (p.right!=node)
                p=p.right;                                 //沿着右孩子链逐层向下寻找
        }
        return p;
    }
}
