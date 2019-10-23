package util;

/**
 * A mutable collection of strings
 */
public class Trie {
	
	TrieNode root;
	
	/** 
	 * Creates an empty trie. 
	 */
	public Trie() {
		root = new TrieNode((char)0);
	}

	/**
	 * Adds an element to the trie.
	 * @param elem the element being added
	 */
	public void insert(String elem) {
		if(elem == null) {
			throw new NullPointerException();
		}
		int limit = elem.length();
		TrieNode temp = root;
		for( int i = 0; i < limit; i++) 
        { 
            HashTable<Character,TrieNode> child = temp.children;
            char data = elem.charAt(i); 
            if(child.containsKey(data)) 
                temp = child.get(data); 
            else  
            { 
                TrieNode newChild = new TrieNode(data); 
                child.put(data, newChild ); 
                temp = newChild; 
            } 
        } 
        temp.isWord = true; 
     
	}

	/**
	 * Removes a word from the collection if it is there.
	 * @param elem the word to be removed
	 */
	public void delete(String elem) {
		if (!contains(elem)) {
			return;
		}
		int limit = elem.length();
		TrieNode temp = root;
		for( int level = 0; level < limit; level++) 
        { 
            HashTable<Character,TrieNode> child = temp.children;
            char ch = elem.charAt(level); 
            temp = child.get(ch);
                
        } 
		temp.isWord = false;
	}

	/**
	 * Checks if a string is in the collection.
	 * @param elem the string being checked for
	 * @return true if the trie contains the string
	 */
	public boolean contains(String elem) {
		int limit = elem.length();
		TrieNode temp = root;
		for( int level = 0; level < limit; level++) 
        { 
            HashTable<Character,TrieNode> child = temp.children;
            char ch = elem.charAt(level); 
            if(!child.containsKey(ch)) {
            	return false;
            }
            temp = child.get(ch);
                
        } 
		return temp.isWord;
	}
	/**
	 * Returns whether or not the trie contains a path corresponding to a given string.
	 * @param elem the string being checked for in the trie
	 * @return true if the corresponding path of trie nodes exist within the trie
	 */
	private boolean containsPrefix(String elem) {
		int limit = elem.length();
		TrieNode temp = root;
		for( int level = 0; level < limit; level++) 
        { 
            HashTable<Character,TrieNode> child = temp.children;
            char ch = elem.charAt(level); 
            if(!child.containsKey(ch)) {
            	return false;
            }
            temp = child.get(ch);
                
        } 
		return true;
	}
	/**
	 * Return a word contained in the trie of minimal length with {@code prefix}. If
	 * no such word exists, return null.
	 */
	public String closestWordToPrefix(String prefix) {
		if(!containsPrefix(prefix)) {
			return null;
		}
		StringBuilder string = new StringBuilder();
		char[] stringArray = prefix.toCharArray();
		TrieNode temp = root;
		LinkedList<Tracker> queue = new LinkedList<Tracker>();
		int limit = prefix.length();
		for( int level = 0; level < limit; level++) 
        { 
            HashTable<Character,TrieNode> child = temp.children;
            char ch = prefix.charAt(level); 
            string.append(ch);
            temp = child.get(ch);                
        } 
		if(temp.isWord) {
			return string.toString();
		}
		queue.addAtTail(new Tracker(temp, ""));
		while(queue.getSize() != 0) {
			Tracker t = queue.deleteAt(0);
			TrieNode node = t.node;
			String build = t.path;
			if(node.isWord) {
				return prefix + build;
			}
			Character[] keys = new Character[node.children.keySet().toArray().length];
			Object[] source = node.children.keySet().toArray();
			System.arraycopy(source, 0, keys, 0, keys.length);
			for(Character c: keys) {
				queue.addAtTail(new Tracker(node.children.get(c), build + c));
			}
		}
		return null;
	}


}
