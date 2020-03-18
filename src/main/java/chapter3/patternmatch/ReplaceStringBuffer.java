package chapter3.patternmatch;

/**
 * Created by lili on 2017/7/1.
 */
//3.3   串的模式匹配
//3.3.1   朴素的模式匹配（Brute-Force）算法
//【例3.4】 java.lang.StringBuffer字符串的替换和删除子串操作。

public class ReplaceStringBuffer
{
    //将target串中首个与pattern匹配的子串替换成replacement，返回替换后的target串，改变target串
    public static StringBuffer replaceFirst(StringBuffer target, String pattern, String replacement)
    {
        int i=target.indexOf(pattern);
        if(i!=-1)
        {
            target.delete(i, i+pattern.length());          //删除i～i+pattern.length()-1的子串
            target.insert(i, replacement);                 //在第i个字符处插入replacement串
        }
        return target;
    }

    //将target串中所有与pattern匹配的子串全部替换成replacement，返回替换后的target串，改变target串
    public static StringBuffer replaceAll(StringBuffer target, String pattern, String replacement)
    {
        int i=target.indexOf(pattern);
        while (i!=-1)
        {
            target.delete(i, i+pattern.length());
            target.insert(i, replacement);
            i=target.indexOf(pattern, i+replacement.length());
//            i=target.indexOf(pattern, i+1);            //错
        }
        return target;
    }

    //删除target串中首个与pattern匹配的子串，返回删除后的target串，改变target串
    public static StringBuffer deleteFirst(StringBuffer target, String pattern)
    {
        int i=target.indexOf(pattern);
        if(i!=-1)
            target.delete(i, i+pattern.length());
        return target;
    }

    public static void main(String args[])
    {
        StringBuffer target = new StringBuffer("ababdabcdabcabc"); //例3.3 数据
        String pattern="abc", replacement="xy";
//        StringBuffer target = new StringBuffer("aaaa");    //例3.4 数据
//        String pattern="a", replacement="ab";

        System.out.println("replaceFirst(\""+target+"\", \""+pattern+"\", \""+replacement+"\")="+
                replaceFirst(target, pattern, replacement));
        System.out.println("replaceAll(\""+target+"\", \""+pattern+"\", \""+replacement+"\")="+
                replaceAll(target, pattern, replacement));
        pattern=replacement;
        System.out.println("deleteFirst(\""+target+"\", \""+pattern+"\")="+deleteFirst(target, pattern));
        System.out.println("deleteAll(\""+target+"\", \""+pattern+"\")="+deleteAll(target, pattern));

        System.out.println("removeAll(\""+target+"\", \""+pattern+"\")="+removeAll(target, pattern));
    }

    //习题3，【例3.4】思考题
    //删除target串中所有与pattern匹配的子串，返回删除后的target串，改变target串
    public static StringBuffer deleteAll(StringBuffer target, String pattern)
    {
        int i=target.indexOf(pattern);
        while (i!=-1)
        {
            target.delete(i, i+pattern.length());
            i=target.indexOf(pattern, i);
        }
        return target;
    }

    //删除target串中所有与pattern匹配的子串，返回删除后的target串，改变target串
    //改进上述deleteAll()方法，对StringBuffer字符串删除所有匹配子串，字符一次移动到位
    public static StringBuffer removeAll(StringBuffer target, String pattern)
    {
        int m=target.length(), n=pattern.length();
        int i=target.indexOf(pattern), k=i;
        while (k!=-1)
        {
            int j=k+n;
            k=target.indexOf(pattern, j);
            while (k>0 && j<k || k<0 && j<m)
                target.setCharAt(i++, target.charAt(j++));
        }
        target.setLength(i);                          //设置target串长度为i
        return target;
    }
}

/*
程序运行结果如下：
//例3.3 数据
replaceFirst("ababdabcdabcabc", "abc", "xy")=ababdxydabcabc
replaceAll("ababdxydabcabc", "abc", "xy")=ababdxydxyxy
deleteFirst("ababdxydxyxy", "xy")=ababddxyxy
deleteAll("ababddxyxy", "xy")=ababdd

//例3.4 数据
replaceFirst("aaaa", "a", "ab")=abaaa
replaceAll("abaaa", "a", "ab")=abbababab
deleteFirst("abbababab", "ab")=bababab
deleteAll("bababab", "ab")=b
*/

/*
程序设计说明:
1、replaceAll()方法
       如果while中语句如下，当pattern="a", replacement="ab"时，死循环。
    i=target.indexOf(pattern, i);
    如果while中语句如下，当pattern="a", replacement="aab"时，死循环。
    i=target.indexOf(pattern, i+1);
*/

