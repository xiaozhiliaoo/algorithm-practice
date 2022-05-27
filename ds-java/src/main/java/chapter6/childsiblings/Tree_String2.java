package chapter6.childsiblings;

/**
 * Created by lili on 2017/7/1.
 */
//6.6.3   树的孩子兄弟链表实现
//【例6.8】 以树的横向凹入表示构造树或森林。
//第4版程序，TTree树接口可不声明getLastChild(TreeNode<T> p)和getLastSibling(TreeNode<T> p)方法

public class Tree_String2
{
    //以横向凹入表示构造树或森林，prelist数组存储树或森林的横向凹入表示字符串序列
    //非递归算法，逐个结点添加方式，没有直接调用返回、插入结点方法
    public static Tree<String> create(String prelist[])
    {
        Tree<String> tree = new Tree<String>();            //创建空树
        if (prelist.length==0)
            return tree;                                   //返回空树
        tree.root = new TreeNode<String>(prelist[0]);      //创建根结点
        TreeNode<String> p = tree.root;
        int len=0;                                         //p结点的前缀'\t'个数
        for (int i=1; i<prelist.length; i++)
        {        //创建元素为prelist[i]结点，添加到tree森林的最后一棵树中，结点值去除了所有\t前缀
            int j=0;
            while (j<prelist[i].length() && prelist[i].charAt(j)=='\t')
                j++;                                       //统计prelist[i]串中'\t'前缀个数

            String str = prelist[i].substring(j);          //去除prelist[i]串中若干前缀'\t'
            if (j==len+1)                                  //prelist[i]比p.data多一个'\t'前缀
            {
                p.child = new TreeNode<String>(str);       //插入作为p的第一个孩子
                p = p.child;
                len++;
                continue;
            }
            if (j<len)                                     //prelist[i]比p.data的'\t'少，p从根开始再找插入位置
            {
                len=0;
                p = tree.root;
                while (p.sibling!=null)                    //选择森林中最后一棵树
                    p=p.sibling;                           //p指向根的最后一个兄弟
                while (len < j)                            //寻找插入位置，由p指向
                {
                    p=p.child;                             //p向下一层
                    len++;
                    while (p.sibling!=null)                //p指向最后一个兄弟
                        p=p.sibling;
                }
            }
            p.sibling = new TreeNode<String>(str);         //插入作为p结点的兄弟
            p = p.sibling;
        }
        return tree;
    }

    public static void main(String[] args)
    {
        String prelist[]={"中国","\t北京","\t上海","\t江苏","\t\t南京","\t\t\t江宁","\t\t苏州",
                "\t\t无锡","\t\t\t锡山","\t浙江","\t\t杭州","\t\t宁波","\t\t温州","\t广东","\t\t广州",
                "韩国","\t首尔","法国","意大利","美国","\t华盛顿州","\t\t华盛顿","\t纽约州","\t\t纽约"};
        Tree<String> tree = create(prelist);                //以树的横向凹入表示法构造树或森林
        System.out.print(tree.toString());                 //先根次序遍历树并输出树的横向凹入表示字符串

/*   	    String prelist2[]={"A","\tB","\t\tE","\t\tF","\tC","\tD","\t\tG"}; //6.45
//      String prelist[]={"A","\tB","\t\tE","\t\tF","\tC","\t\tG","\tD","\t\tH","\t\tI","\t\tJ"};   //6.2
//        String prelist[]={"A","\tB","\t\tE","\t\tF","\tC","\tD","\t\tG","H","\tI","J","\tK","\tL"}; //6.48
        Tree<String> tree2 = create(prelist2);                //以树的横向凹入表示法构造树或森林
//        System.out.print(tree2.toString());                 //先根次序遍历树并输出树的横向凹入表示字符串

        tree.insertChild(tree.root, tree2, 2);
        System.out.print(tree.toString());       */
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



