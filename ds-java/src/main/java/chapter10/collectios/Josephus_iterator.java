package chapter10.collectios;

/**
 * Created by lili on 2017/7/1.
 */
//10.1.2   Java集合框架
//使用列表类ArrayList/LinkedList求解约瑟夫环问题。
//使用ListIterator列表迭代器，插入、删除操作

import java.util.LinkedList;
import java.util.ListIterator;

public class Josephus_iterator
{
    private java.util.List<String> list;                   //使用一个列表存储约瑟夫环中多个对象信息

    //创建约瑟夫环并求解，参数指定环长度、起始位置、计数
    public Josephus_iterator(int number,int start,int distance)
    {
//        this.list = new ArrayList<String>(number);       //列表中存储字符串对象，指定列表容量
        this.list = new LinkedList<String>();
        ListIterator<String> it = list.listIterator();     //获得列表迭代器对象
        for (int i=0; i<number; i++)
            it.add((char)('A'+i)+"");                      //通过列表迭代器添加对象，可以连接添加
        System.out.print("约瑟夫环("+number+","+start+","+distance+")，");
        System.out.println(this.list.toString());

        it = list.listIterator(start-1);                   //获得列表迭代器对象，指定初始位置
        int count=0;
        String str="";
        while (this.list.size()>1)
        {
            if (!(it.hasNext()))
                it = list.listIterator();                  //重新获得列表迭代器对象，初始序号为-1
            str = it.next();
            count++;
            if (count==distance)
            {
                System.out.print("删除"+str+"，");
                it.remove();                               //删除
                System.out.println(this.list.toString());
                count=0;
            }
        }
        System.out.println("赦免者是"+list.get(0).toString());
    }

    public static void main(String args[])
    {
        new Josephus_iterator(5,1,2);
    }
}

/*
程序运行结果如下：
约瑟夫环(5,1,2)，[A, B, C, D, E]
删除B，[A, C, D, E]
删除D，[A, C, E]
删除A，[C, E]
删除E，[C]
赦免者是C

*/
