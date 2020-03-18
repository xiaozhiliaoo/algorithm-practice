package chapter6.childsiblings;

/**
 * Created by lili on 2017/7/1.
 */
//6.6.3   树的孩子兄弟链表实现
//习题6

public class Tree_ex
{
    public static Tree<String> make645()                   //构造图6.45所示的一棵树
    {
        Tree<String> tree = new Tree<String>();
        tree.root = new TreeNode<String>("A");
        TreeNode<String> child = tree.insertLastChild(tree.root, "B");
        tree.insertLastChild(child, "E");
        tree.insertLastChild(child, "F");
        tree.insertLastChild(tree.root, "C");
        child = tree.insertLastChild(tree.root, "D");
        tree.insertLastChild(child, "G");
        return tree;
    }

    public static Tree<String> make602()                   //构造图6.2所示的一棵树
    {
        Tree<String> tree = new Tree<String>();
        tree.root = new TreeNode<String>("D");
        tree.insertChild(tree.root, "H", 2);                //0
        tree.insertChild(tree.root, "J", 3);                //1
        tree.insertChild(tree.root, "I", 1);                //1
        tree.insertRoot("A");
        TreeNode<String> child = tree.insertChild(tree.root, "B", 0); //0
        tree.insertChild(child, "E", 2);                    //1
        tree.insertChild(child, "F", 3);                    //2
        child = tree.insertChild(tree.root, "C", 1);
        tree.insertChild(child, "G", 2);                    //0
        return tree;
    }

    public static void main(String args[])
    {
        Tree<String> tree = make602();
        System.out.print("图6.2树，"+tree.toString());        //先根次序遍历树并以树的横向凹入表示法输出树
//        Tree<String> tree = make645();
//        System.out.print("图6.45树，"+tree.toString());     //先根次序遍历树并以树的横向凹入表示法输出树
        tree.preOrderTraverse();                           //先根次序遍历树并输出
        tree.postOrder();                                  //后根次序遍历树并输出
        System.out.println("结点个数是"+tree.count()+"，高度是"+tree.height());
        System.out.println(tree.root.data.toString()+"有"+tree.childCount(tree.root)+"个孩子结点");
        System.out.print(tree.toGenListString());          //输出树的广义表形式
        tree.levelOrder();                                 //按层次遍历树

        System.out.println("J结点的层次是"+tree.getLevel("J"));
        TreeNode<String> find = tree.search("J");          //查找
        System.out.println(find.data+"结点的父母结点是"+tree.getParent(find));
        find = tree.search("D");
        tree.removeChild(find, 1);
        System.out.print("删除"+find.data+"的第1个孩子，");
        tree.preOrder();                                   //先根次序遍历树并输出
        tree.removeChild(find, 0);
        System.out.print("删除"+find.data+"的第0个孩子，");
        tree.preOrder();
        tree.removeChild(find, 0);
        System.out.print("删除"+find.data+"的第0个孩子，");
        tree.preOrder();


    }
}
/*
程序运行结果如下：
图6.2树，先根次序遍历树：
 A
	B
		E
		F
	C
		G
	D
		H
		I
		J
后根次序遍历树:  E F B G C H I J D A
结点个数是10，高度是7
A有3个孩子结点
输出树的广义表表示：A(B(E,F),C(G),D(H,I,J))
层次遍历树：  A B C D E F G H I J
J结点的层次是3
删除D的第1个孩子，先根次序遍历树:  A B E F C G D H J
删除D的第0个孩子，先根次序遍历树:  A B E F C G D J
删除D的第0个孩子，先根次序遍历树:  A B E F C G D

        BinaryTree<String> bitree3 = new BinaryTree<String>(bitree2); //深拷贝
        System.out.println("两棵二叉树相等?  "+bitree3.equals(bitree2));
        System.out.println("第3棵二叉树替换(\"D\",\"F\")：  "+bitree3.replace("D","F"));
        System.out.println("两棵二叉树相等?  "+bitree3.equals(bitree2));
        System.out.println("第3棵二叉树全部替换(\"F\",\"Y\")  ");
        bitree3.replaceAll("F","Y");
        */


