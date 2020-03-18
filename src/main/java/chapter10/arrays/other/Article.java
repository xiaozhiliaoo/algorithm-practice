package chapter10.arrays.other;

/**
 * Created by lili on 2017/7/1.
 */
//10.3.3   贪心法
//【例10.3】 用贪心法求解背包问题。
//物品类
public class Article implements java.lang.Comparable<Article>, java.util.Comparator<Article>
{
    int weight, price;                                     //物品的重量和价值
    float pricePerWeight;                                  //物品的单位重量价值

    public Article(int weight, int price)
    {
        this.weight = weight;
        this.price = price;
        this.pricePerWeight = (float)price/weight;
    }
    public String toString()                     //返回物品的价值、重量和单位重量价值等属性的描述字符串
    {
        return "("+weight+","+price+","+String.format("%5.2f", pricePerWeight)+")";
    }

    //实现Comparable可比较接口，提供物品按单位重量价值比较大小的规则，提供物品按单位重量价值降序排序依据
    public int compareTo(Article art)
    {
//        return (int)((this.pricePerWeight - art.pricePerWeight)*100);      //升序
        return (int)((art.pricePerWeight - this.pricePerWeight)*100);        //物品按单位重量价值比较大小，降序
    }

    //实现Comparator比较器接口，提供物品按重量比较大小的规则，即提供物品按重量降序排序依据
    public int compare(Article art1, Article art2)
    {
        return (int)(art2.weight - art1.weight);                 //物品按重量比较大小，降序
    }
}

//PriceComparator类实现比较器接口，提供物品按价值比较大小的规则，即提供物品按价值降序排序依据
class PriceComparator implements java.util.Comparator<Article>
{
    public int compare(Article art1, Article art2)
    {
        return (int)(art2.price - art1.price);
    }
}
