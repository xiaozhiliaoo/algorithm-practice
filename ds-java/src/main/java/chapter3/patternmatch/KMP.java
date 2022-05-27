package chapter3.patternmatch;

/**
 * Created by lili on 2017/7/1.
 */
//3.3.2   KMP算法

public class KMP
{
    private static int count=0;                            //记载比较次数
    private static int[] next;                             //模式串pattern改进的next数组
    private static int[] nextk;                            //模式串pattern未改进的next数组

    //返回模式串pattern在目标串target中从begin开始的首次匹配位置，匹配失败时返回-1
    public static int indexOf(String target, String pattern, int begin)
    {
        if (pattern.length()>0 && target.length()>=pattern.length())
        {                                                  //当目标串比模式串长时进行比较
            int i=begin, j=0;                              //i、j分别为目标串、模式串当前比较字符下标
            count=0;
            nextk = getNextk(pattern);
            System.out.println("nextk[]: "+toString(nextk));
            next = getNext(pattern);                       //返回模式串pattern改进的next数组
            System.out.println("next[]:  "+toString(next));

            while (i<target.length())
            {
                if (j!=-1)
                    System.out.println("KMP.count="+KMP.count+"，i="+i+"，j="+j+"，"+target.charAt(i)+"=="+pattern.charAt(j)+"？");

                if (j==-1 || target.charAt(i)==pattern.charAt(j))
                {                                          //若条件成立（当前两字符相等），则继续比较后续字符
                    i++;
                    j++;
                }
                else                                       //否则目标串下标i不回溯，进行下次匹配
                    j=next[j];                             //模式串下标j退回到next[j]

                if(j!=-1) count++;
                if (j==pattern.length())                   //一次匹配结束，匹配成功
                {
                    System.out.println("KMP.count="+KMP.count);
                    return i-j;                            //返回匹配的子串序号
                }
            }
        }
        System.out.println("KMP.count="+KMP.count);
        return -1;                                         //匹配失败
    }
    //返回模式串pattern在目标串target中从0开始的首次匹配位置，匹配失败时返回-1
    public static int indexOf(String target, String pattern)
    {
        return indexOf(target, pattern, 0);
    }

    private static int[] getNextk(String pattern)          //返回模式串pattern的next数组
    {
        int j=0, k=-1;
        int[] next=new int[pattern.length()];
        next[0]=-1;
        while (j<pattern.length()-1)
            if (k==-1 || pattern.charAt(j)==pattern.charAt(k))
            {
                j++;
                k++;
                next[j]=k;                                 //有待改进
            }
            else k=next[k];
        return next;
    }

    private static int[] getNext(String pattern)           //返回模式串pattern改进的next数组
    {
        int j=0, k=-1;
        int[] next=new int[pattern.length()];
        next[0]=-1;
        while (j<pattern.length()-1)
            if (k==-1 || pattern.charAt(j)==pattern.charAt(k))
            {
                j++;
                k++;
                if (pattern.charAt(j)!=pattern.charAt(k))  //改进之处
                    next[j]=k;
                else
                    next[j]=next[k];
            }
            else k=next[k];
        return next;
    }

    private static String toString(int[] next)             //输出next[]数组
    {
        String str="";
        for (int i=0; i<next.length; i++)
            str += next[i]+" ";
        return str;
    }

    public static void main(String args[])
    {
//    	  String target="abdabcabbabcabc", pattern="abcabc"; //图3.17
//          String target="ababdabcd", pattern="abc";        //BF用例，图3.10
//          String target="abcabdabcabcaa", pattern="abcabdabcabcaa";  //表3.4
//          String target="aaaaa", pattern="aab";              //最坏情况，匹配不成功，图3.13(b)
//          String target="aaaab", pattern="aab";           //最坏情况，匹配成功

        //习题3
        String target="ababaab", pattern="aab";      //习3.12①
//        String target="aaabaaaab", pattern="aaaab";      //习3.12②
//      String target="acabbabbabc", pattern="abbabc";    //习3.12③
//      String target="acabcabbabcabc", pattern="abcabaa";    //习3.12④
//        String target="aabcbabcaabcaababc", pattern="abcaababc"; //习3.12⑤，张乃孝书

        System.out.println("KMP.indexOf(\""+target+"\", \""+pattern+"\")="+KMP.indexOf(target, pattern));
    }
}

/*
程序运行结果如下：
KMP.indexOf("abdabcabbabcabc", "abcabc")=9       //图3.17
nextk[]: -1 0 0 0 1 2
next[]:  -1 0 0 -1 0 0
KMP.count=17

KMP.indexOf("ababdabcd", "abc")=5                //BF用例，图3.10
nextk[]: -1 0 0
next[]:  -1 0 0
KMP.count=10

KMP.indexOf("abcabdabcabcaa", "abcabdabcabcaa")=0  //表3.4
nextk[]: -1 0 0 0 1 2 0 1 2 3 4 5 3 4
next[]:  -1 0 0 -1 0 2 -1 0 0 -1 0 5 -1 4
KMP.count=14

KMP.indexOf("aaaaa", "aab")=-1                   //图3.13(b)，最坏情况，匹配不成功，比较n+m次
nextk[]: -1 0 1
next[]:  -1 -1 1
KMP.count=8

KMP.indexOf("aaaab", "aab")=2                    //最坏情况，匹配成功，O(n+m)
nextk[]: -1 0 1
next[]:  -1 -1 1
KMP.count=7

//习题3，//习3.12
KMP.indexOf("ababaab", "aab")=4                  //习3.12①
nextk[]: -1 0 1
next[]:  -1 -1 1
KMP.count=7

KMP.indexOf("aaabaaaab", "aaaab")=4              //习3.12②
nextk[]: -1 0 1 2 3
next[]:  -1 -1 -1 -1 3
KMP.count=9

KMP.indexOf("acabbabbabc", "abbabc")=5           //习3.12③
nextk[]: -1 0 0 0 1 2
next[]:  -1 0 0 -1 0 2
KMP.count=13

KMP.indexOf("acabcabbabcabc", "abcabaa")=-1       //习3.12④
nextk[]: -1 0 0 0 1 2 1
next[]:  -1 0 0 -1 0 2 1
KMP.count=18

KMP.indexOf("aabcbabcaabcaababc", "abcaababc")=9   //习3.12⑤,张乃孝书
nextk[]: -1 0 0 0 1 1 2 1 2
next[]:  -1 0 0 -1 1 0 2 0 0
KMP.count=20




KMP.indexOf("abbabaaba", "aba")=3                 //BF用例
nextk[]: -1 0 0
next[]:  -1 0 -1
KMP.count=6

*/
