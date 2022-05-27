package chapter4.stack;

/**
 * Created by lili on 2017/7/1.
 */
//【例4.1】 判断表达式中圆括号是否匹配。

public class Exp_bracket
{
    //判断expstr表达式中的圆括号是否匹配，若匹配，返回空串，否则返回错误信息
    public static String isValid(String expstr)
    {
//        SeqListStack<String> stack = new SeqListStack<String>(expstr.length()); //顺序栈
        SeqStack<String> stack = new SeqStack<String>(expstr.length());  //顺序栈
//        LinkedStack<String> stack = new LinkedStack<String>();           //链式栈
//    	SinglyLinkedStack<String> stack = new SinglyLinkedStack<String>(); //链式栈

        for (int i=0; i<expstr.length(); i++)
        {
            char ch=expstr.charAt(i);
            switch(ch)
            {
                case '(': stack.push(ch+"");               //左括号入栈
                    break;
                case ')': if (stack.isEmpty() || !stack.pop().equals("("))  //遇见右括号时，出栈
                    return "期望(";              //判断出栈字符是否为左括号
            }
        }
        return (stack.isEmpty()) ? "" : "期望)";           //返回空串表示没有错误
    }

    public static void main(String args[])
    {
        String expstr="((1+2)*3+4))(";
        System.out.println(expstr+"  "+isValid(expstr));
    }
}

/*
程序多次运行时，若expstr分别表示不同的表达式字符串，运行结果如下：
((1+2)*3+4)
((1+2)*3+4  期望)
((1+2)*3+4))(  期望(

*/