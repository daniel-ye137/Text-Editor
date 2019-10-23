package util;

import java.util.Collection;

/**
 * A collection of elements of type E for which the only operation is a
 * probabilistic membership test.
 */
public class BloomFilter<E> {
	private byte[] bitArray;
	private int numHashFunctions;
	/**
	 * Create a new Bloom filter with {@code elems} inside. The bit array is of
	 * length 8 * numBytes. The Bloom filter uses the specified number of hash
	 * functions.
	 * 
	 * @param elems            The collection of elements to be added to this filter
	 * @param numBytes         The length of the byte array representing bit array
	 * @param numHashFunctions The number of hash functions to be used in this
	 *                         filter (less than 27)
	 */
	public BloomFilter(Collection<E> elems, int numBytes, int numHashFunctions) {
		// TODO implement
		this.bitArray = new byte[numBytes * 8];
		this.numHashFunctions = numHashFunctions;
		for (E elem : elems) {
			insert(elem);
		}
	}

	/**
	 * Add {@code elem} to the Bloom filter.
	 */
	public void insert(E elem) {
		// TODO implement
		int[] indexes = hash(elem);
		for (int index : indexes) {
			bitArray[index] = 1;
		}
	}

	/**
	 * Check whether {@code elem} might be in the collection.
	 */
	public boolean mightContain(E elem) {
		// TODO implement
		int[] indexes = hash(elem);
		for (int index : indexes) {
			if (bitArray[index] == 0) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Multiplicative hash function that relies on {@code elem} being a String to create different hashes by appending
	 * a different character per hash.
	 * 
	 * @param elem            String element to be hashed a number of times (based on BloomFilter properties)
	 * @return                Array of indexes that will be used to show existence of element
	 */
	public int[] hash(E elem) {
		int[] indexes = new int[numHashFunctions];
		String entry = (String)elem;
		for (int i = 0; i < numHashFunctions; i++) {
			int hash = (int)(Math.pow(2, 31) * ((entry.hashCode() * 0.61803398875) / Math.pow(2, 31)) % bitArray.length);
			indexes[i] = Math.abs(hash);
			entry += (char)(97 + i);
		}
		return indexes;
	}

}
