import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

class AnagramSort {
    public static void main(String[] args) {
        String[] anagrams = {"abc","cats", "dec", "cab", "ced", "bac", "acts"};
        sortAnagram(anagrams);
        printStrArr(anagrams);
    }

    private static void sortAnagram(String[] anagrams) {
        Map<String, List<String>> valMap = new HashMap<String, List<String>>();
        for(String anagram: anagrams) {
            String sorted = sortString(anagram);
            if(valMap.get(sorted) == null) {
                valMap.put(sorted, new ArrayList<String>());
            }
            valMap.get(sorted).add(anagram);
        }
        int sortedIndex = 0;
        for(String key : valMap.keySet()) {
            List<String> angs = valMap.get(key);
            if(angs != null) {
                for(String ang : angs) {
                    anagrams[sortedIndex] = ang;
                    sortedIndex++;
                }
            }
        }
    }

    private static void sortAnagramInPlace(String[] anagrams) {
        Map<String, String> valMap = new HashMap<String, String>();
        populateValMap(anagrams, valMap);
        quickSort(anagrams, valMap, 0, anagrams.length - 1);
    }

    private static void quickSort(String[] anagrams, Map<String, String> valMap, int left, int right) {
        int index = partition(anagrams, valMap, left, right);
        if(left < index - 1) {
            quickSort(anagrams, valMap, left, index - 1);
        }
        if(right > index) {
            quickSort(anagrams, valMap, index, right);
        }
    }

    private static int partition(String[] angs, Map<String, String> valMap, int left, int right) {
        String pivot = angs[(left + right) / 2];
        while(left <= right) {
            while(valMap.get(angs[left]).compareTo(valMap.get(pivot)) < 0) {
                left++;
            }
            while(valMap.get(angs[right]).compareTo(valMap.get(pivot)) > 0) {
                right--;
            }
            if(left <= right) {
                swap(angs, left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private static void populateValMap(String[] angs, Map<String, String> valMap) {
        for(String ang : angs) {
            valMap.put(ang, sortString(ang));
        }
    }

    private static void swap(String[] arr, int i, int j) {
        String temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private static void printStrArr(String[] arr) {
        for(String str : arr) {
            System.out.print(str + ", ");
        }
        System.out.println();
    }
}