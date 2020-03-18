package chapter6.tribinarytree;

/**
 * Created by lili on 2017/7/1.
 */
//【例6.4】  输出二叉树的直径。

public class TriBinaryTree_ex
{
    public static void main(String args[])
    {
        //图6.18所示二叉树标明空子树的先根序列
        String[] prelist = {"A","B","D",null,"G",null,null,null,"C","E",null,null,"F","H"};
        TriBinaryTree<String> bitree = new TriBinaryTree<String>(prelist);
        bitree.preOrder();
        bitree.inOrder();
        bitree.postOrder();
        bitree.levelOrder();
        System.out.println("结点个数是"+bitree.count()+"，高度是"+bitree.height());

        //习题6
        TriBinaryTree<String> bitree2 = new TriBinaryTree<String>(bitree);//深拷贝
        System.out.println("两棵二叉树相等?  "+bitree.equals(bitree2));
        TriNode<String> find = bitree2.searchNode("F");        //查找
        if (find==null)
            System.out.println("第2棵二叉树查找：  null");
        else
        {
            System.out.println(find.data.toString()+"的父母结点是"+find.parent.data.toString());
            find.data = "Y";
            bitree2.insertChild(find, "Z", true);
            System.out.println("插入Z作为 "+find.data+"的左孩子");
        }
        TriNode<String> q = bitree2.insertChild(bitree2.root.right, "X", false);
        //插入X作为根右孩子的右孩子
        System.out.println("删除"+q.data.toString()+"右孩子的左孩子E");
        System.out.println("第1棵二叉树：  ");
        bitree.preOrder();
        bitree.inOrder();
        bitree.postOrder();
        bitree.levelOrder();
        System.out.println("第2棵二叉树：  ");
        bitree2.preOrder();
        bitree2.inOrder();
        bitree2.postOrder();
        bitree2.levelOrder();
    }
}
/*
程序运行结果如下：
先根次序遍历二叉树：  1A 2B 3D 4G 2C 3E 3F 4H
中根次序遍历二叉树：  3D 4G 2B 1A 3E 2C 4H 3F
后根次序遍历二叉树：  4G 3D 2B 3E 4H 3F 2C 1A
层次遍历：  1A 2B 2C 3D 3E 3F 4G 4H
结点个数是8，高度是4
两棵二叉树相等?  true
F的父母结点是C
插入Z作为 Y的左孩子
删除X右孩子的左孩子E
第1棵二叉树：
先根次序遍历二叉树：  1A 2B 3D 4G 2C 3E 3F 4H
中根次序遍历二叉树：  3D 4G 2B 1A 3E 2C 4H 3F
后根次序遍历二叉树：  4G 3D 2B 3E 4H 3F 2C 1A
层次遍历：  1A 2B 2C 3D 3E 3F 4G 4H
第2棵二叉树：
先根次序遍历二叉树：  1A 2B 3D 4G 2C 3E 3X 4Y 5Z 6H
中根次序遍历二叉树：  3D 4G 2B 1A 3E 2C 3X 6H 5Z 4Y
后根次序遍历二叉树：  4G 3D 2B 3E 6H 5Z 4Y 3X 2C 1A
层次遍历：  1A 2B 2C 3D 3E 3X 4G 4Y 5Z 6H

*/
