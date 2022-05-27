package chapter6.tribinarytree;

/**
 * Created by lili on 2017/7/1.
 */
//【例6.4】  输出三叉链表存储二叉树的一条直径。
//在一棵三叉链表存储的二叉树中，输出一条直径，即从根到最深叶子结点的一条路径。

public class TriBinaryTree_diameter
{
    //输出三叉链表存储二叉树的一条直径
    public static<T> void printDiameter(TriBinaryTree<T> bitree)
    {
        String path=")";
        TriNode<T> deep=findDeepest(bitree.root,bitree.root);//deep找到首次出现的最深叶子结点
        while (deep!=bitree.root)
        {
            path = ","+deep.data.toString()+path;
            deep = deep.parent;                            //向上到父母结点
        }
        if (bitree.root!=null)
            path = deep.data.toString()+path;
        System.out.println("二叉树的直径（从根到最深叶子结点）： ("+path);
    }

    //返回在以p为根的子树中寻找到的最深叶子结点，deep指向当前已知层次最深结点，先根次序遍历的递归算法
    private static<T> TriNode<T> findDeepest(TriNode<T> p, TriNode<T> deep)
    {
        if (p!=null)
        {
            if (p.level > deep.level)                      //当前结点的层次更深
                deep = p;
            deep = findDeepest(p.left, deep);
            deep = findDeepest(p.right, deep);
        }
        return deep;
    }

    public static void main(String args[])
    {                                            //图6-19所示二叉树标明空子树的先根序列
        String[] prelist = {"A","B","D",null,"G",null,null,null,"C","E",null,null,"F","H"};
        TriBinaryTree<String> bitree = new TriBinaryTree<String>(prelist);
        bitree.preOrder();
        printDiameter(bitree);                             //输出二叉树的直径
    }
}
/*
程序运行结果如下：
先根次序遍历二叉树：  1A 2B 3D 4G 2C 3E 3F 4H
二叉树的直径（从根到最深叶子结点）： (A,B,D,G)

先根次序遍历二叉树：                                                                                                    //空子树运行正确
二叉树的直径（从根到最深叶子结点）： ()

*/
//问题：如何输出二叉树所有直径的路径？

