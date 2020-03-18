package chapter4.queue;

/**
 * Created by lili on 2017/7/1.
 */
//4.2.2  顺序循环队列
public class SeqQueue<T> implements QQueue<T>              //顺序循环队列类，实现队列接口
{
    private Object element[];                              //存储队列数据元素的数组
    private int front, rear;                               //front、rear分别为队列头尾下标

    public SeqQueue(int length)                            //构造容量为length的空队列
    {
        if (length<64)
            length=64;                                     //设置队列数组容量最小值
        this.element = new Object[Math.abs(length)];
        this.front = this.rear = 0;                        //设置空队列
    }
    public SeqQueue()                                      //构造默认容量的空队列
    {
        this(64);
    }

    public boolean isEmpty()                               //判断队列是否空，若空返回true
    {
        return this.front==this.rear;
    }

    public void enqueue(T x)                               //元素x入队，空对象不能入队
    {
        if (x==null)
            return;                                         //空对象不能入队
        if (this.front==(this.rear+1)%this.element.length) //当队列满时，扩充容量
        {
            Object[] temp = this.element;
            this.element = new Object[temp.length*2];      //重新申请一个容量更大的数组
            int j=0;
            for (int i=this.front;  i!=this.rear;  i=(i+1) % temp.length) //按照队列元素次序复制数组元素
                this.element[j++] = temp[i];
            this.front = 0;
            this.rear = j;
        }
        this.element[this.rear] = x;
        this.rear = (this.rear+1) % this.element.length;
    }

    public T dequeue()                           //出队，返回队头元素，若队列空返回null
    {
        if (isEmpty())                           //若队列空返回null
            return null;
        T temp = (T)this.element[this.front];    //取得队头元素
        this.front = (this.front+1) % this.element.length;
        return temp;
    }

    public String toString()                     //返回队列所有元素的描述字符串，形式为“(,)”，按照队列元素次序
    {
        String str="(";
        if (!isEmpty())
        {
            str += this.element[this.front].toString();
            int i=(this.front+1) % this.element.length;
            while(i!=this.rear)
            {
                str += ", "+this.element[i].toString();
                i=(i+1) % this.element.length;
            }
        }
        return str+")";
    }
}

