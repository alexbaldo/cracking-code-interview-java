package collections;

/**
 * Provides an implementation of a string builder.
 * @author Alejandro Baldominos <me@bal.do>
 */
public class StringBuilder {

    // The string separator.
    private String sep;

    // The underlying list of strings.
    private ArrayList<String> list;

    // The total length of the string (excluding separators).
    private int length;

    /**
     * Default constructor.
     * Initializes the string builder with an empty separator.
     */
    public StringBuilder () {
        this("");
    }

    /**
     * Custom constructor.
     * Initializes the string builder with a user-specified separator.
     */
    public StringBuilder (String sep) {
        this.sep = sep;
        this.list = new ArrayList<String>();
    }

    /**
     * Appends a new string.
     */
    public void append (String s) {
        this.list.add(s);
        this.length += s.length();
    }

    /**
     * Returns the built string.
     * @return the resulting string.
     */
    public String toString () {
        // Handle the case of the empty string.
        if (this.list.size() == 0) return "";

        // The length of the resulting string.
        int length = this.length + this.sep.length() * (this.list.size() - 1);

        // Array that will store the characters and cursor to write in the array.
        char[] arr = new char[length];
        int pos = 0;
        
        // Iterate through strings in the list.
        for (String s : this.list) {
            // Copies the string character by character.
            for (int i = 0; i < s.length(); i++) {
                arr[pos++] = s.charAt(i);
            }

            // Writes the separator (except for the last string).
            if (pos < length) {
                for (int i = 0; i < this.sep.length(); i++) {
                    arr[pos++] = this.sep.charAt(i);
                }
            }
        }

        return new String(arr);
    }

    /**
     * Test case.
     */
    public static void main (String[] args) {
        StringBuilder sb = new StringBuilder(", ");
        sb.append("Hola");
        sb.append("mundo");
        sb.append("¿qué tal?");
        System.out.println(sb);
    }
}