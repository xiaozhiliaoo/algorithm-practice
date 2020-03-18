//集合接口
package chapter1;
public interface SSet<T>                         //集合接口，T是泛型参数，指定元素类型
{
    boolean isEmpty();                           //判断集合是否为空
    int count();                                 //返回集合的元素个数
    String toString();                           //返回集合中所有元素的描述字符串
    T search(T key);                             //查找，返回关键字为key元素
    boolean contain(T x);                        //判断集合是否包含元素x，即元素x是否属于集合
    void add(T x);                               //增加元素x
    void remove(T x);                            //删除首次出现的元素x
    void removeAll();                            //删除集合所有元素

    //以下方法描述集合运算，参数是另一个集合
    boolean equals(SSet<T> s);                   //比较当前集合与集合s是否相等
    void containAll(SSet<T> s);                  //判断当前集合是否包含集合s中的所有元素，s是否子集
    void addAll(SSet<T> s);                      //增加集合s中的所有元素，集合并
    void removeAll(SSet<T> s);                   //删除那些也包含在集合s中的元素，集合差
    void retainAll(SSet<T> s);                   //仅保留那些也包含在集合s中的元素
}
//void print();                           	//输出集合中所有元素
