package chapter5.matrix;

/**
 * Created by lili on 2017/7/1.
 */
//5.1   数组
//5.1.2   多维数组
//【例5.1】  矩阵类。

public class Matrix                                       //矩阵类
{
    private int element[][];                               //存储矩阵元素的二维数组

    public Matrix(int m, int n)                            //构造m×n零矩阵
    {
        this.element=new int[m][n];                        //数组元素初值为0
        //若m或n为负数，Java将抛出负数组长度异常NegativeArraySizeException
    }
    public Matrix(int n)                                   //构造n×n零方阵
    {
        this(n,n);
    }
    public Matrix(int m, int n, int mat[][])               //构造m×n矩阵，由mat提供元素
    {
        this(m, n);
        for (int i=0; i<mat.length && i<m; i++)            //mat元素不足时补0，忽略多余元素
            for (int j=0; j<mat[i].length && j<n; j++)
                this.element[i][j] = mat[i][j];
    }

    public int get(int i, int j)                           //返回矩阵第i行第j列元素值，O(1)
    {
        return this.element[i][j];                         //若i、j下标越界，Java将抛出数组下标越界异常ArrayIndexOutOfBoundsException
    }
    public void set(int i, int j, int value)               //设置矩阵第i行第j列的元素值为value，O(1)
    {
        this.element[i][j]=value;
    }

    public String toString()                               //返回矩阵所有元素的描述字符串，行主序遍历
    {
        String str=" 矩阵"+this.getClass().getName()+"（"+this.element.length+"×"+this.element[0].length+"）：\n";
        for (int i=0; i<this.element.length; i++)
        {
            for (int j=0; j<this.element[i].length; j++)
                str += String.format("%4d", this.element[i][j]);
            str += "\n";
        }
        return str;
    }

    //当前矩阵与mat矩阵相加，this+=mat，各对应元素相加；改变当前矩阵
    public void add(Matrix mat)
    {
        if (this.element.length!=mat.element.length || this.element[0].length!=mat.element[0].length)
            throw new IllegalArgumentException("两个矩阵阶数不同，不能相加"); //抛出无效参数异常
        for (int i=0; i<this.element.length; i++)
            for (int j=0; j<this.element[i].length; j++)
                this.element[i][j] += mat.element[i][j];
    }

    //习题5
    public Matrix(Matrix mat)                              //深拷贝
    {
        this(mat.element.length, mat.element[0].length, mat.element);
    }

    //返回当前矩阵与mat相加后的矩阵，不改变当前矩阵，=this+mat，各对应元素相加
    public Matrix plus(Matrix mat)
    {
        Matrix matc=new Matrix(this);                      //深拷贝
        matc.add(mat);
        return matc;                                       //返回对象引用
    }

    public boolean equals(Object obj)                      //比较两个同阶矩阵是否相等
    {
        if (this==obj)
            return true;
        if (!(obj instanceof Matrix))
            return false;
        Matrix mat=(Matrix)obj;
        if (this.element.length!=mat.element.length || this.element[0].length!=mat.element[0].length)
            return false;
        for (int i=0; i<this.element.length; i++)
            for (int j=0; j<this.element[i].length; j++)
                if (this.element[i][j]!=mat.element[i][j]) //比较对应元素是否相等
                    return false;
        return true;
    }

    public Matrix transpose()                              //返回当前矩阵的转置矩阵
    {
        Matrix trans = new Matrix(this.element[0].length, this.element.length);//构造零矩阵
        for (int i=0; i<this.element.length; i++)
            for (int j=0; j<this.element[i].length; j++)
                trans.element[j][i]=this.element[i][j];
        return trans;                                      //返回对象引用
    }

    public boolean isUpTriangular()                        //判断当前矩阵是否为上三角矩阵
    {
        if (this.element.length!=this.element[0].length)
            return false;
        for (int i=0; i<this.element.length; i++)
            for (int j=0; j<i; j++)
                if (this.element[i][j]!=0)                 //下三角元素应为0
                    return false;
        return true;
    }

    public boolean isDownTriangular()                      //判断当前矩阵是否为下三角矩阵
    {
        if (this.element.length!=this.element[0].length)
            return false;
        for (int i=0; i<this.element.length; i++)
            for (int j=i+1; j<this.element.length; j++)
                if (this.element[i][j]!=0)                 //上三角元素应为0
                    return false;
        return true;
    }

    public boolean isSymmetric()                           //判断当前矩阵是否为对称矩阵
    {
        if (this.element.length!=this.element[0].length)
            return false;
        for (int i=0; i<this.element.length; i++)
            for (int j=0; j<this.element[i].length; j++)
                if (this.element[i][j]!=this.element[j][i])
                    return false;
        return true;
    }
}

/*
不支持以下默认构造方法，必须指定行列数
    public Matrix()                              //默认构造方法，构造10×10空矩阵，初值为0
    {
        this(10,10);
    }
int Matrix::saddlePoint()       //返回鞍点值
{
}
*/