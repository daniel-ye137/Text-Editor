package util;


import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/**
 * A class that stores data values and maps them to certain keys, which cannot be null.
 * @param <K> A class whose instances are used as key values 
 * @param <V> A class whose instances are stored as data
 */
public class HashTable<K, V> implements Map<K, V> {
	private Object[] buckets;
	private int size;
	private int numBuckets;
	static final double loadFactor = .75;
	/**
    * Initialize a hash table with 2 empty buckets.
    */
   public HashTable() {
	   buckets = new Object[2];
	   numBuckets = 2;
	   size = 0;
	   for(int i = 0; i < numBuckets; i++) {
		   buckets[i] = new LinkedList<HashNode<K,V>>();
	   }	   
   }
   /**
    * Initialize a hash table with the specified number of empty buckets that is not 0.
    * @param initNumBuckets the number of empty buckets
    */
   public HashTable(int initNumBuckets) {
	   if (initNumBuckets == 0) {
		   initNumBuckets = 1;
		   }
	   buckets = new Object[initNumBuckets];
	   numBuckets = initNumBuckets;
	   size = 0;
	   for(int i = 0; i < numBuckets; i++) {
		   buckets[i] = new LinkedList<HashNode<K,V>>();
	   }
   }
   /**
    * Returns the number of buckets in the hash table.
    * @return the number of buckets in the hash table
    */
   public int getNumBuckets() {
	   return numBuckets;
   }
   /**
    * Returns the index of the bucket corresponding to the key based on a hash function.
    * @param key a key that a certain value is mapped to
    * @return the index of the bucket where the key and its corresponding value will be stored
    */
   private int getIndex(Object key) {
	   if(key == null) {
		   throw new NullPointerException();
	   }
	   int hash = 7;
	   hash += 31 * 7 + key.hashCode();
	   return Math.abs(hash % numBuckets);
   }
   /**
    * Doubles the amount of buckets whenever the load factor exceeds .75.
    */
   private void resize() {
	   size = 0;
	   Object[] temp = buckets;
	   buckets = new Object[numBuckets*= 2];
	   for(int j = 0; j < buckets.length; j ++) {
		   buckets[j] = new LinkedList<HashNode<K,V>>();
	   }
	   for(int i = 0; i < temp.length; i++) {
		   LinkedList<HashNode<K,V>> list = ((LinkedList<HashNode<K,V>>)temp[i]);
		   for(int j = 0; j < list.getSize(); j++) {
			   HashNode<K,V> node = list.getNode(j).data;
			   put(node.key, node.value);
		   }
 	   }
	   
   }
   /**
    * Returns the number of mapped values in the hash table.
    * @return the number of mapped values in the table
    */
   @Override
   public int size() {
      return size;
   }
   /**
    * Returns whether or not there are any mapped values in the hash table.
    * @return true if there are no mapped values in the table
    */
   @Override
   public boolean isEmpty() {
      return size == 0;
   } 
   /**
    * Returns whether or not the table has a value mapped to a certain key
    * @param key the key being checked for in the table
    * @return true if the table has a value mapped to key
    */
   @Override
   public boolean containsKey(Object key) {
	   if(key == null) {
		   throw new NullPointerException();
	   } 
      return get(key) != null;
   }
   /**
    * Returns whether or not the table contains a certain value
    * @param value the value being checked for in the table
    * @return true if the table contains value
    */
   @Override
   public boolean containsValue(Object value) {
	   for(int i = 0; i < buckets.length; i++) {
		   LinkedList<HashNode<K,V>> list = ((LinkedList<HashNode<K,V>>)buckets[i]);
		   for(int j = 0; j < list.getSize(); j++) {
			   HashNode<K,V> node = list.getNode(j).data;
			   if(node.value.equals(value)) {
				   return true;
			   }
		   }
 	   }
	   return false;
   }
   /**
    * Returns the value mapped to a certain key and returns null if no value is mapped.
    * @param key the key that the value is mapped to
    * @return the value mapped to the key
    */
   @Override
   public V get(Object key) {
      int index = getIndex(key);
      LinkedList<HashNode<K,V>> search = (LinkedList<HashNode<K,V>>) buckets[index];
      for(int i = 0; i < search.getSize(); i++) {
    	  if(search.getNode(i).data.key.equals(key)) {
    		  return search.getNode(i).data.value;
    	  }
      }
      return null;
   }
   /**
    * Maps a specified value to a specified non-null key in the hash table and returns the previous value mapped to the same key.
    * @param key the key that the value should be mapped to
    * @param value the value that should be stored in the table
    * @return the value previously mapped to key or null if no value was mapped to key
    */
   @Override
   public V put(K key, V value) {
      int index = getIndex(key);
      LinkedList<HashNode<K,V>> target = (LinkedList<HashNode<K,V>>) buckets[index];
      if(!containsKey(key)) {
    	  size += 1;
    	  target.addAtHead(new HashNode<K, V>(key, value));
    	  if((double)size/(double)numBuckets >= .75) {
    		  resize();
    	  }
    	  
    	  return null;
      }
      if(containsKey(key)) {
    	  size += 1;
    	  V temp = remove(key);
    	  target.addAtHead(new HashNode<K, V>(key, value));
    	  return temp;
      }
      return null;
   }
   /**
    * Removes a value mapped to a certain key and returns the value.
    * @param key the key that the value being removed is mapped to
    * @return returns the value being removed or null if the value is not in the table
    */
   @Override
   public V remove(Object key) {
      int index = getIndex(key);
      LinkedList<HashNode<K,V>> target = (LinkedList<HashNode<K,V>>) buckets[index];
      for(int i = 0; i < target.getSize(); i++) {
    	  if(target.getNode(i).data.key.equals(key)) {
    		  size -= 1;
    		  HashNode<K,V> value = target.deleteAt(i);
    		  return (value.value);
    	  }
      }
      return null;
   }
   /**
    * Places all the values in a given map into the hash table.
    * @param m a map of values where the keys are subclasses of the keys of this and the values are subclasses of the values of this
    */
   @Override
   public void putAll(Map<? extends K, ? extends V> m) {
	   for (K key : (K[])m.keySet().toArray()) {
		   put(key, m.get(key));
	   }
   }
   /**
    * Empties the hash table.
    */
   @Override
   public void clear() {
      buckets = new Object[numBuckets];
      size = 0;
      for(int i = 0; i < numBuckets; i++) {
		   buckets[i] = new LinkedList<HashNode<K,V>>();
	  }
   }
   /**
    * Returns a set containing all the keys contained in the hash table.
    * @return a set containing all the keys contained in the hash table
    */
   @Override
   public Set<K> keySet() {
      return new KeySet<K>();
   }
   public class KeySet<K> implements Set<K> {

	@Override
	public boolean add(K e) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addAll(Collection<? extends K> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public boolean contains(Object o) {
		return containsValue(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Returns whether or not the key set is empty.
	 * @return returns true if the key set is empty and false if it is not
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<K> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}
	/**
	 * Returns the number of values stored in the hash table.
	 * @return the number of values in the hash table
	 */
	@Override
	public int size() {
		return size;
	}
	/**
	 * Returns an array containing all the keys stored in the key set.
	 * @return an array of all the keys stored in the key set.
	 */
	@Override
	public Object[] toArray() {
		Object[] keys = new Object[size];
		int index = 0;
		for(int i = 0; i < buckets.length; i++) {
			   LinkedList<HashNode<K,V>> list = ((LinkedList<HashNode<K,V>>)buckets[i]);
			   for(int j = 0; j < list.getSize(); j++) {
				   HashNode<K,V> node = list.getNode(j).data;
				   if(node.key != null) {
					   keys[index] = node.key;
					   index++;
				   }
			   }
	 	   }
		return keys;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
   }

   /*
    * You are not required to implement the values() or entrySet() operations.
    */
   @Override
   public Collection<V> values() {
      throw new UnsupportedOperationException();
   }

   @Override
   public Set<Map.Entry<K, V>> entrySet() {
      throw new UnsupportedOperationException();
   }

}
