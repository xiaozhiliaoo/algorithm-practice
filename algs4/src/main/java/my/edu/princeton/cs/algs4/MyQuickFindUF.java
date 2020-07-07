package my.edu.princeton.cs.algs4;

/**
 * @author lili
 * @date 2020/6/20 18:22
 * @description
 * @notes
 */
public class MyQuickFindUF {
    private int[] id;
    private int count;

    public MyQuickFindUF(int n) {
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
    }

    public int find(int p) {
        return id[p];
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        if(pid == qid) {
            return;
        }
        for (int i = 0; i < id.length; i++) {
            if(id[i] == pid) {
                id[i] = q;
            }
        }
    }


}
