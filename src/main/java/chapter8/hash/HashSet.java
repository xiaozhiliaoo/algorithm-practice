package chapter8.hash;

import chapter10.iterator.AbstractCCollection;
import chapter2.singlylinkedlist.SinglyLinkedList;

/**
 * Created by lili on 2017/7/1.
 */
//8.3   散列
//8.3.4   构造链地址法的散列表
//采用链地址法的散列表类，包括插入、删除、查找、遍历等操作。
//public class HashSet<T>                                  //采用链地址法的散列表类，8.3 散列
public class HashSet<T> extends AbstractCCollection<T>     //第10章，10.2 实现迭代器
{
    private SinglyLinkedList<T>[] table;                   //散列表，同义词单链表对象数组
//    private int count;                                       //散列表元素个数

    public HashSet(int length)                             //构造指定容量的散列表
    {
        this.table = new SinglyLinkedList[Math.abs(length)];
        for (int i=0; i<table.length; i++)
            table[i] = new SinglyLinkedList<T>();          //构造空单链表
//        this.len=0;
    }
    public HashSet()                                       //构造默认容量的散列表
    {
        this(97);                                          //默认97是100以内的最大素数
    }

    //散列函数，确定关键字为key元素的散列地址。若x==null，将抛出空对象异常
    private int hash(T x)
    {
        int key = Math.abs(x.hashCode());                  //每个对象的hashCode()方法返回int
        return key % table.length;                         //除留余数法，除数是散列表长度
    }

    public void insert(T x)                                //插入x元素
    {
        if (search(x)==null)                               //不插入关键字重复元素
            table[hash(x)].insert(0, x);                   //插入作为同义词单链表的第一个结点

//        this.len++;
    }
    public void remove(T x)                                //删除x元素
    {
        table[hash(x)].remove(x);                          //在同义词单链表中删除x元素结点
//        this.len--;
    }

    public T search(T key)                            //返回查找到的关键字为key元素，若查找不成功返回null
    {
        return table[hash(key)].search(key);          //在同义词单链表中查找关键字为key元素
    }
    public boolean contain(T key)                     //判断散列表是否包含关键字为key元素
    {
        return this.search(key)!=null;
    }
    public String toString()                          //返回散列表所有元素的描述字符串
    {
        String str="";
        for (int i=0; i<table.length; i++)            //遍历各同义词单链表
            if (!table[i].isEmpty())
                str += table[i].toString()+"\n";
        return str;
    }

    //第10章，10.2 实现迭代器
    public java.util.Iterator<T> iterator()                //返回Java迭代器对象
    {
        return new HashSetIterator();
    }

    private class HashSetIterator implements java.util.Iterator<T> //私有内部类，实现迭代器接口
    {
        private int index=0;                               //当前单链表序号
        private java.util.Iterator<T> it;                  //单链表迭代器对象

        public HashSetIterator()
        {
            this.index=0;
            this.it=table[index].iterator();               //获得第一条单链表的迭代器对象
        }
        public boolean hasNext()                           //若有后继元素，返回true
        {
            if (this.it.hasNext())
                return this.it.hasNext();                  //
            int i=this.index+1;
            while (i<table.length)                         //len散列表元素个数
            {
                if (table[i].iterator().hasNext())
                    return true;    //获得下一条单链表的迭代器对象
                i++;
            }
            return false;
        }

        public T next()                                    //返回后继元素
        {
            if (this.it.hasNext())
                return this.it.next();                     //返回当前单链表当前结点的后继元素
            this.index++;
            while (this.index<table.length)                             //len散列表元素个数
            {
                this.it=table[this.index].iterator();               //获得下一条单链表的迭代器对象
                if (this.it.hasNext())
                    return this.it.next();                     //
                this.index++;
            }
            return null;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();  //不支持该操作，抛出异常
        }
    }

    public int length()                          //返回集合元素个数，抽象方法
    {
        return 0;
    }

}

