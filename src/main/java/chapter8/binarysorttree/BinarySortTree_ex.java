package chapter8.binarysorttree;

/**
 * Created by lili on 2017/7/1.
 */
//【例8.4】  二叉排序树的插入和查找操作。

class BinarySortTree_ex
{
    public static void main(String args[])
    {
/*        BinarySortTree<Integer> bstree = new BinarySortTree<Integer>();//构造空二叉排序树
        int[] values={54,18,66,87,36,12,54,81,15,76,57,6,40,99,85,99};
        for (int i=0; i<values.length; i++)
             bstree.insert(new Integer(values[i]));                 //插入
//        bstree.insert(values[i]);                     //也可，若插入int，Java自动将其转换成Integer
        bstree.inOrder();                        //中根次序遍历二叉排序树，得到按关键字升序排列的数据元素序列
*/
        Integer[] values={54,18,66,87,36,12,54,6,40,76,81,57,99};
        BinarySortTree<Integer> bstree=new BinarySortTree<Integer>(values); //构造二叉排序树
        bstree.inOrder();                        //中根次序遍历二叉排序树，得到按关键字升序排列的数据元素序列
        Integer key = new Integer(values[values.length-1]);
        System.out.println("查找"+key+", "+(bstree.search(key)!=null?"":"不")+"成功 ");
        key = new Integer(50);
        System.out.println("查找"+key+", "+(bstree.search(key)!=null?"":"不")+"成功 ");

        System.out.print("删除6,36,66,87,"+key+"，");
        bstree.remove(new Integer(6));
        bstree.remove(new Integer(36));
        bstree.remove(key);
        key = new Integer(66);
        bstree.remove(key);
        bstree.remove(new Integer(87));
        bstree.inOrder();

        System.out.print("插入66，");
        bstree.insert(key);
        bstree.inOrder();
        System.out.println("查找"+key+"，"+(bstree.search(key)!=null?"":"不")+"成功 ");

        while (bstree.root!=null)
        {
            System.out.print("删除"+bstree.root.data+"，");
            bstree.remove(bstree.root.data);
            bstree.inOrder();
        }
    }
}
/*
程序运行结果如下：
中根次序遍历二叉树：  6 12 18 36 40 54 57 66 76 81 87 99
54? 66? 87? 99? 查找99, 成功
54? 18? 36? 40? 查找50, 不成功
删除6,36,66,87,50，中根次序遍历二叉树：  12 18 40 54 57 76 81 99
插入66，中根次序遍历二叉树：  12 18 40 54 57 66 76 81 99
54? 76? 57? 66? 查找66，成功
删除54，中根次序遍历二叉树：  12 18 40 57 66 76 81 99
删除57，中根次序遍历二叉树：  12 18 40 66 76 81 99
删除66，中根次序遍历二叉树：  12 18 40 76 81 99
删除76，中根次序遍历二叉树：  12 18 40 81 99
删除81，中根次序遍历二叉树：  12 18 40 99
删除99，中根次序遍历二叉树：  12 18 40
删除18，中根次序遍历二叉树：  12 40
删除40，中根次序遍历二叉树：  12
删除12，中根次序遍历二叉树：

*/

