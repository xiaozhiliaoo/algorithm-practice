package chapter4.stack;

/**
 * Created by lili on 2017/7/1.
 */

public class Stack_ex
{
    public static void main(String args[])
    {
/*	    SeqStack<String> stack = new SeqStack<String>(20);
	    System.out.print("Push: ");
	    char ch='a';
	    for(int i=0;i<5;i++)
	    {
	        String str = (char)(ch+i)+"";
	        stack.push(str);
	        System.out.print(str+"  ");
	    }
*/
        LinkedStack<Integer> stack = new LinkedStack<Integer>();
        System.out.print("Push: ");
        for (int i=1; i<=5; i++)
        {
            Integer intobj = new Integer(i);
            stack.push(intobj);
            System.out.print(intobj+"  ");
        }

        System.out.println("\nStack: "+stack.toString());
        System.out.print("Pop : ");
        while(!stack.isEmpty())                  //全部出栈
            System.out.print(stack.pop().toString()+"  ");
        System.out.println();
    }
}
/*
Push: a  b  c  d  e
Stack: (e, d, c, b, a)
Pop : e  d  c  b  a

Push: 1  2  3  4  5
Stack: (5, 4, 3, 2, 1)
Pop : 5  4  3  2  1

*/
