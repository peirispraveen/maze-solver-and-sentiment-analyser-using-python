import java.io.*;

public class LinkedList {
    Node head;

    public static LinkedList insert(LinkedList list, String data, int sentiment) {
        Node newNode = new Node(data, sentiment);
        newNode.next = null;

        if (list.head == null) {
            list.head = newNode;
        } else {
            Node last = list.head;

            while (last.next != null) {
                last = last.next;
            }

            last.next = newNode;
        }
        return list;
    }

    public int countWordOccurrences(String word) {
        int count = 0;
        Node currNode = head;
        while (currNode != null) {
            String[] words = currNode.data.split("\\s+"); // Separating words with spaces
            for (String w : words) {
                if (w.equalsIgnoreCase(word)) {
                    count++;  // Counting how many times the word is repeated
                }
            }
            currNode = currNode.next;
        }
        return count;
    }

    public int calculateSentiment(String word) {
        int positiveCount = 0;
        int negativeCount = 0;

        Node currNode = head;
        while (currNode != null) {
            String[] words = currNode.data.split("\\s+");
            for (String w : words) {
                if (w.equalsIgnoreCase(word)) {
                    if (currNode.sentiment == 1) {  // Positive sentiment values are counted
                        positiveCount++;
                    } else if (currNode.sentiment == 0) {  // Negative sentiment values are counted
                        negativeCount++;
                    }
                }
            }
            currNode = currNode.next;
        }

        if (positiveCount > negativeCount) {
            return 1;
        } else if (positiveCount < negativeCount) {
            return 0;
        } else {
            return -1;
        }
    }
}

class Node {
    String data;
    int sentiment;
    Node next;

    Node(String d, int s) {
        data = d;
        sentiment = s;
        next = null;
    }
}
