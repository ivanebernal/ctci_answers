class StringRotation {
    public static void main(String[ ] args) {
        String s1 = "waterbottle";
        String s2 = "erbottlewat";
        System.out.print(isRotation(s1, s2));
    }
    
    static boolean isRotation(String s1, String s2) {
        int len = s1.length();
        if(len == s2.length() && len > 0) {
            String duplicate = s2 + s2;
            return isSubString(duplicate, s1);
        }
        return false;
    }
    
    static boolean isSubString(String s1, String s2) {
        for(int i = 0; i < s1.length() - s2.length(); i++) {
            String sub = s1.substring(i, i + s2.length());
            if(sub.equals(s2)) return true;
        }
        return false;
    }
}
