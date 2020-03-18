package chapter10.iterator;

/**
 * Created by lili on 2017/7/1.
 */
//10.2   实现迭代器
//import java.util.Iterator;                       //Java迭代器接口

//抽象列表类，继承AAbstractCollection<T>类，为线性表提供迭代方式的遍历算法
public abstract class AbstractLList<T> extends AbstractCCollection<T>
{
    public boolean equals(Object obj)                      //比较两个集合对象是否相等
    {
        if (obj == this)
            return true;
        if (!(obj instanceof AbstractLList))
            return false;
        java.util.Iterator<T> it1 = this.iterator();
        java.util.Iterator<T> it2 = ((AbstractLList<T>)obj).iterator();
        while (it1.hasNext() && it2.hasNext())
            if (!(it1.next().equals(it2.next())))          //比较集合元素，本书声明的集合中没有null对象
                return false;
        return !it1.hasNext() && !it2.hasNext();           //两个空集合也相等
    }
}

