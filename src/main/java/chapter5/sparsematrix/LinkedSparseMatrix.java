package chapter5.sparsematrix;

import chapter2.polynomial.PolySLinkedList;
import chapter2.seqlist.SeqList;
import chapter2.singlylinkedlist.Node;

/**
 * Created by lili on 2017/7/1.
 */
//5.2.2 稀疏矩阵的压缩存储
//4.  稀疏矩阵行的单链表

public class LinkedSparseMatrix
{
    private int rows, columns;                             //矩阵行数、列数
    private SeqList<PolySLinkedList<Triple>> list;         //行指针顺序表，元素是多项式排序单链表对象

    public LinkedSparseMatrix(int rows, int columns)       //构造rows行columns列零矩阵
    {
        if (rows<=0 || columns<=0)
            throw new IllegalArgumentException("矩阵行数或列数非正数。");
        this.rows = rows;
        this.columns = columns;
        this.list = new SeqList<PolySLinkedList<Triple>>();//构造空顺序表，元素是null
        for (int i=0; i<rows; i++)                         //顺序表增加rows个空单链表元素
            this.list.append(new PolySLinkedList<Triple>());
    }

    //构造rows行columns列矩阵，由三元组数组elems提供矩阵初值
    public LinkedSparseMatrix(int rows, int columns, Triple[] elems)
    {
        this(rows, columns);
        for (int i=0; i<elems.length; i++)
            this.set(elems[i]);                            //按行主序插入一个元素的三元组
    }

    public int get(int i, int j)                           //返回矩阵第i行第j列的元素
    {
        if (i<0 || i>=rows || j<0 || j>=columns)
            throw new IndexOutOfBoundsException("矩阵元素的行或列序号越界");
        PolySLinkedList<Triple> link = this.list.get(i);   //获得第i行多项式排序单链表
        Triple find=link.search(new Triple(i,j,0));   //在排序单链表中顺序查找，返回找到结点，算法实现见8.2.1节
        return (find==null) ? 0 : find.value;         //没有找到时返回0，否则返回结点元素
    }

    public void set(Triple elem)                           //以三元组设置矩阵元素
    {
        this.set(elem.row, elem.column, elem.value);
    }
    public void set(int row, int column, int value)        //设置矩阵第row行第column列元素值为value
    {
        if (value==0)
            return;                                        //不存储值为0元素
        if (row>=this.rows || column>=this.columns)
            throw new IllegalArgumentException("三元组的行或列序号越界");

        //以下在第row条单链表中查找指定三元组对象，或更改，或按行主序插入一个三元组
        PolySLinkedList<Triple> link=this.list.get(row);   //获得第row行多项式排序单链表
        Node<Triple> front=link.head, p=front.next;        //front是p的前驱结点
        while (p!=null && p.data.column<=column)           //在排序单链表中进行顺序查找
        {
            if (p.data.column==column)                     //查找到，更改矩阵元素值
            {
                p.data.value = value;
                return;
            }
            front = p;
            p = p.next;
        }
        front.next = new Node<Triple>(new Triple(row,column,value), p); //在front之后插入三元组元素
    }
    //思考题：set(elem)方法中能否调用link.insert(elem)？为什么？
    //答：不能，因为值相同时要替换，不插入。

    public String toString()                               //返回稀疏矩阵三元组顺序表和稀疏矩阵描述字符串
    {
        String str="三元组行的单链表：";
        for (int i=0; i<this.list.length(); i++)           //调用SeqList的length()方法
            str += this.list.get(i).toString();            //调用SinglyLinkedList的toString()方法
        str += "\n稀疏矩阵"+this.getClass().getName()+"（"+rows+"×"+columns+"）：\n";
        for (int i=0; i<this.list.length(); i++)
        {
            PolySLinkedList<Triple> link = this.list.get(i);
            Node<Triple> p=link.head.next;
            for (int j=0; j<this.columns; j++)
                if (p!=null && j==p.data.column)            //有i==p.data.row
                {
                    str += String.format("%4d",p.data.value);
                    p = p.next;
                }
                else
                    str +=String.format("%4d",0);
            str += "\n";
        }
        return str;
    }

    //当前矩阵与smat矩阵相加，this+=smat，改变当前矩阵，每两条单链表的合并算法同两个多项式相加
    public void add(LinkedSparseMatrix smat)
    {
        if (this.rows!=smat.rows || this.columns!=smat.columns)
            throw new IllegalArgumentException("两个矩阵阶数不同，不能相加");
        for (int i=0; i<this.rows; i++)
            this.list.get(i).add(smat.list.get(i));        //调用多项式单链表相加(+=)算法
    }

    //深度拷贝，复制顺序表，复制顺序表中所有单链表及其中所有结点和元素对象
    public LinkedSparseMatrix(LinkedSparseMatrix smat)
    {
        this(smat.rows, smat.columns);           //构造rows行columns列零矩阵，顺序表有rows个空单链表对象
        for (int i=0; i<this.rows; i++)
        {
            PolySLinkedList<Triple> link=new PolySLinkedList<Triple>(smat.list.get(i));
            //多项式单链表深拷贝，已复制所有结点，没有复制元素对象
            for (Node<Triple> p=link.head.next;  p!=null;  p=p.next)
                p.data = new Triple(p.data);     //复制一条单链表中各结点引用的元素对象
            this.list.set(i, link);              //将复制后的单链表设置为顺序表第i个元素
        }
    }

    //返回当前矩阵与smat相加后的矩阵，不改变当前矩阵，smatc=this+smat
    public LinkedSparseMatrix plus(LinkedSparseMatrix smat)
    {
        LinkedSparseMatrix smatc=new LinkedSparseMatrix(this);   //深拷贝
        smatc.add(smat);
        return smatc;                                      //返回对象引用
    }

    public boolean equals(Object obj)                      //比较两个矩阵是否相等，算法同SeqSparseMatrix类
    {
        if (this==obj)
            return true;
        if (!(obj instanceof LinkedSparseMatrix))
            return false;
        LinkedSparseMatrix smat=(LinkedSparseMatrix)obj;
        return this.rows==smat.rows && this.columns==smat.columns && this.list.equals(smat.list);
        //比较两个三元组顺序表是否相等
    }

    //习题5
    /*    //可行，效率低，用于测试get(i,j)方法是否正确
        public String toString()                               //返回稀疏矩阵三元组顺序表和稀疏矩阵描述字符串
        {
            String str="三元组行的单链表："+ this.list.toString()+"\n";
            str+="稀疏矩阵"+this.getClass().getName()+"（"+rows+"×"+columns+"）：\n";
            for (int i=0; i<this.rows; i++)
            {
                for (int j=0; j<this.columns; j++)
                    str += String.format("%4d", this.get(i,j));
                str += "\n";
            }
            return str;
        } */

    public LinkedSparseMatrix transpose()                  //返回转置矩阵
    {
        LinkedSparseMatrix trans = new LinkedSparseMatrix(columns, rows);    //构造零矩阵，指定行数和列数
        for (int i=0; i<this.rows; i++)
        {                                                  //归并（相加）两条排序的单链表
            Node<Triple> p=this.list.get(i).head.next;
            while (p!=null)                                //对称元素的三元组item插入排序的单链表
            {
                trans.set(p.data.toSymmetry());            //复制q结点并插入到front结点之后
                p = p.next;
            }
        }
        return trans;
    }
}

