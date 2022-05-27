//【例1.5】  判断指定字符串是否为Java关键字。
package chapter1;
public class Ex105_Keyword
{
    //关键字表
    private static String[] keywords={"abstract","assert","boolean","break","byte","case","catch",
            "char","class","continue","default","do","double","else","extends","false","final","finally",
            "float","for","if","implements","import","instanceof","int","interface","long","native","new",
            "null","package","private","protected","public","return","short","static","super","switch",
            "synchronized","this","throw","throws","transient","true","try","void","volatile","while"};

    public static boolean isKeyword(String str)            //判断str是否为Java关键字
    {
        return Ex104_SortedArray_Comparable.indexOf(keywords, str)!=-1;
    }

    public static void main(String[] args)
    {
        if (args.length==0)
            System.out.println("length"+(isKeyword("length")?"":"不")+"是关键字");
        else                                               //有命令行参数时
            for (int i=0; i<args.length; i++)
                System.out.println(args[i]+(isKeyword(args[i])?"":"不")+"是关键字");
    }

/*    public static void main(String[] args) throws java.io.IOException  //抛出异常交由Java虚拟机处理
    {
        System.out.print("字符串: ");
        byte buffer[] = new byte[512];                    	//以字节数组作为缓冲区
        int count = System.in.read(buffer);
                       //从标准输入流中读取若干字节到缓冲区buffer，以回车换行符结束，返回实际读取字节数
        String str=new String(buffer,0,count-2);
                            //由buffer数组中从0开始长度为count-2的若干字节构造串，不计回车换行符
        System.out.println(str+(isKeyword(str)?"":"不")+"是关键字");
    }*/

}
/*
程序运行结果如下：
abstract? assert? boolean? break? byte? case? catch? char? class? continue? default? do? double? else? extends? false? final? finally? float? for? if? implements? import? instanceof? int? interface? length不是关键字

命令行参数为Welcome public时，
Welcome 不是关键字
public 是关键字


字符串:                 //输入回车符，表示空串
不是关键字

字符串: if
if是关键字

字符串: length
length不是关键字

*/
/*
也可
public static void main(String args[]) throws java.io.IOException  //抛出异常交由Java虚拟机处理
{
    System.out.print("字符串: ");
    String str="";
    int i;
    while ((i=System.in.read())!=-1)  //等待键盘输入，从标准输入流中读取一个字符的ASCII码
        str+=(char)i;
    System.out.println((isKeyword(str)?"":"不")+"是关键字");
}*/

