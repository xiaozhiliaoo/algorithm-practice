package chapter3.stringbuffer;

/**
 * Created by lili on 2017/7/1.
 */
//3.2.3 变量串的操作实现
//1.  StringBuffer类声明

public final class MyStringBuffer implements java.io.Serializable //字符串类
{
    private char[] value;                                  //字符数组，私有成员变量
    private int len;                                       //串长度

    public MyStringBuffer(int size)                        //构造指定容量的空串
    {
        this.value = new char[size<32 ? 32 : size];
        this.len = 0;
    }

    public MyStringBuffer()                                //以默认容量构造空串
    {
        this(32);
    }

    public MyStringBuffer(String str)                      //以字符串常量构造串对象
    {
        this(str.length()*2);
        this.append(str);
    }

    public int length()                                    //返回字符串长度
    {
        return this.len;                                  //区别，value.length是数组容量
    }
    public void setLength(int len)                         //设置当前字符串长度为len
    {
        this.len = len;
    }

    public synchronized char charAt(int i)                 //返回第i（i≥0）个字符
    {
        if (i<0 || i>=this.len)
            throw new StringIndexOutOfBoundsException(i);
        return this.value[i];
    }
    public void setCharAt(int i, char ch)                  //设置第i个字符为ch
    {
        if (i<0 || i>=this.len)
            throw new StringIndexOutOfBoundsException(i);
        this.value[i] = ch;
    }

    public synchronized String toString()
    {
        return new String(this.value, 0, this.len);        //以字符数组value从0至len元素构造String串
    }

    //2.  插入串
    public synchronized MyStringBuffer insert(int i, MyStringBuffer str)  //在第i个字符处插入str串
    {
        if (i<0)  i=0;                                     //序号容错
        if (i>this.len) i=this.len;
        char temp[]=this.value;
        if (this.value.length-this.len < str.len)          //若当前串数组空间不足，则扩充容量
        {
            this.value = new char[this.value.length+str.len*2]; //重新申请字符数组空间
            for (int j=0; j<i; j++)                        //复制当前串前i-1个字符
                this.value[j] = temp[j];
        }
        for (int j=i; j<this.len; j++)
            this.value[str.len+j] = temp[j];               //从i开始向后移动n个字符
        for (int j=0; j<str.len; j++)                      //复制字符串str
            this.value[i+j] = str.value[j];
        this.len += str.len;
        return this;
    }

    public synchronized MyStringBuffer insert(int i, String str)     //在第i个字符处插入str串
    {
        if (i<0)  i=0;                                     //序号容错
        if (i>this.len) i=this.len;
//        if (i<0 || i>len)
//            throw new StringIndexOutOfBoundsException(i);
        char temp[]=this.value;
        if (this.value.length-this.len < str.length())     //若当前串的数组空间不足，则扩充容量
        {
            this.value = new char[this.value.length+str.length()*2]; //重新申请字符数组空间
            for (int j=0; j<i; j++)                        //复制当前串前i-1个字符
                this.value[j] = temp[j];
        }
        for (int j=this.len-1; j>=i; j--)
            this.value[str.length()+j] = temp[j];          //从i开始向后移动n个字符
        for (int j=0; j<str.length(); j++)                 //复制字符串str
            this.value[i+j] = str.charAt(j);
        this.len += str.length();
        return this;
    }

    public synchronized MyStringBuffer insert(int i, boolean b)      //在i处插入变量值转换成的串
    {
        return this.insert(i, b ? "true" : "false");
    }
    public synchronized MyStringBuffer append(String str)            //添加指定串
    {
        return this.insert(this.len, (str==null) ? "null" : str);
    }

    //3.  删除子串
    public synchronized MyStringBuffer delete(int begin, int end)  //删除从begin到end-1的子串
    {
        if (begin < 0)                                     //序号容错
            begin=0;                                       //从串首开始删除
        if (end > this.len)
            end = this.len;                                //删除至串尾的子串
        if (begin > end)
            throw new StringIndexOutOfBoundsException(end - begin);
        for(int i=0; i<this.len-end; i++)                  //从end开始至串尾的子串向前移动
            this.value[begin+i] = this.value[end+i];
        this.len -= end-begin;
        return this;
    }

    //习题3
    public MyStringBuffer reverse()                        //将当前串逆转并返回
    {
        for (int i=0; i<this.len/2; i++)
        {
            char temp = value[i];
            value[i] = value[this.len-i-1];
            value[this.len-i-1] = temp;
        }
        return this;
    }

}

/*
习题3，未完成
public String substring(int begin)                //返回从begin（≥0）开始到串尾的子串
public String substring(int begin, int end)  //返回从begin开始到end-1的子串
public StringBuffer deleteCharAt(int i) //删除第i个字符

    //将串中从begin到end-1的子串替换为str串
    public MyStringBuffer replace(int begin, int end, String str)
    {
        if (begin < 0)
            throw new StringIndexOutOfBoundsException(begin);
        if (begin > this.len)
            throw new StringIndexOutOfBoundsException("begin > length()");
        if (begin > end)
            throw new StringIndexOutOfBoundsException("begin > end");
        if (end > this.len)
            end = this.len;

        System.arraycopy(value, end, value, begin + len, count - end);
        str.getChars(value, begin);
        count = newCount;
        return this;

        char temp[]=this.value;
        if (this.value.length-this.len < str.length() - (end - begin))     //若当前串的数组空间不足，则扩充容量
        {
            this.value = new char[this.value.length+str.length()*2]; //重新申请字符数组空间
            for (int j=0; j<i; j++)                        //复制当前串前i-1个字符
                this.value[j] = temp[j];
        }
        for (int j=this.len-1; j>=i; j--)
            this.value[str.length()+j] = temp[j];          //从i开始向后移动n个字符
        for (int j=0; j<str.length(); j++)                 //复制字符串str
            this.value[i+j] = str.charAt(j);
        this.len += str.length();
        return this;
    }

*/
