package chapter4.queue;

/**
 * Created by lili on 2017/7/1.
 */

public class Queue_ex
{
    public static void main(String args[])
    {
        SeqQueue<Integer> que = new SeqQueue<Integer>(5);
        que.enqueue(new Integer(10));
        que.enqueue(new Integer(20));
        System.out.println("dequeue : "+que.dequeue().toString()+"  "+que.dequeue().toString()+"  ");
        System.out.println(que.toString());
        que.enqueue(new Integer(30));
        que.enqueue(new Integer(40));
        que.enqueue(new Integer(50));
        que.enqueue(new Integer(60));
        System.out.println(que.toString());
        que.enqueue(new Integer(70));
        System.out.println(que.toString());

        LinkedQueue<Integer> q = new LinkedQueue<Integer>();
        System.out.print("enqueue: ");
        for (int i=1; i<=5; i++)
        {
            Integer intobj = new Integer(i);
            q.enqueue(intobj);
            System.out.print(intobj+"  ");
        }
        System.out.println("\n"+q.toString());

        System.out.print("dequeue : ");
        while (!q.isEmpty())
            System.out.print(q.dequeue().toString()+"  ");
        System.out.println();
    }
}
/*
dequeue : 10  20
()
(30, 40, 50, 60)
(30, 40, 50, 60, 70)
enqueue: 1  2  3  4  5
(1, 2, 3, 4, 5)
dequeue : 1  2  3  4  5


*/

