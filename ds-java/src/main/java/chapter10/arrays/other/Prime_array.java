package chapter10.arrays.other;

/**
 * Created by lili on 2017/7/1.
 */
//《Java程序设计实用教程（第3版）》//【习2.12】 “筛选法”求素数。
//《数据结构(Java版)(第3版)》10.3.2   动态规划法

public class Prime_array
{
    public static int[] prime(int max)           //返回包含max以内素数的数组
    {
        if (max<=0)
            return null;
        int primes[] = new int[max/2];
        primes[0]=2;                             //已知的最小素数
        int n=1;                                 //素数个数,即数组的实际长度(元素个数)
        int i=1;                                 //下一个素数应存放的数组下标位置
        int k=3;                                 //从最小奇数开始测试,所有偶数不需测试
        do
        {
            int j=0;
            while ((j<n) && (k % primes[j]!=0))  //用已知素数primes[j]测试k
                j++;
            if (j==n)                            //k是素数
            {
                primes[i]=k;                     //将k添加到数组prime中
                i++;
                n++;
            }
            k+=2;                                //测试下一个奇数是否是素数
        }  while(k<max);
        int[] temp = new int[n];
        System.arraycopy(primes, 0, temp, 0, n);
        return temp;
    }

    public static void main(String args[])
    {
        int max=100;
        int primes[] = prime(max);               //返回包含max以内素数的数组
        System.out.println(max+"以内的素数有"+primes.length+"个: ");
        for (int i=0; i<primes.length; i++)
        {
            System.out.print(String.format("%5d",primes[i]));
            if ((i+1)%10==0)
                System.out.println();             //每行写10个数
        }
        System.out.println();
    }
}

/*
程序运行结果如下：
100以内的素数有25个:
    2    3    5    7   11   13   17   19   23   29
   31   37   41   43   47   53   59   61   67   71
   73   79   83   89   97

500以内的素数有95个:
    2    3    5    7   11   13   17   19   23   29
   31   37   41   43   47   53   59   61   67   71
   73   79   83   89   97  101  103  107  109  113
  127  131  137  139  149  151  157  163  167  173
  179  181  191  193  197  199  211  223  227  229
  233  239  241  251  257  263  269  271  277  281
  283  293  307  311  313  317  331  337  347  349
  353  359  367  373  379  383  389  397  401  409
  419  421  431  433  439  443  449  457  461  463
  467  479  487  491  499

*/
