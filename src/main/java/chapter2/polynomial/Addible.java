package chapter2.polynomial;

/**
 * Created by lili on 2017/7/1.
 */
//2.4   线性表的应用：多项式的表示及运算
//2.4.1   一元多项式的表示及运算

public interface Addible<T>                      //可相加接口
{
    public void add(T t);                        //+=加法，约定两元素相加规则
    public boolean removable();                  //约定删除元素条件
}

