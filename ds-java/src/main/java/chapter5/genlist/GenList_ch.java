package chapter5.genlist;

/**
 * Created by lili on 2017/7/1.
 */
//5.3   广义表
//5.3.2   广义表的存储结构
//2.  广义表的双链表示

public class GenList_ch
{
    private static int i=0;
    public static GenList<String> createGenList(String gliststr)  //以指定字符串创建并返回广义表
    {
        i=0;
        return create(gliststr);
    }

    //返回以从gliststr[i]开始的子串创建的广义表，语法正确
    private static GenList<String> create(String gliststr)
    {
        i++;                                               //跳过'('
        GenList<String> glist = new GenList<String>();     //构造空广义表，只有头结点
        GenListNode<String> p = glist.head;                //指向头结点
        while (i<gliststr.length())
        {
            char ch=gliststr.charAt(i);
            if (ch>='a' && ch<='z')                        //小写字母表示原子
            {
                p.next=new GenListNode<String>(ch+"", null, null); //创建结点
                p = p.next;
                i++;
            }
            if (ch==',')
                i++;
            if (ch=='(')
            {
                p.next=new GenListNode<String>(null,null,null); //创建子表的结点
                p = p.next;
                p.child = create(gliststr);                //创建子表
            }
            if (ch==')')
            {
                i++;
                return glist;
            }
        }
        return null;
    }
    public static void main(String args[])
    {
        GenList<String> glist_empty = createGenList("()"); //构造空广义表
        System.out.print("glist_empty："+glist_empty.toString()+"，  length="+glist_empty.length());
        System.out.println("，depth="+glist_empty.depth());

        GenList<String> glist_L = createGenList("(a,b,c,d)");  //构造广义表
        System.out.print("glist_L："+glist_L.toString()+"，  length="+glist_L.length());
        System.out.println("，depth="+glist_L.depth());

        GenList<String> glist_T = createGenList("(a,b,c,(p,q),(x,y,(a,b)))");
        System.out.print("glist_T："+glist_T.toString()+"，  length="+glist_T.length());
        System.out.println("，depth="+glist_T.depth());
    }
}

