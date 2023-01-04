package c01_arrays_strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Determines if a string has all unique characters.
 * @author Alejandro Baldominos <me@bal.do>
 */
public class IsUnique {

    /**
     * This proceeds by inserting all of the characters to a 
     * set and comparing the length of the string with the size
     * of the set. If both differs, then there are repeated
     * elements.
     * 
     * @complexity O(n)
     * 
     * @param s the string whose uniqueness is being tested.
     * @return true if the string has unique chars, false otherwise
     */
    public static boolean isUniqueWithSet (String s) {
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) set.add(s.charAt(i));
        return set.size() == s.length();
    }

    /**
     * This proceeds by using an UTF-8 bitmap to mark
     * those existing characters. If a character was
     * previously marked, then it is repeated.
     * This assumes that the string contains only
     * ASCII characters.
     * 
     * @complexity O(n)
     * 
     * @param s the string whose uniqueness is being tested.
     * @return true if the string has unique chars, false otherwise
     */
    public static boolean isUniqueWithBitmap (String s) {
        boolean[] bitmap = new boolean[256];
        for (int i = 0; i < s.length(); i++) {
            int chr = (int) s.charAt(i);
            if (bitmap[chr]) return false;
            bitmap[chr] = true;
        }
        return true;
    }

    /**
     * This proceeds by sorting the string and then
     * checking whether two consecutive values are
     * identical. If that is not the case, then the
     * string has unique characters.
     * 
     * @complexity O(n * logn)
     * 
     * @param s the string whose uniqueness is being tested.
     * @return true if the string has unique chars, false otherwise
     */
    public static boolean isUniqueWithSort (String s) {
        char[] arr = s.toCharArray();
        Arrays.sort(arr);
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == arr[i+1]) return false;
        }
        return true;
    }

    /**
     * Test case.
     */
    public static void main (String[] args) {
        System.out.println("Testing string 'javadoc'");
        System.out.println("  isUniqueWithSet: " + isUniqueWithSet("javadoc"));
        System.out.println("  isUniqueWithBitmap: " + isUniqueWithBitmap("javadoc"));
        System.out.println("  isUniqueWithSort: " + isUniqueWithSort("javadoc"));

        System.out.println("Testing string 'python'");
        System.out.println("  isUniqueWithSet: " + isUniqueWithSet("python"));
        System.out.println("  isUniqueWithBitmap: " + isUniqueWithBitmap("python"));
        System.out.println("  isUniqueWithSort: " + isUniqueWithSort("python"));
    }
    
}
