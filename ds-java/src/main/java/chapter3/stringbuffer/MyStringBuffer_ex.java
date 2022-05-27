package chapter3.stringbuffer;

/**
 * Created by lili on 2017/7/1.
 */

public class MyStringBuffer_ex
{
    public static void main(String args[])
    {
/*        MyStringBuffer sb1 = new MyStringBuffer("abcde");  //以字符串常量构造串对象
        MyStringBuffer sb2 = new MyStringBuffer("xyz");
        System.out.println("sb1.insert(2,sb2)=\""+sb1.insert(2,sb2).toString()+"\"");    //插入，改变sb1串
        System.out.println("sb1.delete(2,5)=\""+sb1.delete(2,5).toString()+"\"");
        sb1.append("pqrst").append(null);
        System.out.println("\""+sb1.toString()+"\"");
*/

        //Brute-Force模式匹配算法
//        MyString target=new MyString("abbabaaba"), pattern=new MyString("aba");//图3.10
//        MyString target=new MyString("aabaaa"), pattern=new MyString("aab");   //最好情况
//        MyString target=new MyString("aaaaaa"), pattern=new MyString("aab");     //最坏情况
//        System.out.println("\""+target+"\".indexOf_BF(\""+pattern+"\")="+target.indexOf_BF(pattern));
    }
}
/*
sb1.insert(2,sb2)="abxyzcde"
sb1.delete(2,5)="abcde"
"abcdepqrstnull"

//Brute-Force模式匹配算法
"abbabaaba".indexOf_BF("aba")=3                 //图3.10
count=8

indexOf("aabaaa", "aab")=0                      //最好情况
count=3

indexOf("aaaaab", "aab")=3
count=12

indexOf("aaaaaa", "aab")=-1                      //最坏情况
count=12                                         //比较m*(n-m+1)次，O(n*m)


 **/
