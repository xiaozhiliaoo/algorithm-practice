package chapter7.graph;

import chapter4.queue.LinkedQueue;

/**
 * Created by lili on 2017/7/1.
 */
//7.3   图的遍历
//非连通图的深度优先搜索遍历和广度优先搜索遍历

public abstract class AbstractGraph<T> implements GGraph<T>//抽象图类
{
    public abstract int vertexCount();                     //返回顶点数，方法由子类实现
    public abstract T get(int i);                          //返回顶点vi的数据域
    public abstract int getNextNeighbor(int i, int j);     //返回vi在vj后的下一个邻接顶点序号
    //当j=-1时，vi的第一个邻接顶点序号，若不存在邻接顶点，返回-1

    public void DFSTraverse(int i)                         //从顶点vi出发对非连通图的一次深度优先搜索遍历
    {
        boolean[] visited=new boolean[this.vertexCount()]; //访问标记数组，元素初值为false，表示未被访问
        int j=i;
        do
        {   if (!visited[j])                               //若顶点vj未被访问
        {
            System.out.print("{ ");
            this.depthfs(j, visited);                  //从顶点vi出发的一次深度优先搜索遍历
            System.out.print("} ");
        }
            j = (j+1) % this.vertexCount();                //在其他连通分量中寻找未被访问顶点
        } while (j!=i);
        System.out.println();
    }
    //从顶点vi出发的一次深度优先搜索遍历，遍历一个连通分量
    private void depthfs(int i, boolean[] visited)
    {
        System.out.print(this.get(i)+" ");                 //访问顶点vi
        visited[i] = true;                                 //置已访问标记
        int j = this.getNextNeighbor(i, -1);               //获得vi的第一个邻接顶点序号
        while (j!=-1)                                      //若存在邻接顶点vj
        {
            if(!visited[j])                                //若邻接顶点vj未被访问
                depthfs(j, visited);                       //从vj出发的深度优先搜索遍历，递归调用
            j = this.getNextNeighbor(i, j);                //返回vi在vj后的下一个邻接顶点序号
        }
    }

    //从顶点vi出发对非连通图进行一次广度优先搜索遍历
    public void BFSTraverse(int i)
    {
        boolean[] visited = new boolean[this.vertexCount()]; //访问标记数组
        int j=i;
        do
        {   if (!visited[j])                               //若顶点vj未被访问
        {
            System.out.print("{ ");
            breadthfs(j, visited);                     //从顶点vj出发的广度优先搜索遍历
            System.out.print("} ");
        }
            j = (j+1) % this.vertexCount();                //在其他连通分量中寻找未被访问顶点
        } while (j!=i);
        System.out.println();
    }

    //从顶点vi出发的广度优先搜索遍历，遍历一个连通分量
    private void breadthfs(int i, boolean[] visited)
    {
        System.out.print(this.get(i)+" ");
        visited[i] = true;
//        SeqQueue<Integer> que = new SeqQueue<Integer>(this.vertexCount());   //创建顺序队列
        LinkedQueue<Integer> que = new LinkedQueue<Integer>();//this.vertexCount());   //创建顺序队列
        que.enqueue(new Integer(i));                       //访问过的顶点vi的序号入队
        while (!que.isEmpty())                             //当队列不空时循环
        {
            i = que.dequeue().intValue();                  //出队
            int j = this.getNextNeighbor(i,-1);            //获得顶点vi的第一个邻接顶点序号
            while (j!=-1)                                  //若存在邻接顶点vj
            {
                if (!visited[j])                           //若顶点vj未访问过
                {
                    System.out.print(this.get(j)+" ");     //访问顶点vj
                    visited[j] = true;
                    que.enqueue(new Integer(j));           //访问过的顶点vj的序号入队
//                    System.out.println("顶点队列："+que.toString());
                }
                j = this.getNextNeighbor(i, j);            //返回vi在vj后的下一个邻接顶点的序号
            }
        }
    }

    //7.4.2   最小生成树的构造算法
    public abstract int getWeight(int i, int j);           //返回<vi,vj>边的权值

    public void minSpanTree_prim()                         //构造带权图最小生成树的普里姆算法
    {
        Edge[] mst = new Edge[vertexCount()-1];            //mst存储最小生成树的边集合，边数为顶点数-1
        for (int i=0; i<mst.length; i++)                   //初始化mst数组，从顶点v0出发构造最小生成树
            mst[i]=new Edge(0,i+1,this.getWeight(0,i+1));  //保存从顶点v0到其他各顶点的边的权

        System.out.print("mst数组：");
        for(int j=0; j<mst.length; j++)                    //显示mst数组的变化过程
            System.out.print(mst[j].toString());

        for (int i=0; i<mst.length; i++)                   //共选出“顶点数-1”条边
        {
            int minweight = MAX_WEIGHT;                    //最小权值
            int min = i;
            for (int j=i; j<mst.length; j++)               //寻找当前权值最小边的顶点
                if (mst[j].weight<minweight)
                {
                    minweight = mst[j].weight;             //更新最小权值
                    min = j;                               //保存当前权值最小边的终点序号
                }

            Edge temp = mst[i];                            //交换权值最小的边
            mst[i] = mst[min];
            mst[min] = temp;

            int tv = mst[i].dest;                          //刚并入TV的顶点
            for (int j=i+1; j<mst.length; j++)             //调整mst[i+1]及其后元素为权值最小的边
            {
                int v = mst[j].dest;                       //原边在V-TV中的终点
                if (this.getWeight(tv,v)<mst[j].weight)    //若有权值更小的边(tv,v)，则用(tv,v)边替换原边
                {
                    mst[j].weight = this.getWeight(tv,v);
                    mst[j].start = tv;
                }
            }
            System.out.print("\nmst数组：");
            for(int j=0; j<mst.length; j++)                //显示mst数组的变化过程
                System.out.print(mst[j].toString());
        }
        System.out.print("\n最小生成树的边集合：");
        int mincost=0;
        for (int i=0; i<mst.length; i++)
        {
            System.out.print(mst[i]+" ");
            mincost += mst[i].weight;
        }
        System.out.println("，最小代价为"+mincost);
    }

    //7.5   最短路径
    //7.5.1   非负权值的单源最短路径（Dijkstra算法）
    public void shortestPath(int i)              //以Dijkstra算法求带权图中顶点vi的单源最短路径
    {
        int n = this.vertexCount(),j,k;          //n为顶点数
        int[] dist = new int[n];                 //最短路径长度
        int[] path = new int[n];                 //最短路径的终点的前一个顶点
        int[] vset = new int[n];                 //已求出最短路径的顶点集合，初值全为0
        vset[i] = 1;                             //标记源点vi在集合S中
        for (j=0; j<n; j++)                      //初始化dist和path数组
        {
            dist[j] = this.getWeight(i,j);
            path[j] = (j!=i && dist[j]<MAX_WEIGHT) ? i : -1;
        }
        System.out.print("\nvset数组"+toString(vset));
        System.out.print("\tpath数组"+toString(path));
        System.out.print("\tdist数组"+toString(dist));

        for (j=(i+1)%n; j!=i; j=(j+1)%n)         //寻找从vi到顶点vj的最短路径
        {
            int mindist=MAX_WEIGHT, u=0;
            for (k=0; k<n; k++)                  //选择路径长度最小值
                if (vset[k]==0 && dist[k]<mindist)
                {
                    u = k;                       //当前长度最小路径的终点
                    mindist = dist[k];           //当前路径长度最小值
                }
            if (mindist==MAX_WEIGHT)             //若没有其他最短路径则算法结束； 此语句对非连通图是必须的
                break;
            vset[u] = 1;                         //确定一条最短路径的终点u并入集合S
            for (k=0; k<n; k++)                  //调整从vi到V-S中其他顶点的最短路径及长度
                if(vset[k]==0 && this.getWeight(u,k)<MAX_WEIGHT && dist[u]+this.getWeight(u,k)<dist[k])
                {
                    dist[k] = dist[u] + this.getWeight(u,k);
                    path[k] = u;
                }
            System.out.print("\nvset数组"+toString(vset));
            System.out.print("\tpath数组"+toString(path));
            System.out.print("\tdist数组"+toString(dist));
        }

        System.out.println("\n从顶点"+this.get(i)+"到其他顶点的最短路径如下：");
        for (j=0; j<n; j++)
            if (j!=i)
            {
                String pathstr="";
                for (k=path[j]; k!=i && k!=j && k!=-1; k=path[k])
                    pathstr=","+this.get(k)+pathstr;       //最短路径字符串
                pathstr = "("+this.get(i)+pathstr+","+this.get(j)+")长度为 "+(dist[j]==MAX_WEIGHT ? "∞" : dist[j]);
                System.out.print(pathstr+"，");
            }
        System.out.println();
    }
    private static String toString(int[] value)        //输出数组值
    {
        if (value!=null && value.length>0)
        {
            String str="{";
            int i=0;
            for(i=0; i<value.length-1; i++)
                str += (value[i]==MAX_WEIGHT ? "∞" : value[i])+",";
            return str+(value[i]==MAX_WEIGHT ? "∞" : value[i])+"}";
        }
        return null;
    }

    //7.5.2   每对顶点间的最短路径（Floyd算法）
    public void shortestPath()                   //以Floyd算法求带权图每对顶点间的最短路径
    {
        int n=this.vertexCount(), i, j;          //n为顶点数
        int dist[][] = new int[n][n];            //dist存储每对顶点间的最短路径长度
        int path[][] = new int[n][n];            //存储最短路径终点的前一个顶点序号
        for (i=0; i<n; i++)                      //初始化path数组
            for (j=0; j<n; j++)
            {
                dist[i][j] = this.getWeight(i,j);          //dist初始为邻接矩阵
                path[i][j] = (i!=j && dist[i][j]<MAX_WEIGHT) ? i : -1;
            }
        System.out.print("初值path数组\n"+toString(path));
        System.out.println("dist数组\n"+toString(dist));

        for (int k=0; k<n; k++)                  //以 作为其他路径的中间顶点，测试路径长度是否更短
        {
            System.out.println("以"+get(k)+"作为中间顶点");
            for (i=0; i<n; i++)
                if (k!=i)
                    for (j=0; j<n; j++)
                        if (k!=j && i!=j)
                        {
                            System.out.print(pathToString(path,i,j)+"路径长度"+dist[i][j]+"，替换为"+
                                    pathToString(path,i,k)+","+pathToString(path,k,j)+"路径长度"+(dist[i][k]+dist[k][j])+"？");
                            if (k!=j && i!=j && dist[i][j]>dist[i][k]+dist[k][j])
                            {
                                dist[i][j] = dist[i][k]+dist[k][j];
                                path[i][j] = path[k][j];
                                System.out.println("是，d"+i+j+"="+dist[i][j]+"，p"+i+j+"="+path[i][j]);
                            }
                            else
                                System.out.println("否");
                        }
            System.out.print("path数组\n"+toString(path));
            System.out.println("dist数组\n"+toString(dist));
        }

        System.out.println("每对顶点间的最短路径如下：");
        for (i=0; i<n; i++)
            for (j=0; j<n; j++)
                if (i!=j)
                {
                    String pathstr="";
                    for (int k=path[i][j]; k!=i && k!=j && k!=-1;  k=path[i][k])
                        pathstr=","+this.get(k)+pathstr;   //最短路径的顶点序号是反序的
                    pathstr = "("+this.get(i)+pathstr+","+this.get(j)+")长度为"+(dist[i][j]==MAX_WEIGHT ? "∞" : dist[i][j]);
                    System.out.print("，"+pathstr);
                }
        System.out.println();
    }

    private String pathToString(int[][] path, int i, int j)
    {
        String pathstr="";
        for (int k=path[i][j]; k!=i && k!=j && k!=-1;  k=path[i][k])
            pathstr=","+this.get(k)+pathstr;               //最短路径的顶点序号是反序的
        pathstr = "("+get(i)+pathstr+","+this.get(j)+")";
        return pathstr;
    }
    public static String toString(int[][] value)
    {
        String str="";
        for (int i=0; i<value.length; i++)
        {
            for (int j=0; j<value[i].length; j++)
                str += value[i][j]==MAX_WEIGHT ? "  ∞" : "  "+value[i][j];
            str+="\n";
        }
        return str;
    }
}