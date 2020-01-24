class SparseSearch {
    public static void main(String[] args) {
        String[] arr = {"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""};
        String str = "car";
        int result = searchSparse(arr, str);
        System.out.println(result);
    }

    private static int searchSparse(String[] arr, String str) {
        return binarySearch(arr, str, 0, arr.length - 1);
    }

    private static int binarySearch(String[] arr, String str, int start, int end) {
        if(start > end) return -1;
        int mid = (start + end) / 2;
        String middle = arr[mid];
        if(str.equals(middle)) return mid;
        if("".equals(middle)) {
            int result = binarySearch(arr, str, start, mid - 1);
            if(result == -1) {
                result = binarySearch(arr, str, mid + 1, end);
            }
            return result;
        } else if(str.compareTo(middle) < 0) {
            return binarySearch(arr, str, mid + 1, end);
        } else {
            return binarySearch(arr, str, start, mid - 1);
        }
    }
}