package chapter6.binarytree;

/**
 * Created by lili on 2017/7/1.
 */
//【例6.3】 二叉树的广义表表示。

public class BinaryTree_genlist
{
    private static int i=0;
    public static BinaryTree<String> createByGenList(String glist) //以广义表表示构造二叉树
    {
        BinaryTree<String> bitree = new BinaryTree<String>();
        i=0;
        if (glist.length()>0)
            bitree.root = create(glist);                   //创建子树，子树根值是glist[0]
        return bitree;
    }
    //以广义表表示创建一棵子树，子树根值是glist[i]，返回根结点，递归方法
    private static BinaryNode<String> create(String glist)
    {
        BinaryNode<String> p=null;
        char ch=glist.charAt(i);
        if (ch>='A' && ch<='Z')                            //大写字母
        {
            p = new BinaryNode<String>(ch+"");             //创建叶子结点
            i++;
            if (glist.charAt(i)=='(')
            {
                i++;                                       //跳过'('
                p.left = create(glist);                    //创建左子树，递归调用
                i++;                                       //跳过','
                p.right = create(glist);                   //创建右子树，递归调用
                i++;                                       //跳过')'
            }
        }
        if (ch=='^')
            i++;                                           //跳过'^'
        return p;                                          //空串返回null
    }
    public static void main(String args[])
    {
        String glist = "A(B(D(^,G),^),C(E,F(H,^)))";       //图6.18所示二叉树的广义表表示
        BinaryTree<String> bitree = createByGenList(glist);
        System.out.println(bitree.toGenListString());
        System.out.println("是否完全二叉树？  "+bitree.isCompleteBinaryTree());

        glist = "A(B,C)";
        bitree = createByGenList(glist);
        System.out.println(bitree.toGenListString());
        //习题6
        System.out.println("是否完全二叉树？  "+bitree.isCompleteBinaryTree());
    }
}

/*
程序运行结果如下：
二叉树的广义表表示：A(B(D(^,G),^),C(E,F(H,^)))
二叉树的广义表表示：A(B,C)

*/

