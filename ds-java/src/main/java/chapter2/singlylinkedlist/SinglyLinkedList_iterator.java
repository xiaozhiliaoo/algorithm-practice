package chapter2.singlylinkedlist;

/**
 * Created by lili on 2017/7/1.
 */
//10.2   实现迭代器

public class SinglyLinkedList_iterator
{
    public static SinglyLinkedList<Integer> random(int n)              //返回产生n个随机数的数组
    {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>(); //空表
        for (int i=0; i<n; i++)
            list.append(new Integer((int)(Math.random()*100)));   //产生随机数通过列表迭代器对象添加元素，可以连续添加
        return list;
    }

    public static int sum(SinglyLinkedList<Integer> list)
    {
        java.util.Iterator<Integer> it = list.iterator();      //获得迭代器对象
        int sum=0, value=0;
        while (it.hasNext())
        {
            value = it.next().intValue();
            sum += value;
            System.out.print(value);
            if (it.hasNext())
                System.out.print("+");
        }
        System.out.println("="+sum);
        return sum;
    }

    public static void main(String args[])
    {
        SinglyLinkedList<Integer> list1 = random(10);
        System.out.println("list1:"+list1.toString()+"，");
        sum(list1);

        SinglyLinkedList<Integer> list2 = new SinglyLinkedList<Integer>(list1);    	//深拷贝
        System.out.println("list2:"+list2.toString()+"，");
        java.util.Iterator<Integer> it = list2.iterator();      //获得迭代器对象
        Integer value = it.next();
        it.remove();                                       //删除第一个元素
        //若不执行it.next();，则抛出java.lang.IllegalStateException异常
        System.out.println("删除"+value+"，list2:"+list2.toString()+"， ");
        value = it.next();
        it.remove();                                       //删除第一个元素
        System.out.println("删除"+value+"，list2:"+list2.toString());
        sum(list2);                              //同时有两个迭代器
    }
}
/*
程序运行结果如下：
list1:(37, 61, 67, 63, 35, 5, 11, 27, 89, 87)，
37+61+67+63+35+5+11+27+89+87=482
list2:(37, 61, 67, 63, 35, 5, 11, 27, 89, 87)，
删除37，list2:(61, 67, 63, 35, 5, 11, 27, 89, 87)，
删除61，list2:(67, 63, 35, 5, 11, 27, 89, 87)
67+63+35+5+11+27+89+87=384

*/


