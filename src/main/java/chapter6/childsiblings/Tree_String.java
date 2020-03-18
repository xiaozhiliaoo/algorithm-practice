package chapter6.childsiblings;

/**
 * Created by lili on 2017/7/1.
 */
//6.6.3   树的孩子兄弟链表实现
//【例6.8】 以树的横向凹入表示构造树或森林。

public class Tree_String
{
    //以横向凹入表示构造树或森林，prelist数组存储树或森林的横向凹入表示字符串序列
    public static Tree<String> create(String prelist[])
    {
        Tree<String> tree = new Tree<String>();            //创建空树
        if (prelist.length!=0)
        {
            tree.root = new TreeNode<String>(prelist[0]);  //创建根结点
            for (int i=1; i<prelist.length; i++)
                insert(tree, prelist[i]);                  //将值为prelist[i]结点插入到tree森林的最后一棵树中
        }
        return tree;
    }

    //在tree森林最后一棵树中插入以str横向凹入表示的一个结点，结点值去除了str所有\t前缀
    //非递归算法
    public static void insert(Tree<String> tree, String str)
    {
        TreeNode<String> p = tree.getLastSibling(tree.root); //p指向根的最后一个兄弟
        if (p==null)
            p = tree.root;                                 //p没有兄弟时指向根，选择森林中最后一棵树
        if (str.charAt(0)!='\t')
            p.sibling = new TreeNode<String>(str);
//            tree.insertLastSibling(p, str);                //没有\t前缀时，插入str作为p结点的最后一个兄弟
        else
        {                                                  //有\t前缀时，插入str到p的最后一棵子树中
            str = str.substring(1);                        //去除str串中一个前缀'\t'
            while (str.charAt(0)=='\t')                    //字符串以\t开头
            {
                TreeNode<String> lastChild=tree.getLastChild(p);//获得p的最后一个孩子结点
                if (lastChild!=null)
                    p = lastChild;
                str = str.substring(1);                    //去除str串中一个前缀'\t'
            }
            tree.insertLastChild(p, str);                  //插入str作为p结点的最后一个孩子
        }
    }

    public static void main(String[] args)
    {
        String prelist[]={"中国","\t北京","\t上海","\t江苏","\t\t南京","\t\t\t江宁","\t\t苏州",
                "\t\t无锡","\t\t\t锡山","\t浙江","\t\t杭州","\t\t宁波","\t\t温州","\t广东","\t\t广州",
                "韩国","\t首尔","美国","\t华盛顿州","\t\t华盛顿","\t纽约州","\t\t纽约"};
        Tree<String> tree = create(prelist);                //以树的横向凹入表示法构造树或森林
//        System.out.print(tree.toString());                 //先根次序遍历树并输出树的横向凹入表示字符串

        String prelist2[]={"A","\tB","\t\tE","\t\tF","\tC","\tD","\t\tG"}; //6.45
//      String prelist[]={"A","\tB","\t\tE","\t\tF","\tC","\t\tG","\tD","\t\tH","\t\tI","\t\tJ"};   //6.2
//        String prelist[]={"A","\tB","\t\tE","\t\tF","\tC","\tD","\t\tG","H","\tI","J","\tK","\tL"}; //6.48
        Tree<String> tree2 = create(prelist2);                //以树的横向凹入表示法构造树或森林
//        System.out.print(tree2.toString());                 //先根次序遍历树并输出树的横向凹入表示字符串

        tree.insertChild(tree.root, tree2, 2);
        System.out.print(tree.toString());
    }

    //习题6
    //递归算法
    //以横向凹入表示构造树或森林，prelist数组存储树或森林的横向凹入表示字符串序列
    /*    public static Tree<String> create(String prelist[])
        {
            Tree<String> tree = new Tree<String>();            //创建空树
            if (prelist!=null && prelist.length!=0)
            {
                tree.root = new TreeNode<String>(prelist[0]);  //创建根结点
                for (int i=1; i<prelist.length; i++)
                {
                    TreeNode<String> lastSibling=tree.getLastSibling(tree.root);//获得根的最后一个兄弟结点
                    if (lastSibling==null)                     //根没有兄弟
                        insert(tree, tree.root, prelist[i]);   //将值为prelist[i]结点插入到根的子树中
                    else
                        insert(tree, lastSibling, prelist[i]); //插入到根最后一个兄弟结点的子树中
                }
            }
            return tree;
        }
    */
    //在tree树中插入一个结点作为p的兄弟或孩子（子孙），str以横向凹入表示一个结点值，
    //插入的结点值为str去除所有前缀\t的字符串，递归算法
    //前提p!=null
    private static void insert(Tree<String> tree, TreeNode<String> p, String str)
    {
        if (str.charAt(0)!='\t')
            tree.insertLastSibling(p, str);           //没有\t前缀时，插入str作为p的最后一个兄弟
        else
        {                                             //有\t前缀时，插入str到p的最后一棵子树中
            str = str.substring(1);                        //去除str串中一个前缀'\t'
            TreeNode<String> lastChild=tree.getLastChild(p);//获得p的最后一个孩子结点
            if (lastChild==null)                           //p没有孩子
                tree.insertLastChild(p, str);              //插入str作为p的最后一个孩子
            else
                insert(tree, lastChild, str);              //插入到p的最后一个子树中，递归调用
        }
    }
}
/*
程序运行结果如下：
先根次序遍历树：
 中国
	北京
	上海
	江苏
		南京
			江宁
		苏州
		无锡
			锡山
	浙江
		杭州
		宁波
		温州
	广东
		广州
韩国
	首尔
美国
	华盛顿州
		华盛顿
	纽约州
		纽约

*/

