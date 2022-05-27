package chapter5.genlist;

/**
 * Created by lili on 2017/7/1.
 */
//5.3   广义表
//5.3.1   广义表抽象数据类型
//3.  广义表抽象数据类型

//广义表接口GGenList，描述广义表抽象数据类型，泛型参数T表示数据元素的数据类型
public interface GGenList<T>
{
    boolean isEmpty();                                     //判断广义表是否空
    int length();                                          //返回广义表长度
    int depth();                                           //返回广义表深度
    GenListNode<T> insert(int i, T x);                     //插入原子x作为第i个元素
    GenListNode<T> insert(int i, GenList<T> glist);        //插入子表作为第i个元素
    void remove(int i);                                    //删除第i个元素
}

//T get(int i);                                //返回第i（i≥0）个元素
//void set(int i, T x);                     //设置第i个元素值为x
//T search(T key);                             //查找，返回首次出现的关键字为key元素
//add??