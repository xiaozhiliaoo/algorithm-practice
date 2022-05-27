package chapter6.childsiblings;

import chapter4.queue.LinkedQueue;
import chapter4.stack.LinkedStack;

/**
 * Created by lili on 2017/7/1.
 */
//6.6.3   树的孩子兄弟链表实现
//树类，实现TTree<T>接口，泛型T指定结点的元素类型
public class Tree<T> implements TTree<T>
{
    public TreeNode<T> root;                     //根结点，结点结构是树的孩子兄弟链表

    public Tree()                                //构造空树
    {
        this.root=null;
    }
    public boolean isEmpty()                     //判断树是否空
    {
        return this.root==null;
    }

    //3. 树的先根遍历算法
    public String toString()                     //先根次序遍历树并返回树的横向凹入表示字符串
    {
        return "先根次序遍历树： \n "+toString(root,"");
    }
    //先根次序遍历以p为根的子树，tab参数指定缩进量，返回子树的横向凹入表示字符串，递归算法
    private String toString(TreeNode<T> p, String tab)
    {
        if (p==null)
            return "";
        return tab+p.data.toString()+"\n" + toString(p.child,tab+"\t") + toString(p.sibling,tab);//递归调用
    }

    //4.返回 结点
    public TreeNode<T> getLastChild(TreeNode<T> p)         //返回p结点的最后一个孩子
    {
        if (p==null || p.child==null)                      //p没有孩子
            return null;
        p = p.child;
        while (p.sibling!=null)                            //沿着兄弟链到达最后一个兄弟结点
            p = p.sibling;
        return p;                                          //返回孩子链的最后一个结点
    }

    public TreeNode<T> getLastSibling(TreeNode<T> p)       //返回p结点的最后一个兄弟
    {
        if (p==null || p.sibling==null)
            return null;
        while (p.sibling!=null)                            //p沿着兄弟链到达最后一个兄弟结点
            p = p.sibling;
        return p;
    }

    //5.插入结点
    public void insertRoot(T x)                            //插入元素x作为根结点，原根结点作为其孩子结点
    {
        this.root = new TreeNode<T>(x, this.root, null);
    }
    //插入x作为p结点的最后一个兄弟结点，返回插入结点
    public TreeNode<T> insertLastSibling(TreeNode<T> p, T x)
    {
        if (p==null)
            return null;
        while (p.sibling!=null)                            //p沿着兄弟链到达最后一个兄弟结点
            p = p.sibling;
        p.sibling = new TreeNode<T>(x);                    //插入最后一个兄弟
        return p.sibling;
    }
    //插入x作为p结点的最后一个孩子结点，返回插入结点
    public TreeNode<T> insertLastChild(TreeNode<T> p, T x)
    {
        if (p==null)
            return null;
        if (p.child==null)
        {
            p.child = new TreeNode<T>(x);                  //插入作为p结点的第一个孩子
            return p.child;
        }
        else
            return insertLastSibling(p.child, x);          //插入作为p.child的最后一个兄弟
    }

    public void removeAll()                                //删除树的所有结点
    {
        this.root = null;
    }

    //【例6.7】  构造一棵城市树或森林。
    //【例6.8】 以树的横向凹入表示构造树或森林。

    //7.树的广义表表示
    public String toGenListString()                        //返回树或森林的广义表表示字符串
    {
        return "树的广义表表示："+toGenListString(this.root);
    }
    public String toGenListString(TreeNode<T> p)           //返回以p结点为根的子树的广义表表示
    {
        if (p==null)
            return "";                                     //返回空子树表示
        String str=p.data.toString();
        if (p.child!=null)
            str += "("+ toGenListString(p.child)+ ")";
        if (p.sibling!=null)
            str += ","+ toGenListString(p.sibling);
        return str;
    }
    //【例6.9】 树的广义表表示及构造。


    //习题6
    //返回 结点
    public TreeNode<T> getRoot()                 //返回树的根结点
    {
        return this.root;
    }
    public TreeNode<T> getChild(TreeNode<T> p, int i)      //返回p结点的第i（i≥0）个孩子结点
    {
        if (p==null || p.child==null || i<=0)
            return null;
        p = p.child;
        for (int j=1;  p!=null && j<i; j++)
            p = p.sibling;
        return p;
    }
    /*以下算法有什么错误？
    public TreeNode<T> getLastChild(TreeNode<T> p)         //返回p结点的最后一个孩子
    {
        if (p==null || p.child==null)
            return null;
        return getLastSibling(p.child);                    //返回p.child结点的最后一个兄弟
    }
           不能返回p的第一个孩子结点    */

    public int childCount(TreeNode<T> p)         //返回p结点的孩子结点数
    {
        int i=0;
        if (p!=null)
            for (p=p.child;  p!=null;  p=p.sibling)
                i++;
        return i;
    }

    //判断node结点是否是parent结点的孩子结点
    public boolean isChild(TreeNode<T> parent, TreeNode<T> node)
    {
        if (root==null || parent==null || node==null)
            return false;
        for (TreeNode<T> p=parent.child;  p!=null;  p=p.sibling)
            if (p==node)
                return true;
        return false;
    }

    //返回node结点的父母结点，若空树、未找到或node为根，则返回null
    public TreeNode<T> getParent(TreeNode<T> node)
    {
        if (root==null || node==null || node==root)
            return null;
        return getParent(root, node);
    }
    //在以p为根的子树中查找并返回node结点的父母结点
    public TreeNode<T> getParent(TreeNode<T> p, TreeNode<T> node)
    {
        if (p==null)
            return null;
        if (isChild(p,node))
            return p;
        TreeNode<T> find = getParent(p.child, node);
        if (find==null)
            find = getParent(p.sibling, node);
        return find;
    }

    //3. 树的先根和后根次序遍历算法
    public void postOrder()                      //后根次序遍历树
    {
        System.out.print("后根次序遍历树:  ");
        postOrder(root);
        System.out.println();
    }
    //后根次序遍历以p为根的子树，递归算法，算法同二叉树的中根次序遍历
    public void postOrder(TreeNode<T> p)
    {
        if (p!=null)
        {
            postOrder(p.child);
            System.out.print(p.data+" ");
            postOrder(p.sibling);
        }
    }

    //插入x元素作为p结点的第i个孩子结点，返回插入结点。
    //当i≤0时，插入x作为p结点的第一个孩子结点；当i大于p结点的孩子个数时，插入x作为p结点的最后一个孩子结点。
    public TreeNode<T> insertChild(TreeNode<T> p, T x, int i)
    {
        if (p==null)
            return null;
        TreeNode<T> q=new TreeNode<T>(x);
        if (p.child==null || i<=0)                         //插入x作为p结点的第一个孩子
        {
            q.sibling = p.child;
            p.child = q;
        }
        else                                               //插入x作为p结点的第i（i≥1）个孩子
        {
            p = p.child;
            for (int j=1;  j<i && p.sibling!=null;  j++)
                p = p.sibling;
            q.sibling = p.sibling;
            p.sibling = q;
        }
        return q;
    }

    //插入x元素作为p结点的下一个兄弟结点，返回插入结点??有必要吗??
    public TreeNode<T> insertNextSibling(TreeNode<T> p, T x)
    {
        if (p==null)
            return null;
        TreeNode<T> q = new TreeNode<T>(x, null, p.sibling);
        p.sibling = q;
        return q;
    }

    //复制sub子树并插入作为p的第i（i≥0）个子树，返回插入结点。
    //当i≤0时，插入x作为p结点的第一个孩子结点；当i大于p结点的孩子个数时，插入x作为p结点的最后一个孩子结点。
    public TreeNode<T> insertChild(TreeNode<T> p, Tree<T> sub, int i)
    {
        TreeNode<T> q = insertChild(p, sub.root.data, i);
        q.child = copy(sub.root.child);
        return q;
    }

    //树的删除操作
    public void removeRoot()                               //删除树
    {
        this.root = null;
    }

    public void removeChild(TreeNode<T> p, int i)          //删除以p的第i（i≥0）个孩子为根的子树
    {
        if (p==null || p.child==null || i<0)
            return;
        if (i==0)
            p.child = p.child.sibling;
        else
        {
            p = p.child;
            for (int j=1;  j<i-1 && p!=null;  j++)
                p = p.sibling;
            p.sibling = p.sibling.sibling;
        }
    }

    //基于遍历的操作，递归算法
    public int height()                                    //返回树的高度
    {
        return height(root);
    }
    public int height(TreeNode<T> p)                       //返回以p结点为根的子树高度，后根次序遍历，递归算法
    {
        if (p!=null)
        {
            int lh = height(p.child)+1;
            int rh = height(p.sibling);
            return (lh>=rh) ? lh: rh;
        }
        return 0;
    }

    //返回元素为x的结点所在的层次，若空树或未查找到x返回-1
    public int getLevel(T x)
    {
        return getLevel(root, 1, x);                       //令根结点的层次为1
    }
    //在以p结点（层次为i）为根的子树中，求x结点所在的层次
    private int getLevel(TreeNode<T> p, int i, T x)
    {
        if (p==null)                                       //空树或查找不成功
            return -1;
        if (p.data.equals(x))
            return i;                                      //查找成功
        int level = getLevel(p.child, i+1, x);             //在子树查找
        if (level==-1)
            level = getLevel(p.sibling, i, x);             //继续在其他兄弟子树中查找
        return level;
    }

    //树的层次遍历
    public void levelOrder()                               //按层次遍历树
    {
        System.out.print("层次遍历树：  ");
        LinkedQueue<TreeNode<T>> que=new LinkedQueue<TreeNode<T>>(); //创建一个空队列
        for (TreeNode<T> p=this.root;  p!=null;  p=que.dequeue())
        {
            System.out.print(p.data+ " ");
            for (p=p.child;  p!=null;  p=p.sibling)        //所有孩子结点入队
                que.enqueue(p);
        }
        System.out.println();
    }

    //以下算法同二叉树
    public void preOrder()                                 //先根次序遍历树，算法同二叉树
    {
        System.out.print("先根次序遍历树:  ");
        preOrder(root);
        System.out.println();
    }
    public void preOrder(TreeNode<T> p)                    //先根次序遍历以p为根的子树，递归算法
    {
        if (p!=null)
        {
            System.out.print(p.data+" ");
            preOrder(p.child);                             //递归调用
            preOrder(p.sibling);
        }
    }

    public int count()                                     //返回树的结点个数，算法同二叉树
    {
        return count(root);
    }
    public int count(TreeNode<T> p)                        //返回以p结点为根的子树的结点个数
    {
        if (p==null)
            return 0;
        return 1+count(p.child)+count(p.sibling);
    }

    public TreeNode<T> search(T x)                         //查找值为x的结点，算法同二叉树
    {
        return search(root, x);
    }
    //在以p为根的子树中查找值为x结点，先根次序遍历，返回首次出现的值为x的结点，若未找到返回null
    public TreeNode<T> search(TreeNode<T> p, T x)
    {
        if (p==null || x==null)
            return null;
        if (p.data.equals(x))
            return p;                                      //查找成功，返回指定结点
        TreeNode<T> find=search(p.child, x);               //在左子树中查找，递归调用
        if (find==null)                                    //若在左子树中未找到
            find=search(p.sibling, x);                     //则继续在右子树中查找，递归调用
        return find;                                       //返回查找结果
    }

    public Tree(Tree<T> bitree)                            //深拷贝，算法同二叉树
    {
        this.root = copy(bitree.root);
    }
    public TreeNode<T> copy(TreeNode<T> p)                 //复制以p根的子树，返回新建子树的根结点
    {
        if (p==null)
            return null;
        TreeNode<T> q = new TreeNode<T>(p.data);
        q.child = copy(p.child);                           //递归调用
        q.sibling = copy(p.sibling);                       //递归调用
        return q;                                          //返回建立子树的根结点
    }

    public boolean equals(Object obj)                      //比较两棵树是否相等，算法同二叉树
    {
        return obj==this || obj instanceof Tree && this.equals(this.root,((Tree<T>)obj).root);
    }
    //判断以p和q结点为根的两棵子树是否相等，递归方法
    private boolean equals(TreeNode<T> p, TreeNode<T> q)
    {
        return p==null && q==null || p!=null && q!=null && p.data.equals(q.data) &&
                equals(p.child, q.child) && equals(p.sibling, q.sibling);
    }

    public void preOrderTraverse()                         //先根次序遍历树的非递归算法，算法同二叉树
    {
        System.out.print("先根次序遍历（非递归）：  ");
        LinkedStack<TreeNode<T>> stack = new LinkedStack<TreeNode<T>>();   //创建一个空栈
        TreeNode<T> p = this.root;
        while (p!=null || !stack.isEmpty())                //p非空或栈非空时
            if (p!=null)
            {
                System.out.print(p.data+" ");              //访问结点
                stack.push(p);                             //p结点入栈
                p=p.child;                                 //进入左子树
            }
            else                                           //p为空且栈非空时
            {
                p=stack.pop();                             //p指向出栈结点
                p=p.sibling;                               //进入右子树
            }
        System.out.println();
    }
}

