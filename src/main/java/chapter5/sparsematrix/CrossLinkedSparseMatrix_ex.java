package chapter5.sparsematrix;

/**
 * Created by lili on 2017/7/1.
 */
//5.2.2 稀疏矩阵的压缩存储
//5.  稀疏矩阵十字链表

class CrossLinkedSparseMatrix_ex
{
    public static void main(String args[])
    {
        Triple[] elemsa = {new Triple(0,2,11),new Triple(0,4,17),new Triple(1,1,20),
                new Triple(3,0,19),new Triple(3,5,28),new Triple(4,4,50)};
        CrossLinkedSparseMatrix smata = new CrossLinkedSparseMatrix(5,6,elemsa);
        System.out.print("A "+smata.toString());
        Triple[] elemsb={new Triple(0,2,-11),new Triple(0,4,-17),new Triple(2,3,51),
                new Triple(3,0,10),new Triple(4,5,99),new Triple(1,1,0)};//不存储值为0元素
        CrossLinkedSparseMatrix smatb = new CrossLinkedSparseMatrix(5,6,elemsb);
        System.out.print("B "+smatb.toString());
        CrossLinkedSparseMatrix smatc=smata.plus(smatb);
        System.out.print("C=A+B "+smatc.toString());
        smata.add(smatb);
        System.out.print("A+=B "+smata.toString());
        System.out.println("C.equals(A)？"+smatc.equals(smata));

//        System.out.println("\n//习题5");
//        System.out.println("A转置"+smata.transpose().toString());        //习题5
    }
}
/*
程序运行结果如下：
A 三元组十字链表
三元组行的单链表：((0,2,11),(0,4,17)), ((1,1,20)), (), ((3,0,19),(3,5,28)), ((4,4,50))
三元组列的单链表：((3,0,19)), ((1,1,20)), ((0,2,11)), (), ((0,4,17),(4,4,50)), ((3,5,28))
稀疏矩阵CrossLinkedSparseMatrix（5×6）：
   0   0  11   0  17   0
   0  20   0   0   0   0
   0   0   0   0   0   0
  19   0   0   0   0  28
   0   0   0   0  50   0
B 三元组十字链表
三元组行的单链表：((0,2,-11),(0,4,-17)), (), ((2,3,51)), ((3,0,10)), ((4,5,99))
三元组列的单链表：((3,0,10)), (), ((0,2,-11)), ((2,3,51)), ((0,4,-17)), ((4,5,99))
稀疏矩阵CrossLinkedSparseMatrix（5×6）：
   0   0 -11   0 -17   0
   0   0   0   0   0   0
   0   0   0  51   0   0
  10   0   0   0   0   0
   0   0   0   0   0  99
C=A+B 三元组十字链表
三元组行的单链表：(), ((1,1,20)), ((2,3,51)), ((3,0,29),(3,5,28)), ((4,4,50),(4,5,99))
三元组列的单链表：((3,0,29)), ((1,1,20)), (), ((2,3,51)), ((4,4,50)), ((3,5,28),(4,5,99))
稀疏矩阵CrossLinkedSparseMatrix（5×6）：
   0   0   0   0   0   0
   0  20   0   0   0   0
   0   0   0  51   0   0
  29   0   0   0   0  28
   0   0   0   0  50  99
A+=B 三元组十字链表
三元组行的单链表：(), ((1,1,20)), ((2,3,51)), ((3,0,29),(3,5,28)), ((4,4,50),(4,5,99))
三元组列的单链表：((3,0,29)), ((1,1,20)), (), ((2,3,51)), ((4,4,50)), ((3,5,28),(4,5,99))
稀疏矩阵CrossLinkedSparseMatrix（5×6）：
   0   0   0   0   0   0
   0  20   0   0   0   0
   0   0   0  51   0   0
  29   0   0   0   0  28
   0   0   0   0  50  99
C.equals(A)？true

*/
