package chapter3.string;

/**
 * Created by lili on 2017/7/1.
 */
//5.String的插入、删除操作。

public class InsertString
{
    public static void main(String args[])
    {
/*        String s1="abcde", s2="xyz";
        int i=2;
        String s3=s1.substring(0,i)+s2+s1.substring(i);  //返回在s1串的i处插入s2后的串，s1、s2串不变
        System.out.println("insert(\""+s1+"\","+i+",\""+s2+"\")=\""+s3+"\"");
        String s4=s3.substring(0,i)+s3.substring((i+3)); //删除s1串中从begin到end-1处的子串，返回删除后的串，s串不变
        System.out.println("remove(\""+s3+"\","+i+","+(i+3)+")=\""+s4+"\"");
*/
        //test
//        System.out.println("new String()==\"\"? "+(new String()==""));
//        System.out.println("new String()==null? "+(new String()==null));
//        System.out.println("new String().length()= "+new String().length());
//      System.out.println("new String().isEmpty()? "+(new String().isEmpty()));
        System.out.println("\"\".length()= "+"".length());
        System.out.println("\"\".isEmpty()? "+("".isEmpty()));

    }
}

/*
insert("abcde",2,"xyz")="abxyzcde"
remove("abxyzcde",2,5)="abcde"

//test
new String()==""? false
new String()==null? false
new String().length()= 0
new String().isEmpty()? true
"".length()= 0
"".isEmpty()? true

*/
