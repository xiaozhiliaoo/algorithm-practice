package chapter3.string;

/**
 * Created by lili on 2017/7/1.
 */
//【例3.2】获得一个正整数的二进制或十六进制形式字符串。

public class Int
{
/*    public static String toBinaryString(int n)             //返回int整数的二进制字符串，除2取余法
    {
        String str="";
        for (int i=0x80000000; i!=0; i>>>=1)               //一个int占32位，右移一位，高位以0填充
            str += (n & i)==0 ? '0': '1';
        return str;                                        //返回字符串
    }*/

    public static String toHexString(int n)                //返回正整数n的十六进制字符串
    {
        String str="";
        while (n>0)                                        //除16取余法，余数存入str字符串
        {
            int k = n % 16;
            str = (char)(k<=9 ? k+'0' : k+'A'-10) + str;   //将0～9、10～15转换为'0'～'9'、'A'～'F'
            n /= 16;
        }
        return str;
    }

    public static String toString(int n, int radix)        //返回正整数n的radix进制字符串，radix取值为2、8、16
    {
        String str="";
        while (n>0)                                        //除radix取余法，余数存入str字符串
        {
            int k = n % radix;
            str = (char)(k<=9 ? k+'0' : k+'A'-10) + str;
            n /= radix;
        }
        return str;
    }

    //第4章习题,以下递归算法
    public static void binary(int n)                       //输出正整数n的二进制字符串，递归算法
    {
        if (n==0)
            return;
        binary(n/2);
        System.out.print((char)(n%2+'0'));
    }

    public static void hex(int n)                          //输出正整数n的十六进制字符串，递归算法
    {
        if (n==0)
            return;
        hex(n/16);
        n %= 16;
        System.out.print((char)(n<=9 ? n+'0' : n+'A'-10));
    }

    public static String toBinary(int n)                   //返回正整数n的二进制字符串，递归算法
    {
        if (n>0)
            return toBinary(n/2)+(char)(n%2+'0');
        return "";
    }

    public static String toHex(int n)                      //返回正整数n的十六进制字符串，递归算法
    {
        int k= n % 16;
        if (n>0)
            return toHex(n/16)+(char)(k<=9 ? k+'0' : k+'A'-10);
        return "";
    }

    public static void main(String args[])
    {
        byte n=-126;
//        int n=254;
        System.out.println("Integer.toBinaryString("+n+")="+Integer.toBinaryString(n));
        System.out.println("toBinaryString("+n+")="+Int.toBinaryString(n));
        System.out.println("toHexString("+n+")="+Int.toHexString(n));
        System.out.println("toString("+n+",8)="+Int.toString(n,8));

        //第4章习题,以下递归算法
        System.out.print("binary("+n+")=");
        Int.binary(n);
        System.out.print("\nhex("+n+")=");
        Int.hex(n);

        System.out.println("\ntoBinary("+n+")="+Int.toBinary(n));
        System.out.println("toHex("+n+")="+Int.toHex(n));

        n=-1;
        System.out.println("toBinaryString("+n+")="+Int.toBinaryString(n));
    }

/*    //算法有错
    public static String toBinaryString(int n)             //返回正整数n的二进制字符串
    {
        String str="";
        while (n>0)                                        //除2取余法，余数存入str字符串
        {
            str = (char)(n%2 +'0') +str;
            n /= 2;
        }
        return str;
    }*/
//    n=-1时，n%2结果为-1，(char)(n%2 +'0')结果为'/'（'0'的前一字符），所以不行。

    public static String toBinaryString(byte n)           //返回byte整数的二进制字符串
    {
        String str="";
        for (int i=0x00000080; i!=0; i>>>=1)
//不行         for (byte i=-128; i!=0; i>>>=1)                  //??-128(1111111110000000)
            str += (n & i)==0 ? '0': '1';
        return str;                                        //返回字符串
    }
//i=-128(1111111110000000)，i>>>=1，高位以1填充，i!=0条件永远成立，死循环。

}
/*
程序多次运行结果如下：
Integer.toBinaryString(254)=11111110
toBinaryString(254)=00000000000000000000000011111110
toHexString(254)=FE
toString(254,8)=376
binary(254)=11111110
hex(254)=FE
toBinary(254)=11111110
toHex(254)=FE
toBinaryString(-1)=11111111111111111111111111111111

*/


