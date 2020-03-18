package chapter6.binarytree;

/**
 * Created by lili on 2017/7/1.
 */
//【例6.2】  输出二叉树中指定结点的所有祖先结点。

public class Ancestors
{
    public static void main(String args[])
    {
        //以图6.15（a）所示二叉树的先根序列和中根序列创建二叉树
        String[] prelist = {"A","B","D","G","C","E","F","H"};
        String[] inlist = {"D","G","B","A","E","C","H","F"};
        BinaryTree<String> bitree = new BinaryTree<String>(prelist, inlist);

        //图6.18所示二叉树标明空子树的先根序列
/*        String[] prelist = {"A","B","D",null,"G",null,null,null,"C","E",null,null,"F","H"};
        BinaryTree<String> bitree = new BinaryTree<String>(prelist);
*/        bitree.preOrder();
        bitree.inOrder();
        bitree.postOrder();
        System.out.println("结点个数是"+bitree.count()+"，高度是"+bitree.height());
/*
        String value="H";
        BinaryNode<String> find = bitree.search(value);    //查找
        if (find==null)
            System.out.println("未找到"+value);
        else
        {
            BinaryNode<String> parent = bitree.getParent(find);
            System.out.print(find.data+"的祖先结点是");
            while (parent!=null)
            {
                System.out.print(parent.data+"  ");
                parent = bitree.getParent(parent);
            }
            System.out.println();
        }*/
    }
}
/*
程序运行结果如下：
先根次序遍历二叉树：  A B D G C E F H
中根次序遍历二叉树：  D G B A E C H F
后根次序遍历二叉树：  G D B E H F C A
结点个数是8，高度是4
H的祖先结点是F  C  A

未找到Z
D的祖先结点是B  A

*/
/*
        //以图6.15（a）所示二叉树的先根序列和中根序列创建二叉树
prelist:ABDGCEFH，inlist:DGBAECHF
prelist:BDG，inlist:DGB
prelist:DG，inlist:DG
prelist:，inlist:
prelist:G，inlist:G
prelist:，inlist:
prelist:，inlist:
prelist:，inlist:
prelist:CEFH，inlist:ECHF
prelist:E，inlist:E
prelist:，inlist:
prelist:，inlist:
prelist:FH，inlist:HF
prelist:H，inlist:H
prelist:，inlist:
prelist:，inlist:
prelist:，inlist:
先根次序遍历二叉树：  A B D G C E F H
中根次序遍历二叉树：  D G B A E C H F
后根次序遍历二叉树：  G D B E H F C A
结点个数是8，高度是4
 * */

