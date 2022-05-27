package chapter6.binarytree;

/**
 * Created by lili on 2017/7/1.
 */
//【例6.1】  构造并遍历二叉树。

public class BinaryTree_make
{
    public static BinaryTree<String> make()                  //构造给定的一棵二叉树
    {
        BinaryTree<String> bitree=new BinaryTree<String>();  //创建空二叉树
        BinaryNode<String> child_f, child_d, child_b, child_c;
        child_d = new BinaryNode<String>("D", null, new BinaryNode<String>("G"));
        child_b = new BinaryNode<String>("B", child_d, null);
        child_f = new BinaryNode<String>("F", new BinaryNode<String>("H"), null);
        child_c = new BinaryNode<String>("C", new BinaryNode<String>("E"), child_f);
        bitree.root = new BinaryNode<String>("A", child_b, child_c);
        return bitree;
    }
    public static void main(String args[])
    {
        BinaryTree<String> bitree = make();
        bitree.preOrder();                                   //先根次序遍历二叉树
        bitree.inOrder();
        bitree.postOrder();
    }
}
/*
程序运行结果如下：
先根次序遍历二叉树：  A B D G C E F H
中根次序遍历二叉树：  D G B A E C H F
后根次序遍历二叉树：  G D B E H F C A
*/
