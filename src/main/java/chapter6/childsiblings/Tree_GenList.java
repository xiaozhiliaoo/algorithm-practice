package chapter6.childsiblings;

/**
 * Created by lili on 2017/7/1.
 */
//【例6.9】 树的广义表表示及构造。

public class Tree_GenList
{
    private static int i=0;
    public static Tree<String> createByGenList(String glist) //以广义表表示构造树
    {
        Tree<String> tree = new Tree<String>();            //构造空树，tree.root==null
        i=0;
        if (glist.length()>0)
            tree.root = create(glist);                     //创建子树，子树根值是glist[0]
        return tree;
    }

    //以广义表表示创建一棵子树，子树根结点值是glist[i]，返回子树的根结点，递归方法
    //glist不会是空串""，
    private static TreeNode<String> create(String glist)
    {
        TreeNode<String> p=null;
        int j=i+1;
        if (j<glist.length())
        {
            char ch=glist.charAt(j);
            while (ch!='(' && ch!=',' && ch!=')')          //识别字符串作为结点元素值
            {
                j++;
                ch=glist.charAt(j);
            }
        }
        p=new TreeNode<String>(glist.substring(i,j));      //创建结点
        i=j;
        if (i<glist.length() && glist.charAt(i)=='(')      //遇到(，创建子树
        {
            i++;                                           //跳过'('
            p.child = create(glist);                       //创建子树，递归调用
        }
        if (i<glist.length() && glist.charAt(i)==',')      //遇到,，创建下一个兄弟子树
        {
            i++;                                           //跳过','
            p.sibling = create(glist);                     //创建下一个兄弟子树，递归调用
        }
        if (i<glist.length() && glist.charAt(i)==')')      //遇到)，子树创建完成
            i++;                                           //跳过')'
        return p;                                          //空串返回null
    }

    public static void main(String args[])
    {
        String glist_empty = "";                           //空树
        System.out.println("空"+createByGenList(glist_empty).toGenListString());
        String glist_a = "A";                              //只有根结点A的树
        System.out.println("只有根结点A的"+createByGenList(glist_a).toGenListString());
        String glist_abc = "A(B,C)";                       //树
        System.out.println(glist_abc+createByGenList(glist_abc).toGenListString());
        String glist_abe = "A(B(E,F),C(G),D(H,I,J))";      //图6.2(c)所示树的广义表表示
        System.out.println("图6.2(c)"+createByGenList(glist_abe).toGenListString());

        String glist="中国(北京,上海,江苏(南京(江宁),苏州,无锡),浙江(杭州,宁波,温州),广东(广州)),"+
                "韩国(首尔),美国(华盛顿)";
        System.out.println(createByGenList(glist).toGenListString());
    }
}

/*
程序运行结果如下：
空树的广义表表示：
只有根结点A的树的广义表表示：A
A(B,C)树的广义表表示：A(B,C)
图6.2(c)树的广义表表示：A(B(E,F),C(G),D(H,I,J))
树的广义表表示：中国(北京,上海,江苏(南京(江宁),苏州,无锡),浙江(杭州,宁波,温州),广东(广州)),韩国(首尔),美国(华盛顿)

*/