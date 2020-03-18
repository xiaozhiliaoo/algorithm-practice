package chapter2.polynomial;

/**
 * Created by lili on 2017/7/1.
 */
//2.4   线性表的应用：多项式的表示及运算
//2.4.2   二元多项式的表示及运算

public class TermXY extends TermX                          //二元多项式的一项
{
    protected int exp_y;                                   //y指数

    public TermXY(int coef, int exp, int exp_y)            //构造一项
    {
        super(coef, exp);                                  //构造父类一项，调用父类同参数构造方法
        this.exp_y = exp_y;
    }

    //返回二元多项式的一项对应的“系数x^指数”的省略形式字符串，省略形式要求同TermX类。覆盖父类同名方法
    public String toString()
    {
        String str=super.toString();                       //执行父类TermX同名方法，输出系数和x指数
        if (this.exp_y>0)
            str+="y";
        if (this.exp_y>1)
            str+="^"+this.exp_y;
        return str;
    }

    public int compareTo(TermXY term)                      //约定按指数比较两项大小，覆盖父类同名方法
    {
        if (this.exp==term.exp && this.exp_y==term.exp_y)  //两个指数对应相等
            return 0;
        return (this.exp<term.exp || this.exp==term.exp && this.exp_y<term.exp_y)? -1 : 1;
    }

    public boolean equals(Object obj)                      //比较两项是否相等，覆盖父类同名方法
    {
        return this==obj || obj instanceof TermXY && super.equals(obj) && this.exp==((TermXY)obj).exp_y;
    }

    //习题2
    //以“系数x^指数”的省略形式构造一元多项式的一项。
    public TermXY(String termstr)
    {
        super(termstr);
        if (termstr.charAt(0)=='+')
            termstr=termstr.substring(1);
        int i = termstr.indexOf('y');
        if (i==-1)
        {
            this.coef = Integer.parseInt(termstr);
            this.exp = 0;
        }
        else if (i==0)
        {
            this.coef = 1;
            this.exp = 1;
        }
        else
        {
            String sub=termstr.substring(0,i);
            if (sub.equals("-"))
                this.coef=-1;
            else
                this.coef = Integer.parseInt(sub);
            i = termstr.indexOf('^');
            if (i==-1)
                this.exp=1;
            else
                this.exp = Integer.parseInt(termstr.substring(i+1));
        }
    }

    public TermXY(int coef, int exp)                       //构造一项，指定默认值
    {
        this(coef, exp, 0);
    }
    public TermXY(TermXY term)                             //拷贝构造方法
    {
        this(term.coef, term.exp, term.exp_y);
    }
    public TermXY(TermX term, int exp_y)                   //构造一项
    {
        this(term.coef, term.exp, exp_y);
    }
    public TermXY(TermX term)                              //构造一项
    {
        this(term.coef, term.exp, 0);
    }
}
 /*
    public double value(int x, int y)                       //求一项的值
    {
        return super.value(x)*Math.pow(y, this.exp_y);       //Term::value(x)执行基类被覆盖的同名函数
    }
}*/