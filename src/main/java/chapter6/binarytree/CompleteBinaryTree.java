package chapter6.binarytree;

/**
 * Created by lili on 2017/7/1.
 */
//【例6.3】  建立二叉链表表示的完全二叉树。

//（4） 以完全二叉树的层次遍历序列构造二叉链表结构存储的完全二叉树

//二叉链表表示的完全二叉树类，继承二叉树类
public class CompleteBinaryTree<T> extends BinaryTree<T>
{
    public CompleteBinaryTree()                            //构造空二叉树
    {
        super();
    }

    //以完全二叉树的层次序列构造完全二叉树，levellist指定层次序列
    public CompleteBinaryTree(T[] levellist)
    {
        this.root = create(levellist, 0);
    }

    //创建以levellist[i]为根的一棵子完全二叉树，返回所建子树的根结点
    private BinaryNode<T> create(T[] levellist, int i)
    {
        BinaryNode<T> p = null;
        if (i<levellist.length)
        {
            p = new BinaryNode<T>(levellist[i]);           //创建叶子结点
            p.left = create(levellist, 2*i+1);             //创建p的左子树
            p.right = create(levellist, 2*i+2);            //创建p的右子树
        }
        return p;
    }

    public static void main(String args[])
    {
        //图6.10所示完全二叉树的层次遍历序列
        String[] levellist = {"A","B","C","D","E","F","G","H"};
        CompleteBinaryTree<String> cbitree = new CompleteBinaryTree<String>(levellist);
        cbitree.preOrder();
        cbitree.inOrder();
        cbitree.postOrder();
        //习题6
        System.out.println("是否完全二叉树？  "+cbitree.isCompleteBinaryTree());
    }
}
/*
先根序列：  A B D H E C F G
中根序列：  H D B E A F C G
后根序列：  H D E B F G C A
*/
