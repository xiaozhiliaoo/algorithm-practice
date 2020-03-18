package chapter4.stack;

/**
 * Created by lili on 2017/7/1.
 */
//4.1.2   顺序栈

public class SeqStack<T> implements SStack<T>              //顺序栈类，实现栈接口
{
    private Object element[];                              //存储栈数据元素的数组
    private int top;                                       //栈顶元素下标

    public SeqStack(int size)                              //构造容量为size的空栈
    {
        this.element = new Object[Math.abs(size)];
        this.top=-1;
    }
    public SeqStack()                                      //构造默认容量的空栈
    {
        this(64);
    }
    public boolean isEmpty()                               //判断栈是否空，若空返回true
    {
        return this.top==-1;
    }

    public void push(T x)                                  //元素x入栈，空对象不能入栈
    {
        if (x==null)
            return;                                         //空对象不能入栈
        if (this.top==element.length-1)                    //若栈满，则扩充栈容量
        {
            Object[] temp = this.element;
            this.element = new Object[temp.length*2];      //重新申请一个容量更大的数组
            for (int i=0; i<temp.length; i++)              //复制数组元素，O(n)
                this.element[i] = temp[i];
        }
        this.top++;
        this.element[this.top] = x;
    }

    public T pop()                                         //出栈，返回栈顶元素，若栈空返回null
    {
        return this.top==-1 ? null : (T)this.element[this.top--];
    }

    public T get()                                         //取栈顶元素，未出栈，若栈空返回null
    {
        return this.top==-1 ? null : (T)this.element[this.top];
    }

    public String toString()            //返回栈所有元素的描述字符串，形式为“(,)”，算法同顺序表
    {
        String str="(";
        if (this.top!=-1)
            str += this.element[this.top].toString();
        for (int i=this.top-1; i>=0; i--)
            str += ", "+this.element[i].toString();
        return str+") ";
    }
}

