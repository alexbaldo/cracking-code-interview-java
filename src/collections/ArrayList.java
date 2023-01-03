package collections;

import java.util.Iterator;

@SuppressWarnings("unchecked")

/**
 * Provides an implementation of an array list.
 * @author Alejandro Baldominos <me@bal.do>
 */
public class ArrayList<T> implements Iterable<T> {

    // The underlying array.
    private T[] arr;

    // The number of elements in the list.
    private int size;

    /**
     * Default constructor.
     * Initializes the array list with an array size of 8.
     */
    public ArrayList () {
        this(8);
    }

    /**
     * Custom constructor.
     * Initializes the array list with a user-specified array size.
     */
    public ArrayList (int size) {
        this.arr = (T[]) new Object[size];
    }

    /**
     * Resizes the array when full.
     */
    private void resize () {
        T[] arr = (T[]) new Object[this.arr.length * 2];
        System.arraycopy(this.arr, 0, arr, 0, this.arr.length);
        this.arr = arr;
    }

    /**
     * Adds a new element to the end of the list.
     * @param item the item to be added to the list.
     */
    public void add (T item) {
        if (this.size == this.arr.length) this.resize();
        this.arr[this.size] = item;
        this.size++;
    }

    /**
     * Adds a new element to the i-th position of the list.
     * This might require to move further elements in the array.
     * @param item the item to be added to the list.
     * @param i the index of the new element.
     */
    public void add (T item, int i) {
        if (this.size == this.arr.length) this.resize();
        System.arraycopy(this.arr, i, this.arr, i + 1, size - i);
        this.arr[i] = item;
        this.size++;
    }

    /**
     * Retrieves the i-th element of the list.
     * @param i the index of the element to be retrieved.
     * @return the element at the i-th position.
     */
    public T get (int i) {
        if (i >= this.size) throw new ArrayIndexOutOfBoundsException();
        return (T) this.arr[i];
    }

    /**
     * Removes the i-th element of the list.
     * @param i the index of the element to be removed.
     */
    public void remove (int i) {
        if (i >= this.size) throw new ArrayIndexOutOfBoundsException();
        System.arraycopy(this.arr, i + 1, this.arr, i, size - i - 1);
        this.size--;
        this.arr[size] = null;
    }

    /**
     * Returns the size of the list.
     * @return the size of the list.
     */
    public int size () {
        return this.size;
    }

    /**
     * Returns a string representation of the array list.
     * @return a comma-separated list of values.
     */
    public String toString () {
        StringBuilder sb = new StringBuilder(", ");
        for (T item : this) {
            sb.append(item != null ? item.toString() : "");
        }
        return sb.toString();
    }

    /**
     * Provides an iterator.
     */
    @Override
    public Iterator<T> iterator () {
        return new ArrayListIterator<T>(this);
    }

    /**
     * Test case.
     */
    public static void main (String[] args) {
        // Initialize a new list with some values.
        ArrayList<Integer> l = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            l.add(i);
        }

        // Remove first, middle and last values.
        l.remove(0);
        l.remove(23);
        l.remove(11);

        // Add two items in intermediate positions.
        l.add(-1, 0);
        l.add(0, 10);

        // Print two values from the list.
        System.out.println(l.get(3));
        System.out.println(l.get(16));

        // Print the whole list.
        System.out.println(l);
    }
}

/**
 * Provides an iterator for the array list.
 */
class ArrayListIterator<T> implements Iterator<T> {

    ArrayList<T> list;
    int pos;

    /**
     * Initializes the iterator for an array list.
     * @param list the array list to be iterated.
     */
    public ArrayListIterator (ArrayList<T> list) {
        this.list = list;
    }

    @Override
    public boolean hasNext () {
        return this.pos < this.list.size();
    }

    @Override
    public T next () {
        return this.list.get(this.pos++);
    }
}