package chapter2.singlylinkedlist;

import chapter10.iterator.AbstractLList;
import chapter2.seqlist.LList;

/**
 * Created by lili on 2017/7/1.
 */
//2.3   线性表的链式表示和实现
//带头结点的单链表类，实现线性表接口

//public class SinglyLinkedList<T> implements LList<T>     //2.3   线性表的链式表示和实现
public class SinglyLinkedList<T> extends AbstractLList<T> implements LList<T>//第10章，10.2 实现迭代器
{
    public Node<T> head;                              //头指针，指向单链表的头结点

    public SinglyLinkedList()                         //默认构造方法，构造空单链表
    {
        this.head = new Node<T>();                    //创建头结点，data和next值均为null
    }

    //由指定数组中的多个对象构造单链表。采用尾插入构造单链表
    //若element==null，Java将抛出空对象异常；若element.length==0，构造空链表
    public SinglyLinkedList(T[] element) {
        this();                                       //创建空单链表，只有头结点
        Node<T> rear = this.head;                       //rear指向单链表最后一个结点
        for (int i = 0; i < element.length; i++)          //若element==null，抛出空对象异常
        {                                             //element.length==0时，构造空链表
            rear.next = new Node<T>(element[i], null);   //尾插入,创建结点链入rear结点之后
            rear = rear.next;                         //rear指向新的链尾结点
        }
    }

    public boolean isEmpty()                          //判断单链表是否空，O(1)
    {
        return this.head.next == null;
    }

    //以下length()、toString()、get()、set()方法基于单链表遍历算法
    public int length()                               //返回单链表长度，O(n)
    {
        int i = 0;
        Node<T> p = this.head.next;                     //p从单链表第一个结点开始
        while (p != null)                               //若单链表未结束
        {
            i++;
            p = p.next;                               //p到达后继结点
        }
        return i;
    }

    //返回单链表所有元素的描述字符串，形式为“(,)”，覆盖Object类的toString()方法，O(n)
    public String toString() {
        String str = "(";
        for (Node<T> p = this.head.next; p != null; p = p.next) {
            str += p.data.toString();
            if (p.next != null)
                str += ",";                                //不是最后一个结点时后加分隔符
        }
        return str + ")";                                    //空表返回()
    }

    public T get(int i)                //返回第i（≥0）个元素，若i<0或大于表长则返回null，O(n)
    {
        if (i >= 0) {
            Node<T> p = this.head.next;
            for (int j = 0; p != null && j < i; j++)
                p = p.next;
            if (p != null)
                return p.data;                             //p指向第i个结点
        }
        return null;                                       //当i<0或大于表长时
    }

    //设置第i（≥0）个元素值为x。若i<0或大于表长则抛出序号越界异常；若x==null，不操作。O(n)
    public void set(int i, T x) {
        if (x == null) return;                              //不能设置元素为空对象
        Node<T> p = this.head.next;
        for (int j = 0; p != null && j < i; j++)
            p = p.next;
        if (i >= 0 && p != null)
            p.data = x;                                    //p指向第i个结点
        else throw new IndexOutOfBoundsException(i + "");    //抛出序号越界异常
    }

    //以下insertAfter()、insert()、append()算法讨论单链表插入操作
/*    //插入x作为p结点的后继结点，若操作成功返回新插入结点；否则返回null，O(1)
    public Node<T> insertAfter(Node<T> p, T x)
    {
        if (x==null || p==null)
            return null;
        Node<T> q=new Node<T>(x, p.next);             //插入x作为p结点的后继结点
        p.next = q;
        return q;
    }*/

    //插入第i（≥0）个元素值为x。若x==null，不插入。
    //若i<0，插入x作为第0个元素；若i大于表长，插入x作为最后一个元素。O(n)
    public void insert(int i, T x) {
        if (x == null) return;                         //不能插入空对象
        Node<T> p = this.head;                          //p指向头结点
        for (int j = 0; p.next != null && j < i; j++)     //寻找插入位置
            p = p.next;                               //循环停止时，p指向第i-1结点或最后一个结点
        p.next = new Node<T>(x, p.next);              //插入x作为p结点的后继结点，包括头插入（i<=0）、中间/尾插入（i>0）
    }

    public void append(T x)                           //在单链表最后添加x对象，O(n)
    {
        insert(Integer.MAX_VALUE, x);                //遍历一次
//      insert(this.length(), x);                  //需遍历单链表两次，效率较低
//      this.insertAfter(this.getLast(),x)!=null;      //遍历一次
    }
    /*
    //将x对象插入在序号为i结点，若操作成功返回新插入结点；否则返回null，O(n)
    public Node<T> insert(int i, T x)
    {
        if (x==null)
            return null;                              //不能插入空对象
        Node<T> p=this.head;                          //p指向头结点
        for (int j=0; p.next!=null && j<i; j++)       //寻找插入位置
            p = p.next;                               //循环停止时，p指向第i-1结点或最后一个结点
        Node<T> q=new Node<T>(x, p.next);             //插入x作为p结点的后继结点
        p.next = q;                                   //包括头插入（i<=0）、中间/尾插入（i>0）
        return q;
    }*/


    //以下removeAfter()、remove()、removeAll()算法实现单链表删除操作
/*    //删除p结点的后继结点，若操作成功返回删除结点；否则返回null，O(1)
    public Node<T> removeAfter(Node<T> p)
    {
        if (p==null || p.next==null)
            return null;
        Node<T> q=p.next;
        p.next = q.next;                              //删除p结点的后继结点q
        return q;
    }*/

    //删除第i（≥0）个元素，返回被删除对象。若i<0或i大于表长，不删除，返回null。O(n)
    public T remove(int i) {
        if (i >= 0) {
            Node<T> p = this.head;
            for (int j = 0; p.next != null && j < i; j++)      //定位到待删除结点（i）的前驱结点（i-1）
                p = p.next;
            if (p.next != null) {
                T old = p.next.data;                       //获得原对象
                p.next = p.next.next;                      //删除p的后继结点
                return old;
            }
        }
        return null;                                       //当i<0或大于表长时
//        throw new IndexOutOfBoundsException(i+"");         //抛出序号越界异常
    }

    public void removeAll()                                //删除单链表所有元素
    {
        this.head.next = null;                               //Java将自动收回各结点所占用的内存空间
    }

    /* 7.  提高单链表操作效率的措施
                    算法可行，但效率低，时间复杂度是O(n*n)。
        public String toString()
        {
            String str="(";
            if (this.length()!=0)
            {
                for(int i=0; i<this.length()-1; i++)
                    str += this.get(i).toString()+", ";
                str += this.get(this.length()-1).toString();
            }
            return str+")";
        }
    */

    //8.  单链表的浅拷贝与深拷贝
    //深拷贝构造方法，复制单链表list的所有结点构造新的单链表
    public SinglyLinkedList(SinglyLinkedList<T> list) {
        this();                                       //创建空单链表，只有头结点
        Node<T> rear = this.head;
        for (Node<T> p = list.head.next; p != null; p = p.next)  //若list==null，抛出空对象异常
        {
            rear.next = new Node<T>(p.data, null);
            rear = rear.next;
        }
    }

    //9.  单链表比较相等
    //比较两条单链表是否相等 ，覆盖Object类的equals(obj)方法
/*    public boolean equals(Object obj)
    {
        if (obj == this)
            return true;
        if (!(obj instanceof SinglyLinkedList))
            return false;
        Node<T> p=this.head.next;
        Node<T> q=((SinglyLinkedList<T>)obj).head.next;
        while (p!=null && q!=null && p.data.equals(q.data))
        {
            p=p.next;
            q=q.next;
        }
        return p==null && q==null;
    }*/
    //以上实现LList接口，第2章

    //习题2

    //(1)以下4个方法提供迭代遍历单链表方式
    public Node<T> getFirst()                    //返回单链表第一个元素结点（非头结点），O(1)
    {
        return this.head.next;                   //若单链表空返回null
    }

    public Node<T> getNext(Node<T> p)            //返回p的后继结点，O(1)
    {
        if (this.head.next == null || p == null)
            return null;
        return p.next;
    }

    public Node<T> getPred(Node<T> p)            //返回p的前驱结点，O(n)
    {
        if (p == null || this.head.next == p)
            return null;
        Node<T> front = this.head.next;
        while (front != null && front.next != p)
            front = front.next;
        return front;
    }

    public Node<T> getLast()                     //返回单链表最后一个元素结点，O(n)
    {
        Node<T> p = this.head.next;
        while (p != null && p.next != null)
            p = p.next;
        return p;                                //若单链表空返回null
    }
/*    public String toString()                     //O(n)
    {
        String str="(";
        for (Node<T> p=this.getFirst();  p!=null;  p=this.getNext(p))
        {
            str += p.data.toString();
            if (p.next!=null)
                str += ", ";
        }
        return str+")";
    }*/

    //(2)构造
    //由指定数组中的多个对象构造单链表，采用尾插入构造单链表，调用insertAfter()方法
/*    public SinglyLinkedList(T[] element)
    {
        this();                                       //创建空单链表，只有头结点
        Node<T> rear=this.head;
        for (int i=0; i<element.length; i++)
            rear = insertAfter(rear, element[i]);
    }   */
    //由指定数组中的多个对象构造单链表，采用头插入构造反序的单链表
/*    public SinglyLinkedList(T[] element)
    {
        this();                                       //创建空单链表，只有头结点
        for (int i=0; i<element.length; i++)
            this.head.next = new Node<T>(element[i], this.head.next);
    }*/

    //（3） 求子表、包含、匹配、连接、插入、删除等单链表对子表的操作

    //返回从第i(≥0)个结点开始、长度为n(≥0)的子表（深拷贝）
    //若i<0或n<0，抛出异常；若i>表长或n=0，返回空链表；若n超长，返回至表尾的子表
    public SinglyLinkedList<T> sub(int i, int n) {
        if (i < 0) throw new IndexOutOfBoundsException(i + "");    //抛出序号越界异常
        if (n < 0) throw new IllegalArgumentException(i + "");     //抛出无效参数异常
        Node<T> p = this.head.next;
        for (int j = 0; j < i && p != null; j++)               //寻找子表首结点
            p = p.next;                                    //循环停止时，p指向第i结点。若i>表长，则p==null
        SinglyLinkedList<T> list = new SinglyLinkedList<T>();
        Node<T> rear = list.head;                            //复制子表到list链表
        for (int j = 0; p != null && j < n; j++, p = p.next) {
            rear.next = new Node<T>(p.data, null);          //在rear结点之后插入值为p.data的结点
            rear = rear.next;
        }
        return list;                                       //返回对象引用
    }

    //判断当前单链表是否包含list单链表的所有结点，判断无序的子集
    public boolean contain(SinglyLinkedList<T> list) {
        for (Node<T> q = list.head.next; q != null; q = q.next) {
            Node<T> p = head.next;
            while (p != null && !p.data.equals(q.data))      //比较q结点值是否包含在当前单链表中
                p = p.next;
            if (p == null)                                   //不包含
                return false;
        }
        return true;
    }

    //将list单链表中所有结点链接在当前单链表之后，并设置list为空
    public void concat(SinglyLinkedList<T> list) {
        Node<T> rear = this.head;
        while (rear.next != null)
            rear = rear.next;
        rear.next = list.head.next;
        list.head.next = null;
    }

    //（9） 插入子表（深拷贝）
    //将list单链表中的所有结点复制插入到this单链表front结点之后
    public void insert(Node<T> front, SinglyLinkedList<T> list) {
        for (Node<T> q = list.head.next; q != null; q = q.next) {
            front.next = new Node<T>(q.data, front.next);  //在p结点之后插入值为q.data的结点
            front = front.next;
        }
    }

    //将list单链表中的所有结点复制插入到this单链表第i个结点之前
    public void insert(int i, SinglyLinkedList<T> list) {
        Node<T> p = head;
        for (int j = 0; j < i && p.next != null; j++)          //寻找插入位置
            p = p.next;                                    //循环停止时，p指向第i-1结点或最后一个结点
        this.insert(p, list);                               //复制插入list链表到p结点之后
    }

    //将list单链表中所有结点复制添加到当前单链表最后
    public void append(SinglyLinkedList<T> list) {
        Node<T> rear = head;
        while (rear.next != null)                            //寻找链尾结点作为插入位置
            rear = rear.next;
        insert(rear, list);                                //在rear结点之后复制插入list链表
    }
/*或者
    public void append(SinglyLinkedList<T> list)
    {
        insert(this.getLast(), list);
    }    */

    //删除从第i（≥0）个结点开始、长度为n（≥1）的子表
    //若i<0或n<0，抛出异常；若i>表长或n=0，不删除；若n超长，删除至表尾的子表
    public void remove(int i, int n) {
        if (i < 0) throw new IndexOutOfBoundsException(i + "");    //抛出序号越界异常
        if (n < 0) throw new IllegalArgumentException(i + "");     //抛出无效参数异常
        Node<T> front = head;
        for (int j = 0; front != null && j < i; j++)             //寻找待删除子表首结点的前驱结点
            front = front.next;
        if (front == null)                                   //i越界，不删除
            return;
        Node<T> p = front.next;
        for (int j = 0; p != null && j < n; j++)                 //寻找待删除子表之后的结点
            p = p.next;
        front.next = p;                                    //删除front后继结点至p前驱结点之间的子表
    }

    //返回this单链表首次出现的与pattern匹配的子表首结点，
    //查找不成功、当前或pattern单链表为空时，返回null
    public Node<T> index(SinglyLinkedList<T> pattern) {
        return index(head.next, pattern);
    }

    //返回this单链表从start结点开始首次出现的与pattern匹配的子表首结点。
    //BF模式匹配。逐个结点比较，一重循环。
    public Node<T> index(Node<T> start, SinglyLinkedList<T> pattern) {
        if (pattern.isEmpty())                             //若无此句，则返回start，错误
            return null;
        Node<T> p = start, q = pattern.head.next;
        while (p != null && q != null) {
            if (p.data.equals(q.data))                     //继续比较下一个结点
            {
                p = p.next;
                q = q.next;
            } else                                           //一次匹配失败
            {
                start = start.next;                          //this继续匹配下个子表
                p = start;
                q = pattern.head.next;                       //pattern回退至表头
            }
        }
        if (q == null)                                       //匹配成功
            return start;                                  //返回匹配子表首结点
        return null;                                       //this空或全部匹配失败
    }

    /* 也可，    //BF模式匹配。逐个子表匹配，二重循环。
    public Node<T> index(Node<T> start, SinglyLinkedList<T> pattern)
    {
        if (pattern.isEmpty())                             //若无此句，则返回start，错误
            return null;
        while (start!=null)
        {
            Node<T> p=start, q=pattern.head.next;
            while (p!=null && q!=null && p.data.equals(q.data))//继续比较下一个结点
            {
                p=p.next;
                q=q.next;
            }
            if (q==null)                                   //匹配成功
                return start;                              //返回匹配子表首结点
            start=start.next;
        }
        return null;                                       //this空或全部匹配失败
    }    */

    //删除所有与pattern匹配的子表，BF模式匹配查找到再删除
    public void removeAll(SinglyLinkedList<T> pattern) {
        System.out.print("将" + this.toString() + "中" + pattern.toString() + "全部删除");
        if (pattern.isEmpty())                             //若无此句，则死循环，错误
            return;
        Node<T> start = this.head.next, front = this.head;
        while (start != null) {
            Node<T> p = start, q = pattern.head.next;
            while (p != null && q != null && p.data.equals(q.data)) //一次匹配
            {
                p = p.next;
                q = q.next;
            }
            if (q != null)                                   //匹配失败，进行下次匹配
            {
                front = start;
                start = start.next;
            } else                                           //匹配成功，删除该匹配子表
            {
                front.next = p;
                start = p;
            }
        }
        System.out.println("的结果是" + this.toString());
    }

    //将this单链表中所有与pattern匹配的子表替换为dest子表。当dest为空时，相当于删除匹配子表。
    //包含BF模式匹配、删除匹配子表、复制插入子表算法
    public void replaceAll(SinglyLinkedList<T> pattern, SinglyLinkedList<T> dest) {
        System.out.print("将" + this.toString() + "中" + pattern.toString() + "全部替换为" + dest.toString());
        if (pattern.isEmpty())                             //若无此句，则将dest插入到start结点之后，错误
            return;
        Node<T> start = this.head.next, front = this.head;     //front是start的前驱结点
        while (start != null)                                //start指向每次匹配的起始结点
        {
            Node<T> p = start, q = pattern.head.next;
            while (p != null && q != null && p.data.equals(q.data))//一次匹配的多次比较
            {
                p = p.next;
                q = q.next;
            }
            if (q != null)                                   //匹配失败，进行下次匹配
            {
                front = start;
                start = start.next;
            } else                                           //匹配成功，替换该匹配子表
            {
                front.next = p;
                start = p;
                Node<T> d = dest.head.next;
                while (d != null)                            //将dest剩余结点深拷贝插入到this单链表front之后p之前
                {
                    front.next = new Node<T>(d.data, p);
                    front = front.next;
                    d = d.next;
                }
            }
        }
        System.out.println("的结果是" + this.toString());
    }

    //以下第4章，递归方法
/*    public String toString()                               //返回单链表描述字符串
    {
        return "("+ this.toString(this.head.next) +")";
    }
    public String toString(Node<T> p)                      //返回从p结点开始的子表描述字符串，递归方法
    {
         if (p==null)
             return "";
         String str=p.data.toString();
         if (p.next!=null)
             str+=", ";
         return str+this.toString(p.next);                 //递归调用
    }

    public SinglyLinkedList(T[] element)                  //由指定数组中的多个对象构造单链表
    {
        this();                                            //创建空单链表，只有头结点
        this.head.next = create(element,0);
    }
    private Node<T> create(T[] element, int i)            //由指定数组构造单链表，递归方法
    {
        Node<T> p=null;
        if (i<element.length)
        {
            p = new Node<T>(element[i], null);
            p.next = create(element, i+1);
        }
        return p;
    }

    public SinglyLinkedList(SinglyLinkedList<T> list)      //深拷贝，以单链表list构造新的单链表
    {
        this();
        this.head.next = copy(list.head.next);
    }
    private Node<T> copy(Node<T> p)                        //复制单链表，递归方法
    {
        Node<T> q=null;
        if (p!=null)
        {
            q = new Node<T>(p.data, null);
            q.next = copy(p.next);
        }
        return q;
    }

    //习题4
    public int length()                                    //返回单链表长度
    {
        return length(this.head.next);
    }
    public int length(Node<T> p)                           //返回从p结点开始的单链表长度，递归方法
    {
        if (p==null)
            return 0;
        return 1+length(p.next);                           //递归调用
    }

    public boolean equals(Object obj)                      //比较两条单链表是否相等
    {
        if (obj == this)
            return true;
        if (obj instanceof SinglyLinkedList)
        {
            SinglyLinkedList<T> list = (SinglyLinkedList<T>)obj;
            return equals(this.head.next, list.head.next);
        }
        return false;
    }
    private boolean equals(Node<T> p, Node<T> q)           //比较两条单链表是否相等，递归方法
    {
        return p==null && q==null ||
               p!=null && q!=null && p.data.equals(q.data) && equals(p.next, q.next);
    }
    */

    //以下第8章 8.2.1 顺序查找，散列表中用

    //顺序查找关键字为key元素，返回首次出现的元素，若查找不成功返回null
    //key可以只包含关键字数据项，由T类的equals()方法提供比较对象相等的依据
    public T search(T key) {
        if (key == null)
            return null;
        for (Node<T> p = this.head.next; p != null; p = p.next)
            if (p.data.equals(key))
                return p.data;
        return null;
    }

    public boolean contain(T key)                          //判断线性表是否包含关键字为key元素
    {
        return this.search(key) != null;                     //以查找结果获得判断结果
    }

    public void remove(T x)                   //删除首次出现的值为x的结点，若没找到指定结点则不删除。O(n)
    {
        if (x == null)
            return;
        Node<T> front = this.head, p = front.next;
        while (p != null && !p.data.equals(x)) {
            front = p;
            p = p.next;
        }
        if (p != null)
            front.next = p.next;                     //头删除、中间/尾删除
    }
    //思考题：remove(x)方法能否调用search(x)方法定位？为什么？

    //以下是第8章习题
    public void removeAll(T x)                           //删除所有值为x的结点
    {
        if (x != null) {
            Node<T> front = this.head, p = front.next;
            while (p != null)
                if (p.data.equals(x)) {
                    front.next = p.next;                   //删除p结点
                    p = front.next;
                } else {
                    front = p;
                    p = p.next;
                }
        }
    }

    public void replace(T x, T y)                          //将首次出现的元素x替换为y，O(n)
    {
        if (x != null && y != null)
            for (Node<T> p = this.head.next; p != null; p = p.next)
                if (p.data.equals(x)) {
                    p.data = y;
                    return;
                }
    }

    public void replaceAll(T x, T y)                //将所有值为x元素替换为y，O(n)
    {
        if (x != null && y != null)
            for (Node<T> p = this.head.next; p != null; p = p.next)
                if (p.data.equals(x))
                    p.data = y;
    }

    //第10章，10.2 实现迭代器
    public java.util.Iterator<T> iterator()                //返回Java迭代器对象
    {
        return new SinglyIterator();
    }

    private class SinglyIterator implements java.util.Iterator<T> //私有内部类，实现迭代器接口
    {
        Node<T> current = SinglyLinkedList.this.head;        //当前结点，初值为外部类单链表头结点
        Node<T> front = null;                                //当前结点的前驱结点

        public boolean hasNext()                           //若有后继元素，返回true
        {
            return this.current != null && this.current.next != null;
        }

        public T next()                                    //返回后继元素
        {
            if (this.hasNext()) {
                this.front = this.current;
                this.current = this.current.next;
                return this.current.data;
            } else throw new java.util.NoSuchElementException();  //抛出无此元素异常
        }

        public void remove()                               //删除迭代器对象表示的集合当前元素
        {
            if (this.front != null) {
                this.front.next = this.current.next;       //删除当前结点
                this.current = this.front;
                this.front = null;                           //设置不能连续删除
            } else throw new IllegalStateException(); //抛出无效状态异常
//            throw new UnsupportedOperationException();     //不支持该操作，抛出异常
        }
    }//内部类结束
}
