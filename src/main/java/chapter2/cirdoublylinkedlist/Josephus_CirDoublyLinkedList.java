package chapter2.cirdoublylinkedlist;

/**
 * Created by lili on 2017/7/1.
 */
//【例2.1】使用顺序表类SeqList求解约瑟夫环问题。
//【例2.2】使用单链表求解约瑟夫环问题。

public class Josephus_CirDoublyLinkedList
{
    private CirDoublyLinkedList<String> list;         //存储约瑟夫环中多个对象

    //创建约瑟夫环并求解，参数指定环长度、起始位置、计数
    public Josephus_CirDoublyLinkedList(int number, int start, int distance)
    {
        this.list = new CirDoublyLinkedList<String>();
        for (int i=0; i<number; i++)
            this.list.append(new String((char)('A'+i)+""));
        System.out.print("约瑟夫环("+number+","+start+","+distance+")，");
        System.out.println(this.list.toString());

        int index = start-1;                               //计数起始位置
        while (this.list.length()>1)                       //多于一个对象时循环
        {
            index = (index+distance-1) % this.list.length();
            System.out.print("删除"+this.list.remove(index).toString()+"，");  //删除指定位置对象
            System.out.println(this.list.toString());
        }
        System.out.println("被赦免者是"+list.get(0).toString());
    }

    public static void main(String args[])
    {
        new Josephus_CirDoublyLinkedList(5,1,2);
    }
}

/*
程序运行结果如下：
约瑟夫环(5,1,2)，(A, B, C, D, E)
删除B，(A, C, D, E)
删除D，(A, C, E)
删除A，(C, E)
删除E，(C)
被赦免者是C


*/
