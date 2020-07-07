package my.edu.princeton.cs.algs4;

/**
 * @author lili
 * @date 2020/6/20 18:22
 * @description
 * @notes
 */
public class MyQuickUnionUF {
    private int[] parent;
    private int count;

    public MyQuickUnionUF(int count) {
        parent = new int[count];
        for (int i = 0; i < count; i++) {
            parent[i] = i;
        }
    }

    public int find(int p) {
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ) {
            return;
        }
        parent[rootP] = rootQ;
    }
}
