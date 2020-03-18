package chapter10.telephone;

/**
 * Created by lili on 2017/7/1.
 */
//【例10.1】  电话簿。
//②	TelephoneBookTreeSet类实现电话簿的存储和管理。

//自定义树集合类，继承TreeSet<Friend>类，存储电话簿中的Friend对象，提供查找对象功能。
//关联一个对象文件，提供读写对象文件功能。
//其中查找、读写文件等算法适用于基于迭代器的所有集合。

import java.util.*;
import java.io.*;

public class PhoneBookTreeSet extends TreeSet<Friend>
{
    private String filename;                          //文件名

    public PhoneBookTreeSet(String filename)          //构造方法，参数指定文件名
    {
        super();                                      //构造空集，new TreeSet<Friend>()，默认由Comparable接口提供排序规则
        this.filename = filename;
        this.readFromFile();                          //从指定文件中读取对象添加到集合中
    }

    private void readFromFile()                       //从指定文件中读取对象添加到集合中
    {
        try
        {   FileInputStream fin=new FileInputStream(this.filename);  //文件字节输入流
            ObjectInputStream objin=new ObjectInputStream(fin);      //对象字节输入流
            while (true)                              //输入流未结束时
                try
                {
                    this.add((Friend)objin.readObject());  //读取一个对象添加到集合
                }
                catch (Exception e)                   //捕获ClassNotFoundException和EOFException异常
                {   break;
                }
            objin.close();                            //先关闭对象流
            fin.close();                              //再关闭文件流
//            System.out.println();
        }
        catch (IOException ioe){}                     //指定文件不存在时，集合为空
    }

    public void writeToFile()                         //将集合中所有对象写入指定文件，若文件不存在，创建文件
    {
        if (!this.isEmpty())
            try
            {   FileOutputStream fout=new FileOutputStream(this.filename); //文件字节输出流
                ObjectOutputStream objout=new ObjectOutputStream(fout);    //对象字节输出流
                Iterator<Friend> it = this.iterator();
                while (it.hasNext())
                {
                    Friend f =it.next();
                    System.out.println(f.name+" "+(int)f.name.charAt(0)+" "+f.phonecode);
                    objout.writeObject(f);    //写入一个对象
//              	objout.writeObject(it.next());    //写入一个对象
                }
                objout.close();
                fout.close();
                System.out.println();
            }
            catch (IOException ioe){}
    }

    //查找x对象，返回首次出现的对象，由比较器c指定比较规则
    public Friend search(Friend x, Comparator<Friend> c)
    {
        Iterator<Friend> it = this.iterator();
        while (it.hasNext())                          //未找到且有后继元素时迭代
        {
            Friend f = it.next();
            if (c.compare(f,x)==0)                    //由比较器c指定比较规则
                return f;
        }
        return null;                                  //未找到时返回null，此时比较了所有元素
    }
}
