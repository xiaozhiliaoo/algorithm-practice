package chapter6.binarytree;

/**
 * Created by lili on 2017/7/1.
 */
//习题6

public class BinaryTree_ex
{
    public static void main(String args[])
    {
/*        //标明空子树的先根序列，两棵二叉树也对
        String[] prelist1 = {"A","B",null,null,"C",};
        BinaryTree<String> bitree1 = new BinaryTree<String>(prelist1);
        bitree1.preOrder();
        bitree1.inOrder();
        bitree1.postOrder();
        System.out.println("是否完全二叉树？  "+bitree1.isCompleteBinaryTree());
*/
        String[] prelist2 = {"A","B","D",null,"G",null,null,null,"C","E",null,null,"F","H"};
        BinaryTree<String> bitree2 = new BinaryTree<String>(prelist2);
        bitree2.preOrder();
        bitree2.inOrder();
        bitree2.postOrder();
        bitree2.preOrderTraverse();
        bitree2.inOrderTraverse();
        bitree2.postOrderTraverse();
/*        System.out.println(bitree2.toGenListString());
        bitree2.levelOrder();                         //按层次遍历二叉树
        System.out.println("H结点的层次是 "+bitree2.getLevel("H"));
        System.out.println("是否完全二叉树？  "+bitree2.isCompleteBinaryTree());

        System.out.print("叶子结点：  ");
        bitree2.leaf();
        System.out.println("，共"+bitree2.countLeaf()+"个");

        BinaryTree<String> bitree3 = new BinaryTree<String>(bitree2); //深拷贝
        System.out.println("两棵二叉树相等?  "+bitree3.equals(bitree2));
        System.out.println("第3棵二叉树替换(\"D\",\"F\")，");
        bitree3.replace("D","F");

        System.out.println("两棵二叉树相等?  "+bitree3.equals(bitree2));
        System.out.println("第3棵二叉树全部替换(\"F\",\"Y\")  ");
        bitree3.replaceAll("F","Y");
        bitree3.preOrder();

        BinaryNode<String> find = bitree2.search("D");      //查找
        bitree2.insertChild(find, "Z", true);
        System.out.println("插入Z作为 "+find.data+"的左孩子");
        bitree2.preOrder();
*/
    }
}
/*
程序运行结果如下：
先根次序遍历二叉树：  A B C
中根次序遍历二叉树：  B A C
后根次序遍历二叉树：  B C A
是否完全二叉树？  true
先根次序遍历二叉树：  A B D G C E F H
中根次序遍历二叉树：  D G B A E C H F
后根次序遍历二叉树：  G D B E H F C A
以广义表表示输出二叉树：A(B(D(^,G),^),C(E,F(H,^)))
层次遍历二叉树：  A B C D E F G H
H结点的层次是 4
是否完全二叉树？  false
叶子结点：  G E H ，共3个
两棵二叉树相等?  true
第3棵二叉树替换("D","F")：  true
两棵二叉树相等?  false
第3棵二叉树全部替换("F","Y")
先根次序遍历二叉树：  A B Y G C E Y H
插入Z作为 D的左孩子
先根次序遍历（非递归）：  A B D Z G C E F H
中根次序遍历（非递归）：  Z D G B A E C H F
后根次序遍历（非递归）：  Z G D B E H F C A


        BinaryTree<String> bitree4 = new BinaryTree<String>(prelist2);
        bitree4.root = bitree4.create(prelist2);           //错，i越界，私有化可避免问题
        bitree4.preOrder();

先根序列：  A B D G C E F H
中根序列：  D G B A E C H F
后根序列：  G D B E H F C A

中根序列：  A B C D E
二叉排序树? true

        */

