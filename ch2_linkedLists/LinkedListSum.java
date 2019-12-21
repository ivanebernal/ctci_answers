import java.util.HashSet;

class Playground {
    public static void main(String[ ] args) {
        Node n2 = new Node(9);
        n2.appendToTail(9);
        // n2.appendToTail(9);
        Node n1 = new Node(9);
        n1.appendToTail(9);
        n1.appendToTail(9);
        printLinkedList(sumDigitsReverse(n1, n2));
        printLinkedList(sumDigits(n1, n2));
    }
    
    static Node sumDigitsReverse(Node n1, Node n2) {
        Node resultH = null;
        Node resultE = null;
        int carry = 0;
        while(n1 != null || n2 != null) {
            int d1 = 0;
            int d2 = 0;
            if(n1 != null) {
                d1 = n1.d;
                n1 = n1.next;
            }
            if(n2 != null) {
                d2 = n2.d;
                n2 = n2.next;
            }
            int sum  = d1 + d2 + carry;
            carry = sum / 10;
            sum %= 10;
            if(resultH == null) {
                resultH = new Node(sum);
                resultE = resultH;
            } else {
                resultE.next = new Node(sum);
                resultE = resultE.next;
            }
        }
        if(carry > 0) {
            resultE.next = new Node(carry);
        }
        return resultH;
    }
    
    static Node sumDigits(Node n1, Node n2) {
        makeSameLength(n1, n2);
        Node n = getCarrySum(n1, n2);
        int carry = n.d/10;
        n.d %=10;
        if(carry > 0) {
            Node newH = new Node(carry);
            newH.next = n;
            return newH;
        }
        return n;
    }
    
    static void makeSameLength(Node n1, Node n2) {
        Node h1 = n1;
        Node h2 = n2;
        while(h1.next != null || h2.next != null) {
            if(h1.next == null && h2.next != null) {
                h2 = h2.next;
                addZeroToStart(n1);
            } else if(h2.next == null && h1.next != null) {
                h1 = h1.next;
                addZeroToStart(n2);
            } else {
                h1 = h1.next;
                h2 = h2.next;
            }
        }
    }
    
    static void addZeroToStart(Node n) {
        Node temp = new Node(n.d);
                temp.next = n.next;
                n.next = temp;
                n.d = 0;
    }
    
    static Node getCarrySum(Node n1, Node n2) {
        if(n1.next == null && n2.next == null) {
            return new Node(n1.d + n2.d);
        }
        Node sum = getCarrySum(n1.next, n2.next);
        int carry = sum.d/10;
        sum.d %= 10;
        Node newH = new Node(n1.d + n2.d + carry);
        newH.next = sum;
        return newH;
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