import java.util.Set;
import java.util.HashSet;

class KthToLast {
    public static void main(String[ ] args) {
        Node n = new Node(1);
        n.appendToTail(1);
        n.appendToTail(2);
        n.appendToTail(3);
        n.appendToTail(1);
        n.appendToTail(1);
        n.appendToTail(3);
        printLinkedList(n);
        Node result = kthToLast_onePass(n, 1);
        if(result != null) System.out.print(result.d);
        else System.out.print("Invalid");
    }
    
    static Node kthToLast(Node head, int k) {
        Node n = head;
        int size = 1;
        while(n.next != null) {
            size++;
            n = n.next;
        }
        int pos = size - k;
        int curr = 1;
        n = head;
        while(n != null) {
            if(curr == pos) return n;
            curr++;
            n = n.next;
        }
        return null;
    }
    
    static Node kthToLast_onePass(Node head, int k) {
        Node n1 = head;
        Node n2 = head;
        int distance = 0;
        while(n2.next != null) {
            if(distance == k) n1 = n1.next;
            n2 = n2.next;
            if(distance < k) distance++;
        }
        if(distance < k) return null;
        else return n1;
    }
    

    static class Node {
        Node next = null;
        int d;
        Node(int d) {
            this.d = d;
        }
        
        void appendToTail(int d) {
            Node end = new Node(d);
            Node n = this;
            while(n.next != null) {
                n = n.next;
            }
            n.next = end;
        }
    }
    
    static void printLinkedList(Node head) {
        Node n = head;
        while(n.next != null) {
            System.out.print("(" + n.d + ")->");
            n = n.next;
        }
        System.out.println("(" + n.d + ")");
    }
}
