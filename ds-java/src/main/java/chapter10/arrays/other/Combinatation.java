package chapter10.arrays.other;

/**
 * Created by lili on 2017/7/1.
 */
//例10.2  动态规划法求组合数。

public class Combinatation
{
    public static int combine(int m, int n)      //返回组合数Cmn，分治策略递归算法
    {
        if (n==0 || m==n)
            return 1;
        return combine(m-1, n-1) + combine(m-1, n);
    }

    public static int combinate(int m, int n)    //返回组合数Cmn，动态规划法，采用三角形的二维数组
    {
        if (n==0 || m==n)
            return 1;
        int mat[][] = new int[m][];
        for (int i=0; i<m; i++)
        {
            mat[i] = new int[i+2];
            mat[i][0]=1;
            mat[i][i+1]=1;
            for (int j=1; j<=i; j++)
                mat[i][j] = mat[i-1][j-1]+mat[i-1][j];
            for (int j=0; j<=i+1; j++)
                System.out.print(String.format("%4d", mat[i][j]));
            System.out.println();
        }
        return mat[m-1][n];
    }

    public static int combination(int m, int n)  //返回组合数Cmn，动态规划法，采用一维数组
    {
        if (n==0 || m==n)
            return 1;
        int table[] = new int[m+1], i;
        for (i=0; i<=m; i++)                     //初始化
            table[i]=1;
        for (i=0; i<m; i++)
        {
            for (int j=i; j>0; j--)              //生成下一行数据
                table[j] = table[j-1]+table[j];  //通式
            for (int j=0; j<=i+1; j++)
                System.out.print(String.format("%4d", table[j]));
            System.out.println();
        }
        return table[n];                         //返回Cmn
    }

    public static void main(String args[])
    {
        int m=5, n=2;
//        System.out.println("combine("+m+","+n+")="+combine(m,n));      //分治策略递归算法
//        System.out.println("combinate("+m+","+n+")="+combinate(m,n));    //动态规划法，采用三角形的二维数组
//        System.out.println("combination("+m+","+n+")="+combination(m,n));//动态规划法，采用一维数组

        //combinat(m, n);                          //输出Cmn 组合
        enumerate(m, n);
    }
/*
程序运行结果如下：
//分治策略递归算法
combine(5,1)=5
combine(5,2)=10
combine(5,3)=10

//动态规划法，采用三角形的二维数组，采用一维数组
   1   1
   1   2   1
   1   3   3   1
   1   4   6   4   1
   1   5  10  10   5   1
combinate(5,1)=5
combinate(5,2)=10
combinate(5,3)=10

*/

    static void combinat(int m, int n)                     //输出Cmn组合，循环
    {
        if (n==0)
        {
            System.out.println("{}");
            return;
        }
        for (int i=1; i<m; i++)
        {
            for (int j=i; j<m; j++)
            {
                System.out.print("{"+i);
                for (int k=j+1; k<j+n; k++)
                    System.out.print(","+k);
                System.out.print("} ");
            }
            System.out.println();
        }
    }
    /*
    {1,2} {1,3} {1,4} {1,5}
    {2,3} {2,4} {2,5}
    {3,4} {3,5}
    {4,5}
      */
    static int count=0;
    static int x[];
    public static void enumerate(int m, int n)                   //穷举法输出所有选项
    {
        x=new int[n];
        enumerate(0, m, n);                   //穷举法输出所有选项，递归回溯
        System.out.println(count+"个选项\n");
    }
    public static void enumerate(int i, int m, int n)                   //穷举法输出所有选项，递归回溯
    {
        if (i<n)
            for (int j=0; j<=1; j++)
            {
                if (restrict(i,m))
                {
                    x[i]=j;
                    enumerate(i+1, n);
                }
            }
        else                                               //i==n，到达叶子结点，获得一个n元组
        {
            count++;                                       //计数
            print(x);                                      //输出一个解，输出一维数组x，方法体省略
        }
    }
    public static boolean restrict(int i, int m)    //约束条件
    {
        int sum=0;
        for (int j=0; j<i; j++)
            sum+=x[j];
        return sum<m;
    }
    public static void print(int x[])                            //输出一维数组，用于输出一个解
    {
        System.out.print("("+x[0]);
        for (int i=1; i<x.length; i++)
            System.out.print(","+x[i]);
        System.out.println(")");
    }
}


