package chapter8.hash;

/**
 * Created by lili on 2017/7/1.
 */
//8.3   散列
//8.3.4   构造链地址法的散列表
//【例8.3】 统计文本中各字符的出现次数，为建立Huffman树做准备。
//采用散列表存储从指定一段文本中统计出的各字符及其出现次数。

public class CharCount implements Comparable<CharCount>    //字符及其出现次数
{
    char character;                              //字符
    int count;                                   //出现次数
    public CharCount(char character,int count)
    {
        this.character = character;
        this.count = count;
    }
    public String toString()                     //返回字符及其出现次数描述字符串，形式为“(字符,出现次数)”
    {
        return "("+this.character+","+this.count+")";
    }
    public void add(int n)                       //出现次数加1
    {
        this.count+=n;
    }
    public int hashCode()                        //返回散列码，覆盖Object类的hashCode()方法
    {
        return (int)this.character;              //根据字符决定对象在散列表中的位置
    }
    public boolean equals(Object obj)            //比较两个对象是否相等 ，覆盖Object类的equals(obj)方法
    {
        return obj==this || obj instanceof CharCount &&
                this.character==((CharCount)obj).character;    //仅比较字符是否相等
    }

    public int compareTo(CharCount c)
    {
        return this.character - c.character;
    }
}

