class SortedMerge {
    public static void main(String[] args) {
        Integer[] a = {1,2,4,5,7,null,null,null,null};
        Integer[] b = {3,6,8,9};
        sortedMergeBook(a, b, 5, 4);
        printArray(a);
    }

    //O(n^2)
    private static void sortedMerge(Integer[] a, Integer[] b) {
        int aIndex = 0;
        int bIndex = 0;
        while(aIndex < a.length && bIndex < b.length) {
            if(a[aIndex] == null) {
                a[aIndex] = b[bIndex];
                bIndex++;
            } else if(a[aIndex] > b[bIndex]) {
                translateArray(a, aIndex);
                a[aIndex] = b[bIndex];
                bIndex++;
            }
            aIndex++;
        }
    }

    //O(n)
    private static void sortedMergeBook(Integer[] a, Integer[] b, int lastA, int lastB) {
        int aIndex = lastA - 1;
        int bIndex = lastB - 1;
        int indexMerged = lastA + lastB - 1;
        while(bIndex >= 0) {
            if(aIndex >= 0 && a[aIndex] > b[bIndex]) {
                a[indexMerged] = a[aIndex];
                aIndex--;
            } else {
                a[indexMerged] = b[bIndex];
                bIndex--;
            }
            indexMerged--;
        }
    }

    private static void translateArray(Integer[] arr, int i) {
        int index = i;
        Integer temp = null;
        while(index < arr.length) {
            Integer current = arr[index];
            arr[index] = temp;
            temp = current;
            index++;
        }
    }

    private static void printArray(Integer[] arr) {
        for(Integer i : arr) {
            System.out.print( i + ", " );
        }
        System.out.println();
    }
}
