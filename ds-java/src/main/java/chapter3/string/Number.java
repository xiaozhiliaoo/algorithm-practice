package chapter3.string;

/**
 * Created by lili on 2017/7/1.
 */
//【例3.1】 获得整数和实数字符串表示的数值。

public class Number
{
    public static int parseInt(String s)                      //返回整数字符串s表示的整数值
    {
        int x=0, i=0;
        int sign = s.charAt(0)=='-' ? -1 : 1;              //符号位，记住正负数标记
        if (s.charAt(0)=='+' || s.charAt(0)=='-')          //跳过符号位
            i++;                                           //i记住当前字符序号
        while (i<s.length())
            if (s.charAt(i)>='0' && s.charAt(i)<='9')
                x = x*10+s.charAt(i++)-'0';                //x记住当前获得的整数值
            else
                throw new NumberFormatException(s);        //抛出数值格式异常
        return x*sign;                                     //返回整数值，忽略其后的字符
    }

    public static double parseDouble(String s)                //返回实数字符串表示的浮点数值
    {
        int n=s.length(), i=0;
        int sign = s.charAt(0)=='-' ? -1 : 1;              //符号位，记住正负数标记
        double x=0, power=10.0E0;                          //power表示底数为10的幂//能够将整数赋给浮点数
        if (s.charAt(0)=='+' || s.charAt(0)=='-')          //跳过符号位
            i++;
        while (i<n && s.charAt(i)>='0' && s.charAt(i)<='9')//获得整数部分值
            x = x*10+s.charAt(i++)-'0';
        if (i<n && s.charAt(i)=='.')                       //若是小数点
        {
            i++;
            while (i<n && s.charAt(i)>='0' && s.charAt(i)<='9')//获得小数部分值
            {
                x += (s.charAt(i)-'0')/power;
                i++;
                power*=10;
            }
        }
        x *=sign;

        if (i<n && (s.charAt(i)=='E' || s.charAt(i)=='e'))  //处理阶码
        {
            i++;
            power = (s.charAt(i)=='-') ? 0.1 :10;           //阶码的符号位决定指数的正负及其运算
            if (s.charAt(i)=='+' || s.charAt(i)=='-')
                i++;
            int exp=0;
            while (i<n && s.charAt(i)>='0' && s.charAt(i)<='9')
                exp = exp*10+s.charAt(i++)-'0';            //获得指数的绝对值
            for (int j=0; j<exp; j++)
                x*=power;
        }
        return x;
    }

    public static void main(String args[])
    {
        String s="-12345abc";
        System.out.println("parseInt(\""+s+"\")="+parseInt(s));
        s="-12345.67E-2";
        System.out.println("parseDouble(\""+s+"\")="+parseDouble(s));
    }
}
/*
程序多次运行结果如下：
parseInt("12345")=12345
parseInt("-12345")=-12345
parseInt("-12345abc")=-12345
parseDouble("-12345.67")=-12345.67
parseDouble("-12345.67E-2")=-123.45670000000001

Exception in thread "main" java.lang.NumberFormatException: -12345abc
	at Number.toInt(Number.java:15)
	at Number.main(Number.java:58)

*/
/*
    //习题3未完成
    public static int BinaryToInt(String s)      //返回二进制整数字符串s表示的整数值
    public static int HexToInt(String s)         //返回十六进制整数字符串s表示的整数值
    public static int toInt(String s, int radix) //返回radix进制整数字符串s表示的整数值


/*    //??未完成 toDouble("-12345.67E-2")=-12344.33
    public static double toDouble2(String s)               //返回实数字符串表示的浮点数值
    {
        double x=0;
        int i=s.indexOf(".");                              //寻找小数点
        if (i!=-1)                                         //没找到
            x=toInt(s.substring(0,i));                     //获得整数部分值
        else
            x=toInt(s);

        i=s.indexOf(".");                                  //寻找小数点
        int n=s.length();
        double power=10.0E0;                               //power表示底数为10的幂//能够将整数赋给浮点数
        i++;                                               //跳过小数点
        while (i<n && s.charAt(i)>='0' && s.charAt(i)<='9')  //获得小数部分值
        {
            x += (s.charAt(i)-'0')/power;
            System.out.println("x="+x);
            i++;
            power*=10;
        }
        if (i<n && (s.charAt(i)=='E' || s.charAt(i)=='e'))
        {
            i++;
            int exp=toInt(s.substring(i));                 //获得指数部分的整数值
            while (i<n && s.charAt(i)>='0' && s.charAt(i)<='9')
            {
                x += (s.charAt(i)-'0')/power;
                i++;
                power*=10;
            }
        }
        return x;
    } */
