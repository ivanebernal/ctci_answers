import java.util.Set;
import java.util.HashSet;

class RemoveDuplicates {
    public static void main(String[ ] args) {
        Node n = new Node(1);
        n.appendToTail(1);
        n.appendToTail(2);
        n.appendToTail(3);
        n.appendToTail(1);
        n.appendToTail(1);
        n.appendToTail(3);
        printLinkedList(n);
        removeDuplicates_noExtraMem(n);
        printLinkedList(n);
    }
    
    static void removeDuplicates(Node head) {
        Node n = head;
        Set<Integer> data = new HashSet();
        data.add(n.d);
        while(n != null && n.next != null) {
            if(!data.add(n.next.d)) {
                n.next = n.next.next;
            } else {
                n = n.next;
            }
        }
    }
    
    static void removeDuplicates_noExtraMem(Node head) {
        Node n1 = head;
        while(n1 != null && n1.next != null) {
            Node n2 = n1;
            while(n2.next != null) {
                if(n2.next.d == n1.d) {
                    n2.next = n2.next.next;
                } else {
                    n2 = n2.next;
                }
            }
            n1 = n1.next;
        }
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
