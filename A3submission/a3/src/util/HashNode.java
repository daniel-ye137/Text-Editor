package util;
/**
 * A class that contains a key value and a data value that is stored in the buckets of a hash table.
 * @param <K> A class whose instances are used for key values
 * @param <V> A class whose instances are stored as data
 */
public class HashNode<K ,V> {
	K key;
	V value;
	HashNode<K, V>next;
	/**
	 * Creates a hash node object with a given value and key to be mapped to
	 * @param k the key that val should be mapped to
	 * @param val the value that should be mapped to k
	 */
	public HashNode(K k, V val)
	{
		key = k;
		value = val;
	}
	/*
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return "Key: " + key + ", Value: " + value + "; ";
	}
}