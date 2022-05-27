package chapter10.arrays.other;

/**
 * Created by lili on 2017/7/1.
 */
//10.3.3   贪心法
//【例10.3】 用贪心法求解背包问题。

public class Knapsack                                      //背包问题
{
    //构造方法，求解背包问题。参数kweight指定背包重量，数组arts提供若干物品
    public Knapsack(float kweight, Article arts[])
    {
        System.out.println(arts.length+"件物品(重量,价值,单位重量价值)：");
        print(arts);                                       //输出物品序列，输出一维对象数组，方法体省略
        java.util.Arrays.sort(arts);                       //数组排序，默认compareTo()方法指定排序次序
//        java.util.Arrays.sort(arts,new CompareByPerprice()); //数组排序，由比较器对象指定排序依据
        System.out.println(arts.length+"件物品(重量,价值,单位重量价值)，按单位重量价值降序排列：");
        print(arts);                                       //输出物品序列，输出一维对象数组，方法体省略
        float kprice=0, x[]=new float[arts.length];        //x数组保存求解结果
        int i=0;
        for (i=0; i<arts.length && arts[i].weight<kweight; i++)  //若干物品全部放入背包
        {
            kweight -= arts[i].weight;                     //kweight为背包重量
            kprice += arts[i].price;                       //kprice为背包内物品总价值
            x[i] = 1;                                      //物品i全部装入
        }
        if (i<arts.length && kweight>0)                    //物品i部分放入背包
        {
            x[i] = kweight/arts[i].weight;
            kprice += arts[i].price*x[i];
        }
        System.out.print("最优解： ");
        print(arts,x);                                     //输出一个解及背包实际重量
        System.out.println("，背包价值："+String.format("%5.2f", kprice)+"\n");
    }

    private void print(Article arts[], float x[])          //输出一个解及背包实际重量
    {
        float kweight=arts[0].weight*x[0];                 //背包实际重量
        System.out.print("("+x[0]);
        for (int i=1; i<x.length; i++)
        {
            System.out.print(","+String.format("%5.2f", x[i]));
            kweight+=arts[i].weight*x[i];
        }
        System.out.print(")，背包重量："+String.format("%5.2f", kweight));
    }

    private void print(Object table[])                     //输出一维对象数组，输出物品序列
    {
        for (int i=0; i<table.length; i++)
            System.out.print(table[i]+"  ");
        System.out.println();
    }

    public static Article[] random(int n)              //返回产生n个随机数的数组
    {
        Article[] list = new Article[n];
        for (int i=0; i<n; i++)
            list[i]=new Article((int)(Math.random()*100),(int)(Math.random()*100));   //产生随机数
        return list;
    }

    public static void main(String args[]) throws Exception
    {
        Article arts[]={new Article(80,20),new Article(50,25),new Article(15,45)}; //物品序列，没排序
        new Knapsack(100, arts);
        new Knapsack(100, Knapsack.random(3));
    }
}
/*
程序运行结果如下：
3件物品(重量,价值,单位重量价值)：
(80,20, 0.25)  (50,25, 0.50)  (15,45, 3.00)
3件物品(重量,价值,单位重量价值)，按单位重量价值降序排列：
(15,45, 3.00)  (50,25, 0.50)  (80,20, 0.25)
最优解： (1.0, 1.00, 0.44)，背包重量：100.00，背包价值：78.75

3件物品(重量,价值,单位重量价值)：
(12,33, 2.75)  (65,35, 0.54)  (78,72, 0.92)
3件物品(重量,价值,单位重量价值)，按单位重量价值降序排列：
(12,33, 2.75)  (78,72, 0.92)  (65,35, 0.54)
最优解： (1.0, 1.00, 0.15)，背包重量：100.00，背包价值：110.38


*/
/*??
    private void printAll(float kweight, Article arts[])   //输出所有可用解
    {
        System.out.println(arts.length+"件物品(重量,价值,单位重量价值)：");
        print(arts);                                       //输出物品序列，输出一维对象数组，方法体省略
        System.out.println("背包重量："+kweight);
    	float kprice=0, x[]=new float[arts.length];        //x数组保存求解结果
    	int i=0;
        for (i=0; i<arts.length && arts[i].weight<kweight; i++)  //若干物品全部放入背包
        {
            kweight -= arts[i].weight;                     //kweight为背包重量
            kprice += arts[i].price;                       //kprice为背包内物品总价值
            x[i] = 1;                                      //物品i全部装入
        }
        if (i<arts.length && kweight>0)                    //物品i部分放入背包
        {
            x[i] = kweight/arts[i].weight;
            kprice += arts[i].price*x[i];
            i++;
        }

        System.out.print("可用解： ");
        print(x);                                          //输出一个解，输出一维数组x，方法体省略
        System.out.println("背包价值："+kprice+"\n");
    }
*/