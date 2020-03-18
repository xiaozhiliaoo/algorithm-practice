//【例1.3】 数组的顺序查找算法。
//(2) 对象数组的顺序查找算法
package chapter1;
public class Ex103_ArraySearch_Object
{
    //在对象数组value中查找key对象，若查找成功返回元素下标，否则返回-1
    //若value或key为null，将抛出空对象异常NullPointerException
    public static int indexOf(Object[] value, Object key)
    {
        for(int i=0; i<value.length; i++)
            if (key.equals(value[i]))                     //对象采用equals()方法比较相等
                return i;
        return -1;
    }

    public static void print(Object[] value)              //输出对象数组元素
    {
        for (int i=0; i<value.length; i++)
            System.out.print(" "+value[i].toString());    //输出对象默认执行其toString()方法
        System.out.println();
    }

    public static void main(String[] args)
    {
//        Integer[] value = {new Integer(32),new Integer(15),new Integer(78),new Integer(99)};
        Integer[] value = {32,15,78,99,87,34,14,19,76,46,1};
//        Object[] value =randomInteger(10);
        System.out.print("随机数序列: ");
        print(value);
        Object key = 99;//new Integer(99);
        System.out.println("顺序查找 "+key+", "+((indexOf(value,key)==-1)?"不":"")+"成功");

        //习题1
/*        value =randomString(10);
        System.out.print("随机字符序列: ");
        print(value);
        key = "X";
        System.out.println("顺序查找 "+key+", "+((indexOf(value,key)==-1)?"不":"")+"成功");*/
    }

    //习题1
    public static Integer[] randomInteger(int n)           //返回由n个随机数组成的整数对象数组
    {
        Integer[] value = new Integer[n];
        for (int i=0; i<value.length; i++)
            value[i]=new Integer((int)(Math.random()*100));//产生随机数
        return value;                                     //返回数组引用
    }

    public static Integer[] randomInteger(int n, int size) //返回产生n个随机数的数组，范围是1～size
    {
        Integer[] value = new Integer[n];
        for (int i=0; i<n; i++)
            value[i]=new Integer((int)(Math.random()*size));
        return value;
    }

    public static String[] randomString(int n)             //返回由n个随机字符组成的字符串数组
    {
        String[] value = new String[n];
        for (int i=0; i<value.length; i++)
            value[i]=(char)(Math.random()*26+'A')+"";     //由一个大写字母构成的字符串
//            value[i]=(char)(Math.random()*93+'!')+"";   //由一个ASCII可打印字符构成的字符串
        return value;
    }

}
/*
程序运行结果如下：
随机数序列:  33 15 87 34 78 14 19 76 46 1
顺序查找 99, 不成功
随机字符序列:  J Y R Y R U X U B T
顺序查找 X, 成功
随机字符序列:  S K s L S ` ) - a U
顺序查找 X, 成功
*/
