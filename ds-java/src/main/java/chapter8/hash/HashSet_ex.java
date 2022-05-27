package chapter8.hash;

/**
 * Created by lili on 2017/7/1.
 */
//8.3   散列
//8.3.4   构造链地址法的散列表
//采用链地址法的散列表类，包括插入、删除、查找、遍历等操作。

public class HashSet_ex
{
    public static void main(String args[])
    {
        int n=10;
        HashSet<Integer> hashset = new HashSet<Integer>(n);
        System.out.print("插入关键字： ");
        int key;
        Integer x=null;
        for (int i=0; i<n; i++)
        {
            key=(int)(Math.random()*100);             //产生n个随机数作为关键字
            x = new Integer(key);
            hashset.insert(x);                      //在散列表中插入整数类型对象
            System.out.print(key+" ");
        }
        hashset.insert(x);                            //插入关键字重复元素
//        hashset.insert(null);                      //插入空对象，抛出异常

        System.out.print("\n散列表：\n"+hashset.toString());
/*        System.out.println("查找 "+x+", "+(hashset.contain(x)?"":"不")+"成功");
        key = 50;
        System.out.println("查找 "+key+", "+(hashset.contain(new Integer(key))?"":"不")+"成功");
        hashset.remove(x);
        hashset.remove(new Integer(key));
        System.out.println("删除 "+x+"， 删除 "+key+"\n");
        System.out.println("散列表："+hashset.toString());
*/
    }
}
/*
public final class Integer
{
    public int hashCode()                //覆盖Object类中方法
    {
        return value;
    }
}
 **/


/*
程序运行结果如下：
插入关键字： 26 21 75 96 21 80 84 98 42 23
散列表：
table[0]= (80)
table[1]= (21, 21)        ??
table[2]= (42)
table[3]= (23)
table[4]= (84)
table[5]= (75)
table[6]= (96, 26)
table[7]= ()
table[8]= (98)
table[9]= ()
查找 23, 成功
查找 50, 不成功
删除 23， 删除 50

散列表：table[0]= (80)
table[1]= (21, 21)
table[2]= (42)
table[3]= ()
table[4]= (84)
table[5]= (75)
table[6]= (96, 26)
table[7]= ()
table[8]= (98)
table[9]= ()


*/

