package chapter6.binarytree;

import chapter4.queue.LinkedQueue;
import chapter4.queue.SeqQueue;
import chapter4.stack.LinkedStack;

/**
 * Created by lili on 2017/7/1.
 */
//二叉树类，实现BinaryTTree<T>接口，泛型T指定结点的元素类型
public class BinaryTree<T> implements BinaryTTree<T>
{
    public BinaryNode<T> root;                   //根结点，结点结构为二叉链表

    public BinaryTree()                          //构造空二叉树
    {
        this.root=null;
    }
    public boolean isEmpty()                     //判断二叉树是否空
    {
        return this.root==null;
    }

    //3. 二叉树的先根、中根和后根次序遍历算法
    public void preOrder()                       //先根次序遍历二叉树
    {
        System.out.print("先根次序遍历二叉树：  ");
        preOrder(root);                          //调用先根次序遍历二叉树的递归方法
        System.out.println();
    }
    public void preOrder(BinaryNode<T> p)        //先根次序遍历以p结点为根的子二叉树，递归方法
    {
        if (p!=null)                             //若二叉树不空
        {
            System.out.print(p.data.toString()+" ");  //访问当前结点
            preOrder(p.left);                    //按先根次序遍历当前结点的左子树，递归调用
            preOrder(p.right);                   //按先根次序遍历当前结点的右子树，递归调用
        }
    }
    public String toString()                     //返回先根次序遍历二叉树所有结点的描述字符串
    {
        return toString(root);
    }
    private String toString(BinaryNode<T> p)     //返回先根次序遍历以p为根的子树描述字符串，递归算法
    {
        if (p==null)
            return "";
        return p.data.toString()+" " + toString(p.left) + toString(p.right);//递归调用
    }

    public void inOrder()                        //中根次序遍历二叉树
    {
        System.out.print("中根次序遍历二叉树：  ");
        inOrder(root);
        System.out.println();
    }
    public void inOrder(BinaryNode<T> p)         //中根次序遍历以p结点为根的子二叉树，递归方法
    {
        if (p!=null)
        {
            inOrder(p.left);                     //中根次序遍历左子树，递归调用
            System.out.print(p.data.toString()+" ");
            inOrder(p.right);                    //中根次序遍历右子树，递归调用
        }
    }

    public void postOrder()                      //后根次序遍历二叉树
    {
        System.out.print("后根次序遍历二叉树：  ");
        postOrder(root);
        System.out.println();
    }
    public void postOrder(BinaryNode<T> p)       //后根次序遍历以p结点为根的子二叉树，递归方法
    {
        if (p!=null)
        {
            postOrder(p.left);
            postOrder(p.right);
            System.out.print(p.data.toString()+" ");
        }
    }
    //【例6.1】  构造并遍历二叉树。

    //4. 基于遍历的操作
    public int count()                           //返回二叉树的结点个数
    {
        return count(root);
    }
    public int count(BinaryNode<T> p)            //返回以p结点为根的子树的结点个数
    {
        if (p==null)
            return 0;
        return 1+count(p.left)+count(p.right);
    }

    public int height()                           //返回二叉树的高度
    {
        return height(root);
    }
    public int height(BinaryNode<T> p)            //返回以p结点为根的子树高度，后根次序遍历
    {
        if (p==null)
            return 0;
        int lh = height(p.left);                 //返回左子树的高度
        int rh = height(p.right);                //返回右子树的高度
        return (lh>=rh) ? lh+1 : rh+1;           //当前子树高度为较高子树的高度加1
    }

    public T search(T key)                           //查找并返回首次出现的关键字为key元素
    {
        return searchNode(root, key).data;
    }
    public BinaryNode<T> searchNode(T key)           //查找并返回首次出现的关键字为key元素结点
    {
        return searchNode(root, key);
    }
    //在以p为根的子树中查找并返回首次出现的关键字为key元素结点，若未找到返回null，先根次序遍历
    public BinaryNode<T> searchNode(BinaryNode<T> p, T key)
    {
        if (p==null || key==null)
            return null;
        if (p.data.equals(key))
            return p;                            //查找成功，返回找到结点
        BinaryNode<T> find=searchNode(p.left, key);  //在左子树中查找，递归调用
        if (find==null)                          //若在左子树中未找到
            find=searchNode(p.right, key);           //则继续在右子树中查找，递归调用
        return find;                             //返回查找结果
    }

    //返回node结点的父母结点，若空树、未找到或node为根，则返回null
    public BinaryNode<T> getParent(BinaryNode<T> node)
    {
        if (root==null || node==null || node==root)
            return null;
        return getParent(root, node);
    }
    //在以p为根的子树中查找并返回node结点的父母结点
    public BinaryNode<T> getParent(BinaryNode<T> p, BinaryNode<T> node)
    {
        if (p==null)
            return null;
        if (p.left==node || p.right==node)
            return p;
        BinaryNode<T> find = getParent(p.left, node);
        if (find==null)
            find = getParent(p.right, node);
        return find;
    }

    //5. 构造二叉树
    public BinaryTree(T[] prelist, T[] inlist)        //以先根和中根序列构造二叉树
    {
        this.root = create(prelist, inlist, 0, 0, prelist.length);
    }
    //以先根和中根序列创建一棵子树，子树根结点值是prelist[preStart]，n指定子序列长度，
    //返回所创建子树的根结点
    private BinaryNode<T> create(T[] prelist, T[] inlist, int preStart, int inStart, int n)
    {
        System.out.print("prelist:");
        print(prelist, preStart, n);
        System.out.print("，inlist:");
        print(inlist, inStart, n);
        System.out.println();

        if (n<=0)
            return null;
        T elem=prelist[preStart];                          //根结点值
        BinaryNode<T> p=new BinaryNode<T>(elem);           //创建叶子结点
        int i=0;
        while (i<n && !elem.equals(inlist[inStart+i]))     //在中根序列中查找根值所在位置
            i++;
        p.left = create(prelist, inlist, preStart+1, inStart, i);             //创建左子树
        p.right = create(prelist, inlist, preStart+i+1, inStart+i+1, n-1-i);  //创建右子树
        return p;
    }
    private void print(T[] table, int start, int n)
    {
        for (int i=0; i<n; i++)
            System.out.print(table[start+i]);
    }

    public BinaryTree(T[] prelist)                    //以标明空子树的先根序列构造二叉树
    {
        this.root = create(prelist);
    }
    //以标明空子树的先根序列构造一棵子二叉树，子树的根值是prelist[i]，返回所创建子树的根结点
    private int i=0;
    private BinaryNode<T> create(T[] prelist)
    {
        BinaryNode<T> p = null;
        if (i<prelist.length)
        {
            T elem=prelist[i];
            i++;
            if (elem!=null)                           //不能elem!="^"，因为T不一定是String
            {
                p = new BinaryNode<T>(elem);          //创建叶子结点
                p.left = create(prelist);             //创建p的左子树
                p.right = create(prelist);            //创建p的右子树
            }
        }
        return p;
    }
    //【例6.2】  输出二叉树中指定结点的所有祖先结点。

    public String toGenListString()                        //返回二叉树的广义表表示字符串
    {
        return "二叉树的广义表表示："+toGenListString(this.root)+"\n";
    }
    //返回以p结点为根的子二叉树的广义表表示字符串，递归方法
    public String toGenListString(BinaryNode<T> p)
    {
        if (p==null)
            return "^";                                    //返回空子树表示
        String str=p.data.toString();
        if (p.left!=null || p.right!=null)                 //非叶结点，有子树
            str += "("+toGenListString(p.left)+","+toGenListString(p.right)+")";//递归调用
        return str;
    }
    //【例6.3】 二叉树的广义表表示。

    //6. 二叉树的插入和删除操作

    //插入元素x作为根结点，原根结点作为其左孩子
    public void insertRoot(T x)
    {
        this.root = new BinaryNode<T>(x, this.root, null);
    }
    //插入元素x作为p结点的孩子，若leftChild为true，插入结点作为左孩子，否则作为右孩子
    //返回插入结点。若p==null，将抛出空对象异常
    public BinaryNode<T> insertChild(BinaryNode<T> p, T x, boolean leftChild)
    {
        if (x==null)
            return null;
        if (leftChild)
        {
            p.left=new BinaryNode<T>(x,p.left,null);  //插入x结点作为p的左孩子，p原左孩子成为x的左孩子
            return p.left;                            //返回插入结点
        }
        p.right=new BinaryNode<T>(x,null,p.right);    //插入x结点作为p的右孩子，p原右孩子成为x的右孩子
        return p.right;
    }

    //删除p结点的左或右子树，若leftChild为true，删除左子树，否则删除右子树
    //若p==null，将抛出空对象异常
    public void removeChild(BinaryNode<T> p, boolean leftChild)
    {
        if (leftChild)
            p.left = null;
        else p.right = null;
    }
    public void removeAll()                     //删除二叉树的所有结点
    {
        this.root = null;
    }

    //7. 二叉树遍历的非递归算法
    public void preOrderTraverse()               //先根次序遍历二叉树的非递归算法
    {
        System.out.print("先根次序遍历（非递归）：  ");
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<BinaryNode<T>>();   //创建一个空栈
        BinaryNode<T> p = this.root;
        while (p!=null || !stack.isEmpty())      //p非空或栈非空时
            if (p!=null)
            {
                System.out.print(p.data+" ");    //访问结点
                stack.push(p);                   //p结点入栈
                p=p.left;                        //进入左子树
            }
            else                                 //p为空且栈非空时
            {
                p=stack.pop();                   //p指向出栈结点
                p=p.right;                       //进入右子树
            }
        System.out.println();
    }

    public void inOrderTraverse()                          //中根次序遍历二叉树的非递归算法
    {
        System.out.print("中根次序遍历（非递归）：  ");
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<BinaryNode<T>>();   //创建一个空栈
        BinaryNode<T> p = this.root;
        while (p!=null || !stack.isEmpty())                //p非空或栈非空时
            if (p!=null)
            {
                stack.push(p);                             //p结点入栈
                p=p.left;                                  //进入左子树
            }
            else                                           //p为空且栈非空时
            {
                p=stack.pop();                             //p指向出栈结点
                System.out.print(p.data+" ");              //访问结点
                p=p.right;                                 //进入右子树
            }
        System.out.println();
    }

    //8. 二叉树的层次遍历
    public void levelOrder()                     //按层次遍历二叉树
    {
        System.out.print("层次遍历二叉树：  ");
        SeqQueue<BinaryNode<T>> que=new SeqQueue<BinaryNode<T>>(); //创建一个空队列
//        LinkedQueue<BinaryNode<T>> que=new LinkedQueue<BinaryNode<T>>(); //创建一个空队列
        BinaryNode<T> p=this.root;
        while (p!=null)
        {
            System.out.print(p.data+ " ");       //访问当前结点
            if (p.left!=null)
                que.enqueue(p.left);             //p的左孩子结点入队
            if (p.right!=null)
                que.enqueue(p.right);            //p的右孩子结点入队
            p = que.dequeue();                   //p指向出队结点，若队列空返回null
        }
        System.out.println();
    }

    //习题6
    public void leaf()                           //遍历输出叶子结点
    {
        leaf(root);
    }
    //输出以p结点为根的子树的所有叶子结点，先根次序遍历算法，3种遍历次序结果一样
    private void leaf(BinaryNode<T> p)
    {
        if(p!=null)
        {
            if (p.left==null && p.right==null)   //p.isLeaf()
                System.out.print(p.data.toString()+" ");
            leaf(p.left);
            leaf(p.right);
        }
    }

    public int countLeaf()                       //返回二叉树的叶子结点数
    {
        return countLeaf(root);
    }
    private int countLeaf(BinaryNode<T> p)       //返回以p结点为根的子树的叶子结点个数
    {
        if (p==null)
            return 0;
        if (p.left==null && p.right==null)
            return 1;
        return countLeaf(p.left)+countLeaf(p.right);
    }

    public void replace(T x, T y)                //将首次出现的值为x的结点值替换为y
    {
        BinaryNode<T> find = searchNode(x);
        if (find!=null)
            find.data = y;
    }
    public void replaceAll(T x, T y)                       //将值为x的结点值全部替换为y
    {
        if (x!=null && y!=null)
            replaceAll(root, x, y);
    }
    private void replaceAll(BinaryNode<T> p, T x, T y)     //在以p为根的子树中实现全部替换
    {
        if (p!=null)
        {
            if(p.data.equals(x))
                p.data = y;
            replaceAll(p.left, x, y);
            replaceAll(p.right, x, y);
        }
    }

    public void postOrderTraverse()                        //后根次序遍历二叉树的非递归算法
    {
        System.out.print("后根次序遍历（非递归）：  ");
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<BinaryNode<T>>();
        BinaryNode<T> p=this.root, front=null;
        while (p!=null || !stack.isEmpty())                //p非空或栈非空时
            if (p!=null)
            {
                stack.push(p);                             //p结点入栈
                p=p.left;                                  //进入左子树
            }
            else                                           //p为空且栈非空时
            {
                p=stack.get();                             //从左子树返回p结点，p结点不出栈
                if (p.right!=null && p.right!=front)       //p有右孩子，且右孩子没被访问过
                {
                    p = p.right;                           //进入右子树
                    stack.push(p);
                    p=p.left;                              //向左走
                }
                else
                {
                    p=stack.pop();                         //从右子树返回p结点，p结点出栈
                    System.out.println(p.data+"的所有祖先结点是："+stack.toString());
                    front = p;                             //front是p在后根遍历次序下的前驱结点
                    p=null;
                }
            }
        System.out.println();
    }

    //返回x结点所在的层次，若空树或未查找到x返回-1
    public int getLevel(T x)
    {
        return getLevel(root, 1, x);                  //令根结点的层次为1
    }
    //在以p结点（层次为i）为根的子树中，求x结点所在的层次
    private int getLevel(BinaryNode<T> p, int i, T x)
    {
        if (p==null)                                  //空树或查找不成功
            return -1;
        if (p.data.equals(x))
            return i;                                 //查找成功
        int level = getLevel(p.left, i+1, x);         //在左子树查找
        if (level==-1)
            level = getLevel(p.right, i+1, x);        //继续在右子树中查找
        return level;
    }

    public boolean equals(Object obj)             //比较两棵二叉树是否相等 ，覆盖Object类的equals(obj)方法
    {
        return obj==this || obj instanceof BinaryTree && equals(this.root, ((BinaryTree<T>)obj).root);
    }
    //判断以p和q结点为根的两棵子树是否相等，递归方法
    private boolean equals(BinaryNode<T> p, BinaryNode<T> q)
    {
        return p==null && q==null || p!=null && q!=null && p.data.equals(q.data) &&
                equals(p.left, q.left) && equals(p.right, q.right);
    }

    public BinaryTree(BinaryTree<T> bitree)      //深拷贝，以已知的bitree构造二叉树
    {
        this.root = copy(bitree.root);
    }

    //复制以p根的子二叉树，返回新建子二叉树的根结点
    private BinaryNode<T> copy(BinaryNode<T> p)
    {
        if (p==null)
            return null;
        BinaryNode<T> q = new BinaryNode<T>(p.data);
        q.left = copy(p.left);                   //复制左子树，递归调用
        q.right = copy(p.right);                 //复制右子树，递归调用
        return q;                                //返回建立子树的根结点
    }

    boolean isCompleteBinaryTree()               //判断是否完全二叉树
    {
        if (this.root==null)
            return true;
//	    SeqQueue<BinaryNode<T>> que = new SeqQueue<BinaryNode<T>>(); //创建空队列
        LinkedQueue<BinaryNode<T>> que = new LinkedQueue<BinaryNode<T>>();
        que.enqueue(root);                           //根结点入队
        BinaryNode<T> p=null;
        while (!que.isEmpty())
        {
            p = que.dequeue();                       //p指向出队结点
            if (p.left!=null )                       //p的非空孩子结点入队
            {
                que.enqueue(p.left);
                if (p.right!=null)
                    que.enqueue(p.right);
                else break;                          //发现空子树，须检测队列中是否都是叶子结点
            }
            else
            if (p.right!=null)
                return false;                    //p的左子树空而右子树不空，确定不是
            else break;                           //p是叶子，须检测队列中是否都是叶子结点
        }
        while (!que.isEmpty())                       //检测队列中是否都是叶子结点
        {
            p = que.dequeue();
            if (p.left!=null || p.right!=null)       //发现非叶子，确定不是
                return false;
        }
        return true;
    }
}

