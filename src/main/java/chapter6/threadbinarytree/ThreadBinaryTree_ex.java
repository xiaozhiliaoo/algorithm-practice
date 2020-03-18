package chapter6.threadbinarytree;

/**
 * Created by lili on 2017/7/1.
 */

public class ThreadBinaryTree_ex                           //中序线索二叉树
{
    public static void main(String args[])
    {
        //遍历
/*        //图6.25所示二叉树标明空子树的先根序列
        String[] prelist = {"A","B","D",null,null,"E","G",null,null,null,"C","F",null,"H",null,null,"K"};
        ThreadBinaryTree<String> tbitree = new ThreadBinaryTree<String>(prelist);    //创建中序线索二叉树
        tbitree.preOrder();                                 //先根次序遍历
        tbitree.inOrder();                                  //中根次序遍历
        tbitree.inOrder_previous();                         //中根次序遍历（求前驱）
        tbitree.postOrder_previous();                       //后根次序遍历（求前驱）

*/        //插入
//        tbitree = new ThreadBinaryTree<String>();           //创建空中序线索二叉树
//        tbitree.insertRoot("A");                          //插入根
        String[] prelist = {"A","B","C",null,null,"D","E","F",null,null,"G",null,"H",null,null,null,
                "I","J",null,"K","L","M",null,null,null,"N",null,null,"O"};
        ThreadBinaryTree<String> tbitree = new ThreadBinaryTree<String>(prelist);
        ThreadNode<String> node = tbitree.insertChild(tbitree.root.left.right, "W", false); //插入D的右孩子W
        System.out.println(node+"的父母结点是"+tbitree.getParent(node));
        node = tbitree.insertChild(tbitree.root.left, "X", false);       //插入B的右孩子X
        System.out.println(node+"的父母结点是"+tbitree.getParent(node));
        node = tbitree.insertChild(tbitree.root.right.left, "Y", true);  //插入J的左孩子Y
//        System.out.println(node+"的父母结点是"+tbitree.getParent(node));
        node = tbitree.insertChild(tbitree.root.right, "Z", true);       //插入I的左孩子Z
        System.out.println(node+"的父母结点是"+tbitree.getParent(node));
        tbitree.preOrder();                                //先根次序遍历
        tbitree.inOrder();                                 //中根次序遍历

/*        //删除右孩子
        String[] prelist = {"A","B","C",null,null,"X","D","E",null,null,"F",null,"G",null,null,
                            "H","I","J",null,null,null,"K",null,null,"L"};
        ThreadBinaryTree<String> tbitree = new ThreadBinaryTree<String>(prelist);
/*        tbitree.removeRightChild(tbitree.root.left.left);            //删除C的右孩子，没有，不删除
        tbitree.removeRightChild(tbitree.root.left);                 //删除B的右孩子X，X是2度结点
        tbitree.removeRightChild(tbitree.root.left.right.right);     //删除F的右孩子G，G是只有右孩子的1度结点
        tbitree.removeRightChild(tbitree.root.left.right.right.right);//删除H的右孩子K，K是0度结点
        tbitree.removeRightChild(tbitree.root.left.right.right);     //删除F的右孩子H，H是只有左孩子的1度结点
        tbitree.preOrder();                                //先根次序遍历
        tbitree.inOrder();                                 //中根次序遍历

        //删除左孩子
        tbitree.removeLeftChild(tbitree.root.left);        //删除B的左孩子，没有，不删除
        tbitree.removeLeftChild(tbitree.root.right);       //删除C的左孩子X，X是2度结点
        tbitree.removeLeftChild(tbitree.root.right.left);  //删除D的左孩子E，E是0度结点
        tbitree.removeLeftChild(tbitree.root.right);       //删除C的左孩子D，D是只有右孩子的1度结点
        tbitree.removeLeftChild(tbitree.root.right.left.right.right);  //删除H的左孩子I，I是只有左孩子的1度结点
        tbitree.preOrder();                                //先根次序遍历
        tbitree.inOrder();                                 //中根次序遍历

        //删除根
        String[] prelist = {"A","B","D",null,null,"E",null,"H",null,null,"C","F","I",null,null,null,"G"};
        ThreadBinaryTree<String> tbitree = new ThreadBinaryTree<String>(prelist);
        tbitree.removeRoot();
        tbitree.preOrder();                                 //先根次序遍历
        tbitree.inOrder();                                  //中根次序遍历
*/    }
}
/*
程序运行结果如下：
//遍历
先根次序遍历中序线索二叉树：  A B D E G C F H K
中根次序遍历中序线索二叉树：  D B G E A F H C K
中根次序（反序）遍历中序线索二叉树：  K C H F A E G B D
后根次序（反序）遍历中序线索二叉树：  A C K F H B E G D

//插入
先根次序遍历中序线索二叉树：  A B C X D E F G H W I Z J Y K L M N O
中根次序遍历中序线索二叉树：  C B X F E G H D W A Y J M L K N Z I O

//删除右孩子
先根次序遍历中序线索二叉树：  A B C D E F I J L
中根次序遍历中序线索二叉树：  C B E D F J I A L

//删除左孩子
先根次序遍历中序线索二叉树：  A X D E F G H I J K L
中根次序遍历中序线索二叉树：  E D F G X J I H K A L

//删除根
先根次序遍历中序线索二叉树：  B D E H C F I G
中根次序遍历中序线索二叉树：  D B E H I F C G


*/

/*
  //删除
    tbitree.remove(tbitree.root.left,false);         //删除B的右孩子X
    tbitree.remove(tbitree.root.left.right,false);  //删除D的右孩子W
    tbitree.remove(tbitree.root.left,false);         //删除B的右孩子D

    tbitree.remove(tbitree.root.left);           //删除B的左孩子，没有，不删除
    tbitree.remove(tbitree.root.right);          //删除C的左孩子X，X是2度结点
    tbitree.remove(tbitree.root.right.left);    //删除D的左孩子E，E是0度结点
    tbitree.remove(tbitree.root.right);          //删除C的左孩子D，D是只有右孩子的1度结点
    tbitree.remove(tbitree.root.right.left.right.right);  //删除H的左孩子I，I是只有左孩子的1度结点


*/
//??
//    ThreadBinaryNode<char> *node = tbitree.root.left.right.right;    //
/*    ThreadBinaryNode<char> *node = tbitree.root.right.left.left;
    ThreadBinaryNode<char> *parent = tbitree.getParent(node);
    cout<<node.data<<"的父母结点是";
    if (parent==NULL)
        cout<<"NULL"<<endl;
    else
        cout<<parent.data<<endl;
*/
