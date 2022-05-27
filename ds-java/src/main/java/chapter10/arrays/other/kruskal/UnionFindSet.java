package chapter10.arrays.other.kruskal;

/**
 * Created by lili on 2017/7/1.
 */
//并查集
public class UnionFindSet                                  //并查集类
{
    private int parent[];                                  //父指针数组

    //构造有n个元素的并查集对象，集合初始状态是包含n棵树的森林，其父指针数组元素值为-1，表示每棵树只有一个结点
    public UnionFindSet(int n)
    {
        this.parent = new int[n];
        for (int i=0; i<n; i++)
            this.parent[i]=-1;
    }

    //查找并返回元素x所在树的根下标。算法沿着父指针向上寻找直到根
    public int find(int x)
    {
        while (this.parent[x]>=0)                          //若x不是根
            x=this.parent[x];                              //找到父结点下标
        return x;                                          //返回根结点下标
    }

    //集合并运算，若x、y不在同一棵树中，则合并结点x和y所在的两棵树，返回true；否则返回false
    //算法首先查找并分别返回结点x和y所在树的根，将结点个数较多的一个根作为另一个根的孩子结点
    public boolean union(int x, int y)
    {
        int rootx=find(x), rooty=find(y);                  //rootx、rooty分别获得结点x和y所在树的根
        if (rootx!=rooty)                                  //x、y不在同一棵树中
            if (parent[rootx]<=parent[rooty])              //rootx树结点个数（负）较多
            {
                this.parent[rootx]+=this.parent[rooty];    //结点个数相加
                this.parent[rooty]=rootx;                  //将rooty作为rootx的孩子结点，数组元素值指向父结点下标
            }
            else
            {
                this.parent[rooty]+=this.parent[rootx];
                this.parent[rootx]=rooty;                  //将rootx作为rooty的孩子结点
            }
        return rootx!=rooty;                               //返回合并与否状态
    }

    public String toString()
    {
        String str="(";
        if (this.parent.length>0)
            str += this.parent[0];
        for (int i=1; i<this.parent.length; i++)
            str += ", "+this.parent[i];
        return str+") ";
    }

    public static void main(String args[])
    {
        //殷人昆p263图6.4
        UnionFindSet ufset = new UnionFindSet(10);          //并查集
        ufset.union(0,6);
        ufset.union(0,7);
        ufset.union(0,8);
        ufset.union(1,4);
        ufset.union(1,9);
        System.out.println("并查集："+ufset.toString());
        ufset.union(0,1);
        System.out.println("并查集："+ufset.toString());
    }
/*
程序运行结果如下：
并查集：(-4, -3, -1, -1, 1, -1, 0, 0, 0, 1)
并查集：(-7, 0, -1, -1, 1, -1, 0, 0, 0, 1)
*/

    //查找并返回元素x所在树的根下标，同时按照折叠规则压缩路径
    //算法沿着父指针向上寻找直到根，将从x到根路径上的所有结点都改成根的孩子
    public int collapsingFind(int x)
    {
        int root=x;
        while (this.parent[root]>=0)                       //若x不是根
            root=this.parent[root];                        //找到父结点下标
        while (root!=x && parent[x]!=root)                 //当x不是根且x不是根的孩子时
        {
            int pa = parent[x];
            parent[x]=root;                                //将x作为root的孩子结点
            x=pa;                                          //向上到x的父结点
        }
        return root;                                       //返回根结点下标
    }
}
