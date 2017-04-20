/**
 * Created by Ramzan Dieze on 26/03/2017.
 */

// Test.java

import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        Comparator<String> comparator = new StringLengthComparator();
        PriorityQueue<String> queue = new PriorityQueue<String>(10, comparator);
        queue.add("short");
        System.out.println(queue);
        queue.add("mediu");
        System.out.println(queue);
        queue.add("medium7709");
        System.out.println(queue);
        queue.add("medium");
        System.out.println(queue);
        queue.add("one");
        System.out.println(queue);
        queue.add("medium77");
        System.out.println(queue);
        queue.add("medium7");
        System.out.println(queue);
//        while (queue.size() != 0)
//        {
//            System.out.println(queue.remove());
//        }
    }
}


class StringLengthComparator implements Comparator<String> {
    @Override
    public int compare(String x, String y) {
        if (x.length() < y.length()) {
            return -1;
        }
        if (x.length() > y.length()) {
            return 1;
        }
        return 0;
    }
}