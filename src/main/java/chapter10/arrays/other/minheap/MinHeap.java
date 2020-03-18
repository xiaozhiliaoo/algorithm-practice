package chapter10.arrays.other.minheap;

/**
 * Created by lili on 2017/7/1.
 */
//10.3.3   贪心法

import java.util.Comparator;
//最小堆，T必须实现Comparable<T>接口，支持对象比较大小
public class MinHeap<T extends java.lang.Comparable<T>>
{
    private Comparable [] element;                         //最小堆元素数组
    private int len;                                       //最小堆元素个数
    private Comparator comparator=null;                    //比较器对象

    public MinHeap(int size)                               //构造方法，创建容量为size的空堆
    {
        this.element = new Comparable[size];
        this.len = 0;
    }
    public MinHeap()                                       //默认构造方法，创建默认容量的空堆
    {
        this(64);
    }
    public MinHeap(T[] element)                            //构造方法，参数数组指定堆初值，深拷贝
    {
        this(element.length*2);
        for (int i=0; i<element.length; i++)
            this.insert(element[i]);                       //数组元素插入最小堆
    }
    public MinHeap(int size, Comparator comparator)        //构造空堆，指定比较器对象
    {
        this(size);
        this.comparator = comparator;
    }
    public MinHeap(Comparator comparator)
    {
        this(64, comparator);
    }
    public MinHeap(T[] element, Comparator comparator)
    {
        this(element.length, comparator);
        for (int i=0; i<element.length; i++)
            this.insert(element[i]);
    }

    public boolean isEmpty()                     //判断是否空堆，若空返回true
    {
        return this.len==0;
    }
    public int length()                          //返回最小堆元素个数
    {
        return this.len;
    }
    public String toString()                     //返回最小堆所有元素的描述字符串，形式为“(,)”
    {
        String str="(";
        if (this.len>0)
            str += this.element[0].toString();
        for (int i=1; i<this.len; i++)
            str += ", "+this.element[i].toString();
        return str+") ";
    }

    public void insert(T x)                      //将x插入到最小堆中，不能插入null
    {
        if (x==null)
            return;
        if (this.len==element.length)                      //若数组满，则扩充堆容量
        {
            Comparable[] temp = this.element;
            this.element = new Comparable[temp.length*2];
            for (int j=0; j<temp.length; j++)
                this.element[j] = temp[j];
        }
        this.element[this.len] = x;                        //在最小堆最后插入元素
        this.len++;
        for (int i=this.len/2-1; i>=0; i-=2)               //自下而上调整各子二叉树为最小堆
            sift(i);
//        System.out.println(this.toString()+" ");
    }

    private void sift(int begin)                           //将以begin为根的子树调整成最小堆
    {
        if (comparator==null)                              //若比较器为空对象，则默认按Comparable接口的方法比较大小
        {
            int i=begin, j=2*i+1;                          //i为子树的根，j为i结点的左孩子
            Comparable temp=this.element[i];               //获得第i个元素的值
            while (j<this.len)                             //沿较小值孩子结点向下筛选
            {
                if (j<this.len-1 && this.element[j].compareTo(this.element[j+1])>0)  //（改成<为最大堆）
                    j++;                                   //j为左右孩子的较小者
                if (temp.compareTo(this.element[j])>0)     //若父母结点值较大（改成<为最大堆）
                {
                    this.element[i]=this.element[j];       //孩子结点中的较小值上移
                    i=j;                                   //i、j向下一层
                    j=2*i+1;
                }
                else break;
            }
            this.element[i]=temp;                          //当前子树的原根值调整后的位置
        }
        else sift(begin, comparator);
    }
    //将以begin为根的子树调整成最小堆，算法同上，由比较器comparator提供堆元素比较大小的方法
    private void sift(int begin, Comparator comparator)
    {
        int i=begin, j=2*i+1;
        Comparable<T> temp=this.element[i];
        while (j<this.len)
        {
            if (j<this.len-1 && comparator.compare(this.element[j],this.element[j+1])>0)
                j++;
            if (comparator.compare(temp,this.element[j])>0)
            {
                this.element[i]=this.element[j];
                i=j;
                j=2*i+1;
            }
            else break;
        }
        this.element[i]=temp;
    }

    public T removeMin()                         //返回最小值元素（根），删除根结点并调整为最小堆
    {
        if (this.len==0)
            return null;
        T x = (T)this.element[0];                         //获得最小堆根结点元素
        this.element[0] = this.element[this.len-1];       //将最后位置元素移到根，即删除根
        this.len--;
        sift(0);                                          //调整根结点值到最小堆的合适位置
        return x;
    }
}

