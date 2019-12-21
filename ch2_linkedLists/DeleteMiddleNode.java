import java.util.Set;
import java.util.HashSet;

class DeleteMiddleNode {
    public static void main(String[ ] args) {
        Node n = new Node(1);
        n.appendToTail(1);
        n.appendToTail(2);
        n.appendToTail(3);
        n.appendToTail(1);
        n.appendToTail(1);
        n.appendToTail(3);
        printLinkedList(n);
        deleteMiddleNode(n.next.next);
        printLinkedList(n);
    }
    
    static void deleteMiddleNode(Node n) {
        if(n.next == null) return;
        n.d = n.next.d;
        n.next = n.next.next;
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
