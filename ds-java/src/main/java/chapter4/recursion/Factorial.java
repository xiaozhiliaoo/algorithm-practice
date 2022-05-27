package chapter4.recursion;

/**
 * Created by lili on 2017/7/1.
 */
//【例4.5】  求n！。

public class Factorial
{
    public static int factorial(int n)                    //求阶乘n!，递归方法
    {
        if (n<0)
            throw new IllegalArgumentException("n="+n); //抛出无效参数异常
        if (n==0 || n==1)
            return 1;
        return n*factorial(n-1);
    }

    public static void main(String args[])
    {
        int n=5;
        System.out.println(n+"!="+factorial(n));           //5!=120

        //习题4
        System.out.println(n+"!="+toString(n)+factorial(n));
    }

    //习题4
    public static String toString(int n)                   //求阶乘n!
    {
        if (n>=0)
            return toString(n, "");
        throw new IllegalArgumentException("n="+n); //抛出无效参数异常
    }
    private static String toString(int n, String str)      //求阶乘n!，递归方法
    {
        if (n==0 || n==1)
            return "";
        else
        {
            str += n+"*"+(n-1)+"!="+toString(n-1, str);
            return str;
        }
    }
}
/*
程序运行结果如下：
5!=120
5!=5*4*3*2*1=120
*/

/*
public static int factorial(int n, StringBuffer str)                //求阶乘，递归方法
{
    str=new StringBuffer();
	if (n<0)
        throw new NumberFormatException("n<0时n!无定义");
}
public static int factorial(int n, StringBuffer str)                //求阶乘，递归方法
{
//    System.out.print(n);
    if (n==0 || n==1)
    {
        str
        return 1;
    }
    else
    {
//        System.out.println(n+"!="+n+"*"+(n-1)+"!");
//        System.out.print("*");
        return n*factorial(n-1);
    }
}

5!=5*4!
4!=4*3!
3!=3*2!
2!=2*1!
5!=120     */

