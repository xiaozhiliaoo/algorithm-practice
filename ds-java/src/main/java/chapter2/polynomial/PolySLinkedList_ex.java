package chapter2.polynomial;

/**
 * Created by lili on 2017/7/1.
 */
//【例2.6】 一元多项式类及其运算。

public class PolySLinkedList_ex
{
    public static void main(String args[])
    {
        TermX aterms[]={new TermX(-7,9), new TermX(2,7), new TermX(-9,4), new TermX(1,2),
                new TermX(-1,1), new TermXY(2,0,0)};     //不要求数组排序
        TermX bterms[]={new TermX(9,11), new TermX(5,10), new TermX(-3,8), new TermX(10,4),
                new TermX(-1,2), new TermX(1,1), new TermX(-1,0)};
        PolySLinkedList<TermX> apol = new PolySLinkedList<TermX>(aterms);
        PolySLinkedList<TermX> bpol = new PolySLinkedList<TermX>(bterms);
//        double x=1;
        System.out.println("A: "+apol.toString());//+"，x="+x+"，A="+apol.value(x));
        System.out.println("B: "+bpol.toString());//+"，x="+x+"，B="+bpol.value(x));
//        System.out.println("A+B: "+apol.plus(bpol).toString());
        apol.add(bpol);
        System.out.println("A+=B: "+apol.toString());//+"，x="+x+"，A+B="+apol.value(x));
/*
        Term t=bpol.list.head.next.data;
        t.coef+=200;
        System.out.println("A: "+apol.toString()+"，x="+x+"，A="+apol.value(1));
        System.out.println("B: "+bpol.toString()+"，x="+x+"，B="+bpol.value(1));*/
    }
}

/*
程序运行结果如下（省略某些系数和指数）：
A: 2.0-x+x^2-9.0x^4+2.0x^7-7.0x^9，x=1.0，A=-12.0
B: -1.0+x-x^2+10.0x^4-3.0x^8+5.0x^10+9.0x^11，x=1.0，B=20.0
A+B: 1.0+x^4+2.0x^7-3.0x^8-7.0x^9+5.0x^10+9.0x^11
A+=B: 1.0+x^4+2.0x^7-3.0x^8-7.0x^9+5.0x^10+9.0x^11，x=1.0，A+B=8.0
A: 1.0+x^4+2.0x^7-3.0x^8-7.0x^9+5.0x^10+9.0x^11，x=1.0，A=8.0
B: 199.0+x-x^2+10.0x^4-3.0x^8+5.0x^10+9.0x^11，x=1.0，B=220.0


*/


