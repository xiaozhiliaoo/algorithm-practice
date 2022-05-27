package chapter4.recursion;

/**
 * Created by lili on 2017/7/1.
 */
public class Dig9_r
{
    static void count(int i,int n)               //递归方法
    {
        if(i<n)
        {
            System.out.print(i+"  ");
            count(i+1,n);
        }
        System.out.print(i+"  ");
    }
    public static void main(String args[])
    {
        int i,j,n=9;
        for(i=1;i<=n;i++)
        {
            for(j=n;j>i;j--)
                System.out.print("   ");         //前导空格
            count(1,i);
            System.out.println();
        }
    }
}