package util;
/**
 * A class that iterates through trie nodes and stores the path it takes as a string.
 */
public class Tracker {
	
	public TrieNode node;
	public String path;
	/**
	 * Constructs a tracker with a specified trie node to traverse and a specified path taken to get there.
	 * @param a the trie node being traversed through
	 * @param b a string containing the path of trie nodes that have previously been traversed.
	 */
	public Tracker(TrieNode a, String b) {
		node = a;
		path = b;
	}
	/**
	 * Checks if one tracker is equal to another.
	 * @param t the tracker being checked for equality with this
	 * @return true if the two trackers are equal, false if they are not
	 */
	public boolean equals(Tracker t) {
		return node.equals(t.node);
	}
}
