package c01_arrays_strings;

/**
 * Replaces all spaces in a string with '%20'.
 * @author Alejandro Baldominos <me@bal.do>
 */
public class URLify {

    /**
     * This receives an array of characters (mutable string) that we
     * will modify to substitute spaces by '%20'.
     * We assume that the array holds extra space, at least to accomodate
     * the converted string.
     * 
     * @complexity O(n)
     * 
     * @param s array of characters, with padding.
     * @param l length o
     * @return true if the string has unique chars, false otherwise
     */
    public static String urlify (char[] s, int l) {
        int ns = 0;
        for (int i = 0; i < l; i++)
            if (s[i] == ' ') ns++;
        
        int ps = l - 1;             // pointer to the source.
        int pt = l - 1 + 2 * ns;    // pointer to the target.
        while (ps > 0) {
            if (s[ps] == ' ') {
                s[pt--] = '0';
                s[pt--] = '2';
                s[pt--] = '%';
                ps--;
            } else {
                s[pt--] = s[ps--];
            }
        }

        return String.copyValueOf(s);
    }

    /**
     * Test case.
     */
    public static void main (String[] args) {
        System.out.println("Testing string 'Mr John Smith'");
        System.out.println("  urlify: " + urlify("Mr John Smith\0\0\0\0".toCharArray(), 13));
    }
    
}
