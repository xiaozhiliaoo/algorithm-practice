package chapter10.iterator;

/**
 * Created by lili on 2017/7/1.
 */
//10.2   实现迭代器
//import java.util.Iterator;                       //Java迭代器接口

//抽象集合类，实现java.lang.Iterable可迭代接口，为所有集合提供迭代方式的遍历算法
public abstract class AbstractCCollection<T> implements Iterable<T>
//public abstract class AAbstractList<T> implements java.util.Collection<T>
{
    public abstract java.util.Iterator<T> iterator();      //获得迭代器对象，抽象方法

    public String toString()                               //返回集合所有元素的字符串描述
    {
        java.util.Iterator<T> it = this.iterator();        //it是一个迭代器对象
        String str="(";
        while (it.hasNext())                               //若有后继元素
        {
            str += it.next().toString();                   //添加后继元素字符串
            if (it.hasNext())
                str += ", ";
        }
        return str+")";
    }

    //习题10
//    boolean contains(Object obj);               	//判断当前集合是否包含元素obj
    public boolean contains(T key)                //判断集合是否包含关键字为key元素，若包含返回true
    {
        if (key!=null)
        {
            java.util.Iterator<T> it = this.iterator();
            while (it.hasNext())
                if (key.equals(it.next()))
                    return true;
        }
        return false;
    }

    public abstract int length();                          //返回集合元素个数，抽象方法
//    int size();                               	//返回当前集合的元素个数

    public Object[] toArray()                              //返回包含当前集合中所有元素的数组
    {
        Object[] temp = new Object[this.length()];
        java.util.Iterator<T> it = this.iterator();        //迭代器对象
        int i=0;
        while (it.hasNext())                               //遍历各元素
            temp[i++]=it.next();
        return temp;
    }
}
/*
    public abstract boolean isEmpty();                     //判断集合是否空，抽象方法
    public abstract boolean add(T x);                         	//增加元素x
    boolean remove(Object obj);                 	//删除首次出现的元素obj
    void clear();                             	//删除当前集合的所有元素
    public <T> T[] toArray(T[] a)             	//返回包含当前集合中所有元素的数组
        //以下方法描述集合运算，参数是另一个集合
    boolean containsAll(Collection<?> c);
                           	//判断当前集合是否包含集合c的所有元素，即判断c是否是子集
    boolean addAll(Collection<? extends T> c);   	//增加集合c中的所有元素，集合并运算
    boolean removeAll(Collection<?> c);       //删除那些也包含在集合c中的元素，集合差运算
    boolean retainAll(Collection<?> c);          	//仅保留那些也包含在集合c中的元素

*/

