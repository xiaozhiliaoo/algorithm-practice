package chapter8.binarysorttree;

import chapter8.hash.CharCount;

/**
 * Created by lili on 2017/7/1.
 */
//【例8.4】 采用二叉排序树作为存储结构，统计文本中各字符的出现次数。

public class BSTCharWeight
{
    //统计text中各字符的出现次数。使用二叉排序树作为集合元素的存储结构，存储统计结果
    public BSTCharWeight(String text)
    {
        BinarySortTree<CharCount> set=new BinarySortTree<CharCount>(); //构造空二叉排序树
        //以下算法同例8.3
        for (int i=0; i<text.length(); i++)                //逐个字符查找计数
        {
            CharCount key = new CharCount(text.charAt(i),1);
            CharCount find = set.search(key);              //查找，find引用查找到的元素
            if (find==null)
                set.insert(key);                           //插入
            else
                find.add(1);                               //对应字符计数加1
        }
        System.out.println("字符及其出现次数："+set.toString());
    }

    public static void main(String args[])
    {
        new BSTCharWeight("public class");
    }
}
/*
程序运行结果如下：
字符及其出现次数：( ,1) (a,1) (b,1) (c,2) (i,1) (l,2) (p,1) (s,2) (u,1)

 */

