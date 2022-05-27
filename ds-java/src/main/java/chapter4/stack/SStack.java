package chapter4.stack;

/**
 * Created by lili on 2017/7/1.
 */
//4.1.1   栈抽象数据类型

//栈接口，描述栈抽象数据类型，泛型参数T表示数据元素的数据类型
public interface SStack<T>             //栈接口，栈抽象数据类型
{
    boolean isEmpty();                 //判断栈是否为空
    void push(T x);                    //元素x入栈
    T pop();                           //出栈，返回栈顶元素
    T get();                           //取栈顶元素，未出栈
}