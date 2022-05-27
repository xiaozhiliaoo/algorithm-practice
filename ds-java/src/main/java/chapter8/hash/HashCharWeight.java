package chapter8.hash;

/**
 * Created by lili on 2017/7/1.
 */
//【例8.3】 统计文本中各字符的出现次数，为建立Huffman树做准备。
//采用散列表存储从指定一段文本中统计出的各字符及其出现次数。

public class HashCharWeight
{
    //统计text中各字符的出现次数。使用散列表作为集合元素的存储结构，存储统计结果
    public HashCharWeight(String text)
    {
        HashSet<CharCount> set=new HashSet<CharCount>(text.length());//创建空散列表
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
//        new HashCharWeight("AAAABBBCDDBBAAA");
//        new HashCharWeight("public class");
        new HashCharWeight("public class CharCount uuii");
    }
}
/*
程序运行结果如下：
字符及其出现次数：((A,7))
((B,5))
((C,1))
((D,2))

字符及其出现次数：((l,2))
((a,1))
((b,1))
((c,2))
((p,1))
((s,2))
(( ,1))
((i,1), (u,1))

字符及其出现次数：((n,1))
((o,1), (C,2))
((p,1))
((r,1))
((s,2))
((t,1))
((u,2))
((a,2))
(( ,2), (b,1))
((c,2))
((h,1))
((i,1))
((l,2))




 */
