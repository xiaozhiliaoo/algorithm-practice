package chapter10.arrays.other.backtracking;

/**
 * Created by lili on 2017/7/1.
 */
//【习2.24】  用回溯法（递归方法）求n个数的无重复全排列。n!个解

public class Permutation_backtrack
{
    private int[] x;                                //保存一个解
    private int count=0;                                   //解个数

    public Permutation_backtrack(int n)                    //构造方法
    {
        x = new int[n];
        enumerate(0, n);                         //穷举法输出1～n的所有排列（有重复），递归回溯
//        permute(0,n);
        System.out.println(count+"个选项\n");
    }

    //穷举法输出1～n的所有排列（有重复），递归回溯
    private void enumerate(int i, int n)
    {
        if (i<n)
            for (int j=1; j<=n; j++)
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

    //穷举法输出1～n的无重复全排列，递归回溯??
    private void permute(int i, int n)
    {
        if (i<n)
        {
            int j=i+1;
            for (int k=0; k<n-i; k++)
            {
                x[i]=j;
                permute(i+1, n);
            }
        }
        else                                               //i==n，到达叶子结点，获得一个n元组
        {
            count++;                                       //计数
            print(x);                                      //输出一个解，输出一维数组x，方法体省略
        }
    }

    private void print(int x[])                            //输出一个解
    {
        System.out.print("("+x[0]);
        for (int i=1; i<x.length; i++)
            System.out.print(","+x[i]);
        System.out.println(") ");
    }

    public static void main(String args[])
    {
        new Permutation_backtrack(3);
    }
}

/*
程序运行结果如下：
(1,1,1)
(1,1,2)
(1,1,3)
(1,2,1)
(1,2,2)
(1,2,3)
(1,3,1)
(1,3,2)
(1,3,3)
(2,1,1)
(2,1,2)
(2,1,3)
(2,2,1)
(2,2,2)
(2,2,3)
(2,3,1)
(2,3,2)
(2,3,3)
(3,1,1)
(3,1,2)
(3,1,3)
(3,2,1)
(3,2,2)
(3,2,3)
(3,3,1)
(3,3,2)
(3,3,3)
27个选项

  1

  1  2
  2  1

  1  2  3
  2  1  3
  3  2  1
  2  3  1
  1  3  2
  3  1  2

        enumerate(0, n);
(1,1,1)
(1,1,2)
(1,1,3)
(1,2,1)
(1,2,2)
(1,2,3)
(1,3,1)
(1,3,2)
(1,3,3)
(2,1,1)
(2,1,2)
(2,1,3)
(2,2,1)
(2,2,2)
(2,2,3)
(2,3,1)
(2,3,2)
(2,3,3)
(3,1,1)
(3,1,2)
(3,1,3)
(3,2,1)
(3,2,2)
(3,2,3)
(3,3,1)
(3,3,2)
(3,3,3)
27个选项


*/

/*
private void swap(int i,int j)               //交换数组两个元素值
{
    if (x!=null && i>=0 && i<x.length && j>=0 && j<x.length)
    {
        int temp = x[i];
        x[i] = x[j];
        x[j] = temp;
    }
}*/

