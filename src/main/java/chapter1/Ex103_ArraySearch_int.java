//【例1.3】 数组的顺序查找算法。
//(1) 整数数组的顺序查找算法
package chapter1;
public class Ex103_ArraySearch_int
{
    public static int[] random(int n)      //产生n个随机数，返回整型数组
    {
        if (n<=0)
            return null;                  //数组变量可获得引用空值
        int[] value = new int[n];         //动态数组使用new运算符申请数组存储空间
        //若n<0，将抛出负数组长度异常
        for (int i=0; i<value.length; i++)     //数组变量通过length属性获得其存储单元数
            value[i] = (int)(Math.random()*100); //产生一个0～100之间的随机数
        return value;                    //返回局部变量value要引用的数组
    }

    public static void print(int[] value)   //输出数组元素
    {
        for (int i=0; i<value.length; i++)
            System.out.print(" "+value[i]);
        System.out.println();
    }

    //在整数数组value中查找key值，若查找成功返回元素下标，否则返回-1
    public static int indexOf(int[] value, int key)
    {
        for (int i=0; i<value.length; i++)   //若value==null，将抛出空对象异常NullPointerException
        {
            System.out.print(value[i]+"? ");
            if (value[i]==key)        //基本数据类型采用==、!=运算符比较相等
                return i;
        }
        return -1;
    }

    //习题1
    //在value数组前n个元素中查找key值，若查找成功返回元素下标，否则返回-1
    public static int indexOf(int[] value, int n, int key)
    {
        for (int i=0; i<n; i++)
            if (value[i]==key)
                return i;
        return -1;
    }

    public static int[] random(int n, int size)            //产生n个随机数，范围是1～size，返回整型数组
    {
        int value[] = new int[n];                          //若n<0，将抛出负数组长度异常
        int i=0;
        while (i<value.length)
        {
            int key = (int)(Math.random()*size);
            if (key!=0)
                value[i++] = key;
        }
        return value;
    }

    public static int[] randomDifferent(int n, int size)   //产生n个互异随机数，范围是1～size，返回整型数组
    {
        int value[]=new int[n], i=0;
        while (i<value.length)
        {
            int key=(int)(Math.random()*size);
            if (indexOf(value, i, key)==-1)               //顺序查找不成功
                value[i++] = key;
        }
        return value;
    }

    public static void main(String[] args)
    {
        int[] value =random(10);
        System.out.print("随机数序列: ");
        print(value);
        int key=value[value.length/2];
        System.out.println("顺序查找 "+key+", "+((indexOf(value,key)==-1)?"不":"")+"成功");
        key=25;
        System.out.println("顺序查找 "+key+", "+((indexOf(value,key)==-1)?"不":"")+"成功");

        //习题1
        value =randomDifferent(10,100);
        System.out.print("习题1，互异随机数序列: ");
        print(value);
    }

}

/*
程序运行结果如下：
随机数序列:  98 21 55 24 97 36 1 42 35 98
98? 21? 55? 24? 97? 36? 顺序查找 36, 成功
98? 21? 55? 24? 97? 36? 1? 42? 35? 98? 顺序查找 25, 不成功

随机数序列:  43 47 14 67 40 0 53 9 45 78
43? 47? 14? 67? 40? 0? 顺序查找 0, 成功
43? 47? 14? 67? 40? 0? 53? 9? 45? 78? 顺序查找 25, 不成功
习题1，互异随机数序列:  11 71 74 72 99 52 91 19 70 44

*/
