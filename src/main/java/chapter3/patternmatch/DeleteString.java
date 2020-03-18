package chapter3.patternmatch;

/**
 * Created by lili on 2017/7/1.
 */
//3.3   串的模式匹配
//3.3.1   朴素的模式匹配（Brute-Force）算法
//【例3.3】 java.lang.String字符串的查找、替换和删除子串操作。

public class DeleteString
{
    //返回将target串中首个与pattern匹配的子串删除后的字符串
    public static String deleteFirst(String target, String pattern)
    {
        int i=target.indexOf(pattern);
        if (i==-1)
            return target;
        return target.substring(0,i)+target.substring(i+pattern.length());
    }

    //返回将target串中所有与pattern匹配的子串删除后的字符串
    public static String deleteAll(String target, String pattern)
    {
        int i=target.indexOf(pattern);
        while (i!=-1)
        {
            target = target.substring(0,i)+target.substring(i+pattern.length());
            i=target.indexOf(pattern,i);
        }
        return target;
    }

    public static void main(String args[])
    {
        //图3.11，替换子串
//        String target="ababdabcdabcabc", pattern="abc", replacement="xy";   //例3.3数据
        String target="aaaa", pattern="a", replacement="ab";   //例3.4数据

        System.out.println("\""+target+"\".indexOf(\""+pattern+"\")="+target.indexOf(pattern));
        System.out.println("\""+target+"\".replaceFirst(\""+pattern+"\", \""+replacement+"\")="+
                target.replaceFirst(pattern,replacement));
        System.out.println("\""+target+"\".replaceAll(\""+pattern+"\", \""+replacement+"\")="+
                target.replaceAll(pattern,replacement));

        //图3.12，删除子串
        System.out.println("deleteFirst(\""+target+"\", \""+pattern+"\")="+deleteFirst(target, pattern));
        System.out.println("deleteAll(\""+target+"\", \""+pattern+"\")="+deleteAll(target, pattern));
    }
}

/*
程序运行结果如下：
//例3.3数据
"ababdabcdabcabc".indexOf("abc")=5
"ababdabcdabcabc".replaceFirst("abc", "xy")=ababdxydabcabc
"ababdabcdabcabc".replaceAll("abc", "xy")=ababdxydxyxy
deleteFirst("ababdabcdabcabc", "abc")=ababddabcabc
deleteAll("ababdabcdabcabc", "abc")=ababdd

//例3.4数据
"aaaa".indexOf("a")=0
"aaaa".replaceFirst("a", "ab")=abaaa
"aaaa".replaceAll("a", "ab")=abababab
deleteFirst("aaaa", "a")=aaa
deleteAll("aaaa", "a")=

*/
