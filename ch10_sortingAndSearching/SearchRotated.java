class SearchRotated {
    public static void main(String[] args) {
        int[] input = {8, 9, 1, 2, 3, 4, 5};
        int n = 9;
        int nIndex = searchRotated1(input, n);
        System.out.println(nIndex);
    }

    //Mine: O(n)
    static int searchRotated(int[] arr, int n) {
        int start = getStartIndex(arr);
        int end = start + arr.length - 1;
        return binarySearch(arr, n, start, end);
    }

    static int binarySearch(int[] arr, int n, int start, int end) {
        if(start > end) return -1;
        int middle = (start + end) / 2;
        int middleIndex = middle % arr.length;
        if(arr[middleIndex] == n) {
            return middleIndex;
        } else if(arr[middleIndex] > n) {
            return binarySearch(arr, n, start, middle - 1);
        } else {
            return binarySearch(arr, n, middle + 1, end);
        }
    }

    static int getStartIndex(int[] arr) {
        int i = 0;
        while(i < arr.length - 2) {
            if(arr[i] > arr[i + 1]) {
                return i + 1;
            }
            i++;
        }
        return 0;
    }

    static int searchRotated1(int[] arr, int n) {
        return binarySearchRotated(arr, n, 0, arr.length - 1);
    }

    //Book O(log n)
    static int binarySearchRotated(int[] arr, int n, int start, int end) {
        if(end < start) return -1;
        int middle = (start + end) / 2;
        if(n == arr[middle]) return middle;
        if(arr[start] < arr[middle]) { //left half is ordered
            if(n >= arr[start] && n < arr[middle]) {
                return binarySearchRotated(arr, n, start, middle - 1);
            } else {
                return binarySearchRotated(arr, n, middle + 1, end);
            }
        } else if(arr[middle] < arr[start]) {
            if(n > arr[middle] && n <= arr[end]) {
                return binarySearchRotated(arr, n, middle + 1, end);
            } else {
                return binarySearchRotated(arr, n, start, middle - 1);
            }
        } else if(arr[start] == arr[middle]) {
            if(arr[middle] != arr[end]) {
                return binarySearchRotated(arr, n, middle + 1, end);
            } else {
                int result = binarySearchRotated(arr, n, start, middle - 1);
                if(result == -1) {
                    result = binarySearchRotated(arr, n, middle + 1, end);
                }
                return result;
            }
        }
        return -1;
    }
}