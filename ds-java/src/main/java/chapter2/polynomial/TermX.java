package chapter2.polynomial;

/**
 * Created by lili on 2017/7/1.
 */
//2.4   线性表的应用：多项式的表示及运算
//2.4.1   一元多项式的表示及运算

//项类，一元多项式的一项，实现可比较接口和可相加接口
public class TermX implements Comparable<TermX>,Addible<TermX>
{
    protected int coef, exp;                               //系数coefficient和指数exponent（可为正、0）

    public TermX(int coef, int exp)                        //构造一项
    {
        this.coef = coef;
        this.exp = exp;
    }
    public TermX(TermX term)                               //拷贝构造方法
    {
        this(term.coef, term.exp);
    }

    //以“系数x^指数”的省略形式构造一元多项式的一项。
    //省略形式说明：当系数为1或-1且指数>0时，省略1，-1只写负号“-”，如x^2、-x^3；
    //当指数为0时，省略x^0，只写系数；当指数为1时，省略^1，只写x。
    public TermX(String termstr)
    {
        if (termstr.charAt(0)=='+')                        //去掉+号
            termstr=termstr.substring(1);
        int i = termstr.indexOf('x');
        if (i==-1)                                         //没有x，即指数为0
        {
            this.coef = Integer.parseInt(termstr);         //获得系数
            this.exp = 0;
        }
        else                                               //有x，x之前为系数，x^之后为指数
        {
            if (i==0)                                      //以x开头，即系数为1
                this.coef = 1;
            else
            {
                String sub=termstr.substring(0,i);         //x之前子中表示系数
                if (sub.equals("-"))                       //系数只有-号，即系数为1
                    this.coef=-1;
                else
                    this.coef = Integer.parseInt(sub);     //获得系数
            }
            i = termstr.indexOf('^');
            if (i==-1)
                this.exp=1;                               //没有^，即指数为1
            else
                this.exp = Integer.parseInt(termstr.substring(i+1));//获得指数
        }
    }

    //返回一元多项式的一项对应的“系数x^指数”的省略形式字符串，省略形式说明同TermX(String)构造方法。
    public String toString()
    {
        String str=this.coef>0 ? "+" : "-";                //系数的符号位
        if (this.exp==0 || this.exp>0 && this.coef!=1 && this.coef!=-1)
            str+=Math.abs(this.coef);                      //系数绝对值，省略系数1
        if (this.exp>0)
            str+="x";                                      //指数为0时，省略x^0，只写系数
        if (this.exp>1)
            str+="^"+this.exp;                             //指数为1时，省略^1，只写x
        return str;
    }

    public int compareTo(TermX term)                       //约定按指数比较两项大小
    {
        if (this.exp == term.exp)                          //比较相等
            return 0;                                      //比较规则与equals(Object obj)不同
        return this.exp<term.exp ? -1 : 1;                 //比较大小，仅比较指数
    }

    public void add(TermX term)                            //加法，＋=运算符作用
    {
        if (this.compareTo(term)==0)
            this.coef += term.coef;
        else
            throw new IllegalArgumentException("两项的指数不同，不能相加。");
    }
    public boolean removable()                             //约定删除元素条件
    {
        return this.coef==0;                               //不存储系数为0的项
    }

    public boolean equals(Object obj)                      //比较两项是否相等
    {
        if (this==obj)
            return true;
        if (!(obj instanceof TermX))
            return false;
        TermX term=(TermX)obj;
        return this.coef==term.coef && this.exp==term.exp; //比较规则与compareTo(term)==0不同
    }
}

/*要不要??
    public double value(int x)                             //求一项的值
    {
        return this.coef*Math.pow(x, this.exp);
    }
    public TermX plus(TermX term)                          //加法，＋运算符作用
    {
        TermX tmp = new TermX(this);                       //执行拷贝构造方法
        tmp.add(term);
        return tmp;
    }

*/
