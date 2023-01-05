package c01_arrays_strings;

import collections.HashTable;
import java.util.Arrays;

/**
 * Given two strings, decides if one is a permutation of the other.
 * @author Alejandro Baldominos <me@bal.do>
 */
public class CheckPermutation {

    /**
     * This proceeds by using a counter, which is a hash table mapping 
     * characters to number of occurrences. By substracting two counters, 
     * and considering that the length of the strings are the same, we can
     * check whether they contain the same characters.
     * We can check whether there are positive elements in the counter 
     * after the subtraction. If there aren't any, then we're done.
     * 
     * @complexity O(n)
     * 
     * @param s1 the first string.
     * @param s2 the second string.
     * @return true if s1 is permutation of s2, false otherwise.
     */
    public static boolean checkPermutationWithCounter (String s1, String s2) {
        // If strings don't have the same length, they can't be permutations.
        if (s1.length() != s2.length()) return false;

        // The first string is used to increment the counter.
        HashTable<Character, Integer> counter = new HashTable<>();
        for (Character c : s1.toCharArray()) {
            Integer count = counter.get(c);
            counter.add(c, count == null ? 1 : count + 1);
        }

        // The first string is used to decrement the counter.
        for (Character c : s2.toCharArray()) {
            Integer count = counter.get(c);
            counter.add(c, count == null ? -1 : count - 1);
        }

        // If there are values different than 0, the strings are not permutations.
        for (Character c : counter) {
            if (counter.get(c) != 0) return false;
        }
        return true;
    }

    /**
     * This proceeds by sorting the two strings and comparing whether they 
     * are identical. If so, then one is a permutation of the other.
     * 
     * @complexity O(n * logn)
     * 
     * @param s1 the first string.
     * @param s2 the second string.
     * @return true if s1 is permutation of s2, false otherwise.
     */
    public static boolean checkPermutationWithSort (String s1, String s2) {
        // If strings don't have the same length, they can't be permutations.
        if (s1.length() != s2.length()) return false;

        // Strings are sorted.
        char[] a1 = s1.toCharArray();
        Arrays.sort(a1);
        char[] a2 = s2.toCharArray();
        Arrays.sort(a2);

        // Strings are permutations if they are identical after sorting.
        return Arrays.equals(a1, a2);
    }

    /**
     * Test case.
     */
    public static void main (String[] args) {
        System.out.println("Testing strings 'carmina' and 'buranas'");
        System.out.println("  checkPermutationWithCounter: " + checkPermutationWithCounter("carmina", "buranas"));
        System.out.println("  checkPermutationWithSort: " + checkPermutationWithSort("carmina", "buranas"));

        System.out.println("Testing strings 'moana' and 'manao'");
        System.out.println("  checkPermutationWithCounter: " + checkPermutationWithCounter("moana", "manao"));
        System.out.println("  checkPermutationWithSort: " + checkPermutationWithSort("moana", "manao"));
    }
    
}
