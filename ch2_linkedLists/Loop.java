import java.util.HashSet;
import java.lang.Math;

class Loop {
    public static void main(String[ ] args) {
        Node n1 = new Node(1);
        n1.appendToTail(2);
        n1.appendToTail(3);
        n1.appendToTail(4);
        n1.appendToTail(5);
        n1.appendToTail(6);
        loop(n1);
        System.out.println(findLoopStart(n1));
        System.out.println(findLoopStart_noExtraMem(n1));
        // printLinkedList(findLoopStart(n1));
    }

    static Integer findLoopStart(Node h) {
        Node n = h;
        HashSet<Node> visited = new HashSet();
        while(n != null) {
            if(visited.contains(n)) return n.d;
            visited.add(n);
            n = n.next;
        }
        return null; //No loop
    }

    static Integer findLoopStart_noExtraMem(Node h) {
        Node slow = h;
        Node fast = h;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                break;
            }
        }
        if(slow == null || fast == null) {
            return null;
        }
        fast = h;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow.d;
    }

    static void loop(Node h) {
        Node n = h;
        while(n.next != null) {
            n = n.next;
        }
        n.next = h.next.next;
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