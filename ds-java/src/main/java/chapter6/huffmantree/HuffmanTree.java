package chapter6.huffmantree;

/**
 * Created by lili on 2017/7/1.
 */
//6.5 构造Huffman树并获得Huffman编码

public class HuffmanTree                                   //Huffman树
{
    private int leafNum;                                   //叶子结点个数
    private TriElement[] huftree;                          //Huffman树的结点数组

    public HuffmanTree(int[] weight)                       //构造指定权值集合的Huffman树
    {
        int n = weight.length;                             //n个叶子结点
        this.leafNum = n;
        this.huftree = new TriElement[2*n-1];              //n个叶子结点的Huffman树共有2n-1个结点
        for(int i=0; i<n; i++)                             //结点数组初始化有n个叶子结点
            this.huftree[i] = new TriElement(weight[i]);

        for(int i=0; i<n-1; i++)                           //构造n-1个2度结点，每次循环构造1个2度结点
        {
            int min1=Integer.MAX_VALUE, min2=min1;         //最小和次最小权值，初值为整数最大值
            int x1=-1, x2=-1;                              //记录两个无父母的最小权值结点下标
            for(int j=0; j<n+i; j++)                       //查找两个无父母的最小权值结点
                if(huftree[j].data<min1 && huftree[j].parent==-1)
                {
                    min2 = min1;
                    x2 = x1;
                    min1 = huftree[j].data;                //min1记下最小权值
                    x1 = j;                                //x1记下最小权值结点的下标
                }
                else if(huftree[j].data<min2 && huftree[j].parent==-1)
                {
                    min2 = huftree[j].data;
                    x2 = j;                                //x2记下次最小权值结点的下标
                }

            huftree[x1].parent = n+i;                      //将找出的两棵权值最小的子树合并为一棵子树
            huftree[x2].parent = n+i;
            this.huftree[n+i] = new TriElement(huftree[x1].data+huftree[x2].data, -1, x1, x2);
        }
    }

    public String toString()
    {
        String str="Huffman树的结点数组:\n";
        for (int i=0; i<this.huftree.length && huftree[i]!=null; i++)
            str += "第"+i+"行 "+this.huftree[i].toString()+"\n";
        return str;
    }

    public String[] huffmanCodes()                         //返回当前Huffman树的Huffman编码
    {
        String[] hufcodes = new String[this.leafNum];
        for(int i=0; i<this.leafNum; i++)                  //求n个叶子结点的Huffman编码
        {
            hufcodes[i]="";
            int child = i;
            int parent = huftree[child].parent;
            while (parent!=-1)                             //由叶结点向上直到根结点循环
            {
                if (huftree[parent].left==child)
                    hufcodes[i]="0"+hufcodes[i];           //左孩子结点编码为0
                else
                    hufcodes[i]="1"+hufcodes[i];           //右孩子结点编码为1
                child = parent;
                parent = huftree[child].parent;
            }
        }
        return hufcodes;
    }
}
