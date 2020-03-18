package chapter8.search;

/**
 * Created by lili on 2017/7/1.
 */
//8.2.3  基于索引顺序表的分块查找算法。
//【例8.2】 采用扩充索引表查询Java关键字。
public class KeyWords2
{
    //关键字表
    private static String[] keywords={"abstract","assert","boolean","break","byte","case","catch",
            "char","class","continue","default","do","double","else","extends","false","final","finally",
            "float","for","if","implements","import","instanceof","int","interface","long","native","new",
            "null","package","private","protected","public","return","short","static","super","switch",
            "synchronized","this","throw","throws","transient","true","try","void","volatile","while"};

    private static class IndexItem implements Comparable<IndexItem>  //索引项，私有内部类
    {
        String first;                                      //关键字的首字母
        int start,end;                                     //首字母相同的关键字块在主表中的始末下标
        public IndexItem(String first, int start, int end)
        {
            this.first = first;
            this.start = start;
            this.end = end;
        }
        public String toString()                           //返回索引项的描述字符串
        {
            return "("+this.first+","+start+","+end+")";
        }
        public int compareTo(IndexItem item)               //约定两个索引项比较大小的规则，实现Comparable接口
        {
            return this.first.compareTo(item.first);       //按首字母比较大小
        }
    }//内部类结束

    private static IndexItem index[];                      //索引表
    static                                                 //静态初始化，建立索引表
    {
        index = new IndexItem[26];
        int i=0, j=0;
        for (i=0; i<index.length && j<keywords.length; i++)
        {
            char ch=(char)('a'+i);
            if (keywords[j].charAt(0)>ch)
                index[i]=new IndexItem(ch+"",-1,-1);
            else
            {
                int start = j++;
                while (j<keywords.length && keywords[j].charAt(0)==ch)
                    j++;
                index[i]=new IndexItem(ch+"",start,j-1);
            }
        }

        System.out.print("index[]:");
        for (i=0; i<index.length; i++)
            if (index[i]!=null)
                System.out.print(index[i].toString()+" ");
        System.out.println();
    }

    public static boolean isKeyword(String str)            //判断str是否为Java关键字
    {
        int pos = str.charAt(0)-'a';                       //首字母对应的索引项序号
        if (pos<0 || pos>26)
            return false;
        int begin=index[pos].start;                          //获得主表查找范围的下界
        if (begin==-1)
            return false;
        int end=index[pos].end;                           //获得主表查找范围的上界
        return BinarySearchArray.binarySearch(keywords,begin,end,str)>=0;   //折半查找主表的指定范围
    }

    public static void main(String[] args)
    {                                         //默认首先进行静态初始化，建立索引表
        String str="final";
        System.out.println(str+(isKeyword(str)?"":"不")+"是关键字");
        str ="length";
        System.out.println(str+(isKeyword(str)?"":"不")+"是关键字");
    }
}

/*
程序运行结果如下：
index[]:(a,0,1) (b,2,4) (c,5,9) (d,10,12) (e,13,14) (f,15,19) (g,-1,-1) (h,-1,-1) (i,20,25) (j,-1,-1) (k,-1,-1) (l,26,26) (m,-1,-1) (n,27,29) (o,-1,-1) (p,30,33) (q,-1,-1) (r,34,34) (s,35,39) (t,40,45) (u,-1,-1) (v,46,47) (w,48,48)
finally? false? final? final是关键字
long? length不是关键字

*/


