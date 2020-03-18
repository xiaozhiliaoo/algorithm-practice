package chapter5.matrix;

/**
 * Created by lili on 2017/7/1.
 */
//矩阵接口，为实现矩阵运算约定操作方法
//没有矩阵ADT

public interface MMatrix                         //矩阵接口
{
    //构造方法
    public int get(int i, int j);                //返回矩阵第i行第j列元素值
    public void set(int i, int j, int value);    //设置矩阵第i行第j列的元素值为value
    //String toString();
    public void add(MMatrix mat);                //矩阵相加，this+=mat，各对应元素相加；改变this矩阵

    //习题5
    //深拷贝
    public boolean equals(Object obj);
    public MMatrix plus(MMatrix mat);            //返回this与mat相加后的矩阵，不改变this矩阵
    public MMatrix transpose();                  //返回当前矩阵的转置矩阵
}
