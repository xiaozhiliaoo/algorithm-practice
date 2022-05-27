package chapter10.arrays.other;

/**
 * Created by lili on 2017/7/1.
 */
//【习2.24】  用递归方法求n个数的无重复全排列。

public class Permutation
{
    private int[] table;

    public Permutation(int n)                    //构造方法
    {
        if (n>0)
        {
            table = new int[n];
            for (int i=0;i<n;i++)
                table[i] = i+1;
            permute(n);
        }
        else
            table = null;
    }

    private void output()                        //输出数组元素
    {
        for (int i=0;i<table.length;i++)
            System.out.print("  "+table[i]);
        System.out.println();
    }

    private void swap(int i,int j)               //交换数组两个元素值
    {
        if (table!=null && i>=0 && i<table.length && j>=0 && j<table.length)
        {
            int temp = table[i];
            table[i] = table[j];
            table[j] = temp;
        }
    }

    private void permute(int n)                   //用递归方法求n个数的无重复全排列
    {
        if (n==1)
            this.output();
        else
        {
            permute(n-1);
            for (int j=0;j<n-1;j++)
            {
                swap(n-1,j);
                permute(n-1);
                swap(n-1,j);
            }
        }
    }

    public static void main(String args[])
    {
        new Permutation(3);
    }
}

/*
程序运行结果如下：
  1

  1  2
  2  1

  1  2  3
  2  1  3
  3  2  1
  2  3  1
  1  3  2
  3  1  2

*/


