package chapter5.sparsematrix;

/**
 * Created by lili on 2017/7/1.
 */
//5.2.2 稀疏矩阵的压缩存储
//2.  稀疏矩阵三元组顺序表
//【例5.3】 三元组顺序表表示的稀疏矩阵及其加法运算。

class SeqSparseMatrix_ex
{
    public static void main(String args[])
    {
        //数组没有要求元素 排序
        Triple[] elemsa={new Triple(0,2,11),new Triple(0,4,17),new Triple(1,1,20),
                new Triple(3,0,19),new Triple(3,5,28),new Triple(4,4,50)};
        SeqSparseMatrix smata = new SeqSparseMatrix(5,6,elemsa);
        System.out.print("A "+smata.toString());
        Triple[] elemsb={new Triple(0,2,-11),new Triple(0,4,-17),new Triple(2,3,51),
                new Triple(3,0,10),new Triple(4,5,99),new Triple(1,1,0)};//不存储值为0元素
        SeqSparseMatrix smatb = new SeqSparseMatrix(5,6,elemsb);
        System.out.print("B "+smatb.toString());
        SeqSparseMatrix smatc=smata.plus(smatb);
        System.out.print("C=A+B "+smatc.toString());

        System.out.println("\n//习题5");
        smata.add(smatb);
        System.out.print("A+=B "+smata.toString());
        System.out.println("C.equals(A)？"+smatc.equals(smata));

        //深拷贝，引用测试
        SeqSparseMatrix smatd = new SeqSparseMatrix(smatb);   //深拷贝
        smatb.set(0,2,1);
        System.out.print("B "+smatb.toString());
        System.out.print("D "+smatd.toString());

        System.out.println("A转置"+smata.transpose().toString());        //习题5

    }
}

/*
程序运行结果如下：
A 三元组顺序表：((0,2,11), (0,4,17), (1,1,20), (3,0,19), (3,5,28), (4,4,50))
稀疏矩阵SeqSparseMatrix（5×6）：
   0   0  11   0  17   0
   0  20   0   0   0   0
   0   0   0   0   0   0
  19   0   0   0   0  28
   0   0   0   0  50   0
B 三元组顺序表：((0,2,-11), (0,4,-17), (2,3,51), (3,0,10), (4,5,99))
稀疏矩阵SeqSparseMatrix（5×6）：
   0   0 -11   0 -17   0
   0   0   0   0   0   0
   0   0   0  51   0   0
  10   0   0   0   0   0
   0   0   0   0   0  99
C=A+B 三元组顺序表：((1,1,20), (2,3,51), (3,0,29), (3,5,28), (4,4,50), (4,5,99))
稀疏矩阵SeqSparseMatrix（5×6）：
   0   0   0   0   0   0
   0  20   0   0   0   0
   0   0   0  51   0   0
  29   0   0   0   0  28
   0   0   0   0  50  99

//习题5
A+=B 三元组顺序表：((1,1,20), (2,3,51), (3,0,29), (3,5,28), (4,4,50), (4,5,99))
稀疏矩阵SeqSparseMatrix（5×6）：
   0   0   0   0   0   0
   0  20   0   0   0   0
   0   0   0  51   0   0
  29   0   0   0   0  28
   0   0   0   0  50  99
C.equals(A)？true
B 三元组顺序表：((0,2,1), (0,4,-17), (2,3,51), (3,0,10), (4,5,99))
稀疏矩阵SeqSparseMatrix（5×6）：
   0   0   1   0 -17   0
   0   0   0   0   0   0
   0   0   0  51   0   0
  10   0   0   0   0   0
   0   0   0   0   0  99
D 三元组顺序表：((0,2,-11), (0,4,-17), (2,3,51), (3,0,10), (4,5,99))
稀疏矩阵SeqSparseMatrix（5×6）：
   0   0 -11   0 -17   0
   0   0   0   0   0   0
   0   0   0  51   0   0
  10   0   0   0   0   0
   0   0   0   0   0  99
A转置三元组顺序表：((0,3,29), (1,1,20), (3,2,51), (4,4,50), (5,3,28), (5,4,99))
稀疏矩阵SeqSparseMatrix（6×5）：
   0   0   0  29   0
   0  20   0   0   0
   0   0   0   0   0
   0   0  51   0   0
   0   0   0   0  50
   0   0   0  28  99


*/

