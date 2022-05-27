//【例1.6】 排序算法及其必要性。
//(1) 排序算法的必要性
//(2) 直接插入排序算法，int
package chapter1;
public class Ex106_int
{
    //(1) 排序算法的必要性
    public static void sort(int a, int b)                  //两个整数按升序排序输出
    {
        if (a<b)
            System.out.println(a+", "+b);
        else
            System.out.println(b+", "+a);
    }

    public static void sort(int a, int b, int c)           //三个整数按升序排序输出
    {
        if (a<b)
            if (b<c)
                System.out.println(a+"  "+b+"  "+c);
            else
            if (a<c)
                System.out.println(a+"  "+c+"  "+b);
            else
                System.out.println(c+"  "+a+"  "+b);
        else
        if (a<c)
            System.out.println(b+"  "+a+"  "+c);
        else
        if (c<b)
            System.out.println(c+"  "+b+"  "+a);
        else
            System.out.println(b+"  "+c+"  "+a);
    }

    //(2) 直接插入排序算法，int 
    public static void insert(int[] value, int n, int key)//将key按升序插入到value数组前n个元素中
    {
        int i=0;
        while (i<n && key>=value[i])                      //顺序查找key的插入位置
            i++;
        for (int j=n-1; j>=i; j--)
            value[j+1]=value[j];                         //元素向后移动
        value[i]=key;                                     //i是key的插入位置
    }

    public static void print(int[] value, int n)          //输出数组前n个元素
    {
        for (int i=0; i<n && i<value.length; i++)
            System.out.print(" "+value[i]);
        System.out.println();
    }

    //从标准输入流中读取表示若干整数的一行字符串，以空格分隔，再调用split()方法分解成字符串数组。
    public static int[] readInt() throws java.io.IOException  //抛出IO异常
    {
        System.out.print("输入无序的整数序列: ");
        byte[] buffer = new byte[512];           //以字节数组作为缓冲区
        int count = System.in.read(buffer);      //从标准输入流中读取若干字节到缓冲区buffer，返回实际读取字节数
        if (count<=2)                            //输入回车符或^Z
            return null;
        String s=new String(buffer,0,count-2);   //由buffer数组中从0开始长度为count-2的若干字节构造串
        String[] str=s.split(" ");               //分解成字符串数组，以空格分隔
        int[] value=new int[str.length];
        int i=0, j=0;
        while (i<str.length)
            try                                           //异常处理语句，存在潜在异常的代码
            {   value[j] = Integer.parseInt(str[i]);       //调用声明抛出异常的方法
                j++;
            }
            catch(NumberFormatException e)                 //捕获并处理异常
            {
                System.out.println(str[i]+"字符串不能转换为整数");
            }
            finally                              //最后必须执行的代码，无论是否捕获到异常
            {
                i++;
            }
        if (i==j)
            return value;
        int[] keys=new int[j];
        System.arraycopy(value, 0, keys, 0, j);
        //将value数组从0开始的j个元素复制到keys数组从0开始的元素中
        return keys;
    }

    public static void main(String[] args) throws java.io.IOException  //抛出IO异常交由Java虚拟机处理
    {
        int[] value = readInt();                           //输入一组整数
//        int[] value = readlnInt(10);                          //输入一组整数，习题1
        for (int i=0; i<value.length; i++)
        {
            System.out.print("插入："+value[i]+", \t排序序列：" );
            insert(value, i, value[i]);                  //将value[i]按升序插入到value数组前i个元素中
            print(value, i+1);                            //输出数组前i+1个元素，方法声明省略
        }
        int key=100;
        int index=Ex104_SortedArray_int.indexOf(value, key);
        System.out.println("顺序查找"+key+", "+(index==-1?"不":"")+"成功");

        //习题1
//??        System.out.println("排序? "+SortedArray.isSorted(value));        
    }
    /* 
    程序运行结果如下：
    输入无序的整数序列: 34 23 54 12 xyz 76 32 
    xyz字符串不能转换为整数
    插入：34, 	排序序列： 34
    插入：23, 	排序序列： 23 34
    插入：54, 	排序序列： 23 34 54
    插入：12, 	排序序列： 12 23 34 54
    插入：76, 	排序序列： 12 23 34 54 76
    插入：32, 	排序序列： 12 23 32 34 54 76
    12? 23? 32? 34? 54? 76? 顺序查找 100, 不成功
    排序? true

    */
    //习题1
    //调用语句见例1.4
    public static int[] randomDifferentSorted(int n, int size)   //产生n个互异随机数，范围是0～size，返回整型数组
    {
        if (n<=0)
            return null;
        int value[]=new int[n];
        for (int i=0; i<value.length; i++)
            insert(value, i, (int)(Math.random()*size));
        //将一个0～size之间的随机数按升序插入到value数组前i个元素中
        return value;
    }

    //从标准输入流中读取最多n行表示整数的字符串，以回车换行符分隔，以^Z结束输入流。返回实际读取的整数数组
    public static int[] readlnInt(int n) throws java.io.IOException  //抛出IO异常
    {
        int[] value=new int[n];
        System.out.print("输入最多"+n+"行无序整数: ");
        byte buffer[] = new byte[32];                      //以字节数组作为缓冲区
        int count = System.in.read(buffer);                //从标准输入流中读取若干字节到缓冲区buffer，返回实际读取字节数
        int i=0;
        String s="";
        if (count<=2)                                      //输入回车符或^Z
            return null;
        while (count>2 && i<n-1)                           //输入回车符或^Z
            try                                            //异常处理语句
            {                                              //存在潜在异常的代码
                s=new String(buffer,0,count-2);            //由buffer数组中从0开始长度为count-2的若干字节构造串
                value[i] = Integer.parseInt(s);            //调用声明抛出异常的方法
                i++;
                count = System.in.read(buffer);
            }
            catch(NumberFormatException e)                //捕获并处理parseInt()方法声明的异常对象
            {
                System.out.println(s+"字符串不能转换为整数");
            }
            finally                                        //最后必须执行的代码，无论是否捕获到异常
            {
            }
        if (i==n)
            return value;
        int[] keys=new int[i];
        System.arraycopy(value, 0, keys, 0, i);
        //复制value数组从0开始的i个元素到keys数组从0开始的元素中
        return keys;
    }

}
/* 
程序运行结果如下：
输入最多10行无序整数: 123
332
24
65
55
768
543
3546
88
90
87
插入：123, 	排序序列： 123
插入：332, 	排序序列： 123 332
插入：24, 	排序序列： 24 123 332
插入：65, 	排序序列： 24 65 123 332
插入：55, 	排序序列： 24 55 65 123 332
插入：768, 	排序序列： 24 55 65 123 332 768
插入：543, 	排序序列： 24 55 65 123 332 543 768
插入：3546, 	排序序列： 24 55 65 123 332 543 768 3546
插入：88, 	排序序列： 24 55 65 88 123 332 543 768 3546
插入：90, 	排序序列： 24 55 65 88 90 123 332 543 768 3546
24? 55? 65? 88? 90? 顺序查找100, 不成功


*/