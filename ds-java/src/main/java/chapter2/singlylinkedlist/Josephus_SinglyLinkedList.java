package chapter2.singlylinkedlist;

/**
 * Created by lili on 2017/7/1.
 */
//【例2.2】使用单链表求解约瑟夫环问题。
//同顺序表

public class Josephus_SinglyLinkedList
{
    //创建约瑟夫环并求解，参数指定环长度、起始位置、计数
    public Josephus_SinglyLinkedList(int number, int start, int distance)
    {
        SinglyLinkedList<String> list = new SinglyLinkedList<String>();
        //采用单链表存储约瑟夫环的元素，元素类型是字符串
        for (int i=number-1; i>=0; i--)
            list.insert(0, (char)('A'+i)+"");
        System.out.print("约瑟夫环("+number+","+start+","+distance+")，");
        System.out.println(list.toString());

        int i = start-1;                              //计数起始位置
        while (list.length()>1)
        {
            i = (i+distance-1) % list.length();
            System.out.print("删除"+list.remove(i).toString()+"，");
            System.out.println(list.toString());
        }
        System.out.println("被赦免者是"+list.get(0).toString());
    }

    public static void main(String args[])
    {
        new Josephus_SinglyLinkedList(5,1,2);
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
