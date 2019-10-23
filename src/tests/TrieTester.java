package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import util.Trie;

class TrieTester {
	static Random rand = new Random();
	static Trie trie = new Trie();
	static Set<String> words = new HashSet<>(1000);
	static char randChar() {
        return (char) (rand.nextInt(26) + 'a');
    }

    static String randWord(int length) {
        StringBuilder sb = new StringBuilder();
        int len = rand.nextInt(length) + 1;
        for (int i = 0; i < len; i++) {
            sb.append(randChar());
        }
        return sb.toString();
    }
    static String randWordLength(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(randChar());
        }
        return sb.toString();
    }
    @Test
    public void insertTest() {
        for (int i = 0; i < 200; i++) {
            String word = randWord(10);
            words.add(word);
            trie.insert(word);
            assertTrue(trie.contains(word));
        }
    }
    @Test
    public void containsTest() {
        for (int i = 0; i < 200; i++) {
            String word = randWord(10);
            words.add(word);
            trie.insert(word);
            boolean setContains = words.contains(word);
            boolean trieContains = trie.contains(word);
            assertEquals(setContains, trieContains);
            words.remove(word);
            trie.delete(word);
            setContains = words.contains(word);
            trieContains = trie.contains(word);
            assertEquals(setContains, trieContains);
        }
        Integer notString = 5;
        assertFalse(trie.contains(notString.toString()));
    }
    @Test
    public void deleteTest() {
        for (int i = 0; i < 200; i++) {
            String word = randWord(10);
            words.remove(word);
            trie.delete(word);
            assertEquals(false, trie.contains(word));
        }
    }
    @Test
    public void closestWordTest() {
    	for (int i = 0; i < 200; i++) {
    		String word = randWordLength(10);
    		int[] lengths = {2 + rand.nextInt(3), 4 + rand.nextInt(3)};
    		int index = 0;
    		words.add(word);
    		trie.insert(word);
    		for(int j = 0; j < lengths.length; j++) {
    			words.add(word.substring(0, lengths[index]));
    			trie.insert(word.substring(0, lengths[index]));
    			index++;
    		}
    		System.out.println(word.substring(0, lengths[0]));
    		System.out.println(trie.closestWordToPrefix(word.substring(0, lengths[0])));
    		assertEquals(word.substring(0, lengths[0]).length(), trie.closestWordToPrefix(word.substring(0,lengths[0])).length());
    	}
    }

}
