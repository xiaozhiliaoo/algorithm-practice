package chapter2.seqlist;

/**
 * Created by lili on 2017/7/1.
 */
//【例2.1】使用顺序表类SeqList求解约瑟夫环问题。

public class Josephus
{
    //创建约瑟夫环并求解，参数指定环长度、起始位置、计数
    public Josephus(int number, int start, int distance)
    {
        SeqList<String> list = new SeqList<String>(number);
        //采用顺序表存储约瑟夫环的元素，元素类型是字符串，构造方法参数指定顺序表容量
        for (int i=0; i<number; i++)
            list.append((char)('A'+i)+"");                 //添加字符串对象
        System.out.print("约瑟夫环("+number+","+start+","+distance+")，");
        System.out.println(list.toString());               //输出顺序表的描述字符串

        int i = start;                                     //计数起始位置
        while (list.length()>1)                            //多于一个对象时循环
        {
            i = (i+distance-1) % list.length();            //计数按循环规律变化，顺序表可看作是环形结构
            System.out.print("删除"+list.remove(i).toString()+"，");  //删除指定位置对象
            System.out.println(list.toString());
        }
        System.out.println("被赦免者是"+list.get(0).toString());
    }

    public static void main(String args[])
    {
        new Josephus(5,0,2);
    }
}

/*
程序运行结果如下：
约瑟夫环(5,0,2)，(A, B, C, D, E)
删除B，(A, C, D, E)
删除D，(A, C, E)
删除A，(C, E)
删除E，(C)
被赦免者是C
*/
//注意：泛型<E>的实际参数只能是类，不能是基本类型char、int等

