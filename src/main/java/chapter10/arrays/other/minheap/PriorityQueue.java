package chapter10.arrays.other.minheap;

import chapter4.queue.QQueue;

/**
 * Created by lili on 2017/7/1.
 */
//4.3   优先队列
//10.3.3   贪心法  4.堆及其应用
//优先队列类，实现队列接口，使用最小堆存储队列元素，元素按优先级升序排列
public class PriorityQueue<T extends Comparable<T>> implements QQueue<T>
{
    private MinHeap<T> minheap;                       //使用最小堆存储队列元素

    public PriorityQueue()                            //构造空队列
    {
        this.minheap = new MinHeap<T>();              //创建默认容量的空最小堆
    }

    public boolean isEmpty()                          //判断队列是否空，若空返回true
    {
        return minheap.isEmpty();
    }

    public void enqueue(T x)                          //元素x入队，空对象不能入队
    {
        minheap.insert(x);
    }

    public T dequeue()                                //出队，返回队头元素，若队列空返回null
    {
        return minheap.removeMin();                   //返回最小堆的根元素，删除根结点
    }

    public String toString()                          //返回队列所有元素的描述字符串
    {
        return minheap.toString();
    }
}
