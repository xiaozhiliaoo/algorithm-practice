package chapter8.hash;

import chapter2.seqlist.SeqList;

/**
 * Created by lili on 2017/7/1.
 */
//8.3   散列
//8.3.4   构造链地址法的散列表
//【例8.3】 统计文本中各字符的出现次数，为建立Huffman树做准备。
//采用顺序表存储从指定一段文本中统计出的各字符及其出现次数。
public class SeqCharCount
{
    private SeqList<CharCount> list;             //使用顺序表存储统计结果

    public SeqCharCount(String text)
    {
        list = new SeqList<CharCount>();
        for (int i=0; i<text.length(); i++)
        {
            CharCount key = new CharCount(text.charAt(i),1);
            CharCount find = list.search(key);             //查找，find引用查找到的元素
            if (find==null)
                list.append(key);                          //尾插入
            else
                find.add(1);                               //对应字符计数加1
        }
        System.out.println("字符及其出现次数："+list.toString());
    }

    public static void main(String args[]) //throws java.io.IOException
    {
        new SeqCharCount("AAAABBBCDDBBAAA");
        new SeqCharCount("public class");
        new SeqCharCount("public class CharWeight");
    }
}
/*
程序运行结果如下：
字符及其出现次数：((A,7), (B,5), (C,1), (D,2))
字符及其出现次数：((p,1), (u,1), (b,1), (l,2), (i,1), (c,2), ( ,1), (a,1), (s,2))
字符及其出现次数：((p,1), (u,1), (b,1), (l,2), (i,2), (c,2), ( ,2), (a,2), (s,2), (C,1), (h,2), (r,1), (W,1), (e,1), (g,1), (t,1))

 */

