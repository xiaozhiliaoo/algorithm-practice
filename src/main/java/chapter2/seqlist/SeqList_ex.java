package chapter2.seqlist;

/**
 * Created by lili on 2017/7/1.
 */
//2.2   线性表的顺序表示和实现

public class SeqList_ex
{
    public static Integer[] random(int n)              //返回产生n个随机数的数组
    {
        Integer[] elements = new Integer[n];
        for (int i=0; i<n; i++)
            elements[i] = new Integer((int)(Math.random()*100));   //产生随机数
        return elements;
    }

    public static void main(String args[])
    {
        //顺序表
/*        SeqList<String> lista = new SeqList<String>(4);    //执行默认构造方法
        for (int i=5; i>=0; i--)
            lista.insert(i, new String((char)('A'+i)+"")); //扩容
        System.out.println("lista: "+lista.toString());
        SeqList<String> listb = new SeqList<String>(lista);//执行拷贝构造方法
        System.out.println("listb: "+listb.toString());
        lista.set(3, new String((char)(lista.get(3).charAt(0)+32)+""));
        lista.remove(0);
        lista.remove(3);
        lista.remove(10);                                  //序号越界，没删除
        System.out.println("lista: "+lista.toString());
        System.out.println("listb: "+listb.toString());
        //由于拷贝构造方法实现为数组深拷贝，对象浅拷贝，且因Integer是常量对象，new 引用另一个对象，
        //上述语句结果只能说明两个顺序表对象分别有各自的数组，各数组元素可引用不同对象，
        //但不能说明引用同一个对象导致的数据修改错误
*/
        //深拷贝与比较相等
        SeqList<Integer> list1 = new SeqList<Integer>();   //空表
        System.out.println("list1: "+list1.toString());
        SeqList<Integer> list2 = new SeqList<Integer>();   //空表
        System.out.println("list2: "+list2.toString());
        System.out.println("list1.equals(list2)? "+list1.equals(list2));

        list1 = new SeqList<Integer>(random(5));
        System.out.println("list1: "+list1.toString());
        list2 = new SeqList<Integer>(list1);               //拷贝构造方法
        System.out.println("list2: "+list2.toString());
        System.out.println("list1.equals(list2)? "+list1.equals(list2));

        System.out.println("list1: "+list1.toString());
        list2.set(0, new Integer(list1.get(0).intValue()+100));
        list2.remove(list2.length()-1);                    //删除最后一个元素
        list2.remove(100);                                 //序号越界，没删除
        System.out.println("list2: "+list2.toString());
        System.out.println("list1.equals(list2)? "+list1.equals(list2));
    }
}
/*
程序运行结果如下：
//（1） 浅拷贝
lista: (A, F, B, E, C, D)
listb: (A, F, B, E, C, D)
lista: (F, B, e, D)
Exception in thread "main" java.lang.NullPointerException
	at SeqList.toString(SeqList.java:55)
	at SeqList_ex.main(SeqList_ex.java:30)

//（2） 深拷贝，不能说明深拷贝的正确性，因String、Integer是最终变量
lista: (A, F, B, E, C, D)
listb: (A, F, B, E, C, D)
lista: (F, B, e, D)
listb: (A, F, B, E, C, D)

//深拷贝与比较相等
list1: ()
list2: ()
list1.equals(list2)? true
list1: (16, 14, 81, 74, 68)
list2: (16, 14, 81, 74, 68)
list1.equals(list2)? true
list1: (16, 14, 81, 74, 68)
list2: (116, 14, 81, 74)
list1.equals(list2)? false

*/

/*
泛型类问题讨论:
程序调试情况如下：
        SinglyLinkedList<Object> list3 = new SinglyLinkedList<Object>();
//        SortedSinglyLinkedList<Object> list4 = new SortedSinglyLinkedList<Object>();
                                         //编译错，Object类不能作为E的实际参数，没有实现实现Comparable<E>接口
        SortedSinglyLinkedList<Integer> list4 = new SortedSinglyLinkedList<Integer>();
//        list4.insert(new Object());     //编译错，参数类型不匹配
*/
