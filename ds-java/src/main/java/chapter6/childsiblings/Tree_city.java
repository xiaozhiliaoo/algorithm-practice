package chapter6.childsiblings;

/**
 * Created by lili on 2017/7/1.
 */
//【例6.7】 构造一棵城市树或森林。

public class Tree_city
{
    public static Tree<String> make()                      //构造一棵城市树或森林
    {
        Tree<String> tree = new Tree<String>();            //构造一棵空树
        tree.root = new TreeNode<String>("中国");
        tree.insertLastChild(tree.root, "北京市");
        tree.insertLastChild(tree.root, "上海市");
        TreeNode<String> js = tree.insertLastChild(tree.root, "江苏省");
        tree.insertLastChild(js, "南京市");
        tree.insertLastChild(js, "苏州市");
        TreeNode<String> zj = tree.insertLastSibling(js, "浙江省");
        tree.insertLastChild(zj, "杭州市");
        tree.insertLastChild(zj, "宁波市");
        tree.insertLastChild(zj, "温州市");
        TreeNode<String> korea = tree.insertLastSibling(tree.root, "韩国");
        tree.insertChild(korea, "首尔", 0);
        TreeNode<String> usa = tree.insertLastSibling(korea, "美国");
        tree.insertLastChild(usa, "华盛顿");
        return tree;
    }

    public static void main(String[] args)
    {
        Tree<String> tree = make();
        System.out.print(tree.toString());                 //先根次序遍历树并输出树的横向凹入表示字符串
    }
}

/*
程序运行结果如下：
先根次序遍历树：
 中国
	北京市
	上海市
	江苏省
		南京市
		苏州市
	浙江省
		杭州市
		宁波市
		温州市
韩国
	首尔
美国
	华盛顿

*/
