package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import sun.misc.VM;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import util.HashTable;
import util.HashTable.KeySet;

class HashTester {
	static HashTable<Object, Object> hashTable = new HashTable<Object, Object>();
	static HashTable<Object, Object> hashTable2 = new HashTable<Object, Object>();
	static HashTable<Object, Object> hashTable3 = new HashTable<Object, Object>();
 	static HashTable<Object, Object> paramHashTable = new HashTable<Object, Object>(0);
	static Map<Object, Object> map = new HashMap<Object, Object>();
	static KeySet keySet;
	static Object[] objArray;

	@Test
	void HashTableTester() {
		assertEquals(0, hashTable.size());
		assertEquals(0, paramHashTable.size());
		assertEquals(2, hashTable.getNumBuckets());
		assertEquals(1	, paramHashTable.getNumBuckets());
		assertEquals(true, hashTable.isEmpty());
		assertEquals(true, paramHashTable.isEmpty());
	}
	@Test
	void putTester() {
		hashTable.put("David", 13);
		hashTable.put("Dillan", 12);
		assertEquals(2, hashTable.size());
		assertEquals(4, hashTable.getNumBuckets());
		assertTrue(hashTable.containsKey("David"));
		assertTrue(hashTable.containsValue(13));
		assertTrue(hashTable.containsKey("Dillan"));
		assertTrue(hashTable.containsValue(12));
		assertFalse(hashTable.containsValue(11));
	}
	@Test
	void containsValueTester() {
		assertEquals(true, hashTable.containsValue(12));
		assertEquals(false, hashTable.containsValue("twelve"));
		assertEquals(false, hashTable.containsValue(null));
	}
	@Test
	void getTester() {
		assertEquals(13, hashTable.get("David"));
		assertEquals(12, hashTable.get("Dillan"));
		assertEquals(null, hashTable.get("oasif g"));
	}
	@Test
	void putAllTester() {
		hashTable.putAll(paramHashTable);
		assertEquals(2, hashTable.size());
		paramHashTable.put("Julia", 14);
		paramHashTable.put("David", 15);
		hashTable.putAll(paramHashTable);
		assertEquals(3, hashTable.size());
		assertEquals(15, hashTable.get("David"));
	}
	@Test
	void clearTester() {
		hashTable2.put("David", 12);
		hashTable2.put("Dillan", 13);
		assertEquals(2, hashTable2.size());
		hashTable2.clear();
		assertEquals(0, hashTable2.size());
		assertEquals(false, hashTable2.containsKey("David"));
	}
	@Test
	void containsKeyTester() {
		assertEquals(true, hashTable.containsKey("David"));
		assertEquals(false, hashTable.containsKey("Davi"));
	}
	@Test
	void keySetTest() {
		hashTable3.put("David", 12);
		hashTable3.put("Dillan", 13);
		hashTable3.put(12, "cool");
		keySet = (KeySet) hashTable3.keySet();
		assertEquals(hashTable3.size(), keySet.size());
		assertEquals(hashTable3.containsValue(12), keySet.contains(12));
		assertEquals(true, keySet.contains(12));
		assertEquals(false, keySet.isEmpty());
		objArray = keySet.toArray();
		assertEquals(hashTable3.size(), objArray.length);
		for(int i = 0; i  < hashTable3.size(); i++) {
			assertTrue(hashTable3.containsKey(objArray[i]));
		}
	}
}
