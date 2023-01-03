package collections;

import java.util.List;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;

@SuppressWarnings("unchecked")

/**
 * Provides an implementation of a hash table.
 * @author Alejandro Baldominos <me@bal.do>
 */
public class HashTable<K, V> {

    // The underlying array.
    private List<Entry<K, V>>[] arr;
    private int buckets;

    /**
     * Default constructor.
     * Initializes the hash table with 512 buckets.
     */
    public HashTable () {
        this(512);
    }

    /**
     * Custom constructor.
     * Initializes the hash table with a user-specified number of buckets.
     */
    public HashTable (int buckets) {
        this.arr = (LinkedList<Entry<K, V>>[]) new LinkedList<?>[buckets];
        this.buckets = buckets;
    }

    /**
     * Adds a new element to the hash table.
     * @param key the key of the new element.
     * @param value the value of the new element.
     */
    public void add (K key, V value) {
        // Computes the bucket to store the item.
        int bucket = key.hashCode() % this.buckets;

        // If the bucket does not have a list, create it.
        if (this.arr[bucket] == null) {
            this.arr[bucket] = new LinkedList<Entry<K, V>>();
        }
        List<Entry<K, V>> list = this.arr[bucket];

        // If the key already exists, update its value.
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getKey() == key) {
                list.get(i).setValue(value);
                found = true;
            }
        }

        // Otherwise, add it to the list.
        if (!found) list.add(new SimpleEntry<K,V>(key, value));
    }

    /**
     * Retrieves the value of a given key.
     * @param key the key whose value must be retrieved.
     * @return the value associated to the key, null if not exists.
     */
    public V get (K key) {
        // Computes the bucket where the key must be.
        int bucket = key.hashCode() % this.buckets;
        List<Entry<K, V>> list = this.arr[bucket];

        // If the bucket does not have a list, then the key does not exist.
        if (list == null) return null;

        // Otherwise, look for the key.
        for (Entry<K, V> e : list) {
            if (e.getKey() == key) return e.getValue();
        }
        return null;
    }

    /**
     * Test case.
     */
    public static void main (String[] args) {
        // Initialize a new hash table.
        HashTable<String, Integer> ht = new HashTable<>();

        // Add some key-value pairs.
        ht.add("One", 1);
        ht.add("Two", 2);
        ht.add("Three", 3);
        ht.add("Four", 4);
        ht.add("Five", 5);

        // Retrieve a couple of existing values.
        System.out.println(ht.get("Two"));
        System.out.println(ht.get("Four"));

        // Retrieve a non-existing key, it should be null.
        System.out.println(ht.get("Six"));
    }
}