package chapter5.sparsematrix;

import chapter2.seqlist.SeqList;

/**
 * Created by lili on 2017/7/1.
 */
//5.2.2 稀疏矩阵的压缩存储
//2.  稀疏矩阵三元组顺序表

public class SeqSparseMatrix                               //三元组顺序存储的稀疏矩阵类
{
    private int rows, columns;                             //矩阵行数、列数
    private SeqList<Triple> list;                          //稀疏矩阵三元组顺序表

    public SeqSparseMatrix(int rows, int columns)          //构造rows行columns列零矩阵
    {
        if (rows<=0 || columns<=0)
            throw new IllegalArgumentException("矩阵行数或列数非正数。"); //抛出无效参数异常
        this.rows = rows;
        this.columns = columns;
        this.list = new SeqList<Triple>();                 //构造空顺序表，执行SeqList()构造方法
    }
    //构造rows行columns列矩阵，由三元组数组elems提供矩阵初值
    public SeqSparseMatrix(int rows, int columns, Triple[] elems)
    {
        this(rows, columns);
        for (int i=0; i<elems.length; i++)
            this.set(elems[i]);                            //按行主序插入一个元素的三元组
    }

    //返回矩阵第i行第j列元素，排序顺序表的顺序查找算法，O(n)
    public int get(int i, int j)
    {
        if (i<0 || i>=rows || j<0 || j>=columns)
            throw new IndexOutOfBoundsException("矩阵元素的行或列序号越界");
        Triple item = new Triple(i,j,0);
        int k=0;
        Triple elem = this.list.get(k);
        while (k<this.list.length() && item.compareTo(elem)>=0) //在排序顺序表list中顺序查找item对象
        {
            if (item.compareTo(elem)==0)                   //只比较三元组元素位置，即elem.row==i && elem.column==j
                return elem.value;                         //查找到(i,j)，返回矩阵元素
            k++;                                           //item“大”时向后走
            elem = this.list.get(k);
        }
        return 0;                                          //没有找到时返回0
    }

    public void set(Triple elem)                           //以三元组设置矩阵元素
    {
        this.set(elem.row, elem.column, elem.value);
    }
    //设置矩阵第row行第column列的元素值为value，按行主序在排序顺序表list中更改或插入一个元素的三元组，O(n)
    public void set(int row, int column, int value)
    {
        if (value==0)
            return;                                        //不存储值为0元素
        if (row>=this.rows || column>=this.columns)
            throw new IllegalArgumentException("三元组的行或列序号越界");

        Triple elem = new Triple(row,column,value);
        int i=0;
        while (i<this.list.length())                       //在排序的三元组顺序表中查找elem对象，或更改或插入
        {
            Triple item = this.list.get(i);
            if (elem.compareTo(item)==0)                   //若elem存在，则更改该位置矩阵元素
            {
                this.list.set(i, elem);                    //设置顺序表第i个元素为elem
                return;
            }
            if (elem.compareTo(item)>=0)
                i++;                                       //elem较“大”时向后走
            else break;
        }
        this.list.insert(i, elem);                         //插入elem对象作为顺序表第i个元素
    }

    public String toString()                               //返回稀疏矩阵三元组顺序表和稀疏矩阵描述字符串
    {
        String str="三元组顺序表："+ this.list.toString()+"\n";
        str+="稀疏矩阵"+this.getClass().getName()+"（"+rows+"×"+columns+"）：\n";
        int k=0;
        Triple elem = this.list.get(k++);                  //返回第k个元素，若k指定序号无效则返回null
        for (int i=0; i<this.rows; i++)
        {
            for (int j=0; j<this.columns; j++)
                if (elem!=null && i==elem.row && j==elem.column)
                {
                    str += String.format("%4d",elem.value);
                    elem = this.list.get(k++);
                }
                else
                    str += String.format("%4d",0);
            str += "\n";
        }
        return str;
    }

    //返回当前矩阵与smat相加的矩阵，smatc=this+smat，不改变当前矩阵，算法同两个多项式相加
    public SeqSparseMatrix plus(SeqSparseMatrix smat)
    {
        if (this.rows!=smat.rows || this.columns!=smat.columns)
            throw new IllegalArgumentException("两个矩阵阶数不同，不能相加");

        SeqSparseMatrix smatc=new SeqSparseMatrix(this.rows, this.columns);   //构造rows×columns零矩阵
        int i=0, j=0;
        while (i<this.list.length() && j<smat.list.length())//分别遍历两个矩阵的顺序表
        {
            Triple elema = this.list.get(i);
            Triple elemb = smat.list.get(j);
            if (elema.compareTo(elemb)==0)                 //若两个三元组表示相同位置的矩阵元素，则对应元素值相加
            {
                if (elema.value+elemb.value!=0)            //相加结果不为0，则新建元素
                    smatc.list.append(new Triple(elema.row, elema.column, elema.value+elemb.value));
                i++;
                j++;
            }
            else if (elema.compareTo(elemb)<0)             //将较小三元组复制添加到smatc顺序表最后
            {
                smatc.list.append(new Triple(elema));      //复制elema元素执行Triple拷贝构造方法
                i++;
            }
            else
            {
                smatc.list.append(new Triple(elemb));
                j++;
            }
        }
        while (i<this.list.length())                       //将当前矩阵顺序表的剩余三元组复制添加到smatc顺序表最后
            smatc.list.append(new Triple(this.list.get(i++)));
        while (j<smat.list.length())                       //将smat中剩余三元组复制添加到smatc顺序表最后
            smatc.list.append(new Triple(smat.list.get(j++)));
        return smatc;                                      //返回对象引用
    }

    //习题5
    /*  可行，效率低，用于测试get(i,j)方法是否正确
    public String toString()                               //返回稀疏矩阵三元组顺序表和稀疏矩阵描述字符串
    {
        String str="三元组顺序表："+ this.list.toString()+"\n";
        str+="稀疏矩阵"+this.getClass().getName()+"（"+rows+"×"+columns+"）：\n";
        for (int i=0; i<this.rows; i++)
        {
            for (int j=0; j<this.columns; j++)
                str += String.format("%4d", this.get(i,j));
            str += "\n";
        }
        return str;
    }*/

    //当前矩阵与smat矩阵相加，this+=smat，改变当前矩阵，算法同两个多项式相加
    public void add(SeqSparseMatrix smat)
    {
        if (this.rows!=smat.rows || this.columns!=smat.columns)
            throw new IllegalArgumentException("两个矩阵阶数不同，不能相加");

        int i=0, j=0;
        while (i<this.list.length() && j<smat.list.length())
        {                                                  //将mat的各三元组依次插入（或相加）到当前矩阵三元组顺序表中
            Triple elema = this.list.get(i);
            Triple elemb = smat.list.get(j);
            if (elema.compareTo(elemb)==0)
            {                                              //若两个三元组表示相同位置的矩阵元素，则对应元素值相加
                if (elema.value+elemb.value!=0)            //相加结果不为0，则新建元素
                    this.list.set(i++, new Triple(elema.row, elema.column, elema.value+elemb.value));
                else
                    this.list.remove(i);
                j++;
            }
            else if (elema.compareTo(elemb)<0)              //继续向后寻找elemb元素的插入位置
                i++;
            else
            {
                this.list.insert(i++, new Triple(elemb));//复制elemb元素插入作为this.list的第i个元素
                j++;
            }
        }
        while (j<smat.list.length())                       //将mat中剩余三元组依次复制插入当前矩阵三元组顺序表中
            this.list.append(new Triple(smat.list.get(j++)));
    }

    public SeqSparseMatrix(SeqSparseMatrix smat)           //深拷贝
    {
        this(smat.rows, smat.columns);
        this.list = new SeqList<Triple>();                 //创建空顺序表，默认容量
        for (int i=0; i<smat.list.length(); i++)           //复制smat中所有三元组对象
            this.list.append(new Triple(smat.list.get(i)));
    }
    /*也可
    public SeqSparseMatrix(SeqSparseMatrix smat)           //深拷贝
    {
        this(smat.rows, smat.columns);
        this.add(smat);
    }*/

    //也可，算法效率低
    //返回当前矩阵与smat相加后的矩阵，smatc=this+smat，不改变当前矩阵，算法同两个多项式相加
/*
    public SeqSparseMatrix plus(SeqSparseMatrix smat)
    {
        SeqSparseMatrix smatc=new SeqSparseMatrix(this);   //深拷贝
        smatc.add(smat);
        return smatc;                                      //返回对象引用
    }
*/
    public boolean equals(Object obj)                      //比较两个矩阵是否相等
    {
        if (this==obj)
            return true;
        if (!(obj instanceof SeqSparseMatrix))
            return false;
        SeqSparseMatrix smat=(SeqSparseMatrix)obj;
        return this.rows==smat.rows && this.columns==smat.columns && this.list.equals(smat.list);
        //比较两个三元组顺序表是否相等
    }

    public SeqSparseMatrix transpose()                     //返回转置矩阵
    {
        SeqSparseMatrix trans = new SeqSparseMatrix(columns, rows);    //构造零矩阵，指定行数和列数
        for (int i=0; i<this.list.length(); i++)
            trans.set(this.list.get(i).toSymmetry());      //插入矩阵对称位置元素的三元组
        return trans;
    }

}
/*
    不支持
    public SeqSparseMatrix()
    {
        this(10, 10);
    }


*/

