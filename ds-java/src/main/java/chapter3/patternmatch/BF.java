package chapter3.patternmatch;

/**
 * Created by lili on 2017/7/1.
 */
//3.3   串的模式匹配
//3.3.1   朴素的模式匹配（Brute-Force）算法

public class BF
{
    private static int count=0;                            //记载比较次数

    //返回模式串pattern在目标串target中从begin开始的首次匹配位置，匹配失败时返回-1
    public static int indexOf(String target, String pattern, int begin)
    {
        if (pattern!=null && pattern.length()>0 && target.length()>=pattern.length())
        {                                                  //当目标串比模式串长时进行比较
            int i=begin, j=0;                              //i、j分别为目标串和模式串当前字符的下标
            count=0;
            while (i<target.length())
            {
                if (target.charAt(i)==pattern.charAt(j))   //若当前两字符相等，则继续比较后续字符
                {
                    i++;
                    j++;
                }
                else                                       //否则i、j回溯，进行下一次匹配
                {
                    i=i-j+1;                               //目标串下标i退回到下一个待匹配子串首字符
                    j=0;                                   //模式串下标j退回到0
                }
                count++;
                if (j==pattern.length())                   //一次匹配结束，匹配成功
                {
                    System.out.println("BF.count="+BF.count);
                    return i-j;                            //返回匹配的子串序号
                }
            }
        }
        System.out.println("BF.count="+BF.count);
        return -1;                                         //匹配失败时返回-1
    }
    //返回模式串pattern在目标串target中从0开始的首次匹配位置，匹配失败时返回-1
    public static int indexOf(String target, String pattern)
    {
        return BF.indexOf(target, pattern, 0);
    }

    public static void main(String args[])
    {
        String target="ababdabcd", pattern="abc";        //图3.10，BF用例
//        String target="aabaaa", pattern="aab";           //图3.13(a)，匹配成功，最好情况
//        String target="aaaaa", pattern="aab";            //图3.13(b)，最坏情况，匹配不成功
//        String target="aaaab", pattern="aab";            //最坏情况，匹配成功

        System.out.println("BF.indexOf(\""+target+"\"，\""+pattern+"\")="+BF.indexOf(target,pattern));
    }
}
/*
程序运行结果如下：
BF.count=12
BF.indexOf("ababdabcd"，"abc")=5                            //图3.10，BF用例

BF.count=3
BF.indexOf("aabaaa"，"aab")=0                               //图3.13(a)，匹配成功，最好情况

BF.count=11
BF.indexOf("aaaaa"，"aab")=-1                               //图3.13(b)，最坏情况，匹配不成功

BF.count=9
BF.indexOf("aaaab"，"aab")=2                                //最坏情况，匹配成功


*/

