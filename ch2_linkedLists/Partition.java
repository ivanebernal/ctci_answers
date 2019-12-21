import java.util.HashSet;

class Partition {
    public static void main(String[ ] args) {
        Node n = new Node(3);
        n.appendToTail(5);
        n.appendToTail(8);
        n.appendToTail(5);
        n.appendToTail(10);
        n.appendToTail(2);
        n.appendToTail(1);
        printLinkedList(n);
        partition(n, 5);
        printLinkedList(n);
    }
    
    static void partition(Node n, int partition) {
        Node less = n.next;
        Node greater = n;
        Node previous = n;
        HashSet<Node> visited = new HashSet();
        while(less != null) {
            if(less.d < partition) {
                insertNodeAfter(greater, less.d);
                while(previous.next != less) {
                    previous = previous.next;
                }
                previous.next = less.next;
                less = less.next;
            } else {
                less = less.next;
                previous = previous.next;
            }
        }
        if(greater.d >= partition) {
            insertNodeAfter(previous, greater.d);
            deleteCurrentNode(greater);
        }
    }
    
    static void insertNodeAfter(Node n, int d) {
        Node newNode = new Node(d);
        newNode.next = n.next;
        n.next = newNode;
    }
    
    static void deleteCurrentNode(Node node) {
        node.d = node.next.d;
        node.next = node.next.next;
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
