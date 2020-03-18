package chapter10.telephone;

/**
 * Created by lili on 2017/7/1.
 */
//【例10.1】  电话簿。
//①	Friend类表示电话簿中的对象。

public class Friend implements java.lang.Comparable<Friend>, java.util.Comparator<Friend>, java.io.Serializable  //实现可比较和序列化接口
{
    String name, phonecode;                           //姓名、电话号码，缺省权限，当前包可见

    public Friend(String name, String phonecode)      //构造方法，指定姓名和电话号码
    {
        this.name = name;
        this.phonecode = phonecode;
    }
    public String toString()
    {
        return this.name+"，"+this.phonecode;
    }
    public Object[] toArray()                         //返回包含对象所有成员变量的数组
    {
        Object[] vars = new Object[2];
        vars[0] = this.name;
        vars[1] = this.phonecode;
        return vars;
    }
    public String index()                             //索引函数
    {
        return this.name.substring(0,1);              //以姓氏为索引
    }
    public int compareTo(Friend f)                    //比较两个对象大小，实现Comparable接口
    {                                                 //指定按姓名、电话号码次序排序
        if (this.name.compareTo(f.name)!=0)
            return this.name.compareTo(f.name);       //比较姓名字符串
        return this.phonecode.compareTo(f.phonecode); //姓名相同时（同一人）再比较电话号码
    }
    public int compare(Friend f1, Friend f2)          //比较两个对象大小，实现Comparator接口
    {
        return f1.name.compareTo(f2.name);            //比较姓名
    }
}

//CodeComparator类实现比较器接口，提供Friend按电话号码比较的规则
class CodeComparator implements java.util.Comparator<Friend>
{
    public int compare(Friend f1, Friend f2)
    {
        return f1.phonecode.compareTo(f2.phonecode);  //比较电话号码字符串
    }
}
//IndexComparator类实现比较器接口，提供Friend按姓氏比较的规则
class IndexComparator implements java.util.Comparator<Friend>
{
    public int compare(Friend f1, Friend f2)
    {
        return f1.index().compareTo(f2.index());      //比较姓氏字符串
    }
}

/*
程序设计说明如下。
 *若两对象仅比较姓名，声明如下，则电话簿中不能添加一个人的两个电话号码
    public int compareTo(Friend f)                         //比较两人姓名
    {                                                      //指定排序的自然次序
        return this.name.compareTo(f.name);
    }

//以下方法未用
    public boolean equals(Object obj)                      //覆盖Object类中方法
    {
        if (this==obj)
        	return true;
        if (obj instanceof Friend)                         //判断当前对象是否属于Friend类
        {
            Friend f = (Friend)obj;
            return this.name.equals(f.name) && this.phonecode.equals(f.phonecode);
        }
        return false;
    }
*/


