package chapter5.matrix;

/**
 * Created by lili on 2017/7/1.
 */
//5.1   数组
//5.1.2   多维数组
//【例5.1】  矩阵类。

class Matrix_ex
{
    public static void main(String args[])
    {
        int m1[][]={{1,2,3},{4,5,6,7,8},{9,10,11}};
        Matrix mata=new Matrix(3,4,m1);                    //矩阵对象，初值不足时自动补0，忽略多余元素
        System.out.print("A"+mata.toString());
        Matrix matb=new Matrix(3,4);                       //构造空矩阵对象
        matb.set(0,0,1);
        matb.set(1,1,1);
        matb.set(2,2,1);
        System.out.print("B"+matb.toString());
        Matrix matc = mata.plus(matb);
        System.out.print("C=A+B"+matc.toString());
        mata.add(matb);
        System.out.print("A+=B"+mata.toString());

        //习题5
        System.out.println("\n//习题5");
        System.out.println("C.equals(A)？"+matc.equals(mata));
        System.out.print("A的转置矩阵"+mata.transpose().toString());
        System.out.print("B的转置矩阵"+matb.transpose().toString()+"\n");

        System.out.println("A是上三角矩阵？"+mata.isUpTriangular());
        int m2[][]={{1,2,3,4},{0,5,6,7},{0,0,8,9}};
        Matrix mate=new Matrix(4,4,m2);                    //初值不足时自动补0
        System.out.print("E"+mate.toString());
        System.out.println("E是上三角矩阵？"+mate.isUpTriangular()+"\n");

        System.out.println("A是下三角矩阵？"+mata.isDownTriangular());
        int m3[][]={{1},{2,3},{0,4},{5,6,7}};              //初值不足时自动补0
        Matrix matf=new Matrix(4,4,m3);
        System.out.print("F"+matf.toString());
        System.out.println("F是下三角矩阵？"+matf.isDownTriangular()+"\n");

        System.out.println("A是对称矩阵？"+mata.isSymmetric());
        int m4[][]={{1,2,3,4},{2},{3},{4}};
        Matrix matg=new Matrix(4,4,m4);
        System.out.print("G"+matg.toString());
        System.out.println("G是对称矩阵？"+matg.isSymmetric());
    }
}

/*
程序运行结果如下：
A 矩阵Matrix（3×4）：
   1   2   3   0
   4   5   6   7
   9  10  11   0
B 矩阵Matrix（3×4）：
   1   0   0   0
   0   1   0   0
   0   0   1   0
C=A+B 矩阵Matrix（3×4）：
   2   2   3   0
   4   6   6   7
   9  10  12   0
A+=B 矩阵Matrix（3×4）：
   2   2   3   0
   4   6   6   7
   9  10  12   0

//习题5
C.equals(A)？true
A的转置矩阵 矩阵Matrix（4×3）：
   2   4   9
   2   6  10
   3   6  12
   0   7   0
B的转置矩阵 矩阵Matrix（4×3）：
   1   0   0
   0   1   0
   0   0   1
   0   0   0

A是上三角矩阵？false
E 矩阵Matrix（4×4）：
   1   2   3   4
   0   5   6   7
   0   0   8   9
   0   0   0   0
E是上三角矩阵？true

A是下三角矩阵？false
F 矩阵Matrix（4×4）：
   1   0   0   0
   2   3   0   0
   0   4   0   0
   5   6   7   0
F是下三角矩阵？true

A是对称矩阵？false
G 矩阵Matrix（4×4）：
   1   2   3   4
   2   0   0   0
   3   0   0   0
   4   0   0   0
G是对称矩阵？true


*/
