package chapter5.sparsematrix;

/**
 * Created by lili on 2017/7/1.
 */
//5.2.2 稀疏矩阵的压缩存储
//5.  稀疏矩阵十字链表

public class CrossLinkedSparseMatrix                       //十字链表存储的稀疏矩阵类
{
    private int rows, columns;                             //矩阵行数、列数
    private CrossNode rowheads[],columnheads[];            //行指针数组和列指针数组，元素类型是十字链表结点

    public CrossLinkedSparseMatrix(int rows, int columns)  //构造rows行columns列零矩阵
    {
        if (rows<=0 || columns<=0)
            throw new IllegalArgumentException("矩阵行数或列数非正数。");
        this.rows = rows;
        this.columns = columns;
        this.rowheads = new CrossNode[this.rows];          //构造行指针数组的空顺序表，元素是null
        this.columnheads = new CrossNode[this.columns];    //构造列指针数组的空顺序表，元素是null
    }

    //构造rows行columns列矩阵，由三元组数组elems提供矩阵初值
    public CrossLinkedSparseMatrix(int rows, int columns, Triple[] elems)
    {
        this(rows, columns);
        for (int i=0; i<elems.length; i++)
            this.set(elems[i]);                            //插入一个元素的三元组
    }

    public int get(int i, int j)                           //返回矩阵第i行第j列的元素
    {
        if (i<0 || i>=rows || j<0 || j>=columns)
            throw new IndexOutOfBoundsException("矩阵元素的行或列序号越界");
        for (CrossNode p=this.rowheads[i];  p!=null;  p=p.right) //在第i行排序单链表中顺序查找(i,j,?)结点
            if (p.data.column==j)                          //查找依据是列，忽略三元组元素值
                return p.data.value;                       //查找到(i,j,value)结点，返回矩阵元素
        return 0;                                          //没有找到时返回0
    }

    public void set(Triple elem)                           //以三元组设置矩阵元素
    {
        this.set(elem.row, elem.column, elem.value);
    }
    public void set(int i, int j, int value)               //设置矩阵第i行第j列元素值为value
    {
        if (value==0)
            return;                                        //不存储值为0元素
        if (i>=this.rows || j>=this.columns)
            throw new IllegalArgumentException("三元组的行或列序号越界");

        //以下在第i条单链表中查找指定三元组，或更改，或在行、列单链表中插入三元组结点
        Triple elem = new Triple(i,j,value);
        CrossNode rhead=this.rowheads[i];                  //rhead指向第i行单链表的第一个结点
        if (rhead==null || rhead.data.column>j)            //空表插入或头插入
        {
            this.rowheads[i] = new CrossNode(elem, rhead, null);
            insertColumnHeads(this.rowheads[i]);           //将该结点插入到列的单链表
            return;
        }
        CrossNode front=null, p=rhead;
        while (p!=null && p.data.column<=j)                //在排序单链表中顺序查找(i,j,?)结点
        {
            if (p.data.column==j)                          //查找依据是列，忽略三元组元素值
            {
                p.data.value = value;                     //查找到，更改矩阵元素值
                return;
            }
            front = p;                                     //front是p的前驱结点
            p = p.right;
        }
        front.right = new CrossNode(elem, p, null);        //未找到，在front之后插入三元组结点，中间或尾插入
        insertColumnHeads(front.right);                    //将该结点插入到列的单链表
    }

    private void insertColumnHeads(CrossNode node)         //插入node结点到相应列的单链表中
    {
        Triple elem = node.data;
        CrossNode chead=this.columnheads[elem.column];     //获得第column列单链表
        if (chead==null || chead.data.row>elem.row)        //空表插入或头插入
        {
            this.columnheads[elem.column] = node;
            if (chead!=null)
                node.down = chead.down;
        }
        else                                               //中间插入或尾插入
        {
            CrossNode front=chead, p=front.down;           //front是p的前驱结点
            while (p!=null && p.data.row<=elem.row)        //在排序单链表中顺序查找，寻找插入位置
            {
                front = p;
                p = p.down;
            }
            front.down = node;                             //将node结点插入在front之后，中间或尾插入
            node.down = p;
        }
    }

    public String toString()                               //返回稀疏矩阵三元组十字链表和稀疏矩阵字符串
    {
        String str="三元组十字链表\n";
        str+="三元组行的单链表：";
        for (int i=0; i<this.rowheads.length; i++)
        {
            str+="(";
            for (CrossNode p=this.rowheads[i];  p!=null;  p=p.right)
            {
                str += p.data.toString();
                if (p.right!=null)
                    str+=",";
            }
            str += ")";
            if (i<this.rowheads.length-1)
                str += ", ";
        }
        str+="\n三元组列的单链表：";
        for (int i=0; i<this.columnheads.length; i++)
        {
            str+="(";
            for (CrossNode p=this.columnheads[i];  p!=null;  p=p.down)
            {
                str += p.data.toString();
                if (p.down!=null)
                    str+=",";
            }
            str += ")";
            if (i<this.columnheads.length-1)
                str += ", ";
        }

        str+="\n稀疏矩阵"+this.getClass().getName()+"（"+rows+"×"+columns+"）：\n";
        for (int i=0; i<this.rows; i++)
        {
            CrossNode p=this.rowheads[i];
            for (int j=0; j<this.columns; j++)
                if (p!=null && j==p.data.column)            //有i==p.data.row
                {
                    str += String.format("%4d",p.data.value);
                    p = p.right;
                }
                else
                    str +=String.format("%4d",0);
            str += "\n";
        }
        return str;
    }

    //当前矩阵与smat矩阵相加，this+=smat，改变当前矩阵
    public void add(CrossLinkedSparseMatrix smat)
    {
        if (this.rows!=smat.rows || this.columns!=smat.columns)
            throw new IllegalArgumentException("两个矩阵阶数不同，不能相加");

        for (int i=0; i<this.rows; i++)                    //相加合并两条排序单链表
        {
            CrossNode rhead=this.rowheads[i];              //获得当前矩阵第i行单链表
            CrossNode q=smat.rowheads[i];                  //获得参数矩阵第i行单链表
            if (q==null)
                continue;
            if (rhead==null || rhead.data.column>q.data.column)//空表插入或头插入
            {
                rhead= new CrossNode(new Triple(q.data), rhead, null);   //创建结点，复制元素
                this.rowheads[i]=rhead;
                insertColumnHeads(rhead);
                q = q.right;
            }
            CrossNode front=null, p=rhead;                  //中间或尾插入
            while (p!=null && q!=null)
            {
                if (p.data.column==q.data.column)           //两个结点表示相同位置矩阵元素
                {
                    p.data.value += q.data.value;           //更改结点元素值，矩阵元素值相加
                    if (p.data.value==0)                    //相加元素值为0
                        if (front==null)
                        {
                            this.rowheads[i]=p.right;
                            removeColumnHeads(p);           //在相应列的单链表中删除node结点
                            p=p.right;
                        }
                        else
                        {
                            front.right=p.right;            //相加后元素不需要存储，删除p结点
                            removeColumnHeads(p);           //在相应列的单链表中删除node结点
                            p=front.right;
                        }
                    else
                    {
                        front = p;                         //front是p的前驱结点
                        p = p.right;
                    }
                    q = q.right;
                }
                else if (p.data.column < q.data.column)
                {
                    front = p;
                    p = p.right;                           //当前矩阵元素值小，p向后移动，q不动
                }
                else                                       //复制q结点并插入到front结点之后，复制元素
                {
                    front.right = new CrossNode(new Triple(q.data), p, null);
                    q = q.right;
                    insertColumnHeads(front.right);
                }
            }
            while (q!=null)                                //将smat矩阵单链表中剩余结点复制并插入到当前链表尾
            {
                front.right = new CrossNode(new Triple(q.data), null, null);
                insertColumnHeads(front.right);
                front = front.right;
                q = q.right;
            }
        }
    }
    private void removeColumnHeads(CrossNode node)         //在相应列的单链表中删除node结点
    {
        Triple elem = node.data;
        CrossNode chead=this.columnheads[elem.column];     //获得第column列单链表
        if (chead.data.row==elem.row)                      //头删除，有chead!=null
            this.columnheads[elem.column] = node.down;     //删除node结点
        else                                               //中间或尾删除
        {
            CrossNode front=chead, p=front.down;           //front是p的前驱结点
            while (p!=null && p.data.row<elem.row)         //在排序单链表中顺序查找
            {
                front = p;
                p = p.down;
            }
            if (p!=null && p.data.row==elem.row)           //p为查找到结点，待删除
                front.down = node.down;                    //删除front之后的node结点，中间或尾删除
        }
    }

    //习题5
    public CrossLinkedSparseMatrix(CrossLinkedSparseMatrix smat)           //深拷贝
    {
        this(smat.rows, smat.columns);
        this.add(smat);
    }

    //返回当前矩阵与smat相加后的矩阵，不改变当前矩阵，=this+smat，同SeqSparseMatrix类
    public CrossLinkedSparseMatrix plus(CrossLinkedSparseMatrix smat)
    {
        CrossLinkedSparseMatrix smatc=new CrossLinkedSparseMatrix(this);   //深拷贝
        smatc.add(smat);
        return smatc;                                      //返回对象引用
    }

    public boolean equals(Object obj)                      //比较两个矩阵是否相等
    {
        if (this==obj)
            return true;
        if (!(obj instanceof CrossLinkedSparseMatrix))
            return false;
        CrossLinkedSparseMatrix smat=(CrossLinkedSparseMatrix)obj;
        if (this.rows!=smat.rows || this.columns!=smat.columns)
            return false;
        for (int i=0; i<this.rows; i++) //分别比较this矩阵的第i行单链表与smat矩阵的第i行单链表是否相等
        {
            CrossNode p=this.rowheads[i], q=smat.rowheads[i];
            while (p!=null && q!=null)
            {
                if (!(p.data.equals(q.data)))
                    return false;
                p = p.right;
                q = q.right;
            }
            if (p!=null || q!=null)
                return false;
        }
        return true;
    }
}
/*以下未调试
    public CrossLinkedSparseMatrix transpose()             //返回转置矩阵
    {
    	CrossLinkedSparseMatrix trans = new CrossLinkedSparseMatrix(columns, rows);//构造零矩阵
        for (int i=0; i<this.rows; i++)
        {                                            //归并（相加）两条排序的单链表
            CrossNode p=this.rowheads[i];                    //在第i条单链表中查找
            while (p!=null)   //对称元素的三元组item插入排序的单链表
            {
                trans.set(p.data.toSymmetry());    //复制q结点并插入到front结点之后
                p = p.right;
            }
        }
        return trans;
    }
}*/

