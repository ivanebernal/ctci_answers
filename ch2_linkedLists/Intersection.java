import java.util.HashSet;
import java.lang.Math;

class Intersection {
    public static void main(String[ ] args) {
        Node n1 = new Node(1);
        n1.appendToTail(2);
        n1.appendToTail(3);
        n1.appendToTail(2);
        n1.appendToTail(5);
        n1.appendToTail(6);
        Node n2 = new Node(3);
        n2.appendToTail(2);
        n2.next.next = n1.next.next.next;
        printLinkedList(n1);
        printLinkedList(n2);
        printLinkedList(getIntersection_noExtraMem(n1, n2));
    }
    
    static Node getIntersection(Node h1, Node h2) {
        Node n1 = h1;
        Node n2 = h2;
        HashSet<Node> visited = new HashSet();
        while(n1 != null) {
            visited.add(n1);
            n1 = n1.next;
        }
        while(n2 != null) {
            if(visited.contains(n2)) return n2;
            n2 = n2.next;
        }
        return null;
    }
    
    static Node getIntersection_noExtraMem(Node h1, Node h2) {
        Node n1 = h1;
        Node n2 = h2;
        int size1 = getSize(n1);
        int size2 = getSize(n2);
        int diff = Math.abs(size1-size2);
        int min = Math.min(size1, size2);
        int offset = 0;
        while(offset < diff) {
            if(size2 == min) {
                n1 = n1.next;
            } else {
                n2 = n2.next;
            }
            offset++;
        }
        while(n1 != null && n2 != null) {
            if(n1 == n2) return n1;
            n1 = n1.next;
            n2 = n2.next;
        }
        return null;
    }
    
    static int getSize(Node h) {
        Node n = h;
        int result = 0;
        while(n != null) {
            result++;
            n = n.next;
        }
        return result;
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