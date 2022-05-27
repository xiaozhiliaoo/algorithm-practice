package chapter5.genlist;

/**
 * Created by lili on 2017/7/1.
 */
//5.3   广义表
//5.3.2   广义表的存储结构
//2.  广义表的双链表示

//使用GenList类，由语法图构造广义表
public class GenList_String
{
    private static int i=0;
    public static GenList<String> createGenList(String gliststr)  //以gliststr字符串创建并返回广义表
    {
        i=0;
        return create(gliststr);
    }
    //返回以从gliststr[i]开始的子串创建的子广义表，前提是语法正确，递归方法
    private static GenList<String> create(String gliststr)
    {
        i++;                                               //跳过'('
        GenList<String> glist = new GenList<String>();     //构造空广义表，只有头结点
        GenListNode<String> p = glist.head;                //指向头结点
        while (i<gliststr.length())
        {
            char ch=gliststr.charAt(i);
            switch (ch)
            {
                case ',': i++; break;
                case '(':
                {
                    p.next=new GenListNode<String>();      //创建子表结点
                    p = p.next;
                    p.child = create(gliststr);            //创建子表，递归调用
                    break;
                }
                case ')': i++; return glist;
                default :                                  //字符串表示原子
                {
                    int j=i+1;
                    ch=gliststr.charAt(j);
                    while (ch!='(' && ch!=',' && ch!=')')
                        ch=gliststr.charAt(++j);
                    p.next=new GenListNode<String>(gliststr.substring(i,j)); //创建结点
                    p = p.next;
                    i=j;
                }
            }
        }
        return null;
    }

    public static void main(String args[])
    {
        GenList<String> glist_empty = createGenList("()");     //构造空广义表
        System.out.print("glist_empty："+glist_empty.toString()+"，  length="+glist_empty.length());
        System.out.println("，depth="+glist_empty.depth());

        GenList<String> glist_L = createGenList("(a)");  //构造广义表
//        GenList<String> glist_L = createGenList("(a,b,c,d,e,f)");  //构造广义表
        System.out.print("glist_L："+glist_L.toString()+"，  length="+glist_L.length());
        System.out.println("，depth="+glist_L.depth());

        GenList<String> glist_T = createGenList("(d,(a,b),(c,(a,b)))");
        System.out.print("glist_T："+glist_T.toString()+"，  length="+glist_T.length());
        System.out.println("，depth="+glist_T.depth());

        GenList<String> glist_S = createGenList("(and,(begin,end),(my,your,(his,her)))");
        System.out.print("glist_S："+glist_S.toString()+"，  length="+glist_S.length());
        System.out.println("，depth="+glist_S.depth());
    }
}
/*
程序运行结果如下：
glist_empty：()，  length=0，depth=1
glist_L：(a,b,c,d,e,f)，  length=6，depth=1
glist_T：(d,(a,b),(c,(a,b)))，  length=3，depth=3
glist_S：(and,(begin,end),(my,your,(his,her)))，  length=3，depth=3

*/

