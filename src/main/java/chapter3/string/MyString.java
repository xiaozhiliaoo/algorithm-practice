package chapter3.string;

/**
 * Created by lili on 2017/7/1.
 */
//3.2.2  常量串的基本操作。//JDK 1.6

public final class MyString implements Comparable<MyString>,java.io.Serializable
{
    private final char[] value;                  //字符数组，私有最终变量，只能赋值一次

    public MyString()                            //构造一个空串??
    {
        this.value = new char[0];
    }
    public MyString(java.lang.String original)   //由字符串常量构造串对象
    {
        this.value = original.toCharArray();     //获得字符串中的字符数组
    }

    public MyString(char[] value, int begin, int count)    //以value数组中从begin开始的count个字符构造串对象
    {
//        this.value = value;                    //数组引用赋值
        this.value = new char[count];            //当value==null时，Java抛出空对象异常
        for (int i=begin; i<begin+count; i++)    //复制数组
            this.value[i] = value[i];
//        java.lang.System.arraycopy(value,0,this.value,0,value.length);  //复制数组，功能for语句
//        this.value = java.util.Arrays.copyOf(value,value.length);       //复制数组，包括申请数组空间
    }

    public MyString(char[] value)                //以value数组中字符构造串对象
    {
        this(value, 0, value.length);
    }

    public MyString(MyString str)                //拷贝构造方法，复制对象
    {
        this(str.value);
    }

    public int length()                          //返回字符串的长度
    {
        return this.value.length;
    }

    public char charAt(int i)                    //返回第i（i≥0）个字符
    {
        if (i<0 || i >= this.value.length)
            throw new StringIndexOutOfBoundsException(i);  //抛出字符串序号越界异常
        return this.value[i];
    }

    public java.lang.String toString()
    {
        return new String(this.value);           //java.lang.String实现为 return this;
    }

    public MyString concat(MyString str)         //返回当前串与指定串str连接生成的新串，不改变当前串
    {
        if (str.length()==0)                     //欲连接的串为空时，返回当前串
            return this;
        char[] buffer = new char[this.value.length+str.length()];
        int i;
        for (i=0; i<this.value.length; i++)      //复制当前串
            buffer[i] = this.value[i];
        for (int j=0; j<str.value.length; j++)   //复制指定串str
            buffer[i+j] = str.value[j];
        return new MyString(buffer);             //以字符数组构造串对象
    }

    public MyString substring(int begin, int end)//返回串中序号从begin至end-1的子串
    {
        if (begin<0)
            begin=0;                             //序号容错
        if (end>this.value.length)
            end=this.value.length;
        if (begin==0 && end==this.value.length)
            return this;
        if (begin>end)
            throw new StringIndexOutOfBoundsException(end-begin);
        char[] buffer = new char[end-begin];
        for (int i=0; i<buffer.length; i++)
            buffer[i] = this.value[i+begin];
        return new MyString(buffer);             //以字符数组构造串对象
    }
    public MyString substring(int begin)         //返回串中序号从begin至串尾的子串
    {
        return substring(begin, this.value.length);
    }

    //比较当前串是否与obj引用的串相等 ，覆盖Object类的equals(obj)方法
    public boolean equals(Object obj)
    {
        if (this==obj)
            return true;
        if (obj instanceof MyString)
        {
            MyString str=(MyString)obj;
            if (this.value.length == str.value.length)
            {
                for (int i=0; i<this.value.length; i++)
                    if (this.value[i]!=str.value[i])
                        return false;
                return true;
            }
        }
        return false;
    }

    //比较当前串与str串的大小，返回两者差值，实现Comparable接口
    public int compareTo(MyString str)
    {
        for (int i=0; i<this.value.length && i<str.value.length; i++)
            if (this.value[i]!=str.value[i])
                return this.value[i] - str.value[i];       //返回两串第一个不同字符的差值
        return this.value.length - str.value.length;       //前缀子串，返回两串长度的差值
    }

    //3.3   串的模式匹配
    //3.3.1   朴素的模式匹配（Brute-Force）算法
    //返回模式串pattern在当前串this（目标串target）中从begin开始的首次匹配位置，匹配失败时返回-1
    int count=0;                                           //记载比较次数
    public int indexOf(MyString pattern, int begin)
    {
        if (pattern.length()>0 && this.length()>=pattern.length())
        {                                                  //当目标串比模式串长时进行比较
            int i=begin, j=0;                              //i、j分别为目标串和模式串当前字符的下标
            count=0;
            while (i<this.length())
            {
                if (this.charAt(i)==pattern.charAt(j))     //若当前两字符相等，则继续比较后续字符
                {
                    i++;
                    j++;
                }
                else                                       //否则i、j回溯，进行下一次匹配
                {
                    i=i-j+1;                               //目标串下标i退回到下一个待匹配子串首字符
                    j=0;                                   //模式串下标j退回到0
                }
                count++;
                if (j==pattern.length())                   //一次匹配结束，匹配成功
                    return i-j;                            //返回匹配的子串序号
            }
        }
        return -1;                                         //匹配失败时返回-1
    }
    //返回模式串pattern在当前串中的首次匹配位置，匹配失败时返回-1
    public int indexOf(MyString pattern)
    {
        return this.indexOf(pattern, 0);
    }

    //2.  模式匹配应用
    //返回将当前串中首个与pattern匹配的子串替换成replacement的字符串
    public MyString replaceFirst(MyString pattern, MyString replacement)
    {
        int i=this.indexOf(pattern,0);                     //返回匹配子串的序号，从0开始查找
        if (i==-1)
            return this;                                   //匹配失败时返回当前串
        return this.substring(0,i).concat(replacement).concat(this.substring(i+pattern.length()));//连接3个串
    }

    //返回将当前串中所有与pattern匹配的子串全部替换成replacement的字符串
    public MyString replaceAll(MyString pattern, MyString replacement)
    {
        MyString temp = new MyString(this);                //拷贝构造方法，复制当前串
        int i=this.indexOf(pattern,0);
        while (i!=-1)
        {
            temp = temp.substring(0,i).concat(replacement).concat(temp.substring(i+pattern.length()));
            i=temp.indexOf(pattern, i+replacement.length());//从下一个字符开始再次查找匹配子串
//            i=temp.indexOf(pattern, i+1);                  	//错
        }
        return temp;
    }

    //习题3
    public int indexOf(char ch)                       	   //返回ch字符在当前串中首次出现的序号
    {
        for (int i=0; i<this.value.length; i++)
            if (this.value[i]==ch)
                return i;
        return -1;
    }

    public MyString toLowerCase()                          //将串中的大写字母全部转换成对应的小写字母
    {
        char temp[]=new char[this.value.length];
        for (int i=0; i<this.value.length; i++)
            if (this.value[i]>='A' && this.value[i]<='Z')  //大写字母
                temp[i]=(char)(this.value[i]+'a'-'A');     //转换成对应小写字母
        return new MyString(temp);
    }
    public MyString toUpperCase()                          //将串中的小写字母全部转换成对应的大写字母
    {
        char temp[]=new char[this.value.length];
        for (int i=0; i<this.value.length; i++)
            if (this.value[i]>='a' && this.value[i]<='z')  //小写字母
                temp[i]=(char)(this.value[i]-('a'-'A'));   //转换成对应大写字母
        return new MyString(temp);
    }
    public char[] toCharArray()                            //返回字符数组
    {
        char[] temp = new char[this.value.length];
        for (int i=0; i<temp.length; i++)                  //复制数组
            temp[i] = this.value[i];
        return temp;
    }

    public boolean startsWith(MyString prefix)   //判断prefix是否前缀子串
    {
        if (this.value.length<prefix.value.length)
            return false;
        for (int i=0; i<prefix.value.length; i++)
            if (this.value[i]!=prefix.value[i])
                return false;
        return true;
    }
    public boolean endsWith(MyString suffix)     //判断suffix是否后缀子串
    {
        int j=suffix.value.length-1;
        for (int i=this.value.length-1; i>=0 && j>=0; i--,j--)
            if (this.value[i]!=suffix.value[j])
                return false;
        return j==-1;
    }

    public boolean equalsIgnoreCase(MyString str)     //比较当前串与str串是否相等，忽略字母大小写
    {
        if (this==str)
            return true;
        if (this.value.length==str.value.length)
        {
            for (int i=0; i<this.value.length; i++)
            {
                int c1=this.value[i];
                if (c1>='a' && c1<='z')
                    c1 -= 'a'-'A';
                int c2=str.value[i];
                if (c2>='a' && c2<='z')
                    c2 -= 'a'-'A';
                if (c1!=c2)
                    return false;
            }
            return true;
        }
        return false;
    }

    public int compareToIgnoreCase(MyString str)       //比较两个串大小，忽略字母大小写
    {
        for (int i=0; i<this.value.length && i<str.value.length; i++)
        {
            int c1=this.value[i];
            if (c1>='a' && c1<='z')
                c1 -= 'a'-'A';
            int c2=str.value[i];
            if (c2>='a' && c2<='z')
                c2 -= 'a'-'A';
            if (c1!=c2)
                return this.value[i]-str.value[i];
        }
        return this.value.length-str.value.length;
    }

    public MyString trim()                        	       //返回删除所有空格后的字符串
    {
        char temp[]=new char[this.value.length];
        int j=0;
        for (int i=0; i<this.value.length; i++)
            if (this.value[i]!=' ')
                temp[j++]=this.value[i];
        return new MyString(temp,0,j);                     //以temp数组中从0开始的j个字符构造串对象
    }

}
/*
习题3 未完成
public int indexOf(char ch, int begin)              	//返回ch从begin开始首次出现的序号
public int lastIndexOf(char ch)                   	//返回ch在当前串中最后出现的序号
public int lastIndexOf(char ch, int begin)           	//返回ch从begin开始最后出现的序号
public MyString replace(char old, char newc)      	//将串中所有old字符替换为newc字符
public int lastIndexOf(String pattern)             	//返回pattern最后匹配位置的序号
public int lastIndexOf(String pattern, int begin)
              //返回模式串pattern在当前串中从begin开始向后查找最后一次匹配位置的序号
 * */

