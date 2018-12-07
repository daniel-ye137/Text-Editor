package util;

/** Individual nodes to make up Trie, contain a char, children, and whether it completes a word. */

public class TrieNode {
	HashTable<Character, TrieNode> children;
	char data;
	boolean isWord;
	
	/**
	 * Creates a Trie Node that stores a character
	 * @param data the character being stored
	 */
	public TrieNode(char data) {
		this.data = data;
		this.isWord = false;
		children = new HashTable<>();
	}
	
}
