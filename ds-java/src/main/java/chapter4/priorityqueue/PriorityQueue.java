package chapter4.priorityqueue;

import chapter2.singlylinkedlist.SortedSinglyLinkedList;
import chapter4.queue.QQueue;

/**
 * Created by lili on 2017/7/1.
 */
//4.3   优先队列

//优先队列类，实现队列接口，使用排序单链表存储队列元素，元素按优先级升序排列
public class PriorityQueue<T extends Comparable<T>> implements QQueue<T>
{
    private SortedSinglyLinkedList<T> list;                //使用排序单链表存储队列元素

    public PriorityQueue()                                 //构造空队列
    {
        this.list = new SortedSinglyLinkedList<T>();
    }
    public boolean isEmpty()                               //判断队列是否空，若空返回true
    {
        return list.isEmpty();
    }

    public void enqueue(T x)                               //元素x入队，空对象不能入队
    {
        list.insert(x);                                    //根据元素大小插入在单链表适当位置
    }

    public T dequeue()                                     //出队，返回队头元素，若队列空返回null
    {
        return list.remove(0);                             //返回队头元素，删除队头结点
    }

    public String toString()                               //返回队列所有元素的描述字符串
    {
        return list.toString();
    }
}

