package chapter10.collectios;

/**
 * Created by lili on 2017/7/1.
 */
//10.1.2   Java集合框架
//使用列表类ArrayList/LinkedList求解约瑟夫环问题。同顺序表

import java.util.ArrayList;

public class Josephus_List
{
    //创建约瑟夫环并求解，参数指定环长度、起始位置、计数
    public Josephus_List(int number,int start,int distance)
    {
        java.util.List<String> list;                       //使用一个列表存储约瑟夫环中多个对象信息
        list = new ArrayList<String>(number);              //列表中存储字符串对象，指定列表容量
//        this.list = new LinkedList<String>();
        for (int i=0; i<number; i++)
            list.add((char)('A'+i)+"");                    //列表添加对象
        System.out.print("约瑟夫环("+number+","+start+","+distance+")，");
        System.out.println(list.toString());               //显示列表中的所有对象

        int i = start-1;                                   //计数起始位置
        while (list.size()>1)                              //多于一个对象时循环
        {
            i = (i+distance-1) % list.size();              //计数按循环规律变化，顺序表可看作是环形结构
            System.out.print("删除"+list.remove(i).toString()+"，");//删除指定位置对象
            System.out.println(list.toString());
        }
        System.out.println("赦免者是"+list.get(0).toString());
    }

    public static void main(String args[])
    {
        new Josephus_List(5,1,2);
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

约瑟夫环(5,2,2)，[A, B, C, D, E]
删除C，[A, B, D, E]
删除E，[A, B, D]
删除B，[A, D]
删除A，[D]
赦免者是D
*/
