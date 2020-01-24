import java.util.ArrayList;
import java.util.List;

class SortedSearchNoSize {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < 10; i++) {
            list.add(i);
        }
        Listy listy = new Listy(list);
        int n = 4;
        int result = searchNoSize(listy, n);
        System.out.println(result);
    }

    private static int searchNoSize(Listy listy, int n) {
        int i = 1;
        int previ = 0; 
        while(listy.get(i) != -1 && listy.get(i) < n) {
            previ = i;
            i = i * 2;
        }
        return binarySearch(listy, n, previ, i);
    }

    private static int binarySearch(Listy listy, int n, int start, int end) {
        if(start > end) return -1;
        int mid = (start + end) / 2;
        if(listy.get(mid) == n) return mid;
        if(listy.get(mid) < n && listy.get(mid) != -1) {
            return binarySearch(listy, n, mid + 1, end);
        } else {
            return binarySearch(listy, n, start, mid - 1);
        }
    }
}

class Listy {
    List<Integer> list;

    public Listy(List<Integer> list) {
        for(int i : list) {
            if(i < 0) throw new IllegalArgumentException("Only positive integers allowed in Listy");
        }
        this.list = list;
    }

    public int get(int i) {
        if(i >= list.size()) return -1;
        else return list.get(i);
    }
}