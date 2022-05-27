package chapter10.arrays.other.backtracking;

/**
 * Created by lili on 2017/7/1.
 */
//回溯法的解空间
public class SolutionSpace
{
    static int count=0;
    static int x[];
    public static void enumerate(int n)                   //穷举法输出所有选项
    {
        x=new int[n];
        enumerate(0, n);                   //穷举法输出所有选项，递归回溯
        System.out.println(count+"个选项\n");
    }
    public static void enumerate(int i, int n)                   //穷举法输出所有选项，递归回溯
    {
        if (i<n)
            for (int j=0; j<=1; j++)
            {
                x[i]=j;
                enumerate(i+1, n);
            }
        else                                               //i==n，到达叶子结点，获得一个n元组
        {
            count++;                                       //计数
            print(x);                                      //输出一个解，输出一维数组x，方法体省略
        }
    }
    public static void print(int x[])                            //输出一维数组，用于输出一个解
    {
        System.out.print("("+x[0]);
        for (int i=1; i<x.length; i++)
            System.out.print(","+x[i]);
        System.out.println(")");
    }
    public static void main(String args[])
    {
        enumerate(3);
    }
}
/*
    enumerate(3);
(0,0,0)
(0,0,1)
(0,1,0)
(0,1,1)
(1,0,0)
(1,0,1)
(1,1,0)
(1,1,1)
8个选项

   enumerate(4);
(0,0,0,0)
(0,0,0,1)
(0,0,1,0)
(0,0,1,1)
(0,1,0,0)
(0,1,0,1)
(0,1,1,0)
(0,1,1,1)
(1,0,0,0)
(1,0,0,1)
(1,0,1,0)
(1,0,1,1)
(1,1,0,0)
(1,1,0,1)
(1,1,1,0)
(1,1,1,1)
16个选项


*/

